package com.framework.mapper.system;

import com.framework.model.system.SystemLog;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 邋遢龘鵺
 * @version 1.0
 * @className com.framework.mapper.system
 * @description 系统日志mapper接口类
 * @datetime 2019/10/11
 */
@Repository("systemLogMapper")
public interface SystemLogMapper {

    /**
     * @param row 1 系统日志实体类对象
     * @return int 大于0成功，小于等于0失败
     * @titel 公共非空字段验证添加
     * @description 公共非空字段验证添加
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 9:31
     */
    int insertSelective(SystemLog row);

    /**
     * @param row 1 系统日志实体类对象
     * @return int 0失败，1成功
     * @titel 公共根据编号非空字段验证修改
     * @description 公共根据编号非空字段验证修改
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 9:35
     */
    int updateByPrimaryKeySelective(SystemLog row);

    /**
     * @param id 1 编号
     * @return com.framework.model.system.SystemLog
     * @titel 公共根据编号查询数据
     * @description 公共根据编号查询数据
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 9:33
     */
    SystemLog selectByPrimaryKey(Long id);

    /**
     * @param row 1 系统日志实体类对象
     * @return java.util.List<com.framework.model.system.SystemLog>
     * @titel 公共根据条件分页查询
     * @description 公共根据条件分页查询
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 9:41
     */
    List<SystemLog> findPageList(SystemLog row);

    /**
     * @param row 1 系统日志实体类对象
     * @return int 返回分页总数
     * @titel 公共根据条件分页查询总数
     * @description 公共根据条件分页查询总数
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 9:41
     */
    int findPageListCount(SystemLog row);

    /**
     * @param row 1 系统日志实体类对象
     * @return java.util.List<com.framework.model.system.SystemLog>
     * @titel 公共根据条件查询集合
     * @description 公共根据条件查询集合
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 11:01
     */
    List<SystemLog> findByList(SystemLog row);

}