package com.framework.service.other;

import com.framework.common.response.ResponseResult;
import com.framework.model.other.FileInfo;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author 邋遢龘鵺
 * @version 1.0
 * @className com.framework.service.service.other
 * @description 文件信息业务接口类
 * @datetime 2019/10/11
 */
public interface FileInfoService {
    /**
     * @param row 1 文件实体类对象
     * @return int 大于等于1成功， 0失败
     * @titel 公共根据条件逻辑删除数据
     * @description 公共根据条件逻辑删除数据
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 11:04
     */
    int delete(FileInfo row);

    /**
     * @param row 1 文件实体类对象
     * @return int 大于等于1成功， 0失败
     * @titel 公共根据条件批量逻辑删除数据
     * @description 公共根据条件批量逻辑删除数据
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 11:04
     */
    int deleteList(FileInfo row);

    /**
     * @param row 1 文件实体类对象
     * @return long 大于0成功，小于等于0失败
     * @titel 公共非空字段验证添加
     * @description 公共非空字段验证添加
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 9:31
     */
    int insertSelective(FileInfo row);

    /**
     * @param list 1 文件批量添加集合
     * @return int 返回成功数量
     * @titel 公共批量添加
     * @description 公共批量添加
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 14:12
     */
    int insertList(List<FileInfo> list);

    /**
     * @param row 1 文件实体类对象
     * @return int 0失败，1成功
     * @titel 公共根据编号非空字段验证修改
     * @description 公共根据编号非空字段验证修改
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 9:35
     */
    int updateByPrimaryKeySelective(FileInfo row);


    /**
     * @param list 1 文件批量修改集合
     * @return int 返回成功数量
     * @titel 公共批量根据非空验证编号修改
     * @description 公共批量根据非空验证编号修改
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 13:59
     */
    int updateList(List<FileInfo> list);

    /**
     * @param id 1 编号
     * @return com.framework.model.other.FileInfo
     * @titel 公共根据编号查询数据
     * @description 公共根据编号查询数据
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 9:33
     */
    FileInfo selectByPrimaryKey(Long id);

    /**
     * @param row 1 文件实体类对象
     * @return java.util.List<com.framework.model.other.FileInfo>
     * @titel 公共根据条件分页查询
     * @description 公共根据条件分页查询
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 9:41
     */
    List<FileInfo> findPageList(FileInfo row);

    /**
     * @param row 1 文件实体类对象
     * @return int 返回分页总数
     * @titel 公共根据条件分页查询总数
     * @description 公共根据条件分页查询总数
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 9:41
     */
    int findPageListCount(FileInfo row);

    /**
     * @param row 1 文件实体类对象
     * @return java.util.List<com.framework.model.other.FileInfo>
     * @titel 公共根据条件查询集合
     * @description 公共根据条件查询集合
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 11:01
     */
    List<FileInfo> findByList(FileInfo row);

    /**
     * @param row 1 文件实体类对象
     * @return com.framework.common.response.ResponseResult
     * @titel 本类后台管理添加方法
     * @description 本类后台管理添加方法
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 11:13
     */
    ResponseResult save(FileInfo row);

    /**
     * @param row 1 文件实体类对象
     * @return com.framework.common.response.ResponseResult
     * @titel 本类后台管理修改方法
     * @description 本类后台管理修改方法
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 11:13
     */
    ResponseResult edit(FileInfo row);

    /**
     * @param idList 1 编号集合
     * @return com.framework.common.response.ResponseResult
     * @titel 本类后台管理批量逻辑删除
     * @description 本类后台管理批量逻辑删除
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 11:14
     */
    ResponseResult batchDeleteList(List<Long> idList);

    /**
     * @param id 1 编号
     * @return com.framework.common.response.ResponseResult
     * @titel 本类后台管理根据编号逻辑删除
     * @description 本类后台管理根据编号逻辑删除
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 11:14
     */
    ResponseResult delId(Long id);

    /**
     * @param id 1 编号
     * @return com.framework.model.other.FileInfo
     * @titel 本类根据ID查询数据信息
     * @description 本类根据ID查询数据信息
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 11:16
     */
    FileInfo getByIdParam(Long id);

    /**
     * @param param 1 文件实体类对象
     * @return com.framework.common.response.ResponseResult
     * @titel 本类后台管理根据条件分页查询文件列表
     * @description 本类后台管理根据条件分页查询文件列表
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 11:20
     */
    ResponseResult findParamPageList(FileInfo param);

    /**
     * @param param 1 文件实体类对象
     * @return com.framework.common.response.ResponseResult
     * @titel 根据条件查询附件
     * @description 根据条件查询附件
     * @author 邋遢龘鵺
     * @datetime 2019/12/27 13:32
     */
    ResponseResult findParamList(FileInfo param);

    /**
     * @param param 1 附件实体类对象
     * @return com.framework.common.response.ResponseResult
     * @titel 上传demo方法
     * @description 上传demo方法
     * @author 邋遢龘鵺
     * @datetime 2020/1/8 16:17
     */
    ResponseResult upload(FileInfo param) throws Exception;

    /**
     * @param param    1 附件实体类对象
     * @param response 2 请求响应对象
     * @titel 下载demo方法
     * @description 下载demo方法
     * @author 邋遢龘鵺
     * @datetime 2020/1/8
     */
    void download(FileInfo param, HttpServletResponse response);
}
