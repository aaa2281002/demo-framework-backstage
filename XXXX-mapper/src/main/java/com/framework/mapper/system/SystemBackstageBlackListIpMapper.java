package com.framework.mapper.system;

import com.framework.model.other.FileInfo;
import com.framework.model.system.SystemBackstageBlackListIp;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 邋遢龘鵺
 * @version 1.0
 * @className com.framework.mapper.system
 * @description 系统后台黑名单IPmapper接口类
 * @datetime 2019/10/11
 */
@Repository("systemBackstageBlackListIpMapper")
public interface SystemBackstageBlackListIpMapper {

    /**
     * @param row 1 系统后台黑名单IP实体类对象
     * @return int 大于等于1成功， 0失败
     * @titel 公共根据条件逻辑删除数据
     * @description 公共根据条件逻辑删除数据
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 11:04
     */
    int delete(SystemBackstageBlackListIp row);

    /**
     * @param row 1 系统后台黑名单IP实体类对象
     * @return int 大于等于1成功， 0失败
     * @titel 公共根据条件逻辑删除数据
     * @description 公共根据条件逻辑删除数据
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 11:04
     */
    int deleteList(SystemBackstageBlackListIp row);

    /**
     * @param row 1 系统后台黑名单IP实体类对象
     * @return int 大于0成功，小于等于0失败
     * @titel 公共非空字段验证添加
     * @description 公共非空字段验证添加
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 9:31
     */
    int insertSelective(SystemBackstageBlackListIp row);

    /**
     * @param row 1 系统后台黑名单IP实体类对象
     * @return int 0失败，1成功
     * @titel 公共根据编号非空字段验证修改
     * @description 公共根据编号非空字段验证修改
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 9:35
     */
    int updateByPrimaryKeySelective(SystemBackstageBlackListIp row);


    /**
     * @param id 1 编号
     * @return com.framework.model.system.SystemBackstageBlackListIp
     * @titel 公共根据编号查询数据
     * @description 公共根据编号查询数据
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 9:33
     */
    SystemBackstageBlackListIp selectByPrimaryKey(Long id);

    /**
     * @param row 1 系统后台黑名单IP实体类对象
     * @return int 0不存在， 大于等于1存在
     * @titel 公共根据条件查询是否重复存在
     * @description 公共根据条件查询是否重复存在
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 9:34
     */
    int isExist(SystemBackstageBlackListIp row);

    /**
     * @param row 1 系统后台黑名单IP实体类对象
     * @return java.util.List<com.framework.model.system.SystemBackstageBlackListIp>
     * @titel 公共根据条件分页查询
     * @description 公共根据条件分页查询
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 9:41
     */
    List<SystemBackstageBlackListIp> findPageList(SystemBackstageBlackListIp row);

    /**
     * @param row 1 系统后台黑名单IP实体类对象
     * @return int 返回分页总数
     * @titel 公共根据条件分页查询总数
     * @description 公共根据条件分页查询总数
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 9:41
     */
    int findPageListCount(SystemBackstageBlackListIp row);

    /**
     * @param row 1 系统后台黑名单IP实体类对象
     * @return java.util.List<com.framework.model.system.SystemBackstageBlackListIp>
     * @titel 公共根据条件查询集合
     * @description 公共根据条件查询集合
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 11:01
     */
    List<SystemBackstageBlackListIp> findByList(SystemBackstageBlackListIp row);
}