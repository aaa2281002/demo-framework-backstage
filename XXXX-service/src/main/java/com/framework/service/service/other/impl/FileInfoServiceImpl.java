package com.framework.service.service.other.impl;

import com.framework.common.enums.FileTypeEnum;
import com.framework.common.response.ResponseResult;
import com.framework.common.util.date.DateStyleUtil;
import com.framework.common.util.io.ReadIOUtil;
import com.framework.common.util.other.NumeralUtil;
import com.framework.dao.mapper.other.FileInfoMapper;
import com.framework.model.entity.other.FileInfo;
import com.framework.service.base.BaseService;
import com.framework.service.service.other.FileInfoService;
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
 * @Author 邋遢龘鵺
 * @ClassName com.framework.service.service.other.impl
 * @Description 文件信息业务接口实现类
 * @DateTime 2019/10/11
 * @Version 1.0
 */
@Service
public class FileInfoServiceImpl extends BaseService implements FileInfoService {
    @Value("${spring.file.upload-dir}")
    private String uploadDir;
    @Autowired
    private FileInfoMapper fileInfoMapper;

    /**
     * @param record 1 文件实体类对象
     * @return int 大于等于1成功， 0失败
     * @Titel 公共根据条件逻辑删除数据
     * @Description 公共根据条件逻辑删除数据
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 11:04
     */
    @Override
    public int delete(FileInfo record) {
        return fileInfoMapper.delete(record);
    }

    /**
     * @param record 1 文件实体类对象
     * @return int 大于等于1成功， 0失败
     * @Titel 公共根据条件批量逻辑删除数据
     * @Description 公共根据条件批量逻辑删除数据
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 11:04
     */
    @Override
    public int deleteList(FileInfo record) {
        return fileInfoMapper.deleteList(record);
    }

    /**
     * @param record 1 文件实体类对象
     * @return long 大于0成功，小于等于0失败
     * @Titel 公共添加
     * @Description 公共添加
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 9:31
     */
    @Override
    public long insert(FileInfo record) {
        return fileInfoMapper.insert(record);
    }

    /**
     * @param record 1 文件实体类对象
     * @return long 大于0成功，小于等于0失败
     * @Titel 公共非空字段验证添加
     * @Description 公共非空字段验证添加
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 9:31
     */
    @Override
    public long insertSelective(FileInfo record) {
        return fileInfoMapper.insertSelective(record);
    }

    /**
     * @param list 1 文件批量添加集合
     * @return int 返回成功数量
     * @Titel 公共批量添加
     * @Description 公共批量添加
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 14:12
     */
    @Override
    public int insertList(List<FileInfo> list) {
        return fileInfoMapper.insertList(list);
    }

    /**
     * @param record 1 文件实体类对象
     * @return int 0失败，1成功
     * @Titel 公共根据编号修改
     * @Description 公共根据编号修改
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 9:35
     */
    @Override
    public int updateByPrimaryKey(FileInfo record) {
        return fileInfoMapper.updateByPrimaryKey(record);
    }

    /**
     * @param record 1 文件实体类对象
     * @return int 0失败，1成功
     * @Titel 公共根据编号非空字段验证修改
     * @Description 公共根据编号非空字段验证修改
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 9:35
     */
    @Override
    public int updateByPrimaryKeySelective(FileInfo record) {
        return fileInfoMapper.updateByPrimaryKeySelective(record);
    }

    /**
     * @param list 1 文件批量修改集合
     * @return int 返回成功数量
     * @Titel 公共批量根据非空验证编号修改
     * @Description 公共批量根据非空验证编号修改
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 13:59
     */
    @Override
    public int updateList(List<FileInfo> list) {
        return fileInfoMapper.updateList(list);
    }

    /**
     * @param id 1 编号
     * @return com.framework.model.entity.other.FileInfo
     * @Titel 公共根据编号查询数据
     * @Description 公共根据编号查询数据
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 9:33
     */
    @Override
    public FileInfo selectByPrimaryKey(Long id) {
        return fileInfoMapper.selectByPrimaryKey(id);
    }

    /**
     * @param record 1 文件实体类对象
     * @return java.util.List<com.framework.model.entity.other.FileInfo>
     * @Titel 公共根据条件分页查询
     * @Description 公共根据条件分页查询
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 9:41
     */
    @Override
    public List<FileInfo> findPageList(FileInfo record) {
        return fileInfoMapper.findPageList(record);
    }

    /**
     * @param record 1 文件实体类对象
     * @return int 返回分页总数
     * @Titel 公共根据条件分页查询总数
     * @Description 公共根据条件分页查询总数
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 9:41
     */
    @Override
    public int findPageListCount(FileInfo record) {
        return fileInfoMapper.findPageListCount(record);
    }

    /**
     * @param record 1 文件实体类对象
     * @return java.util.List<com.framework.model.entity.other.FileInfo>
     * @Titel 公共根据条件查询集合
     * @Description 公共根据条件查询集合
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 11:01
     */
    @Override
    public List<FileInfo> findByList(FileInfo record) {
        return fileInfoMapper.findByList(record);
    }


    /**
     * @param record 1 文件实体类对象
     * @return com.framework.common.response.ResponseResult
     * @Titel 本类后台管理添加方法
     * @Description 本类后台管理添加方法
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 11:13
     */
    @Override
//    @Transactional
//    @TargetDataSource(dataSource = DataSourceUtil.WRITE_DATA_SOURCE)
    public ResponseResult save(FileInfo record) {
        ResponseResult r = getResponseResult();
        if (record == null) {
            return r.ResponseResultFail();
        }
        Long userId = getUserId();
        Date date = new Date();
        record.setOperaterTime(date);
        record.setCreateId(userId);
        record.setOperaterId(userId);
        record.setCreateTime(date);
        record.setOperaterStatus(NumeralUtil.POSITIVE_ONE);
        try {
            long is = this.insert(record);
            if (is > NumeralUtil.MULTIPLEXING_LONG_POSITIVE_ZERO) {
                return r.ResponseResultSuccess();
            }
            return r.ResponseResultAddFail();
        } catch (Exception e) {
            e.printStackTrace();
            return r.ResponseResultError();
        }
    }

    /**
     * @param record 1 文件实体类对象
     * @return com.framework.common.response.ResponseResult
     * @Titel 本类后台管理修改方法
     * @Description 本类后台管理修改方法
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 11:13
     */
    @Override
    public ResponseResult edit(FileInfo record) {
        ResponseResult r = getResponseResult();
        if (record == null || record.getId() == null || record.getId().longValue() < NumeralUtil.MULTIPLEXING_LONG_POSITIVE_ONE) {
            return r.ResponseResultFail();
        }
        Date date = new Date();
        Long userId = getUserId();
        record.setOperaterStatus(NumeralUtil.POSITIVE_TWO);
        record.setOperaterId(userId);
        record.setOperaterTime(date);
        try {
            int is = this.updateByPrimaryKeySelective(record);
            if (is > NumeralUtil.POSITIVE_ZERO) {
                return r.ResponseResultSuccess();
            }
            return r.ResponseResultUpdateFail();
        } catch (Exception e) {
            e.printStackTrace();
            return r.ResponseResultError();
        }
    }


    /**
     * @param idList 1 编号集合
     * @return com.framework.common.response.ResponseResult
     * @Titel 本类后台管理批量逻辑删除
     * @Description 本类后台管理批量逻辑删除
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 11:14
     */
    @Override
    public ResponseResult batchDeleteList(List<Long> idList) {
        ResponseResult r = getResponseResult();
        if (idList == null || idList.size() < NumeralUtil.POSITIVE_ONE) {
            return r.ResponseResultFail();
        }
        FileInfo record = new FileInfo();
        record.setOperaterStatus(NumeralUtil.NEGATIVE_ONE);
        record.setOperaterTime(new Date());
        Long userId = getUserId();
        record.setOperaterId(userId);
        try {
            int is = this.deleteList(record);
            if (is > NumeralUtil.POSITIVE_ZERO) {
                return r.ResponseResultSuccess();
            }
            return r.ResponseResultDeleteFail();
        } catch (Exception e) {
            e.printStackTrace();
            return r.ResponseResultError();
        }
    }

    /**
     * @param id 1 编号
     * @return com.framework.common.response.ResponseResult
     * @Titel 本类后台管理根据编号逻辑删除
     * @Description 本类后台管理根据编号逻辑删除
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 11:14
     */
    @Override
    public ResponseResult delId(Long id) {
        ResponseResult r = getResponseResult();
        if (id == null || id.longValue() < NumeralUtil.MULTIPLEXING_LONG_POSITIVE_ONE) {
            return r.ResponseResultFail();
        }
        FileInfo record = new FileInfo();
        record.setOperaterStatus(NumeralUtil.NEGATIVE_ONE);
        record.setOperaterTime(new Date());
        Long userId = getUserId();
        record.setOperaterId(userId);
        record.setId(id);
        try {
            int is = this.delete(record);
            if (is > NumeralUtil.POSITIVE_ZERO) {
                return r.ResponseResultSuccess();
            }
            return r.ResponseResultDeleteFail();
        } catch (Exception e) {
            e.printStackTrace();
            return r.ResponseResultError();
        }
    }


    /**
     * @param id 1 编号
     * @return com.framework.model.entity.other.FileInfo
     * @Titel 本类根据ID查询数据信息
     * @Description 本类根据ID查询数据信息
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 11:16
     */
    @Override
    public FileInfo getByIdParam(Long id) {
        if (id == null || id.longValue() < NumeralUtil.MULTIPLEXING_LONG_POSITIVE_ONE) {
            return null;
        }
        FileInfo sm = this.selectByPrimaryKey(id);
        return sm != null ? sm : null;

    }

    /**
     * @param param 1 文件实体类对象
     * @return com.framework.common.response.ResponseResult
     * @Titel 本类后台管理根据条件分页查询文件列表
     * @Description 本类后台管理根据条件分页查询文件列表
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 11:20
     */
    @Override
    public ResponseResult findParamPageList(FileInfo param) {
        ResponseResult r = getResponseResult();
        try {
            param.setGtaeOperaterStatus(NumeralUtil.POSITIVE_ONE);
            int count = this.findPageListCount(param);
            List<FileInfo> list = this.findPageList(param);
            return r.ResponseResultSuccess().setData(list).setCount(count);
        } catch (Exception e) {
            e.printStackTrace();
            return r.ResponseResultFail();
        }
    }

    /**
     * @param param 1 文件实体类对象
     * @return com.framework.common.response.ResponseResult
     * @Titel 根据条件查询附件
     * @Description 根据条件查询附件
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/27 13:32
     */
    @Override
    public ResponseResult findParamList(FileInfo param) {
        ResponseResult r = getResponseResult();
        try {
            param.setGtaeOperaterStatus(NumeralUtil.POSITIVE_ONE);
            List<FileInfo> list = this.findByList(param);
            return r.ResponseResultSuccess().setData(list).setCount(Integer.MAX_VALUE);
        } catch (Exception e) {
            e.printStackTrace();
            return r.ResponseResultFail();
        }
    }

    /**
     * @param list       1 上传对象集合
     * @param businessId 2 所属编号
     * @param code       3 所属代码
     * @return java.util.List<com.framework.model.entity.other.FileInfo>
     * @Titel 上传解析方法
     * @Description 上传解析方法
     * @Author 邋遢龘鵺
     * @DateTime 2020/1/8 16:18
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
     * @Titel 上传demo方法
     * @Description 上传demo方法
     * @Author 邋遢龘鵺
     * @DateTime 2020/1/8 16:17
     */
    @Override
    public ResponseResult upload(FileInfo param) {
        ResponseResult r = getResponseResult();
        if (param == null && param.getFile() == null || param.getFile().size() == NumeralUtil.POSITIVE_ZERO) {
            return r.ResponseResultFail().setMsg("上传文件不存在!");
        }
        try {
            List<FileInfo> fList = write(param.getFile(), param.getBusinessId(), param.getCode());
            fileInfoMapper.insertList(fList);
            return r.ResponseResultSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            return r.ResponseResultFail();
        }
    }

    /**
     * @param param    1 附件实体类对象
     * @param response 2 请求响应对象
     * @Titel 下载demo方法
     * @Description 下载demo方法
     * @Author 邋遢龘鵺
     * @DateTime 2020/1/8
     */
    @Override
    public void download(FileInfo param, HttpServletResponse response) {
        try {
            FileInfo p = this.selectByPrimaryKey(param.getId());
//            ReadIOUtil.readIoStringToFile(response, "Xi4qycy7p7T6wuvOqr/VOm51bGwuKiQ=", "", "edit过滤规则.txt");
            ReadIOUtil.readIoStringToFile(response, "", p.getFilePath(), p.getFileName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
