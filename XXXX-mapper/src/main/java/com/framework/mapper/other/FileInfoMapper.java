package com.framework.mapper.other;

import com.framework.model.other.FileInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 邋遢龘鵺
 * @version 1.0
 * @className com.framework.dao.mapper.other
 * @description 文件信息mapper接口类
 * @datetime 2019/10/11
 */
@Repository
public interface FileInfoMapper {
    /**
     * @param id 1 编号
     * @return int 成功返回大于1，失败返回小于1
     * @titel 公共根据编号，物理删除数据。
     * @description 公共根据编号，物理删除数据。
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 11:08
     */
    //int deleteByPrimaryKey(Long id);

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
     * @titel 公共分页查询
     * @description 公共分页查询
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 9:41
     */
    List<FileInfo> findPageList(FileInfo row);

    /**
     * @param row 1 文件实体类对象
     * @return int 返回分页总数
     * @titel 公共分页查询总数
     * @description 公共分页查询总数
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

}