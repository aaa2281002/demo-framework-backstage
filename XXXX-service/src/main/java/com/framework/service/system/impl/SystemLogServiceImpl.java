package com.framework.service.system.impl;

import com.framework.common.response.ResponseResult;
import com.framework.mapper.system.SystemLogMapper;
import com.framework.model.system.SystemLog;
import com.framework.model.system.SystemUser;
import com.framework.service.base.BaseService;
import com.framework.service.system.SystemLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;

/**
 * @author 邋遢龘鵺
 * @version 1.0
 * @className com.framework.service.system.impl
 * @description 系统日志业务接口实现类
 * @datetime 2019/10/11
 */
@Service("systemLogServiceImpl")
public class SystemLogServiceImpl extends BaseService implements SystemLogService {
    @Autowired
    private SystemLogMapper systemLogMapper;

    /**
     * @param row 1 系统日志实体类对象
     * @return long 返回主键ID大于0成功，小于等于0失败
     * @titel 公共非空字段验证添加
     * @description 公共非空字段验证添加
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 9:31
     */
    @Override
    public int insertSelective(SystemLog row) {
        return systemLogMapper.insertSelective(row);
    }

    /**
     * @param row 1 系统日志实体类对象
     * @return int 0失败，1成功
     * @titel 公共根据编号非空字段验证修改
     * @description 公共根据编号非空字段验证修改
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 9:35
     */
    @Override
    public int updateByPrimaryKeySelective(SystemLog row) {
        return systemLogMapper.updateByPrimaryKeySelective(row);
    }

    /**
     * @param id 1 编号
     * @return com.framework.model.system.SystemLog
     * @titel 公共根据编号查询数据
     * @description 公共根据编号查询数据
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 9:33
     */
    @Override
    public SystemLog selectByPrimaryKey(Long id) {
        return systemLogMapper.selectByPrimaryKey(id);
    }

    /**
     * @param row 1 系统日志实体类对象
     * @return java.util.List<com.framework.model.system.SystemLog>
     * @titel 公共根据条件分页查询
     * @description 公共根据条件分页查询
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 9:41
     */
    @Override
    public List<SystemLog> findPageList(SystemLog row) {
        return systemLogMapper.findPageList(row);
    }

    /**
     * @param row 1 系统日志实体类对象
     * @return int 返回分页总数
     * @titel 公共根据条件分页查询总数
     * @description 公共根据条件分页查询总数
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 9:41
     */
    @Override
    public int findPageListCount(SystemLog row) {
        return systemLogMapper.findPageListCount(row);
    }

    /**
     * @param row 1 系统日志实体类对象
     * @return java.util.List<com.framework.model.system.SystemLog>
     * @titel 公共根据条件分页查询总数
     * @description 公共根据条件分页查询总数
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 9:41
     */
    @Override
    public List<SystemLog> findByList(SystemLog row) {
        return systemLogMapper.findByList(row);
    }

    /**
     * @param id 1 编号
     * @return com.framework.model.system.SystemLog
     * @titel 本类后台管理根据ID查询数据信息
     * @description 本类后台管理根据ID查询数据信息
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 11:16
     */
    @Override
    public SystemLog getByIdParam(Long id) {
        return systemLogMapper.selectByPrimaryKey(id);
    }

    /**
     * @param user            1 系统用户实体类对象
     * @param ip              2 登录IP
     * @param operaterTitle   3 操作标题
     * @param operaterContent 4 操作内容
     * @titel 用户操作日志添加
     * @description 用户操作日志添加
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 15:42
     */
    @Override
    @Async
    public void loginInfo(SystemUser user, String ip, String operaterTitle, String operaterContent) {
        if (user == null) {
            return;
        }
        Date date = new Date();
        SystemLog row = new SystemLog();
        row.setOperaterTime(date);
        row.setOperaterId(user.getId());
        row.setIp(ip);
        row.setLoginName(user.getLoginName());
        row.setOperaterTitle(operaterTitle);
        row.setOperaterContent(operaterContent);
        systemLogMapper.insertSelective(row);
    }

    /**
     * @param param 1 日志实体类对象
     * @return com.framework.common.response.ResponseResult
     * @titel 本类后台管理根据条件分页查询日志列表
     * @description 本类后台管理根据条件分页查询日志列表
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 11:20
     */
    @Override
    public ResponseResult findParamPageList(SystemLog param) {
        ResponseResult r = getResponseResult();
        int count = systemLogMapper.findPageListCount(param);
        List<SystemLog> list = systemLogMapper.findPageList(param);
        return r.success().setData(list).setCount(count);
    }

    /**
     * @param id 1 编号
     * @return com.framework.common.response.ResponseResult
     * @Title 根据编号查询信息
     * @Description 根据编号查询信息
     * @Author 龘鵺
     * @DateTime 2023/5/15 10:25
     */
    @Override
    public ResponseResult getByIdInfo(Long id) {
        SystemLog param = systemLogMapper.selectByPrimaryKey(id);
        return super.getResponseResult().success().setData(param);
    }
}
