package com.framework.service.system.impl;

import com.alibaba.fastjson.JSONObject;
import com.framework.common.response.ResponseResult;
import com.framework.common.util.other.NumeralUtil;
import com.framework.common.util.redis.RedisKeyUtil;
import com.framework.mapper.system.SystemDictMapper;
import com.framework.model.system.SystemDict;
import com.framework.service.base.BaseService;
import com.framework.service.system.SystemDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;

/**
 * @author 邋遢龘鵺
 * @version 1.0
 * @className com.framework.service.system.impl
 * @description 字段业务接口实现类
 * @datetime 2019/10/11
 */
@Service("systemDictServiceImpl")
public class SystemDictServiceImpl extends BaseService implements SystemDictService {
    @Autowired
    private SystemDictMapper systemDictMapper;

    /**
     * @param row 1 字典实体类对象
     * @return int 大于等于1成功， 0失败
     * @titel 公共根据条件逻辑删除数据
     * @description 公共根据条件逻辑删除数据
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 11:04
     */
    @Override
    public int deleteList(SystemDict row) {
        return systemDictMapper.deleteList(row);
    }

    /**
     * @param row 1 字典实体类对象
     * @return long 返回主键ID大于0成功，小于等于0失败
     * @titel 公共非空字段验证添加
     * @description 公共非空字段验证添加
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 9:31
     */
    @Override
    public int insertSelective(SystemDict row) {
        return systemDictMapper.insertSelective(row);
    }

    /**
     * @param row 1 字典实体类对象
     * @return int 0失败，1成功
     * @titel 公共根据编号非空字段验证修改
     * @description 公共根据编号非空字段验证修改
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 9:35
     */
    @Override
    public int updateByPrimaryKeySelective(SystemDict row) {
        return systemDictMapper.updateByPrimaryKeySelective(row);
    }

    /**
     * @param id 1 编号
     * @return com.framework.model.system.SystemDict
     * @titel 公共根据编号查询数据
     * @description 公共根据编号查询数据
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 9:33
     */
    @Override
    public SystemDict selectByPrimaryKey(Long id) {
        return systemDictMapper.selectByPrimaryKey(id);
    }

    /**
     * @param row 1 字典实体类对象
     * @return int 0不存在， 大于等于1存在
     * @titel 公共根据条件查询是否重复存在
     * @description 公共根据条件查询是否重复存在
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 9:34
     */
    @Override
    public int isExist(SystemDict row) {
        return systemDictMapper.isExist(row);
    }


    /**
     * @param row 1 字典实体类对象
     * @return java.util.List<com.framework.model.system.SystemDict>
     * @titel 公共根据条件分页查询
     * @description 公共根据条件分页查询
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 9:41
     */
    @Override
    public List<SystemDict> findPageList(SystemDict row) {
        return systemDictMapper.findPageList(row);
    }

    /**
     * @param row 1 字典实体类对象
     * @return int 返回分页总数
     * @titel 公共根据条件分页查询总数
     * @description 公共根据条件分页查询总数
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 9:41
     */
    @Override
    public int findPageListCount(SystemDict row) {
        return systemDictMapper.findPageListCount(row);
    }

    /**
     * @param row 1 字典实体类对象
     * @return java.util.List<com.framework.model.system.SystemDict>
     * @titel 公共根据条件查询集合
     * @description 公共根据条件查询集合
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 11:01
     */
    @Override
    public List<SystemDict> findByList(SystemDict row) {
        return systemDictMapper.findByList(row);
    }

    /**
     * @param row 1 字典实体类对象
     * @return java.util.List<com.framework.model.system.SystemDict>
     * @titel 根据条件查询初始化集合
     * @description 根据条件查询初始化集合
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 11:01
     */
    @Override
    public List<SystemDict> findByInitList(SystemDict row) {
        return systemDictMapper.findByInitList(row);
    }

    /**
     * @param row 1 字典实体类对象
     * @return com.framework.common.response.ResponseResult
     * @titel 本类后台管理添加方法
     * @description 本类后台管理添加方法
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 11:13
     */
    @Override
//    @TargetDataSource(dataSource = DataSourceUtil.WRITE_DATA_SOURCE)
    public ResponseResult save(SystemDict row) {
        ResponseResult r = getResponseResult();
        int num = systemDictMapper.isExist(row);
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
        systemDictMapper.insertSelective(row);
        //ceShiProduction.send(JSONObject.toJSONString(row));
        super.redisUtil.setString(RedisKeyUtil.getPermissionDictKey(row.getDictKey()), JSONObject.toJSONString(row));
        return r.success();
    }

    /**
     * @param row 1 字典实体类对象
     * @return com.framework.common.response.ResponseResult
     * @titel 本类后台管理修改方法
     * @description 本类后台管理修改方法
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 11:13
     */
    @Override
    public ResponseResult edit(SystemDict row) {
        ResponseResult r = getResponseResult();
        int num = systemDictMapper.isExist(row);
        if (num > NumeralUtil.POSITIVE_ZERO) {
            return r.failRepeat();
        }
        SystemDict sb = systemDictMapper.selectByPrimaryKey(row.getId());
        if (sb == null) {
            return r.failRepeat().setMsg("修改字典信息不存在!");
        }
        Date date = new Date();
        Long userId = getUserId();
        row.setOperaterStatus(NumeralUtil.POSITIVE_TWO);
        row.setOperaterId(userId);
        row.setOperaterTime(date);
        systemDictMapper.updateByPrimaryKeySelective(row);
        //判断字典键是否修改
        if (!sb.getDictKey().equals(row.getDictKey())) {
            //刷新字典键结束
            super.redisUtil.deleteKey(RedisKeyUtil.getPermissionDictKey(sb.getDictKey()));
        }
        super.redisUtil.setString(RedisKeyUtil.getPermissionDictKey(row.getDictKey()), JSONObject.toJSONString(row));
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
    public ResponseResult batchDeleteList(List<Long> idList) {
        ResponseResult r = getResponseResult();
        SystemDict row = new SystemDict();
        row.setIdList(idList);
        List<SystemDict> buttonList = systemDictMapper.findByList(row);
        if (buttonList == null || buttonList.size() == NumeralUtil.POSITIVE_ZERO) {
            return r.fail().setMsg("请选择操作信息");
        }
        String[] delKey = new String[buttonList.size()];
        for (int i = NumeralUtil.POSITIVE_ZERO; i < buttonList.size(); i++) {
            delKey[i] = RedisKeyUtil.getPermissionDictKey(buttonList.get(i).getDictKey());
        }
        row.setOperaterStatus(NumeralUtil.NEGATIVE_ONE);
        row.setOperaterTime(new Date());
        Long userId = getUserId();
        row.setOperaterId(userId);
        systemDictMapper.deleteList(row);
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
        SystemDict row = new SystemDict();
        row.setOperaterStatus(NumeralUtil.NEGATIVE_ONE);
        row.setOperaterTime(new Date());
        Long userId = getUserId();
        row.setOperaterId(userId);
        row.setId(id);
        systemDictMapper.delete(row);
        return r.success();
    }

    /**
     * @param id      1 编号
     * @param dictKey 2 字典键
     * @return com.framework.common.response.ResponseResult
     * @titel 本类后台管理查询是否存在，只是适合本身的一些方法处理。
     * @description boolean false不存在， true存在
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 11:14
     */
    @Override
    public ResponseResult isExist(Long id, String dictKey) {
        SystemDict param = new SystemDict();
        param.setId(id);
        param.setDictKey(dictKey);
        Boolean is = systemDictMapper.isExist(param) > NumeralUtil.POSITIVE_ZERO ? Boolean.FALSE : Boolean.TRUE;
        return super.getResponseResult().success().setData(is);
    }

    /**
     * @param id 1 编号
     * @return com.framework.model.system.SystemDict
     * @titel 本类后台管理根据ID查询数据信息
     * @description 本类后台管理根据ID查询数据信息
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 11:16
     */
    @Override
    public SystemDict getByIdParam(Long id) {
        SystemDict p = systemDictMapper.selectByPrimaryKey(id);
        return p;
    }

    /**
     * @param param 1 字典实体类对象
     * @return com.framework.common.response.ResponseResult
     * @titel 本类后台管理根据条件分页查询字典列表
     * @description 本类后台管理根据条件分页查询字典列表
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 11:20
     */
    @Override
    public ResponseResult findParamPageList(SystemDict param) {
        ResponseResult r = getResponseResult();
        param.setGtaeOperaterStatus(NumeralUtil.POSITIVE_ONE);
        int count = systemDictMapper.findPageListCount(param);
        List<SystemDict> list = systemDictMapper.findPageList(param);
        return r.success().setData(list).setCount(count);
    }

    /**
     * @param dictKey 1
     * @return com.framework.model.system.SystemDict
     * @titel 根据字典键查询字典信息
     * @description 根据字典键查询字典信息
     * @author 邋遢龘鵺
     * @datetime 2020/4/13 12:29
     */
    public SystemDict getDictKey(String dictKey) {
        SystemDict param = new SystemDict();
        param.setDictKey(dictKey);
        List<SystemDict> list = systemDictMapper.findByList(param);
        return (list != null && list.size() > NumeralUtil.POSITIVE_ZERO) ? list.get(NumeralUtil.POSITIVE_ZERO) : null;
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
        SystemDict param = systemDictMapper.selectByPrimaryKey(id);
        return super.getResponseResult().success().setData(param);
    }
}
