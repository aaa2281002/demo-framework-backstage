package com.framework.common.util.excel;

import com.framework.common.annotation.ExcelVOAttribute;
import com.framework.common.model.excel.ErrorVo;
import com.framework.common.model.excel.StepPic;
import com.framework.common.util.other.NumeralUtil;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.xssf.usermodel.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.util.*;

import com.framework.common.util.date.DateUtil;

/**
 * @Author 邋遢龘鵺
 * @ClassName com.framework.common.util.excel
 * @Description excel生成解析工具类
 * @Date 2020/1/8 10:46
 * @Version 1.0
 */
public class ExcelUtil<T> {
    Class<T> clazz;

    public ExcelUtil(Class<T> clazz) {
        this.clazz = clazz;
    }

    /**
     * @param col 1 参数字符串
     * @return int
     * @Titel 将EXCEL中A, B, C, D, E列映射成0, 1, 2, 3
     * @Description 将EXCEL中A, B, C, D, E列映射成0, 1, 2, 3
     * @Author 邋遢龘鵺
     * @DateTime 2020/1/8 11:14
     */
    public static int getExcelCol(String col) {
        col = col.toUpperCase();
        // 从-1开始计算,字母重1开始运算。这种总数下来算数正好相同。
        int count = -1;
        char[] cs = col.toCharArray();
        for (int i = 0; i < cs.length; i++) {
            count += (cs[i] - 64) * Math.pow(26, cs.length - 1 - i);
        }
        return count;
    }

    /**
     * @param sheet         1 要设置的sheet
     * @param promptTitle   2 标题
     * @param promptContent 3 内容
     * @param firstRow      4 开始行
     * @param endRow        5 结束行
     * @param firstCol      6 开始列
     * @param endCol        7 结束列
     * @return org.apache.poi.hssf.usermodel.HSSFSheet 设置好的sheet
     * @Titel 设置单元格上提示
     * @Description 设置单元格上提示
     * @Author 邋遢龘鵺
     * @DateTime 2020/1/8 11:14
     */
    public static HSSFSheet setHSSFPrompt(HSSFSheet sheet, String promptTitle, String promptContent, int firstRow, int endRow, int firstCol, int endCol) {
        // 构造constraint对象
        DVConstraint constraint = DVConstraint.createCustomFormulaConstraint("DD1");
        // 四个参数分别是：起始行、终止行、起始列、终止列
        CellRangeAddressList regions = new CellRangeAddressList(firstRow, endRow, firstCol, endCol);
        // 数据有效性对象
        HSSFDataValidation data_validation_view = new HSSFDataValidation(regions, constraint);
        data_validation_view.createPromptBox(promptTitle, promptContent);
        sheet.addValidationData(data_validation_view);
        return sheet;
    }

    /**
     * @param sheet    1 要设置的sheet.
     * @param textList 2 下拉框显示的内容
     * @param firstRow 3 开始行
     * @param endRow   4 结束行
     * @param firstCol 5 开始列
     * @param endCol   6 结束列
     * @return org.apache.poi.hssf.usermodel.HSSFSheet 设置好的sheet.
     * @Titel 设置某些列的值只能输入预制的数据, 显示下拉框.
     * @Description 设置某些列的值只能输入预制的数据, 显示下拉框.
     * @Author 邋遢龘鵺
     * @DateTime 2020/1/8 11:13
     */
    public static HSSFSheet setHSSFValidation(HSSFSheet sheet, String[] textList, int firstRow, int endRow, int firstCol, int endCol) {
        // 加载下拉列表内容
        DVConstraint constraint = DVConstraint.createExplicitListConstraint(textList);
        // 设置数据有效性加载在哪个单元格上,四个参数分别是：起始行、终止行、起始列、终止列
        CellRangeAddressList regions = new CellRangeAddressList(firstRow, endRow, firstCol, endCol);
        // 数据有效性对象
        HSSFDataValidation data_validation_list = new HSSFDataValidation(regions, constraint);
        sheet.addValidationData(data_validation_list);
        return sheet;
    }


    /**
     * @param sheet    1 要设置的sheet
     * @param errorVos 2 要设置的错误消息
     * @return org.apache.poi.hssf.usermodel.HSSFSheet 设置好的sheet.
     * @Titel 设置单元格上错误提示
     * @Description 设置单元格上错误提示
     * @Author 邋遢龘鵺
     * @DateTime 2020/1/8 11:11
     */
    public static HSSFSheet setHSSFRichTextString(HSSFSheet sheet, List<ErrorVo> errorVos) {
        disposeErrorVoList(errorVos);
        //是由于cell styles太多create造成，故一般可以把HSSFCellStyle设置放到循环外面
        //否则会 抛出异常：java.lang.IllegalStateException: The maximum number of cell styles was exceeded. You can define up to 4000 styles in a .xls workbook
        HSSFCellStyle style = sheet.getWorkbook().createCellStyle();
        style.setFillForegroundColor(IndexedColors.RED.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        for (ErrorVo vo : errorVos) {
            //获取单元格对象
            HSSFCell cell = sheet.getRow(vo.getRow()).getCell(vo.getCell());
            if (cell != null) {
                //前四个参数是坐标点,后四个参数是编辑和显示批注时的大小.
                HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0, 0, 0, (short) 3, 3, (short) 10, 6);
                //创建绘图对象
                HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
                HSSFComment comment = patriarch.createCellComment(anchor);
                //输入批注信息
                comment.setString(new HSSFRichTextString(vo.getText()));
                //添加作者,选中B5单元格,看状态栏
                comment.setAuthor("toad");
                //将批注添加到单元格对象中
                cell.setCellComment(comment);
                cell.setCellStyle(style);
            }
        }
        return sheet;
    }

    /**
     * @param errorVos 1 错误信息集合对象
     * @return java.util.List<com.framework.common.model.excel.ErrorVo>
     * @Titel 处理List里面相同的行，列错误列表
     * @Description 处理List里面相同的行，列错误列表
     * @Author 邋遢龘鵺
     * @DateTime 2020/1/8 11:11
     */
    public static List<ErrorVo> disposeErrorVoList(List<ErrorVo> errorVos) {
        for (int i = 0; i < errorVos.size() - 1; i++) {
            for (int j = errorVos.size() - 1; j > i; j--) {
                if (errorVos.get(j).getRow() == (errorVos.get(i)).getRow()
                        && errorVos.get(j).getCell() == (errorVos.get(i)).getCell()) {
                    errorVos.get(i).setText(errorVos.get(i).getText() + "，" + errorVos.get(j).getText());
                    errorVos.remove(j);
                }
            }
        }
        return errorVos;
    }

    /**
     * @param input  1 输入流对象
     * @param suffix 2 文件后缀名
     * @return java.util.List<T>
     * @Titel 解析excel方法
     * @Description 解析excel方法
     * @Author 邋遢龘鵺
     * @DateTime 2020/1/8 11:12
     */
    public List<T> importExcel(InputStream input, String suffix) {
        return importExcel(null, input, suffix);
    }

    /**
     * @param sheetName 1 工作表名称
     * @param input     2  输入流对象
     * @param suffix    3 文件后缀名
     * @return java.util.List<T>
     * @Titel 解析excel方法
     * @Description 解析excel方法
     * @Author 邋遢龘鵺
     * @DateTime 2020/1/8 11:12
     */
    public List<T> importExcel(String sheetName, InputStream input, String suffix) {
        List<T> list = new ArrayList<T>();
        try {
            Workbook book = null;
//            Workbook book = Workbook.getWorkbook(input);

            if (suffix.equalsIgnoreCase("xls")) {
                book = new HSSFWorkbook(input);
            } else if (suffix.equalsIgnoreCase("xlsx")) {
                book = new XSSFWorkbook(input);
            }
            if (book == null) {
                return null;
            }
            Sheet sheet = null;
            if (sheet == null) {
                sheet = book.getSheetAt(0);// 如果传入的s  heet名不存在则默认指向第1个sheet.
            } else {
                sheet = book.getSheet(sheetName);// 如果指定sheet名,则取指定sheet中的内容.
            }
            //解析图片
//            Map<Integer, List<StepPic>> allPictures = null;
//            if (suffix.equalsIgnoreCase("xls")) {
//                allPictures = findAllPictureDate((HSSFSheet) sheet, 0);
//            } else if (suffix.equalsIgnoreCase("xlsx")) {
//                allPictures = findAllPictureDateOfXssf((XSSFSheet) sheet, 0);
//            }

            int rows = sheet.getLastRowNum();// 得到数据的行数
            if (rows > 0) {// 有数据时才处理
                Field[] allFields = clazz.getDeclaredFields();// 得到类的所有field.
                Map<Integer, Field> fieldsMap = new HashMap<Integer, Field>();// 定义一个map用于存放列的序号和field.
                for (Field field : allFields) {
                    // 将有注解的field存放到map中.
                    if (field.isAnnotationPresent(ExcelVOAttribute.class)) {
                        ExcelVOAttribute attr = field.getAnnotation(ExcelVOAttribute.class);
                        int col = getExcelCol(attr.column());// 获得列号
                        field.setAccessible(true);// 设置类的私有字段属性可访问.
                        fieldsMap.put(col, field);
                    }
                }
                for (int i = 1; i <= rows; i++) {// 从第2行开始取数据,默认第一行是表头.
                    Row row = sheet.getRow(i);// 得到一行中的所有单元格对象.
                    if (row != null) {
                        T entity = null;
                        for (int j = 0; j < row.getLastCellNum(); j++) {
                            Cell cell = row.getCell(j);
                            if (cell != null) {
                                String c = getValue(cell);
                                if (c.equals("")) {
                                    continue;
                                }
                                entity = (entity == null ? clazz.newInstance() : entity);// 如果不存在实例则新建.
                                Field field = fieldsMap.get(j);// 从map中得到对应列的field.
                                // 取得类型,并根据对象类型设置值.
                                Class<?> fieldType = field.getType();
                                if ((Integer.TYPE == fieldType)
                                        || (Integer.class == fieldType)) {
                                    field.set(entity, Integer.parseInt(c));
                                } else if (String.class == fieldType) {
                                    field.set(entity, String.valueOf(c));
                                } else if ((Long.TYPE == fieldType)
                                        || (Long.class == fieldType)) {
                                    field.set(entity, Long.valueOf(c));
                                } else if ((Float.TYPE == fieldType)
                                        || (Float.class == fieldType)) {
                                    field.set(entity, Float.valueOf(c));
                                } else if ((Short.TYPE == fieldType)
                                        || (Short.class == fieldType)) {
                                    field.set(entity, Short.valueOf(c));
                                } else if ((Double.TYPE == fieldType)
                                        || (Double.class == fieldType)) {
                                    field.set(entity, Double.valueOf(c));
                                } else if (Character.TYPE == fieldType) {
                                    if ((c != null) && (c.length() > 0)) {
                                        field.set(entity, Character.valueOf(c.charAt(0)));
                                    }
                                }
                            }
                        }
                        if (entity != null) {
                            list.add(entity);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * @param cell 1 excel单列对象
     * @return java.lang.String
     * @Titel 解析excel工作文档中每行每列所属内容类型，并且获取对应值
     * @Description 解析excel工作文档中每行每列所属内容类型，并且获取对应值
     * @Author 邋遢龘鵺
     * @DateTime 2020/1/8 11:10
     */
    private String getValue(Cell cell) {

//        switch (cell.getCellType().getCode()) {
//            case STCellType.INT_B:
//                return cell.getBooleanCellValue() ? "true" : "false";
//            case STCellType.INT_N:
//                return cell.getCellFormula();
//            case STCellType.INT_S: // String is in shared strings
//            case STCellType.INT_INLINE_STR: // String is inline in cell
//            case STCellType.INT_STR:
//                return cell.getStringCellValue();
//            case STCellType.INT_E:
//                return "";
//            default:
//                return cell.getStringCellValue();
//        }
//        if (CellType.NUMERIC == cell.getCellType() && DateUtil.isCellDateFormatted(cell)) {
//            return cell.getDateCellValue();
//        }
        switch (cell.getCellType()) {
            case _NONE:
                return null;
            case NUMERIC:
                cell.setCellType(CellType.STRING);
//                return cell.getNumericCellValue();
            case STRING:
                return cell.getStringCellValue();
            case FORMULA:
                return cell.getCellFormula();
            case BLANK:
                break;
            case BOOLEAN:
                return cell.getBooleanCellValue() ? "true" : "false";
            case ERROR:
                break;
        }
        return null;
    }

    /**
     * @param sheet       1 当前excel页签对象
     * @param firstColumn 2 第几栏
     * @return java.util.Map
     * @Titel 得到某个sheet(2003版本) 的图片
     * @Description 得到某个sheet(2003版本) 的图片
     * @Author 邋遢龘鵺
     * @DateTime 2020/1/8 11:09
     */
    public static Map<Integer, List<StepPic>> findAllPictureDate(HSSFSheet sheet, int firstColumn) {

        Map<Integer, List<StepPic>> dataMap = Maps.newHashMap();

        // 处理sheet中的图形
        HSSFPatriarch hssfPatriarch = sheet.getDrawingPatriarch();
        // 获取所有的形状图

        if (hssfPatriarch != null) {
            List<HSSFShape> shapes = hssfPatriarch.getChildren();

            if (shapes != null && shapes.size() > 0) {

                dataMap = new HashMap<Integer, List<StepPic>>();

                List<StepPic> pictureDataList = null;

                for (HSSFShape sp : shapes) {
                    if (sp instanceof HSSFPicture) {
                        // 转换
                        HSSFPicture picture = (HSSFPicture) sp;
                        // 获取图片数据
                        HSSFPictureData pictureData = picture.getPictureData();
                        // 图形定位
                        if (picture.getAnchor() instanceof HSSFClientAnchor) {

                            HSSFClientAnchor anchor = (HSSFClientAnchor) picture.getAnchor();
                            // 获取图片所在行作为key值,插入图片时，默认图片只占一行的单个格子，不能超出格子边界
                            int row1 = anchor.getRow1();
                            int rowNum = row1;

                            if (dataMap.get(rowNum) != null) {
                                pictureDataList = dataMap.get(rowNum);
                            } else {
                                pictureDataList = new ArrayList<StepPic>();
                            }
                            if (firstColumn <= anchor.getRow1() /*&& anchor.getCol2() <= endColumn*/) {
                                StepPic stepPic = new StepPic();
                                stepPic.setSuffix(pictureData.suggestFileExtension());
                                stepPic.setData(pictureData.getData());
                                pictureDataList.add(stepPic);
                                dataMap.put(rowNum, pictureDataList);
//                                int row2 = anchor.getRow2();
//                                short col1 = anchor.getCol1();
//                                short col2 = anchor.getCol2();
//                                int dx1 = anchor.getDx1();
//                                int dx2 = anchor.getDx2();
//                                int dy1 = anchor.getDy1();
//                                int dy2 = anchor.getDy2();
//                                System.out.println(pictureData.suggestFileExtension());
//                                System.out.println(
//                                        "row1: " + row1 + " , row2: " + row2 + " , col1: " + col1 + " , col2: " + col2);
//                                System.out.println("dx1: " + dx1 + " , dx2: " + dx2 + " , dy1: " + dy1 + " , dy2: " + dy2);
                            }
                        }
                    }
                }
            }

            System.out.println("********图片数量明细 START********");
            int t = 0;
            if (dataMap != null) {
                t = dataMap.keySet().size();
            }
            if (t > 0) {
                for (Integer key : dataMap.keySet()) {
                    System.out.println("第 " + key + " 行， 有图片： " + dataMap.get(key).size() + " 张");
                }
            } else {
                System.out.println("Excel表中没有图片!");
            }
            System.out.println("********图片数量明细 END ********");
        }

        return dataMap;
    }

    /**
     * @param sheet       1 当前excel页签对象
     * @param firstColumn 2 第几栏
     * @return java.util.Map
     * @Titel 得到某个sheet(2007以上版本) 的图片
     * @Description 得到某个sheet(2007以上版本) 的图片
     * @Author 邋遢龘鵺
     * @DateTime 2020/1/8 11:07
     */
    public static Map<Integer, List<StepPic>> findAllPictureDateOfXssf(XSSFSheet sheet, int firstColumn) {
        Map<Integer, List<StepPic>> dataMap = null;

        // 处理sheet中的图形
        XSSFDrawing drawingPatriarch = sheet.getDrawingPatriarch();
        // 获取所有的形状图
        List<XSSFShape> shapes = drawingPatriarch.getShapes();

        if (shapes.size() > 0) {

            dataMap = new HashMap<Integer, List<StepPic>>();

            List<StepPic> pictureDataList = null;

            for (XSSFShape sp : shapes) {
                if (sp instanceof XSSFPicture) {
                    // 转换
                    XSSFPicture picture = (XSSFPicture) sp;
                    // 获取图片数据
                    XSSFPictureData pictureData = picture.getPictureData();
                    // 图形定位
                    if (picture.getAnchor() instanceof XSSFClientAnchor) {

                        XSSFClientAnchor anchor = (XSSFClientAnchor) picture.getAnchor();
                        // 获取图片所在行作为key值,插入图片时，默认图片只占一行的单个格子，不能超出格子边界
                        int row1 = anchor.getRow1();
                        int rowNum = row1;

                        if (dataMap.get(rowNum) != null) {
                            pictureDataList = dataMap.get(rowNum);
                        } else {
                            pictureDataList = new ArrayList<StepPic>();
                        }
                        if (firstColumn <= anchor.getRow1()) {
                            StepPic stepPic = new StepPic();
                            stepPic.setSuffix(pictureData.suggestFileExtension());
                            stepPic.setData(pictureData.getData());
                            pictureDataList.add(stepPic);
                            dataMap.put(rowNum, pictureDataList);
                            // 测试部分
                        }

                    }
                }
            }
        }

        System.out.println("********图片数量明细 START********");
        int t = 0;
        if (dataMap != null) {
            t = dataMap.keySet().size();
        }
        if (t > 0) {
            for (Integer key : dataMap.keySet()) {
                System.out.println("第 " + key + " 行， 有图片： " + dataMap.get(key).size() + " 张");
            }
        } else {
            System.out.println("Excel表中没有图片!");
        }
        System.out.println("********图片数量明细 END ********");

        return dataMap;
    }


    /**
     * @param response  1 输出对象
     * @param list      2 数据集合
     * @param sheetName 3 文件名称
     * @Titel 对list数据源将其里面的数据导入到excel表单
     * @Description 对list数据源将其里面的数据导入到excel表单
     * @Author 邋遢龘鵺
     * @DateTime 2019/10/23 14:44
     */
    public void exportExcel(HttpServletResponse response, List<T> list, String sheetName) {
        exportExcel(response, list, sheetName, null);
    }

    /**
     * @param response  1 输出对象
     * @param list      2 数据集合
     * @param sheetName 3 文件名称
     * @param errorVos  4 excel设置的错误消息集合
     * @Titel 对list数据源将其里面的数据导入到excel表单
     * @Description 对list数据源将其里面的数据导入到excel表单
     * @Author 邋遢龘鵺
     * @DateTime 2019/10/23 14:44
     */
    public void exportExcel(HttpServletResponse response, List<T> list, String sheetName, List<ErrorVo> errorVos) {
        int sheetSize = 65536;
        Field[] allFields = clazz.getDeclaredFields();// 得到所有定义字段
        List<Field> fields = new ArrayList<Field>();
        // 得到所有field并存放到一个list中.
        for (Field field : allFields) {
            if (field.isAnnotationPresent(ExcelVOAttribute.class)) {
                fields.add(field);
            }
        }
        HSSFWorkbook workbook = new HSSFWorkbook();// 产生工作薄对象
        double sheetNo = Math.ceil(list.size() / sheetSize);// 取出一共有多少个sheet.
        for (int index = 0; index <= sheetNo; index++) {
            HSSFSheet sheet = workbook.createSheet();// 产生工作表对象
            workbook.setSheetName(index, sheetName + index);// 设置工作表的名称.
            HSSFRow row;
            HSSFCell cell;// 产生单元格
            row = sheet.createRow(0);// 产生一行
            // 写入各个字段的列头名称
            for (int i = 0; i < fields.size(); i++) {
                Field field = fields.get(i);
                ExcelVOAttribute attr = field.getAnnotation(ExcelVOAttribute.class);
                sheet.setColumnWidth(i, attr.width());//设置宽度
                //设置列样式为文本格式
                CellStyle css = workbook.createCellStyle();
                DataFormat format = workbook.createDataFormat();
                css.setDataFormat(format.getFormat("@"));
                css.setAlignment(HorizontalAlignment.LEFT);
                int col = getExcelCol(attr.column());// 获得列号
                sheet.setDefaultColumnStyle(col, css);
                cell = row.createCell(col);// 创建列
                cell.setCellType(CellType.STRING);// 设置列中写入内容为String类型

                HSSFFont font = workbook.createFont();
                font.setFontHeightInPoints((short) 12); // 字体高度
                font.setFontName("宋体"); // 字体
                font.setBold(Boolean.TRUE); // 字体加粗
                HSSFRichTextString ts = new HSSFRichTextString(attr.name());
                ts.applyFont(font);
                cell.setCellValue(ts);

                // 如果设置了提示信息则鼠标放上去提示.
                if (!attr.prompt().trim().equals("")) {
                    setHSSFPrompt(sheet, "", attr.prompt(), 1, fields.size() - 2, col, col);// 这里默认设了列提示.
                }
                // 如果设置了combo属性则本列只能选择不能输入
                if (attr.combo().length > 0) {
                    setHSSFValidation(sheet, attr.combo(), 1, fields.size() - 2, col, col);// 这里默认设了列只能选择不能输入.
                }
            }
            int startNo = index * sheetSize;
            int endNo = Math.min(startNo + sheetSize, list.size());
            // 写入各条记录,每条记录对应excel表中的一行
            for (int i = startNo; i < endNo; i++) {
                row = sheet.createRow(i + 1 - startNo);
                T vo = (T) list.get(i); // 得到导出对象.
                for (int j = 0; j < fields.size(); j++) {
                    Field field = fields.get(j);// 获得field.
                    field.setAccessible(true);// 设置实体类私有属性可访问
                    ExcelVOAttribute attr = field.getAnnotation(ExcelVOAttribute.class);
                    try {
                        // 根据ExcelVOAttribute中设置情况决定是否导出,有些情况需要保持为空,希望用户填写这一列.
                        if (attr.isExport()) {
                            cell = row.createCell(getExcelCol(attr.column()));// 创建cell
                            cell.setCellType(CellType.STRING);
                            Object obj = field.get(vo);
                            if (Date.class.getName().equals(field.getType().getTypeName()) && obj != null) {
                                String dateStr = "";
                                if (StringUtils.isNotEmpty(attr.pattern())) {
                                    Date date = DateUtil.getStringToDate(obj.toString(), null);
                                    dateStr = DateFormatUtils.format(date, attr.pattern());
                                } else {
                                    dateStr = obj.toString().substring(NumeralUtil.POSITIVE_ZERO, obj.toString().lastIndexOf(".0"));
                                }
                                cell.setCellValue(dateStr);// 如果数据存在就填入,不存在填入空格.
                                continue;
                            }
                            cell.setCellValue(obj == null ? "" : String.valueOf(obj));// 如果数据存在就填入,不存在填入空格.
                        }
                    } catch (IllegalArgumentException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
            if (errorVos != null && errorVos.size() > 0) {
                setHSSFRichTextString(sheet, errorVos);
            }
        }
        try {
            OutputStream output = response.getOutputStream();// 取得输出流
            response.reset();// 清空输出流
            response.setHeader("Content-disposition", "attachment; filename=" + URLEncoder.encode(sheetName, "UTF-8") + ".xls");// 设定输出文件头
            response.setContentType("application/vnd.ms-excel");
//            response.setContentType("application/x-download");
            output.flush();
            workbook.write(output);
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
