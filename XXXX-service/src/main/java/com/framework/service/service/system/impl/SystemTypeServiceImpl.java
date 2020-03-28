package com.framework.service.service.system.impl;

import com.framework.common.response.ResponseResult;
import com.framework.common.util.hump.HumpOrLineUtil;
import com.framework.common.util.other.NumeralUtil;
import com.framework.common.util.redis.RedisKeyUtil;
import com.framework.dao.mapper.system.SystemTypeMapper;
import com.framework.model.entity.system.SystemType;
import com.framework.service.base.BaseService;
import com.framework.service.service.system.SystemTypeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Author 邋遢龘鵺
 * @ClassName com.framework.service.service.system.impl
 * @Description 类型业务接口实现类
 * @DateTime 2019/10/11
 * @Version 1.0
 */
@Service
public class SystemTypeServiceImpl extends BaseService implements SystemTypeService {
    @Autowired
    private SystemTypeMapper systemTypeMapper;

    /**
     * @param record 1 类型实体类对象
     * @return int 大于等于1成功， 0失败
     * @Titel 公共根据条件逻辑删除数据
     * @Description 公共根据条件逻辑删除数据
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 11:04
     */
    @Override
    public int deleteList(SystemType record) {
        return systemTypeMapper.deleteList(record);
    }

    /**
     * @param record 1 类型实体类对象
     * @return long 返回主键ID大于0成功，小于等于0失败
     * @Titel 公共添加
     * @Description 公共添加
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 9:31
     */
    @Override
    public long insert(SystemType record) {
        return systemTypeMapper.insert(record);
    }

    /**
     * @param record 1 类型实体类对象
     * @return long 返回主键ID大于0成功，小于等于0失败
     * @Titel 公共非空字段验证添加
     * @Description 公共非空字段验证添加
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 9:31
     */
    @Override
    public long insertSelective(SystemType record) {
        return systemTypeMapper.insertSelective(record);
    }

    /**
     * @param record 1 类型实体类对象
     * @return int 0失败，1成功
     * @Titel 公共根据编号修改
     * @Description 公共根据编号修改
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 9:35
     */
    @Override
    public int updateByPrimaryKey(SystemType record) {
        return systemTypeMapper.updateByPrimaryKey(record);
    }

    /**
     * @param record 1 类型实体类对象
     * @return int 0失败，1成功
     * @Titel 公共根据编号非空字段验证修改
     * @Description 公共根据编号非空字段验证修改
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 9:35
     */
    @Override
    public int updateByPrimaryKeySelective(SystemType record) {
        return systemTypeMapper.updateByPrimaryKeySelective(record);
    }

    /**
     * @param id 1 编号
     * @return com.framework.model.entity.system.SystemType
     * @Titel 公共根据编号查询数据
     * @Description 公共根据编号查询数据
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 9:33
     */
    @Override
    public SystemType selectByPrimaryKey(Long id) {
        return systemTypeMapper.selectByPrimaryKey(id);
    }

    /**
     * @param record 1 类型实体类对象
     * @return int 0不存在， 大于等于1存在
     * @Titel 公共根据条件查询是否重复存在
     * @Description 公共根据条件查询是否重复存在
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 9:34
     */
    @Override
    public int isExist(SystemType record) {
        return systemTypeMapper.isExist(record);
    }

    /**
     * @param record 1 类型实体类对象
     * @return java.util.List<com.framework.model.entity.system.SystemType>
     * @Titel 公共根据条件分页查询
     * @Description 公共根据条件分页查询
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 9:41
     */
    @Override
    public List<SystemType> findPageList(SystemType record) {
        return systemTypeMapper.findPageList(record);
    }

    /**
     * @param record 1 类型实体类对象
     * @return int 返回分页总数
     * @Titel 公共根据条件分页查询总数
     * @Description 公共根据条件分页查询总数
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 9:41
     */
    @Override
    public int findPageListCount(SystemType record) {
        return systemTypeMapper.findPageListCount(record);
    }

    /**
     * @param record 1 类型实体类对象
     * @return java.util.List<com.framework.model.entity.system.SystemType>
     * @Titel 公共根据条件查询集合
     * @Description 公共根据条件查询集合
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 11:01
     */
    @Override
    public List<SystemType> findByList(SystemType record) {
        return systemTypeMapper.findByList(record);
    }

    /**
     * @param record 1 类型实体类对象
     * @return com.framework.common.response.ResponseResult
     * @Titel 本类后台管理添加方法
     * @Description 本类后台管理添加方法
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 11:13
     */
    @Override
//    @TargetDataSource(dataSource = DataSourceUtil.WRITE_DATA_SOURCE)
    public ResponseResult save(SystemType record) {
        ResponseResult r = getResponseResult();
        if (record == null) {
            return r.ResponseResultFail();
        }
//        int num = this.isExist(record);
//        if (num > NumeralUtil.POSITIVE_ZERO) {
//            return r.ResponseResultFailRepeat();
//        }
        Long userId = getUserId();
        Date date = new Date();
        record.setOperaterTime(date);
        record.setCreateId(userId);
        record.setOperaterId(userId);
        record.setCreateTime(date);
        record.setOperaterStatus(NumeralUtil.POSITIVE_ONE);
        try {
            long is = this.insert(record);
            if (is > NumeralUtil.MULTIPLEXING_LONG_POSITIVE_ZERO) {
                //ceShiProduction.send(JSONObject.toJSONString(record));
//                super.redisUtil.setString(record.getTypeCode(), record);
                return r.ResponseResultSuccess();
            }
            return r.ResponseResultAddFail();
        } catch (Exception e) {
            e.printStackTrace();
            return r.ResponseResultError();
        }
    }

    /**
     * @param record 1 类型实体类对象
     * @return com.framework.common.response.ResponseResult
     * @Titel 本类后台管理修改方法
     * @Description 本类后台管理修改方法
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 11:13
     */
    @Override
    public ResponseResult edit(SystemType record) {
        ResponseResult r = getResponseResult();
        if (record == null || record.getId() == null || record.getId().longValue() < NumeralUtil.MULTIPLEXING_LONG_POSITIVE_ONE) {
            return r.ResponseResultFail();
        }
//        int num = this.isExist(record);
//        if (num > NumeralUtil.POSITIVE_ZERO) {
//            return r.ResponseResultFailRepeat();
//        }
        SystemType sb = this.selectByPrimaryKey(record.getId());
        if(sb == null){
            return r.ResponseResultFailRepeat().setMsg("修改类型信息不存在!");
        }
        Date date = new Date();
        Long userId = getUserId();
        record.setOperaterStatus(NumeralUtil.POSITIVE_TWO);
        record.setOperaterId(userId);
        record.setOperaterTime(date);
        try {
            int is = this.updateByPrimaryKeySelective(record);
            if (is > NumeralUtil.POSITIVE_ZERO) {
//                //判断类型代码是否修改
//                if (!sb.getTypeCode().equals(record.getTypeCode())) {
//                    //刷新用户的roleCode角色代码 结束
//                    super.redisUtil.deleteKey(RedisKeyUtil.getPermissionButtonKey(sb.getTypeCode()));
//                }
//                super.redisUtil.setString(record.getTypeCode(), record);
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
    public ResponseResult batchDeleteList(List<Long> idList) {
        ResponseResult r = getResponseResult();
        if (idList == null || idList.size() < NumeralUtil.POSITIVE_ONE) {
            return r.ResponseResultFail();
        }
        SystemType record = new SystemType();
        record.setIdList(idList);
        List<SystemType> buttonList = this.findByList(record);
        String[] delKey = new String[buttonList.size()];
        for (int i = NumeralUtil.POSITIVE_ZERO; i < buttonList.size(); i++) {
            delKey[i] = RedisKeyUtil.getPermissionButtonKey(buttonList.get(i).getTypeCode());
        }
        record.setOperaterStatus(NumeralUtil.NEGATIVE_ONE);
        record.setOperaterTime(new Date());
        Long userId = getUserId();
        record.setOperaterId(userId);
        try {
            int is = this.deleteList(record);
            if (is > NumeralUtil.POSITIVE_ZERO) {
//                if (delKey != null && delKey.length > NumeralUtil.POSITIVE_ZERO) {
//                    this.redisUtil.deleteKey(delKey);
//                }
                return r.ResponseResultSuccess();
            }
            return r.ResponseResultDeleteFail();
        } catch (Exception e) {
            e.printStackTrace();
            return r.ResponseResultError();
        }
    }

    /**
     * @param id       1 编号
     * @param typeCode 2 类型代码
     * @return com.framework.common.response.ResponseResult
     * @Titel 本类后台管理查询是否存在，只是适合本身的一些方法处理。
     * @Description boolean false不存在， true存在
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 11:14
     */
    @Override
    public ResponseResult isExist(Long id, String typeCode) {
        if (StringUtils.isEmpty(typeCode)) {
            return this.getResponseResult().ResponseResultFail().setData(Boolean.TRUE);
        }
        SystemType param = new SystemType();
        param.setId(id);
        param.setTypeCode(typeCode);
        try {
            Boolean is = this.isExist(param) > NumeralUtil.POSITIVE_ZERO ? Boolean.FALSE : Boolean.TRUE;
            return this.getResponseResult().ResponseResultSuccess().setData(is);
        } catch (Exception e) {
            e.printStackTrace();
            return this.getResponseResult().ResponseResultFail().setData(Boolean.FALSE).setMsg(e.getMessage());
        }
    }

    /**
     * @param id 1 编号
     * @return com.framework.model.entity.system.SystemType
     * @Titel 本类后台管理根据ID查询数据信息
     * @Description 本类后台管理根据ID查询数据信息
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 11:16
     */
    @Override
    public SystemType getByIdParam(Long id) {
        if (id == null || id.longValue() < NumeralUtil.MULTIPLEXING_LONG_POSITIVE_ONE) {
            return null;
        }
        SystemType sm = this.selectByPrimaryKey(id);
        return sm;
    }

    /**
     * @param param 1 类型实体类对象
     * @return com.framework.common.response.ResponseResult
     * @Titel 本类后台管理根据条件分页查询类型列表
     * @Description 本类后台管理根据条件分页查询类型列表
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 11:20
     */
    @Override
    public ResponseResult findParamPageList(SystemType param) {
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
            List<SystemType> list = this.findPageList(param);
            return r.ResponseResultSuccess().setData(list).setCount(count);
        } catch (Exception e) {
            e.printStackTrace();
            return r.ResponseResultFail();
        }
    }
}
