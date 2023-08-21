package com.framework.mapper.system;

import com.framework.model.system.SystemType;
import com.framework.model.system.vo.SystemTypeVo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 邋遢龘鵺
 * @classname com.framework.mapper.system
 * @description 类型mapper接口类
 * @datetime 2019/10/11
 * @version 1.0
 */
@Repository("systemTypeMapper")
public interface SystemTypeMapper {

    /**
     * @param row 1 类型实体类对象
     * @return int 大于等于1成功， 0失败
     * @titel 公共根据条件逻辑删除数据
     * @description 公共根据条件逻辑删除数据
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 11:04
     */
    int delete(SystemType row);

    /**
     * @param row 1 类型实体类对象
     * @return int 大于等于1成功， 0失败
     * @titel 公共根据条件逻辑删除数据
     * @description 公共根据条件逻辑删除数据
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 11:04
     */
    int deleteList(SystemType row);

    /**
     * @param row 1 类型实体类对象
     * @return int 大于0成功，小于等于0失败
     * @titel 公共非空字段验证添加
     * @description 公共非空字段验证添加
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 9:31
     */
    int insertSelective(SystemType row);

    /**
     * @param row 1 类型实体类对象
     * @return int 0失败，1成功
     * @titel 公共根据编号非空字段验证修改
     * @description 公共根据编号非空字段验证修改
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 9:35
     */
    int updateByPrimaryKeySelective(SystemType row);

    /**
     * @param id 1 编号
     * @return com.framework.model.system.SystemType
     * @titel 公共根据编号查询数据
     * @description 公共根据编号查询数据
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 9:33
     */
    SystemType selectByPrimaryKey(Long id);

    /**
     * @param row 1 实体类
     * @return com.framework.model.system.SystemType
     * @titel 公共根据编号查询数据
     * @description 公共根据编号查询数据
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 9:33
     */
    SystemType selectByParam(SystemType row);

    /**
     * @param row 1 类型实体类对象
     * @return int 0不存在， 大于等于1存在
     * @titel 公共根据条件查询是否重复存在
     * @description 公共根据条件查询是否重复存在
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 9:34
     */
    int isExist(SystemType row);

    /**
     * @param row 1 类型实体类对象
     * @return java.util.List<com.framework.model.system.SystemType>
     * @titel 公共根据条件分页查询
     * @description 公共根据条件分页查询
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 9:41
     */
    List<SystemType> findPageList(SystemType row);

    /**
     * @param row 1 类型实体类对象
     * @return int 返回分页总数
     * @titel 公共根据条件分页查询总数
     * @description 公共根据条件分页查询总数
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 9:41
     */
    int findPageListCount(SystemType row);

    /**
     * @param row 1 类型实体类对象
     * @return java.util.List<com.framework.model.system.SystemType>
     * @titel 公共根据条件查询集合
     * @description 公共根据条件查询集合
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 11:01
     */
    List<SystemType> findByList(SystemType row);

    /**
     * @param row 1 类型实体类对象
     * @return java.util.List<com.framework.model.system.SystemType>
     * @titel 根据父类代码查询集合
     * @description 根据父类代码查询集合
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 11:01
     */
    List<SystemType> findByCodeList(SystemTypeVo row);

}