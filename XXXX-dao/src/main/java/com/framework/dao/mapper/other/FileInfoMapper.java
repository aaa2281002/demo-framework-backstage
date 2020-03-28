package com.framework.dao.mapper.other;

import com.framework.model.entity.other.FileInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author 邋遢龘鵺
 * @ClassName com.framework.dao.mapper.other
 * @Description 文件信息mapper接口类
 * @DateTime 2019/10/11
 * @Version 1.0
 */
@Repository
public interface FileInfoMapper {
    /**
     * @param id 1 编号
     * @return int 成功返回大于1，失败返回小于1
     * @Titel 公共根据编号，物理删除数据。
     * @Description 公共根据编号，物理删除数据。
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 11:08
     */
    //int deleteByPrimaryKey(Long id);


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
     * @Titel 公共分页查询
     * @Description 公共分页查询
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 9:41
     */
    List<FileInfo> findPageList(FileInfo record);

    /**
     * @param record 1 文件实体类对象
     * @return int 返回分页总数
     * @Titel 公共分页查询总数
     * @Description 公共分页查询总数
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

}