package com.framework.service.service.system;

import com.framework.common.response.ResponseResult;
import com.framework.model.entity.system.SystemLog;
import com.framework.model.entity.system.SystemUser;

import java.util.List;

/**
 * @Author 邋遢龘鵺
 * @ClassName com.framework.service.service.system
 * @Description 系统日志业务接口类
 * @DateTime 2019/10/11
 * @Version 1.0
 */
public interface SystemLogService {

    /**
     * @param record 1 系统日志实体类对象
     * @return long 返回主键ID大于0成功，小于等于0失败
     * @Titel 公共添加
     * @Description 公共添加
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 9:31
     */
    long insert(SystemLog record);

    /**
     * @param record 1 系统日志实体类对象
     * @return long 返回主键ID大于0成功，小于等于0失败
     * @Titel 公共非空字段验证添加
     * @Description 公共非空字段验证添加
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 9:31
     */
    long insertSelective(SystemLog record);

    /**
     * @param record 1 系统日志实体类对象
     * @return int 0失败，1成功
     * @Titel 公共根据编号修改
     * @Description 公共根据编号修改
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 9:35
     */
    int updateByPrimaryKey(SystemLog record);

    /**
     * @param record 1 系统日志实体类对象
     * @return int 0失败，1成功
     * @Titel 公共根据编号非空字段验证修改
     * @Description 公共根据编号非空字段验证修改
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 9:35
     */
    int updateByPrimaryKeySelective(SystemLog record);

    /**
     * @param id 1 编号
     * @return com.framework.model.entity.system.SystemLog
     * @Titel 公共根据编号查询数据
     * @Description 公共根据编号查询数据
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 9:33
     */
    SystemLog selectByPrimaryKey(Long id);

    /**
     * @param record 1 系统日志实体类对象
     * @return java.util.List<com.framework.model.entity.system.SystemLog>
     * @Titel 公共根据条件分页查询
     * @Description 公共根据条件分页查询
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 9:41
     */
    List<SystemLog> findPageList(SystemLog record);

    /**
     * @param record 1 系统日志实体类对象
     * @return int 返回分页总数
     * @Titel 公共根据条件分页查询总数
     * @Description 公共根据条件分页查询总数
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 9:41
     */
    int findPageListCount(SystemLog record);

    /**
     * @param record 1 系统日志实体类对象
     * @return java.util.List<com.framework.model.entity.system.SystemLog>
     * @Titel 公共根据条件查询集合
     * @Description 公共根据条件查询集合
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 11:01
     */
    List<SystemLog> findByList(SystemLog record);

    /**
     * @param id 1 编号
     * @return com.framework.model.entity.system.SystemLog
     * @Titel 本类后台管理根据ID查询数据信息
     * @Description 本类后台管理根据ID查询数据信息
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 11:16
     */
    SystemLog getByIdParam(Long id);

    /**
     * @param user            1 系统用户实体类对象
     * @param ip              2 登录IP
     * @param operaterTitle   3 操作标题
     * @param operaterContent 4 操作内容
     * @Titel 用户操作日志添加
     * @Description 用户操作日志添加
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 15:42
     */
    public void loginInfo(SystemUser user, String ip, String operaterTitle, String operaterContent);

    /**
     * @param param 1 字典实体类对象
     * @return com.framework.common.response.ResponseResult
     * @Titel 本类后台管理根据条件分页查询字典列表
     * @Description 本类后台管理根据条件分页查询字典列表
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 11:20
     */
    ResponseResult findParamPageList(SystemLog param);

}
