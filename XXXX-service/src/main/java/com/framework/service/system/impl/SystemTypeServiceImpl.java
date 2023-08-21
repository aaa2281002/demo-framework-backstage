package com.framework.service.system.impl;

import com.framework.common.response.ResponseResult;
import com.framework.common.util.other.NumeralUtil;
import com.framework.common.util.redis.RedisKeyUtil;
import com.framework.mapper.system.SystemTypeMapper;
import com.framework.model.system.SystemType;
import com.framework.model.system.vo.SystemTypeVo;
import com.framework.service.base.BaseService;
import com.framework.service.system.SystemTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author 邋遢龘鵺
 * @version 1.0
 * @className com.framework.service.system.impl
 * @description 类型业务接口实现类
 * @datetime 2019/10/11
 */
@Service("systemTypeServiceImpl")
public class SystemTypeServiceImpl extends BaseService implements SystemTypeService {
    @Autowired
    private SystemTypeMapper systemTypeMapper;

    /**
     * @param row 1 类型实体类对象
     * @return int 大于等于1成功， 0失败
     * @titel 公共根据条件逻辑删除数据
     * @description 公共根据条件逻辑删除数据
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 11:04
     */
    @Override
    public int deleteList(SystemType row) {
        return systemTypeMapper.deleteList(row);
    }

    /**
     * @param row 1 类型实体类对象
     * @return long 返回主键ID大于0成功，小于等于0失败
     * @titel 公共非空字段验证添加
     * @description 公共非空字段验证添加
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 9:31
     */
    @Override
    public int insertSelective(SystemType row) {
        return systemTypeMapper.insertSelective(row);
    }

    /**
     * @param row 1 类型实体类对象
     * @return int 0失败，1成功
     * @titel 公共根据编号非空字段验证修改
     * @description 公共根据编号非空字段验证修改
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 9:35
     */
    @Override
    public int updateByPrimaryKeySelective(SystemType row) {
        return systemTypeMapper.updateByPrimaryKeySelective(row);
    }

    /**
     * @param id 1 编号
     * @return com.framework.model.system.SystemType
     * @titel 公共根据编号查询数据
     * @description 公共根据编号查询数据
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 9:33
     */
    @Override
    public SystemType selectByPrimaryKey(Long id) {
        return systemTypeMapper.selectByPrimaryKey(id);
    }

    /**
     * @param row 1 类型实体类对象
     * @return int 0不存在， 大于等于1存在
     * @titel 公共根据条件查询是否重复存在
     * @description 公共根据条件查询是否重复存在
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 9:34
     */
    @Override
    public int isExist(SystemType row) {
        return systemTypeMapper.isExist(row);
    }

    /**
     * @param row 1 类型实体类对象
     * @return java.util.List<com.framework.model.system.SystemType>
     * @titel 公共根据条件分页查询
     * @description 公共根据条件分页查询
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 9:41
     */
    @Override
    public List<SystemType> findPageList(SystemType row) {
        return systemTypeMapper.findPageList(row);
    }

    /**
     * @param row 1 类型实体类对象
     * @return int 返回分页总数
     * @titel 公共根据条件分页查询总数
     * @description 公共根据条件分页查询总数
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 9:41
     */
    @Override
    public int findPageListCount(SystemType row) {
        return systemTypeMapper.findPageListCount(row);
    }

    /**
     * @param row 1 类型实体类对象
     * @return java.util.List<com.framework.model.system.SystemType>
     * @titel 公共根据条件查询集合
     * @description 公共根据条件查询集合
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 11:01
     */
    @Override
    public List<SystemType> findByList(SystemType row) {
        return systemTypeMapper.findByList(row);
    }

    /**
     * @param code 1 类型实体类对象
     * @return java.util.List<com.framework.model.system.SystemType>
     * @titel 根据父类代码查询集合
     * @description 根据父类代码查询集合
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 11:01
     */
    @Override
    public List<SystemType> findByCodeList(String code) {
        SystemTypeVo row = new SystemTypeVo();
        row.setParentTypeCode(code);
        row.setGtaeOperaterStatus(NumeralUtil.POSITIVE_ONE);
        List<SystemType> list = systemTypeMapper.findByCodeList(row);
        return list;
    }

    /**
     * @param row 1 类型实体类对象
     * @return com.framework.common.response.ResponseResult
     * @titel 本类后台管理添加方法
     * @description 本类后台管理添加方法
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 11:13
     */
    @Override
//    @TargetDataSource(dataSource = DataSourceUtil.WRITE_DATA_SOURCE)
    public ResponseResult save(SystemType row) {
        ResponseResult r = getResponseResult();
        SystemType param = new SystemType();
        param.setTypeCode(row.getTypeCode());
        param.setParentId(row.getParentId());
        int num = systemTypeMapper.isExist(param);
        if (num > NumeralUtil.POSITIVE_ZERO) {
            return r.fail().setMsg("类型代码已存在!");
        }
        if (row.getParentId() != null && row.getParentId().longValue() > NumeralUtil.POSITIVE_ZERO) {
            param = new SystemType();
            param.setTypeValue(row.getTypeValue());
            param.setParentId(row.getParentId());
            num = systemTypeMapper.isExist(param);
            if (num > NumeralUtil.POSITIVE_ZERO) {
                return r.fail().setMsg("类型数值已存在!");
            }
        }

        Long userId = getUserId();
        Date date = new Date();
        row.setOperaterTime(date);
        row.setCreateId(userId);
        row.setOperaterId(userId);
        row.setCreateTime(date);
        row.setOperaterStatus(NumeralUtil.POSITIVE_ONE);
        systemTypeMapper.insertSelective(row);
        //ceShiProduction.send(JSONObject.toJSONString(row));
//                super.redisUtil.setString(row.getTypeCode(), row);
        return r.success();
    }

    /**
     * @param row 1 类型实体类对象
     * @return com.framework.common.response.ResponseResult
     * @titel 本类后台管理修改方法
     * @description 本类后台管理修改方法
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 11:13
     */
    @Override
    public ResponseResult edit(SystemType row) {
        ResponseResult r = getResponseResult();
        SystemType sb = systemTypeMapper.selectByPrimaryKey(row.getId());
        if (sb == null) {
            return r.fail().setMsg("修改类型信息不存在!");
        }
        SystemType param = new SystemType();
        param.setId(row.getId());
        param.setTypeCode(row.getTypeCode());
        param.setParentId(row.getParentId());
        int num = systemTypeMapper.isExist(param);
        if (num > NumeralUtil.POSITIVE_ZERO) {
            return r.fail().setMsg("类型代码已存在!");
        }
        if (row.getParentId() != null && row.getParentId().longValue() > NumeralUtil.POSITIVE_ZERO) {
            param = new SystemType();
            param.setId(row.getId());
            param.setTypeValue(row.getTypeValue());
            param.setParentId(row.getParentId());
            num = systemTypeMapper.isExist(param);
            if (num > NumeralUtil.POSITIVE_ZERO) {
                return r.fail().setMsg("类型数值已存在!");
            }
        }
        Date date = new Date();
        Long userId = getUserId();
        row.setOperaterStatus(NumeralUtil.POSITIVE_TWO);
        row.setOperaterId(userId);
        row.setOperaterTime(date);
        systemTypeMapper.updateByPrimaryKeySelective(row);
//                //判断类型代码是否修改
//                if (!sb.getTypeCode().equals(row.getTypeCode())) {
//                    //刷新用户的roleCode角色代码 结束
//                    super.redisUtil.deleteKey(RedisKeyUtil.getPermissionButtonKey(sb.getTypeCode()));
//                }
//                super.redisUtil.setString(row.getTypeCode(), row);
        return r.success();
    }

    /**
     * @param idList   1 编号集合
     * @param parentId 2 上级编号
     * @return com.framework.common.response.ResponseResult
     * @titel 本类后台管理批量逻辑删除
     * @description 本类后台管理批量逻辑删除
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 11:14
     */
    @Override
    public ResponseResult batchDeleteList(List<Long> idList, Long parentId) {
        ResponseResult r = getResponseResult();
        SystemType row = new SystemType();
        row.setIdList(idList);
        row.setParentId(parentId);
        List<SystemType> list = systemTypeMapper.findByList(row);
        String[] delKey = new String[list.size()];
        for (int i = NumeralUtil.POSITIVE_ZERO; i < list.size(); i++) {
            delKey[i] = RedisKeyUtil.getPermissionButtonKey(list.get(i).getTypeCode());
        }
        row.setOperaterStatus(NumeralUtil.NEGATIVE_ONE);
        row.setOperaterTime(new Date());
        Long userId = getUserId();
        row.setOperaterId(userId);
        systemTypeMapper.deleteList(row);
//                if (delKey != null && delKey.length > NumeralUtil.POSITIVE_ZERO) {
//                    redisUtil.deleteKey(delKey);
//                }
        return r.success();
    }

    /**
     * @param id       1 编号
     * @param parentId 2 上级编号
     * @return com.framework.common.response.ResponseResult
     * @titel 本类后台管理根据编号逻辑删除
     * @description 本类后台管理根据编号逻辑删除
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 11:14
     */
    @Override
    public ResponseResult delId(Long id, Long parentId) {
        ResponseResult r = getResponseResult();
        SystemType row = new SystemType();
        row.setOperaterStatus(NumeralUtil.NEGATIVE_ONE);
        row.setOperaterTime(new Date());
        row.setParentId(parentId);
        Long userId = getUserId();
        row.setOperaterId(userId);
        row.setId(id);
        systemTypeMapper.delete(row);
        return r.success();
    }

    /**
     * @param id       1 编号
     * @param typeCode 2 类型代码
     * @return com.framework.common.response.ResponseResult
     * @titel 本类后台管理查询是否存在，只是适合本身的一些方法处理。
     * @description boolean false不存在， true存在
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 11:14
     */
    @Override
    public ResponseResult isExist(Long id, String typeCode) {
        SystemType param = new SystemType();
        param.setId(id);
        param.setTypeCode(typeCode);
        Boolean is = systemTypeMapper.isExist(param) > NumeralUtil.POSITIVE_ZERO ? Boolean.FALSE : Boolean.TRUE;
        return super.getResponseResult().success().setData(is);
    }

    /**
     * @param id 1 编号
     * @return com.framework.model.system.SystemType
     * @titel 本类后台管理根据ID查询数据信息
     * @description 本类后台管理根据ID查询数据信息
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 11:16
     */
    @Override
    public SystemType getByIdParam(Long id) {
        SystemType p = systemTypeMapper.selectByPrimaryKey(id);
        return p;
    }

    /**
     * @param id       1 编号
     * @param parentId 2 上级编号
     * @return com.framework.model.system.SystemType
     * @titel 查询类型信息
     * @description 查询类型信息
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 11:16
     */
    @Override
    public SystemType getByIdParam(Long id, Long parentId) {
        SystemType row = new SystemType();
        row.setParentId(parentId);
        row.setId(id);
        row.setGtaeOperaterStatus(NumeralUtil.POSITIVE_ONE);
        return systemTypeMapper.selectByParam(row);
    }

    /**
     * @param param 1 类型实体类对象
     * @return com.framework.common.response.ResponseResult
     * @titel 本类后台管理根据条件分页查询类型列表
     * @description 本类后台管理根据条件分页查询类型列表
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 11:20
     */
    @Override
    public ResponseResult findParamPageListParent(SystemType param) {
        param.setParentId(NumeralUtil.MULTIPLEXING_LONG_POSITIVE_ZERO);
        return findParamPageList(param);
    }

    /**
     * @param param 1 类型实体类对象
     * @return com.framework.common.response.ResponseResult
     * @titel 本类后台管理根据条件分页查询类型列表
     * @description 本类后台管理根据条件分页查询类型列表
     * @author 邋遢龘鵺
     * @datetime 2019/12/26 11:20
     */
    @Override
    public ResponseResult findParamPageList(SystemType param) {
        ResponseResult r = getResponseResult();
        param.setGtaeOperaterStatus(NumeralUtil.POSITIVE_ONE);
        int count = systemTypeMapper.findPageListCount(param);
        List<SystemType> list = systemTypeMapper.findPageList(param);
        return r.success().setData(list).setCount(count);
    }
}
