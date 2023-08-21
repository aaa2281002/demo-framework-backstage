package com.framework.service.other.impl;

import com.framework.common.enums.FileTypeEnum;
import com.framework.common.response.ResponseResult;
import com.framework.common.util.date.DateStyleUtil;
import com.framework.common.util.io.ReadIOUtil;
import com.framework.common.util.other.NumeralUtil;
import com.framework.mapper.other.FileInfoMapper;
import com.framework.model.other.FileInfo;
import com.framework.service.base.BaseService;
import com.framework.service.other.FileInfoService;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author 邋遢龘鵺
 * @version 1.0
 * @className com.framework.service.other.impl
 * @description 文件信息业务接口实现类
 * @datetime 2019/10/11
 */
@Service("fileInfoServiceImpl")
public class FileInfoServiceImpl extends BaseService implements FileInfoService {
    @Value("${spring.file.upload-dir}")
    private String uploadDir;
    @Autowired
    private FileInfoMapper fileInfoMapper;

    /**
     * @param row 1 文件实体类对象
     * @return int 大于等于1成功， 0失败
     * @titel 公共根据条件逻辑删除数据
     * @description 公共根据条件逻辑删除数据
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 11:04
     */
    @Override
    public int delete(FileInfo row) {
        return fileInfoMapper.delete(row);
    }

    /**
     * @param row 1 文件实体类对象
     * @return int 大于等于1成功， 0失败
     * @titel 公共根据条件批量逻辑删除数据
     * @description 公共根据条件批量逻辑删除数据
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 11:04
     */
    @Override
    public int deleteList(FileInfo row) {
        return fileInfoMapper.deleteList(row);
    }

    /**
     * @param row 1 文件实体类对象
     * @return long 大于0成功，小于等于0失败
     * @titel 公共非空字段验证添加
     * @description 公共非空字段验证添加
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 9:31
     */
    @Override
    public int insertSelective(FileInfo row) {
        return fileInfoMapper.insertSelective(row);
    }

    /**
     * @param list 1 文件批量添加集合
     * @return int 返回成功数量
     * @titel 公共批量添加
     * @description 公共批量添加
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 14:12
     */
    @Override
    public int insertList(List<FileInfo> list) {
        return fileInfoMapper.insertList(list);
    }

    /**
     * @param row 1 文件实体类对象
     * @return int 0失败，1成功
     * @titel 公共根据编号非空字段验证修改
     * @description 公共根据编号非空字段验证修改
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 9:35
     */
    @Override
    public int updateByPrimaryKeySelective(FileInfo row) {
        return fileInfoMapper.updateByPrimaryKeySelective(row);
    }

    /**
     * @param list 1 文件批量修改集合
     * @return int 返回成功数量
     * @titel 公共批量根据非空验证编号修改
     * @description 公共批量根据非空验证编号修改
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 13:59
     */
    @Override
    public int updateList(List<FileInfo> list) {
        return fileInfoMapper.updateList(list);
    }

    /**
     * @param id 1 编号
     * @return com.framework.model.other.FileInfo
     * @titel 公共根据编号查询数据
     * @description 公共根据编号查询数据
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 9:33
     */
    @Override
    public FileInfo selectByPrimaryKey(Long id) {
        return fileInfoMapper.selectByPrimaryKey(id);
    }

    /**
     * @param row 1 文件实体类对象
     * @return java.util.List<com.framework.model.other.FileInfo>
     * @titel 公共根据条件分页查询
     * @description 公共根据条件分页查询
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 9:41
     */
    @Override
    public List<FileInfo> findPageList(FileInfo row) {
        return fileInfoMapper.findPageList(row);
    }

    /**
     * @param row 1 文件实体类对象
     * @return int 返回分页总数
     * @titel 公共根据条件分页查询总数
     * @description 公共根据条件分页查询总数
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 9:41
     */
    @Override
    public int findPageListCount(FileInfo row) {
        return fileInfoMapper.findPageListCount(row);
    }

    /**
     * @param row 1 文件实体类对象
     * @return java.util.List<com.framework.model.other.FileInfo>
     * @titel 公共根据条件查询集合
     * @description 公共根据条件查询集合
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 11:01
     */
    @Override
    public List<FileInfo> findByList(FileInfo row) {
        return fileInfoMapper.findByList(row);
    }


    /**
     * @param row 1 文件实体类对象
     * @return com.framework.common.response.ResponseResult
     * @titel 本类后台管理添加方法
     * @description 本类后台管理添加方法
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 11:13
     */
    @Override
//    @Transactional
//    @TargetDataSource(dataSource = DataSourceUtil.WRITE_DATA_SOURCE)
    public ResponseResult save(FileInfo row) {
        ResponseResult r = getResponseResult();
        if (row == null) {
            return r.fail();
        }
        Long userId = getUserId();
        Date date = new Date();
        row.setOperaterTime(date);
        row.setCreateId(userId);
        row.setOperaterId(userId);
        row.setCreateTime(date);
        row.setOperaterStatus(NumeralUtil.POSITIVE_ONE);
        fileInfoMapper.insertSelective(row);
        return r.success();
    }

    /**
     * @param row 1 文件实体类对象
     * @return com.framework.common.response.ResponseResult
     * @titel 本类后台管理修改方法
     * @description 本类后台管理修改方法
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 11:13
     */
    @Override
    public ResponseResult edit(FileInfo row) {
        ResponseResult r = getResponseResult();
        if (row == null || row.getId() == null || row.getId().longValue() < NumeralUtil.MULTIPLEXING_LONG_POSITIVE_ONE) {
            return r.fail();
        }
        Date date = new Date();
        Long userId = getUserId();
        row.setOperaterStatus(NumeralUtil.POSITIVE_TWO);
        row.setOperaterId(userId);
        row.setOperaterTime(date);
        fileInfoMapper.updateByPrimaryKeySelective(row);
        return r.success();
    }


    /**
     * @param idList 1 编号集合
     * @return com.framework.common.response.ResponseResult
     * @titel 本类后台管理批量逻辑删除
     * @description 本类后台管理批量逻辑删除
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 11:14
     */
    @Override
    public ResponseResult batchDeleteList(List<Long> idList) {
        ResponseResult r = getResponseResult();
        if (idList == null || idList.size() < NumeralUtil.POSITIVE_ONE) {
            return r.fail();
        }
        FileInfo row = new FileInfo();
        row.setOperaterStatus(NumeralUtil.NEGATIVE_ONE);
        row.setOperaterTime(new Date());
        Long userId = getUserId();
        row.setOperaterId(userId);
        fileInfoMapper.deleteList(row);
        return r.success();
    }

    /**
     * @param id 1 编号
     * @return com.framework.common.response.ResponseResult
     * @titel 本类后台管理根据编号逻辑删除
     * @description 本类后台管理根据编号逻辑删除
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 11:14
     */
    @Override
    public ResponseResult delId(Long id) {
        ResponseResult r = getResponseResult();
        FileInfo row = new FileInfo();
        row.setOperaterStatus(NumeralUtil.NEGATIVE_ONE);
        row.setOperaterTime(new Date());
        Long userId = getUserId();
        row.setOperaterId(userId);
        row.setId(id);
        fileInfoMapper.delete(row);
        return r.success();
    }


    /**
     * @param id 1 编号
     * @return com.framework.model.other.FileInfo
     * @titel 本类根据ID查询数据信息
     * @description 本类根据ID查询数据信息
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 11:16
     */
    @Override
    public FileInfo getByIdParam(Long id) {
        FileInfo p = fileInfoMapper.selectByPrimaryKey(id);
        return p;

    }

    /**
     * @param param 1 文件实体类对象
     * @return com.framework.common.response.ResponseResult
     * @titel 本类后台管理根据条件分页查询文件列表
     * @description 本类后台管理根据条件分页查询文件列表
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 11:20
     */
    @Override
    public ResponseResult findParamPageList(FileInfo param) {
        ResponseResult r = getResponseResult();
        param.setGtaeOperaterStatus(NumeralUtil.POSITIVE_ONE);
        int count = fileInfoMapper.findPageListCount(param);
        List<FileInfo> list = fileInfoMapper.findPageList(param);
        return r.success().setData(list).setCount(count);
    }

    /**
     * @param param 1 文件实体类对象
     * @return com.framework.common.response.ResponseResult
     * @titel 根据条件查询附件
     * @description 根据条件查询附件
     * @author 邋遢龘鵺
     * @datetime 2019/12/27 13:32
     */
    @Override
    public ResponseResult findParamList(FileInfo param) {
        ResponseResult r = getResponseResult();
        param.setGtaeOperaterStatus(NumeralUtil.POSITIVE_ONE);
        List<FileInfo> list = fileInfoMapper.findByList(param);
        return r.success().setData(list).setCount(Integer.MAX_VALUE);
    }

    /**
     * @param list       1 上传对象集合
     * @param businessId 2 所属编号
     * @param code       3 所属代码
     * @return java.util.List<com.framework.model.other.FileInfo>
     * @titel 上传解析方法
     * @description 上传解析方法
     * @author 邋遢龘鵺
     * @datetime 2020/1/8 16:18
     */
    private List<FileInfo> write(List<MultipartFile> list, Long businessId, String code) throws Exception {
        List<FileInfo> fList = new ArrayList<FileInfo>(list.size());
        Date date = new Date();
        Long userId = getUserId();
        for (MultipartFile mf : list) {
            //文件流转成字符串
//            String ioStr = ReadIOUtil.readIoToString(mf.getInputStream());
//            System.out.println(ioStr);

            //获取文件名称
            String fileName = mf.getOriginalFilename();
            //获取文件后缀名
            String ext = null;
            //判断文件名称是否存在点
            if (fileName.contains(".")) {
                //截取后缀名
                ext = fileName.substring(fileName.lastIndexOf(".") + NumeralUtil.POSITIVE_ONE);
            } else {
                ext = "";
            }
            //输出文件大小
            System.out.println(mf.getSize());

            File targetFile = new File(uploadDir);
            if (!targetFile.exists()) {
                targetFile.mkdir();
            }
            //组装文件名称
            String nFileName = DateFormatUtils.format(new Date(), DateStyleUtil.FORMAT_YYYYMMDDHHMMSSSSS) + "." + ext;
            String path = uploadDir + "\\" + nFileName;
            mf.transferTo(new File(path));
            FileInfo fi = new FileInfo();
            fi.setCreateId(userId);
            fi.setOperaterId(userId);
            fi.setCreateTime(date);
            fi.setOperaterTime(date);
            fi.setOperaterStatus(NumeralUtil.POSITIVE_ONE);
            fi.setFilePath(path);
            fi.setFileName(fileName);
            fi.setFileType(FileTypeEnum.getFileTypeEnum(ext));
            fi.setBusinessId(businessId);
            fi.setCode(code);
            fList.add(fi);
        }
        return fList;
    }

    /**
     * @param param 1 附件实体类对象
     * @return com.framework.common.response.ResponseResult
     * @titel 上传demo方法
     * @description 上传demo方法
     * @author 邋遢龘鵺
     * @datetime 2020/1/8 16:17
     */
    @Override
    public ResponseResult upload(FileInfo param) throws Exception {
        ResponseResult r = getResponseResult();
        if (param == null && param.getFile() == null || param.getFile().size() == NumeralUtil.POSITIVE_ZERO) {
            return r.fail().setMsg("上传文件不存在!");
        }
        List<FileInfo> fList = write(param.getFile(), param.getBusinessId(), param.getCode());
        fileInfoMapper.insertList(fList);
        return r.success();
    }

    /**
     * @param param    1 附件实体类对象
     * @param response 2 请求响应对象
     * @titel 下载demo方法
     * @description 下载demo方法
     * @author 邋遢龘鵺
     * @datetime 2020/1/8
     */
    @Override
    public void download(FileInfo param, HttpServletResponse response) {
        FileInfo p = fileInfoMapper.selectByPrimaryKey(param.getId());
//            ReadIOUtil.readIoStringToFile(response, "Xi4qycy7p7T6wuvOqr/VOm51bGwuKiQ=", "", "edit过滤规则.txt");
        ReadIOUtil.readIoStringToFile(response, "", p.getFilePath(), p.getFileName());
    }
}
