package com.framework.service.service.system.impl;

import com.framework.common.response.ResponseResult;
import com.framework.common.util.hump.HumpOrLineUtil;
import com.framework.common.util.other.NumeralUtil;
import com.framework.dao.mapper.system.SystemLogMapper;
import com.framework.model.entity.system.SystemLog;
import com.framework.model.entity.system.SystemUser;
import com.framework.service.base.BaseService;
import com.framework.service.service.system.SystemLogService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Author 邋遢龘鵺
 * @ClassName com.framework.service.service.system.impl
 * @Description 系统日志业务接口实现类
 * @DateTime 2019/10/11
 * @Version 1.0
 */
@Service
public class SystemLogServiceImpl extends BaseService implements SystemLogService {
    @Autowired
    private SystemLogMapper systemLogMapper;

    /**
     * @param record 1 系统日志实体类对象
     * @return long 返回主键ID大于0成功，小于等于0失败
     * @Titel 公共添加
     * @Description 公共添加
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 9:31
     */
    @Override
    public int insert(SystemLog record) {
        return systemLogMapper.insert(record);
    }


    /**
     * @param record 1 系统日志实体类对象
     * @return long 返回主键ID大于0成功，小于等于0失败
     * @Titel 公共非空字段验证添加
     * @Description 公共非空字段验证添加
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 9:31
     */
    @Override
    public int insertSelective(SystemLog record) {
        return systemLogMapper.insertSelective(record);
    }

    /**
     * @param record 1 系统日志实体类对象
     * @return int 0失败，1成功
     * @Titel 公共根据编号修改
     * @Description 公共根据编号修改
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 9:35
     */
    @Override
    public int updateByPrimaryKey(SystemLog record) {
        return systemLogMapper.updateByPrimaryKey(record);
    }

    /**
     * @param record 1 系统日志实体类对象
     * @return int 0失败，1成功
     * @Titel 公共根据编号非空字段验证修改
     * @Description 公共根据编号非空字段验证修改
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 9:35
     */
    @Override
    public int updateByPrimaryKeySelective(SystemLog record) {
        return systemLogMapper.updateByPrimaryKeySelective(record);
    }

    /**
     * @param id 1 编号
     * @return com.framework.model.entity.system.SystemLog
     * @Titel 公共根据编号查询数据
     * @Description 公共根据编号查询数据
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 9:33
     */
    @Override
    public SystemLog selectByPrimaryKey(Long id) {
        return systemLogMapper.selectByPrimaryKey(id);
    }

    /**
     * @param record 1 系统日志实体类对象
     * @return java.util.List<com.framework.model.entity.system.SystemLog>
     * @Titel 公共根据条件分页查询
     * @Description 公共根据条件分页查询
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 9:41
     */
    @Override
    public List<SystemLog> findPageList(SystemLog record) {
        return systemLogMapper.findPageList(record);
    }

    /**
     * @param record 1 系统日志实体类对象
     * @return int 返回分页总数
     * @Titel 公共根据条件分页查询总数
     * @Description 公共根据条件分页查询总数
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 9:41
     */
    @Override
    public int findPageListCount(SystemLog record) {
        return systemLogMapper.findPageListCount(record);
    }

    /**
     * @param record 1 系统日志实体类对象
     * @return java.util.List<com.framework.model.entity.system.SystemLog>
     * @Titel 公共根据条件分页查询总数
     * @Description 公共根据条件分页查询总数
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 9:41
     */
    @Override
    public List<SystemLog> findByList(SystemLog record) {
        return systemLogMapper.findByList(record);
    }

    /**
     * @param id 1 编号
     * @return com.framework.model.entity.system.SystemLog
     * @Titel 本类后台管理根据ID查询数据信息
     * @Description 本类后台管理根据ID查询数据信息
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 11:16
     */
    @Override
    public SystemLog getByIdParam(Long id) {
        if (id == null || id.longValue() < NumeralUtil.MULTIPLEXING_LONG_POSITIVE_ONE) {
            return null;
        }
        return this.selectByPrimaryKey(id);
    }

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
    @Override
    @Async
    public void loginInfo(SystemUser user, String ip, String operaterTitle, String operaterContent) {
        if (user == null) {
            return;
        }
        Date date = new Date();
        SystemLog record = new SystemLog();
        record.setOperaterTime(date);
        record.setOperaterId(user.getId());
        record.setIp(ip);
        record.setLoginName(user.getLoginName());
        record.setOperaterTitle(operaterTitle);
        record.setOperaterContent(operaterContent);
        try {
            this.insert(record);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param param 1 日志实体类对象
     * @return com.framework.common.response.ResponseResult
     * @Titel 本类后台管理根据条件分页查询日志列表
     * @Description 本类后台管理根据条件分页查询日志列表
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 11:20
     */
    @Override
    public ResponseResult findParamPageList(SystemLog param) {
        ResponseResult r = getResponseResult();
        if (StringUtils.isNotEmpty(param.getSort()) && StringUtils.isNotEmpty(param.getOrder())) {
            param.setSort("p." + HumpOrLineUtil.humpToLine(param.getSort()));
        } else {
            param.setSort("p.operater_Time");
            param.setOrder("desc");
        }
        try {
            int count = this.findPageListCount(param);
            List<SystemLog> list = this.findPageList(param);
            return r.ResponseResultSuccess().setData(list).setCount(count);
        } catch (Exception e) {
            e.printStackTrace();
            return r.ResponseResultFail();
        }
    }
}
