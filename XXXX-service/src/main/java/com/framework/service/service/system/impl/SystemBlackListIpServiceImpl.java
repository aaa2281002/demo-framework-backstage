package com.framework.service.service.system.impl;

import com.framework.common.response.ResponseResult;
import com.framework.common.util.cache.CacheUtil;
import com.framework.common.util.cache.SpringCacheKeyGenerator;
import com.framework.common.util.hump.HumpOrLineUtil;
import com.framework.common.util.other.NumeralUtil;
import com.framework.common.util.redis.RedisKeyUtil;
import com.framework.dao.mapper.system.SystemBlackListIpMapper;
import com.framework.model.entity.system.SystemBlackListIp;
import com.framework.service.base.BaseService;
import com.framework.service.service.system.SystemBlackListIpService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Author 邋遢龘鵺
 * @ClassName com.framework.service.service.system.impl
 * @Description 系统前端操作黑名单IP业务接口实现类
 * @DateTime 2019/10/11
 * @Version 1.0
 */
@Service
public class SystemBlackListIpServiceImpl extends BaseService implements SystemBlackListIpService {
    @Autowired
    private SystemBlackListIpMapper systemBlackListIpMapper;

    /**
     * @param record 1 系统前端操作黑名单IP实体类对象
     * @return int 大于等于1成功， 0失败
     * @Titel 公共根据条件逻辑删除数据
     * @Description 公共根据条件逻辑删除数据
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 11:04
     */
    @Override
    public int deleteList(SystemBlackListIp record) {
        return systemBlackListIpMapper.deleteList(record);
    }

    /**
     * @param record 1 系统前端操作黑名单IP实体类对象
     * @return long 返回主键ID大于0成功，小于等于0失败
     * @Titel 公共添加
     * @Description 公共添加
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 9:31
     */
    @Override
    public int insert(SystemBlackListIp record) {
        return systemBlackListIpMapper.insert(record);
    }

    /**
     * @param record 1 系统前端操作黑名单IP实体类对象
     * @return long 返回主键ID大于0成功，小于等于0失败
     * @Titel 公共非空字段验证添加
     * @Description 公共非空字段验证添加
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 9:31
     */
    @Override
    public int insertSelective(SystemBlackListIp record) {
        return systemBlackListIpMapper.insertSelective(record);
    }

    /**
     * @param record 1 系统前端操作黑名单IP实体类对象
     * @return int 0失败，1成功
     * @Titel 公共根据编号修改
     * @Description 公共根据编号修改
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 9:35
     */
    @Override
    public int updateByPrimaryKey(SystemBlackListIp record) {
        return systemBlackListIpMapper.updateByPrimaryKey(record);
    }

    /**
     * @param record 1 系统前端操作黑名单IP实体类对象
     * @return int 0失败，1成功
     * @Titel 公共根据编号非空字段验证修改
     * @Description 公共根据编号非空字段验证修改
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 9:35
     */
    @Override
    public int updateByPrimaryKeySelective(SystemBlackListIp record) {
        return systemBlackListIpMapper.updateByPrimaryKeySelective(record);
    }

    /**
     * @param id 1 编号
     * @return com.framework.model.entity.system.SystemBlackListIp
     * @Titel 公共根据编号查询数据
     * @Description 公共根据编号查询数据
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 9:33
     */
    @Override
    public SystemBlackListIp selectByPrimaryKey(Long id) {
        return systemBlackListIpMapper.selectByPrimaryKey(id);
    }

    /**
     * @param record 1 系统前端操作黑名单IP实体类对象
     * @return int 0不存在， 大于等于1存在
     * @Titel 公共根据条件查询是否重复存在
     * @Description 公共根据条件查询是否重复存在
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 9:34
     */
    @Override
    public int isExist(SystemBlackListIp record) {
        return systemBlackListIpMapper.isExist(record);
    }

    /**
     * @param record 1 系统前端操作黑名单IP实体类对象
     * @return java.util.List<com.framework.model.entity.system.SystemBlackListIp>
     * @Titel 公共根据条件分页查询
     * @Description 公共根据条件分页查询
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 9:41
     */
    @Override
    public List<SystemBlackListIp> findPageList(SystemBlackListIp record) {
        return systemBlackListIpMapper.findPageList(record);
    }

    /**
     * @param record 1 系统前端操作黑名单IP实体类对象
     * @return int 返回分页总数
     * @Titel 公共根据条件分页查询总数
     * @Description 公共根据条件分页查询总数
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 9:41
     */
    @Override
    public int findPageListCount(SystemBlackListIp record) {
        return systemBlackListIpMapper.findPageListCount(record);
    }

    /**
     * @param record 1 系统前端操作黑名单IP实体类对象
     * @return java.util.List<com.framework.model.entity.system.SystemBlackListIp>
     * @Titel 公共根据条件查询集合
     * @Description 公共根据条件查询集合
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 11:01
     */
    @Override
    public List<SystemBlackListIp> findByList(SystemBlackListIp record) {
        return systemBlackListIpMapper.findByList(record);
    }


    /**
     * @param record 1 系统前端操作黑名单IP实体类对象
     * @return com.framework.common.response.ResponseResult
     * @Titel 本类后台管理添加方法
     * @Description 本类后台管理添加方法
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 11:13
     */
    @Override
    @CacheEvict(value = CacheUtil.CACHE_SUFFIX + "SystemBlackListIp", allEntries = true)
//    @Transactional
//    @TargetDataSource(dataSource = DataSourceUtil.WRITE_DATA_SOURCE)
    public ResponseResult save(SystemBlackListIp record) {
        ResponseResult r = getResponseResult();
        if (record == null) {
            return r.ResponseResultFail();
        }
        int num = this.isExist(record);
        if (num > NumeralUtil.POSITIVE_ZERO) {
            return r.ResponseResultFailRepeat();
        }
        Long userId = getUserId();
        Date date = new Date();
        record.setOperaterTime(date);
        record.setCreateId(userId);
        record.setOperaterId(userId);
        record.setCreateTime(date);
        record.setOperaterStatus(NumeralUtil.POSITIVE_ONE);
        try {
            int is = this.insert(record);
            if (is > NumeralUtil.POSITIVE_ZERO) {
                super.redisUtil.setString(RedisKeyUtil.getSystemUserBlackListIpKey(record.getIp()), record);
                return r.ResponseResultSuccess();
            }
            return r.ResponseResultAddFail();
        } catch (Exception e) {
            e.printStackTrace();
            return r.ResponseResultError();
        }
    }

    /**
     * @param record 1 系统前端操作黑名单IP实体类对象
     * @return com.framework.common.response.ResponseResult
     * @Titel 本类后台管理修改方法
     * @Description 本类后台管理修改方法
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 11:13
     */
    @Override
    @CacheEvict(value = CacheUtil.CACHE_SUFFIX + "SystemBlackListIp", allEntries = true)
    public ResponseResult edit(SystemBlackListIp record) {
        ResponseResult r = getResponseResult();
        if (record == null || record.getId() == null || record.getId().longValue() < NumeralUtil.MULTIPLEXING_LONG_POSITIVE_ONE) {
            return r.ResponseResultFail();
        }
        int num = this.isExist(record);
        if (num > NumeralUtil.POSITIVE_ZERO) {
            return r.ResponseResultFailRepeat();
        }
        SystemBlackListIp sbli = this.selectByPrimaryKey(record.getId());
        if (sbli == null) {
            return r.ResponseResultFailRepeat().setMsg("修改系统前端操作黑名单信息不存在!");
        }
        Date date = new Date();
        Long userId = getUserId();
        record.setOperaterStatus(NumeralUtil.POSITIVE_TWO);
        record.setOperaterId(userId);
        record.setOperaterTime(date);
        try {
            int is = this.updateByPrimaryKeySelective(record);
            if (is > NumeralUtil.POSITIVE_ZERO) {
                //刷新系统后台操作白名单IP
                if (!record.getIp().equals(sbli.getIp())) {
                    super.redisUtil.deleteKey(RedisKeyUtil.getSystemUserBlackListIpKey(sbli.getIp()));
                }
                super.redisUtil.setString(RedisKeyUtil.getSystemUserBlackListIpKey(record.getIp()), record);
                return r.ResponseResultSuccess();
            }
            return r.ResponseResultUpdateFail();
        } catch (Exception e) {
            e.printStackTrace();
            return r.ResponseResultError();
        }
    }

    /**
     * @param idList 1 编号集合
     * @return com.framework.common.response.ResponseResult
     * @Titel 本类后台管理批量逻辑删除
     * @Description 本类后台管理批量逻辑删除
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 11:14
     */
    @Override
    @CacheEvict(value = CacheUtil.CACHE_SUFFIX + "SystemBlackListIp", allEntries = true)
    public ResponseResult batchDeleteList(List<Long> idList) {
        ResponseResult r = getResponseResult();
        if (idList == null || idList.size() < NumeralUtil.POSITIVE_ONE) {
            return r.ResponseResultFail();
        }

        SystemBlackListIp record = new SystemBlackListIp();
        record.setIdList(idList);
        List<SystemBlackListIp> buttonList = this.findByList(record);
        String[] delKey = new String[buttonList.size()];
        for (int i = NumeralUtil.POSITIVE_ZERO; i < buttonList.size(); i++) {
            delKey[i] = RedisKeyUtil.getSystemUserBlackListIpKey(buttonList.get(i).getIp());
        }
        record.setOperaterStatus(NumeralUtil.NEGATIVE_ONE);
        record.setOperaterTime(new Date());
        Long userId = getUserId();
        record.setOperaterId(userId);
        try {
            int is = this.deleteList(record);
            if (is > NumeralUtil.POSITIVE_ZERO) {
                if (delKey != null && delKey.length > NumeralUtil.POSITIVE_ZERO) {
                    this.redisUtil.deleteKey(delKey);
                }
                return r.ResponseResultSuccess();
            }
            return r.ResponseResultDeleteFail();
        } catch (Exception e) {
            e.printStackTrace();
            return r.ResponseResultError();
        }
    }

    /**
     * @param id 1 编号
     * @param ip 2 IP
     * @return com.framework.common.response.ResponseResult
     * @Titel 本类后台管理查询是否存在，只是适合本身的一些方法处理。
     * @Description boolean false不存在， true存在
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 11:14
     */
    @Override
    public ResponseResult isExist(Long id, String ip) {
        if (StringUtils.isEmpty(ip)) {
            return this.getResponseResult().ResponseResultFail().setData(Boolean.TRUE);
        }
        SystemBlackListIp param = new SystemBlackListIp();
        param.setId(id);
        param.setIp(ip);
        try {
            Boolean is = this.isExist(param) > NumeralUtil.POSITIVE_ZERO ? Boolean.FALSE : Boolean.TRUE;
            return this.getResponseResult().ResponseResultSuccess().setData(is);
        } catch (Exception e) {
            return this.getResponseResult().ResponseResultFail().setData(Boolean.FALSE).setMsg(e.getMessage());
        }
    }

    /**
     * @param id 1 编号
     * @return com.framework.model.entity.system.SystemBlackListIp
     * @Titel 本类后台管理根据ID查询数据信息
     * @Description 本类后台管理根据ID查询数据信息
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 11:16
     */
    @Override
    public SystemBlackListIp getByIdParam(Long id) {
        if (id == null || id.longValue() < NumeralUtil.MULTIPLEXING_LONG_POSITIVE_ONE) {
            return null;
        }
        SystemBlackListIp p = this.selectByPrimaryKey(id);
        return p;
    }

    /**
     * @param param 1 系统前端操作黑名单IP实体类对象
     * @return com.framework.common.response.ResponseResult
     * @Titel 本类后台管理根据条件分页查询系统前端操作黑名单IP列表
     * @Description 本类后台管理根据条件分页查询系统前端操作黑名单IP列表
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 11:20
     */
    @Override
    public ResponseResult findParamPageList(SystemBlackListIp param) {
        ResponseResult r = getResponseResult();
        if (StringUtils.isNotEmpty(param.getSort()) && StringUtils.isNotEmpty(param.getOrder())) {
            param.setSort("p." + HumpOrLineUtil.humpToLine(param.getSort()));
        } else {
            param.setSort("p.create_Time");
            param.setOrder("desc");
        }
        try {
            param.setGtaeOperaterStatus(NumeralUtil.POSITIVE_ONE);
            int count = this.findPageListCount(param);
            List<SystemBlackListIp> list = this.findPageList(param);
            return r.ResponseResultSuccess().setData(list).setCount(count);
        } catch (Exception e) {
            e.printStackTrace();
            return r.ResponseResultFail();
        }
    }

    /**
     * @param ip 1 IP
     * @return com.framework.model.entity.system.SystemBlackListIp
     * @Titel 根据IP查询是否存在
     * @Description 根据IP查询是否存在
     * @Author 邋遢龘鵺
     * @DateTime 2020/1/14 9:47
     */
    @Cacheable(value = CacheUtil.CACHE_SUFFIX + "SystemBlackListIp", keyGenerator = SpringCacheKeyGenerator.KEYGENERATOR)
    public SystemBlackListIp getIp(String ip) {
        SystemBlackListIp p = new SystemBlackListIp();
        p.setIp(ip);
        List<SystemBlackListIp> list = this.findByList(p);
        return list != null && list.size() > NumeralUtil.POSITIVE_ZERO ? list.get(NumeralUtil.POSITIVE_ZERO) : null;
    }
}
