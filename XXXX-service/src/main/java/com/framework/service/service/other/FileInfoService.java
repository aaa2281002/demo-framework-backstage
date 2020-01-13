package com.framework.service.service.other;

import com.framework.common.response.ResponseResult;
import com.framework.model.entity.other.FileInfo;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Author 邋遢龘鵺
 * @ClassName com.framework.service.service.other
 * @Description 文件信息业务接口类
 * @DateTime 2019/10/11
 * @Version 1.0
 */
public interface FileInfoService {
    /**
     * @param record 1 文件实体类对象
     * @return int 大于等于1成功， 0失败
     * @Titel 公共根据条件逻辑删除数据
     * @Description 公共根据条件逻辑删除数据
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 11:04
     */
    int delete(FileInfo record);

    /**
     * @param record 1 文件实体类对象
     * @return int 大于等于1成功， 0失败
     * @Titel 公共根据条件批量逻辑删除数据
     * @Description 公共根据条件批量逻辑删除数据
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 11:04
     */
    int deleteList(FileInfo record);

    /**
     * @param record 1 文件实体类对象
     * @return long 大于0成功，小于等于0失败
     * @Titel 公共添加
     * @Description 公共添加
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 9:31
     */
    long insert(FileInfo record);

    /**
     * @param record 1 文件实体类对象
     * @return long 大于0成功，小于等于0失败
     * @Titel 公共非空字段验证添加
     * @Description 公共非空字段验证添加
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 9:31
     */
    long insertSelective(FileInfo record);

    /**
     * @param list 1 文件批量添加集合
     * @return int 返回成功数量
     * @Titel 公共批量添加
     * @Description 公共批量添加
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 14:12
     */
    int insertList(List<FileInfo> list);


    /**
     * @param record 1 文件实体类对象
     * @return int 0失败，1成功
     * @Titel 公共根据编号修改
     * @Description 公共根据编号修改
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 9:35
     */
    int updateByPrimaryKey(FileInfo record);

    /**
     * @param record 1 文件实体类对象
     * @return int 0失败，1成功
     * @Titel 公共根据编号非空字段验证修改
     * @Description 公共根据编号非空字段验证修改
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 9:35
     */
    int updateByPrimaryKeySelective(FileInfo record);


    /**
     * @param list 1 文件批量修改集合
     * @return int 返回成功数量
     * @Titel 公共批量根据非空验证编号修改
     * @Description 公共批量根据非空验证编号修改
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 13:59
     */
    int updateList(List<FileInfo> list);

    /**
     * @param id 1 编号
     * @return com.framework.model.entity.system.SystemBlackListIp
     * @Titel 公共根据编号查询数据
     * @Description 公共根据编号查询数据
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 9:33
     */
    FileInfo selectByPrimaryKey(Long id);

    /**
     * @param record 1 文件实体类对象
     * @return java.util.List<com.framework.model.entity.system.SystemBlackListIp>
     * @Titel 公共根据条件分页查询
     * @Description 公共根据条件分页查询
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 9:41
     */
    List<FileInfo> findPageList(FileInfo record);

    /**
     * @param record 1 文件实体类对象
     * @return int 返回分页总数
     * @Titel 公共根据条件分页查询总数
     * @Description 公共根据条件分页查询总数
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 9:41
     */
    int findPageListCount(FileInfo record);

    /**
     * @param record 1 文件实体类对象
     * @return java.util.List<com.framework.model.entity.system.SystemBlackListIp>
     * @Titel 公共根据条件查询集合
     * @Description 公共根据条件查询集合
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 11:01
     */
    List<FileInfo> findByList(FileInfo record);

    /**
     * @param record 1 文件实体类对象
     * @return com.framework.common.response.ResponseResult
     * @Titel 本类后台管理添加方法
     * @Description 本类后台管理添加方法
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 11:13
     */
    public ResponseResult save(FileInfo record);

    /**
     * @param record 1 文件实体类对象
     * @return com.framework.common.response.ResponseResult
     * @Titel 本类后台管理修改方法
     * @Description 本类后台管理修改方法
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 11:13
     */
    public ResponseResult edit(FileInfo record);

    /**
     * @param idList 1 编号集合
     * @return com.framework.common.response.ResponseResult
     * @Titel 本类后台管理批量逻辑删除
     * @Description 本类后台管理批量逻辑删除
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 11:14
     */
    public ResponseResult batchDeleteList(List<Long> idList);

    /**
     * @param id 1 编号
     * @return com.framework.common.response.ResponseResult
     * @Titel 本类后台管理根据编号逻辑删除
     * @Description 本类后台管理根据编号逻辑删除
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 11:14
     */
    public ResponseResult delId(Long id);

    /**
     * @param id 1 编号
     * @return com.framework.model.entity.other.FileInfo
     * @Titel 本类根据ID查询数据信息
     * @Description 本类根据ID查询数据信息
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 11:16
     */
    FileInfo getByIdParam(Long id);

    /**
     * @param param 1 文件实体类对象
     * @return com.framework.common.response.ResponseResult
     * @Titel 本类后台管理根据条件分页查询文件列表
     * @Description 本类后台管理根据条件分页查询文件列表
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 11:20
     */
    ResponseResult findParamPageList(FileInfo param);

    /**
     * @param param 1 文件实体类对象
     * @return com.framework.common.response.ResponseResult
     * @Titel 根据条件查询附件
     * @Description 根据条件查询附件
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/27 13:32
     */
    ResponseResult findParamList(FileInfo param);

    /**
     * @param param 1 附件实体类对象
     * @return com.framework.common.response.ResponseResult
     * @Titel 上传demo方法
     * @Description 上传demo方法
     * @Author 邋遢龘鵺
     * @DateTime 2020/1/8 16:17
     */
    ResponseResult upload(FileInfo param);

    /**
     * @param param    1 附件实体类对象
     * @param response 2 请求响应对象
     * @Titel 下载demo方法
     * @Description 下载demo方法
     * @Author 邋遢龘鵺
     * @DateTime 2020/1/8
     */
    void download(FileInfo param, HttpServletResponse response);
}
