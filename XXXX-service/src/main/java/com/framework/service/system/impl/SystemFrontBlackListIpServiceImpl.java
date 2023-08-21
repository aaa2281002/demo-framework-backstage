package com.framework.service.system.impl;

import com.alibaba.fastjson.JSONObject;
import com.framework.common.response.ResponseResult;
import com.framework.common.util.cache.CacheUtil;
import com.framework.common.util.cache.SpringCacheKeyGenerator;
import com.framework.common.util.other.NumeralUtil;
import com.framework.common.util.redis.RedisKeyUtil;
import com.framework.common.util.redis.RedisPrefixUtil;
import com.framework.mapper.system.SystemFrontBlackListIpMapper;
import com.framework.model.system.SystemFrontBlackListIp;
import com.framework.service.base.BaseService;
import com.framework.service.system.SystemFrontBlackListIpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;

/**
 * @author 邋遢龘鵺
 * @version 1.0
 * @className com.framework.service.system.impl
 * @description 系统前端黑名单IP业务接口实现类
 * @datetime 2019/10/11
 */
@Service("systemFrontBlackListIpServiceImpl")
public class SystemFrontBlackListIpServiceImpl extends BaseService implements SystemFrontBlackListIpService {
    @Autowired
    private SystemFrontBlackListIpMapper systemFrontBlackListIpMapper;

    /**
     * @param row 1 系统前端黑名单IP实体类对象
     * @return int 大于等于1成功， 0失败
     * @titel 公共根据条件逻辑删除数据
     * @description 公共根据条件逻辑删除数据
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 11:04
     */
    @Override
    public int deleteList(SystemFrontBlackListIp row) {
        return systemFrontBlackListIpMapper.deleteList(row);
    }

    /**
     * @param row 1 系统前端黑名单IP实体类对象
     * @return long 返回主键ID大于0成功，小于等于0失败
     * @titel 公共非空字段验证添加
     * @description 公共非空字段验证添加
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 9:31
     */
    @Override
    public int insertSelective(SystemFrontBlackListIp row) {
        return systemFrontBlackListIpMapper.insertSelective(row);
    }

    /**
     * @param row 1 系统前端黑名单IP实体类对象
     * @return int 0失败，1成功
     * @titel 公共根据编号非空字段验证修改
     * @description 公共根据编号非空字段验证修改
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 9:35
     */
    @Override
    public int updateByPrimaryKeySelective(SystemFrontBlackListIp row) {
        return systemFrontBlackListIpMapper.updateByPrimaryKeySelective(row);
    }

    /**
     * @param id 1 编号
     * @return com.framework.model.system.SystemFrontBlackListIp
     * @titel 公共根据编号查询数据
     * @description 公共根据编号查询数据
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 9:33
     */
    @Override
    public SystemFrontBlackListIp selectByPrimaryKey(Long id) {
        return systemFrontBlackListIpMapper.selectByPrimaryKey(id);
    }

    /**
     * @param row 1 系统前端黑名单IP实体类对象
     * @return int 0不存在， 大于等于1存在
     * @titel 公共根据条件查询是否重复存在
     * @description 公共根据条件查询是否重复存在
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 9:34
     */
    @Override
    public int isExist(SystemFrontBlackListIp row) {
        return systemFrontBlackListIpMapper.isExist(row);
    }

    /**
     * @param row 1 系统前端黑名单IP实体类对象
     * @return java.util.List<com.framework.model.system.SystemFrontBlackListIp>
     * @titel 公共根据条件分页查询
     * @description 公共根据条件分页查询
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 9:41
     */
    @Override
    public List<SystemFrontBlackListIp> findPageList(SystemFrontBlackListIp row) {
        return systemFrontBlackListIpMapper.findPageList(row);
    }

    /**
     * @param row 1 系统前端黑名单IP实体类对象
     * @return int 返回分页总数
     * @titel 公共根据条件分页查询总数
     * @description 公共根据条件分页查询总数
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 9:41
     */
    @Override
    public int findPageListCount(SystemFrontBlackListIp row) {
        return systemFrontBlackListIpMapper.findPageListCount(row);
    }

    /**
     * @param row 1 系统前端黑名单IP实体类对象
     * @return java.util.List<com.framework.model.system.SystemFrontBlackListIp>
     * @titel 公共根据条件查询集合
     * @description 公共根据条件查询集合
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 11:01
     */
    @Override
    public List<SystemFrontBlackListIp> findByList(SystemFrontBlackListIp row) {
        return systemFrontBlackListIpMapper.findByList(row);
    }


    /**
     * @param row 1 系统前端黑名单IP实体类对象
     * @return com.framework.common.response.ResponseResult
     * @titel 本类后台管理添加方法
     * @description 本类后台管理添加方法
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 11:13
     */
    @Override
    @CacheEvict(value = CacheUtil.CACHE_SUFFIX + "SystemFrontBlackListIp", allEntries = true)
//    @Transactional
//    @TargetDataSource(dataSource = DataSourceUtil.WRITE_DATA_SOURCE)
    public ResponseResult save(SystemFrontBlackListIp row) {
        ResponseResult r = getResponseResult();
        int num = systemFrontBlackListIpMapper.isExist(row);
        if (num > NumeralUtil.POSITIVE_ZERO) {
            return r.failRepeat();
        }
        Long userId = getUserId();
        Date date = new Date();
        row.setOperaterTime(date);
        row.setCreateId(userId);
        row.setOperaterId(userId);
        row.setCreateTime(date);
        row.setOperaterStatus(NumeralUtil.POSITIVE_ONE);
        systemFrontBlackListIpMapper.insertSelective(row);
        String ipKey = RedisKeyUtil.getObjKey(RedisPrefixUtil.PREFIX_STR_LOGIN_SYSTEM_FRONT_BLACK_LIST_IP, RedisPrefixUtil.PREFIX_STR_LOGIN_SYSTEM_FRONT_BLACK_LIST_IP, row.getIp());
        super.redisUtil.setString(ipKey, JSONObject.toJSONString(row));
        return r.success();
    }

    /**
     * @param row 1 系统前端黑名单IP实体类对象
     * @return com.framework.common.response.ResponseResult
     * @titel 本类后台管理修改方法
     * @description 本类后台管理修改方法
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 11:13
     */
    @Override
    @CacheEvict(value = CacheUtil.CACHE_SUFFIX + "SystemFrontBlackListIp", allEntries = true)
    public ResponseResult edit(SystemFrontBlackListIp row) {
        ResponseResult r = getResponseResult();
        int num = systemFrontBlackListIpMapper.isExist(row);
        if (num > NumeralUtil.POSITIVE_ZERO) {
            return r.failRepeat();
        }
        SystemFrontBlackListIp sbli = systemFrontBlackListIpMapper.selectByPrimaryKey(row.getId());
        if (sbli == null) {
            return r.failRepeat().setMsg("修改系统前端黑名单信息不存在!");
        }
        Date date = new Date();
        Long userId = getUserId();
        row.setOperaterStatus(NumeralUtil.POSITIVE_TWO);
        row.setOperaterId(userId);
        row.setOperaterTime(date);
        systemFrontBlackListIpMapper.updateByPrimaryKeySelective(row);
        //刷新系统后台操作白名单IP
        if (!row.getIp().equals(sbli.getIp())) {
            String ipKey = RedisKeyUtil.getObjKey(RedisPrefixUtil.PREFIX_STR_LOGIN_SYSTEM_FRONT_BLACK_LIST_IP, RedisPrefixUtil.PREFIX_STR_LOGIN_SYSTEM_FRONT_BLACK_LIST_IP, sbli.getIp());
            super.redisUtil.deleteKey(ipKey);
        }
        String ipKey = RedisKeyUtil.getObjKey(RedisPrefixUtil.PREFIX_STR_LOGIN_SYSTEM_FRONT_BLACK_LIST_IP, RedisPrefixUtil.PREFIX_STR_LOGIN_SYSTEM_FRONT_BLACK_LIST_IP, row.getIp());
        super.redisUtil.setString(ipKey, JSONObject.toJSONString(row));
        return r.success();
    }

    /**
     * @param idList 1 编号集合
     * @return com.framework.common.response.ResponseResult
     * @titel 本类后台管理批量逻辑删除
     * @description 本类后台管理批量逻辑删除
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 11:14
     */
    @Override
    @CacheEvict(value = CacheUtil.CACHE_SUFFIX + "SystemFrontBlackListIp", allEntries = true)
    public ResponseResult batchDeleteList(List<Long> idList) {
        ResponseResult r = getResponseResult();
        SystemFrontBlackListIp row = new SystemFrontBlackListIp();
        row.setIdList(idList);
        List<SystemFrontBlackListIp> list = systemFrontBlackListIpMapper.findByList(row);
        if (list == null || list.size() == NumeralUtil.POSITIVE_ZERO) {
            return r.fail().setMsg("请选择操作信息");
        }
        String[] delKey = new String[list.size()];
        for (int i = NumeralUtil.POSITIVE_ZERO; i < list.size(); i++) {
            delKey[i] = RedisKeyUtil.getObjKey(RedisPrefixUtil.PREFIX_STR_LOGIN_SYSTEM_FRONT_BLACK_LIST_IP, RedisPrefixUtil.PREFIX_STR_LOGIN_SYSTEM_FRONT_BLACK_LIST_IP, list.get(i).getIp());
        }
        row.setOperaterStatus(NumeralUtil.NEGATIVE_ONE);
        row.setOperaterTime(new Date());
        Long userId = getUserId();
        row.setOperaterId(userId);
        systemFrontBlackListIpMapper.deleteList(row);
        if (delKey != null && delKey.length > NumeralUtil.POSITIVE_ZERO) {
            super.redisUtil.deleteKey(delKey);
        }
        return r.success();
    }

    /**
     * @param id 1 编号
     * @return com.framework.common.response.ResponseResult
     * @titel 本类后台管理根据编号逻辑删除
     * @description 本类后台管理根据编号逻辑删除
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 11:14
     */
    @Override
    public ResponseResult delId(Long id) {
        ResponseResult r = getResponseResult();
        SystemFrontBlackListIp row = new SystemFrontBlackListIp();
        row.setOperaterStatus(NumeralUtil.NEGATIVE_ONE);
        row.setOperaterTime(new Date());
        Long userId = getUserId();
        row.setOperaterId(userId);
        row.setId(id);
        systemFrontBlackListIpMapper.delete(row);
        return r.success();
    }

    /**
     * @param id 1 编号
     * @param ip 2 IP
     * @return com.framework.common.response.ResponseResult
     * @titel 本类后台管理查询是否存在，只是适合本身的一些方法处理。
     * @description boolean false不存在， true存在
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 11:14
     */
    @Override
    public ResponseResult isExist(Long id, String ip) {
        SystemFrontBlackListIp param = new SystemFrontBlackListIp();
        param.setId(id);
        param.setIp(ip);
        Boolean is = systemFrontBlackListIpMapper.isExist(param) > NumeralUtil.POSITIVE_ZERO ? Boolean.FALSE : Boolean.TRUE;
        return super.getResponseResult().success().setData(is);
    }

    /**
     * @param id 1 编号
     * @return com.framework.model.system.SystemFrontBlackListIp
     * @titel 本类后台管理根据ID查询数据信息
     * @description 本类后台管理根据ID查询数据信息
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 11:16
     */
    @Override
    public SystemFrontBlackListIp getByIdParam(Long id) {
        SystemFrontBlackListIp p = systemFrontBlackListIpMapper.selectByPrimaryKey(id);
        return p;
    }

    /**
     * @param param 1 系统前端黑名单IP实体类对象
     * @return com.framework.common.response.ResponseResult
     * @titel 本类后台管理根据条件分页查询系统前端黑名单IP列表
     * @description 本类后台管理根据条件分页查询系统前端黑名单IP列表
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 11:20
     */
    @Override
    public ResponseResult findParamPageList(SystemFrontBlackListIp param) {
        ResponseResult r = getResponseResult();
        param.setGtaeOperaterStatus(NumeralUtil.POSITIVE_ONE);
        int count = systemFrontBlackListIpMapper.findPageListCount(param);
        List<SystemFrontBlackListIp> list = systemFrontBlackListIpMapper.findPageList(param);
        return r.success().setData(list).setCount(count);
    }

    /**
     * @param ip 1 IP
     * @return com.framework.model.system.SystemFrontBlackListIp
     * @titel 根据IP查询是否存在
     * @description 根据IP查询是否存在
     * @author 邋遢龘鵺
     * @datetime 2020/1/14 9:47
     */
    @Cacheable(value = CacheUtil.CACHE_SUFFIX + "SystemFrontBlackListIp", keyGenerator = SpringCacheKeyGenerator.KEYGENERATOR)
    public SystemFrontBlackListIp getIp(String ip) {
        SystemFrontBlackListIp p = new SystemFrontBlackListIp();
        p.setIp(ip);
        List<SystemFrontBlackListIp> list = systemFrontBlackListIpMapper.findByList(p);
        return list != null && list.size() > NumeralUtil.POSITIVE_ZERO ? list.get(NumeralUtil.POSITIVE_ZERO) : null;
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
        SystemFrontBlackListIp param = systemFrontBlackListIpMapper.selectByPrimaryKey(id);
        return super.getResponseResult().success().setData(param);
    }
}
