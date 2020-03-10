package com.framework.service.service.system.impl;

import com.framework.common.response.ResponseResult;
import com.framework.common.util.hump.HumpOrLineUtil;
import com.framework.common.util.other.NumeralUtil;
import com.framework.common.util.other.SymbolUtil;
import com.framework.common.util.redis.RedisKeyUtil;
import com.framework.dao.mapper.system.SystemButtonMapper;
import com.framework.model.entity.system.SystemButton;
import com.framework.model.entity.system.SystemMenu;
import com.framework.model.entity.system.SystemRoleMenuButton;
import com.framework.service.activemq.production.CeShiProduction;
import com.framework.service.base.BaseService;
import com.framework.service.service.system.SystemButtonService;
import com.framework.service.service.system.SystemRoleMenuButtonService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Author 邋遢龘鵺
 * @ClassName com.framework.service.service.system.impl
 * @Description 按钮业务接口实现类
 * @DateTime 2019/10/11
 * @Version 1.0
 */
@Service("systemButtonServiceImpl")
public class SystemButtonServiceImpl extends BaseService implements SystemButtonService {
    @Autowired
    private SystemButtonMapper systemButtonMapper;
    @Autowired
    private SystemRoleMenuButtonService systemRoleMenuButtonServiceImpl;
    @Autowired
    private CeShiProduction ceShiProduction;

    /**
     * @param record 1 按钮实体类对象
     * @return int 大于等于1成功， 0失败
     * @Titel 公共根据条件逻辑删除数据
     * @Description 公共根据条件逻辑删除数据
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 11:04
     */
    @Override
    public int deleteList(SystemButton record) {
        return systemButtonMapper.deleteList(record);
    }

    /**
     * @param record 1 按钮实体类对象
     * @return long 返回主键ID大于0成功，小于等于0失败
     * @Titel 公共添加
     * @Description 公共添加
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 9:31
     */
    @Override
    public long insert(SystemButton record) {
        return systemButtonMapper.insert(record);
    }

    /**
     * @param record 1 按钮实体类对象
     * @return long 返回主键ID大于0成功，小于等于0失败
     * @Titel 公共非空字段验证添加
     * @Description 公共非空字段验证添加
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 9:31
     */
    @Override
    public long insertSelective(SystemButton record) {
        return systemButtonMapper.insertSelective(record);
    }

    /**
     * @param list 1 按钮批量添加集合
     * @return int 返回成功数量
     * @Titel 公共批量添加
     * @Description 公共批量添加
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 14:12
     */
    @Override
    public int insertList(List<SystemButton> list) {
        return systemButtonMapper.insertList(list);
    }

    /**
     * @param record 1 按钮实体类对象
     * @return int 0失败，1成功
     * @Titel 公共根据编号修改
     * @Description 公共根据编号修改
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 9:35
     */
    @Override
    public int updateByPrimaryKey(SystemButton record) {
        return systemButtonMapper.updateByPrimaryKey(record);
    }

    /**
     * @param record 1 按钮实体类对象
     * @return int 0失败，1成功
     * @Titel 公共根据编号非空字段验证修改
     * @Description 公共根据编号非空字段验证修改
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 9:35
     */
    @Override
    public int updateByPrimaryKeySelective(SystemButton record) {
        return systemButtonMapper.updateByPrimaryKeySelective(record);
    }

    /**
     * @param list 1 按钮批量修改集合
     * @return int 返回成功数量
     * @Titel 公共批量根据非空验证编号修改
     * @Description 公共批量根据非空验证编号修改
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 13:59
     */
    @Override
    public int updateList(List<SystemButton> list) {
        return systemButtonMapper.updateList(list);
    }

    /**
     * @param id 1 编号
     * @return com.framework.model.entity.system.SystemButton
     * @Titel 公共根据编号查询数据
     * @Description 公共根据编号查询数据
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 9:33
     */
    @Override
    public SystemButton selectByPrimaryKey(Long id) {
        return systemButtonMapper.selectByPrimaryKey(id);
    }

    /**
     * @param record 1 按钮实体类对象
     * @return int 0不存在， 大于等于1存在
     * @Titel 公共根据条件查询是否重复存在
     * @Description 公共根据条件查询是否重复存在
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 9:34
     */
    @Override
    public int isExist(SystemButton record) {
        return systemButtonMapper.isExist(record);
    }


    /**
     * @param record 1 按钮实体类对象
     * @return java.util.List<com.framework.model.entity.system.SystemButton>
     * @Titel 公共根据条件分页查询
     * @Description 公共根据条件分页查询
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 9:41
     */
    @Override
    public List<SystemButton> findPageList(SystemButton record) {
        return systemButtonMapper.findPageList(record);
    }

    /**
     * @param record 1 按钮实体类对象
     * @return int 返回分页总数
     * @Titel 公共根据条件分页查询总数
     * @Description 公共根据条件分页查询总数
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 9:41
     */
    @Override
    public int findPageListCount(SystemButton record) {
        return systemButtonMapper.findPageListCount(record);
    }

    /**
     * @param record 1 按钮实体类对象
     * @return java.util.List<com.framework.model.entity.system.SystemButton>
     * @Titel 公共根据条件查询集合
     * @Description 公共根据条件查询集合
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 11:01
     */
    @Override
    public List<SystemButton> findByList(SystemButton record) {
        return systemButtonMapper.findByList(record);
    }

    /**
     * @param record 1 按钮实体类对象
     * @return com.framework.common.response.ResponseResult
     * @Titel 本类后台管理添加方法
     * @Description 本类后台管理添加方法
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 11:13
     */
    @Override
//    @Transactional
//    @TargetDataSource(dataSource = DataSourceUtil.WRITE_DATA_SOURCE)
    public ResponseResult save(SystemButton record) {
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
            long is = this.insert(record);
            if (is > NumeralUtil.MULTIPLEXING_LONG_POSITIVE_ZERO) {
                //ceShiProduction.send(JSONObject.toJSONString(record));
                super.redisUtil.setAuthButtonString(record.getButtonCode(), record);
                return r.ResponseResultSuccess();
            }
            return r.ResponseResultAddFail();
        } catch (Exception e) {
            e.printStackTrace();
            return r.ResponseResultError();
        }
    }

    /**
     * @param record 1 按钮实体类对象
     * @return com.framework.common.response.ResponseResult
     * @Titel 本类后台管理修改方法
     * @Description 本类后台管理修改方法
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 11:13
     */
    @Override
    public ResponseResult edit(SystemButton record) {
        ResponseResult r = getResponseResult();
        if (record == null || record.getId() == null) {
            return r.ResponseResultFail();
        }
        int num = this.isExist(record);
        if (num > NumeralUtil.POSITIVE_ZERO) {
            return r.ResponseResultFailRepeat();
        }
        SystemButton sb = this.selectByPrimaryKey(record.getId());
        Date date = new Date();
        Long userId = getUserId();
        record.setOperaterStatus(NumeralUtil.POSITIVE_TWO);
        record.setOperaterId(userId);
        record.setOperaterTime(date);
        try {
            int is = this.updateByPrimaryKeySelective(record);
            if (is > NumeralUtil.POSITIVE_ZERO) {
                //判断按钮代码是否修改
                if (!sb.getButtonCode().equals(record.getButtonCode())) {
                    //获取按钮关联角色和菜单代码集合
                    List<SystemRoleMenuButton> rmList = systemRoleMenuButtonServiceImpl.findByButtonIdListRoleCodeAndMenuCode(sb.getId());
                    for (SystemRoleMenuButton smb : rmList) {
                        String roleMenuCode = smb.getRoleCode() + SymbolUtil.NO_INPUT_METHOD_COLON + smb.getMenuCode();
                        Object obj = super.redisUtil.getAuthMenuString(roleMenuCode);
                        if (obj != null) {
                            SystemMenu redisSM = (SystemMenu) obj;
                            boolean isSM = redisSM.getButtonCodeList() != null
                                    && redisSM.getButtonCodeList().size() > NumeralUtil.POSITIVE_ZERO
                                    && redisSM.getButtonCodeList().remove(sb.getButtonCode());
                            if (isSM) {
                                redisSM.getButtonCodeList().add(record.getButtonCode());
                                super.redisUtil.setAuthMenuString(roleMenuCode, redisSM);
                            }
                        }
                    }
                    //刷新用户的roleCode角色代码 结束
                    super.redisUtil.deleteKey(RedisKeyUtil.getPermissionButtonKey(sb.getButtonCode()));
                }
                super.redisUtil.setAuthButtonString(record.getButtonCode(), record);
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
        //验证按钮关联按钮是否存在
        SystemRoleMenuButton systemRoleMenuButton = new SystemRoleMenuButton();
        systemRoleMenuButton.setIdList(idList);
        systemRoleMenuButton.setButtonId(NumeralUtil.MULTIPLEXING_LONG_POSITIVE_ONE);
        List<SystemRoleMenuButton> smbList = systemRoleMenuButtonServiceImpl.findByIsExistList(systemRoleMenuButton);
        if (smbList != null && smbList.size() > NumeralUtil.POSITIVE_ZERO) {
            StringBuilder sb = new StringBuilder("请先清除角色菜单按钮关联管理以下关联按钮:");
            for (SystemRoleMenuButton smb : smbList) {
                if (sb.length() > NumeralUtil.POSITIVE_TWENTY_ONE) {
                    sb.append(SymbolUtil.NO_INPUT_METHOD_COMMA).append(smb.getMenuName()).append(SymbolUtil.NO_INPUT_METHOD_COLON).append(smb.getMenuName());
                } else {
                    sb.append(smb.getMenuName()).append(SymbolUtil.NO_INPUT_METHOD_COLON).append(smb.getMenuName());
                }
            }
//            sb.deleteCharAt(sb.length() - NumeralUtil.POSITIVE_ONE);
            return r.ResponseResultFail().setMsg(sb.toString());
        }
        SystemButton record = new SystemButton();
        record.setIdList(idList);
        List<SystemButton> buttonList = this.findByList(record);
        String[] delKey = new String[buttonList.size()];
        for (int i = NumeralUtil.POSITIVE_ZERO; i < buttonList.size(); i++) {
            delKey[i] = RedisKeyUtil.getPermissionButtonKey(buttonList.get(i).getButtonCode());
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
     * @param id         1 编号
     * @param buttonCode 2 按钮代码
     * @return com.framework.common.response.ResponseResult
     * @Titel 本类后台管理查询是否存在，只是适合本身的一些方法处理。
     * @Description boolean false不存在， true存在
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 11:14
     */
    @Override
    public ResponseResult isExist(Long id, String buttonCode) {
        if (StringUtils.isEmpty(buttonCode)) {
            return this.getResponseResult().ResponseResultFail().setData(Boolean.TRUE);
        }
        SystemButton param = new SystemButton();
        param.setId(id);
        param.setButtonCode(buttonCode);
        try {
            Boolean is = this.isExist(param) > NumeralUtil.POSITIVE_ZERO ? Boolean.FALSE : Boolean.TRUE;
            return this.getResponseResult().ResponseResultSuccess().setData(is);
        } catch (Exception e) {
            return this.getResponseResult().ResponseResultFail().setData(Boolean.FALSE).setMsg(e.getMessage());
        }
    }

    /**
     * @param id 1 编号
     * @return com.framework.model.entity.system.SystemButton
     * @Titel 本类后台管理根据ID查询数据信息
     * @Description 本类后台管理根据ID查询数据信息
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 11:16
     */
    @Override
    public SystemButton getByIdParam(Long id) {
        if (id == null || id.longValue() < NumeralUtil.MULTIPLEXING_LONG_POSITIVE_ONE) {
            return null;
        }
        return this.selectByPrimaryKey(id);
    }

    /**
     * @param param 1 按钮实体类对象
     * @return com.framework.common.response.ResponseResult
     * @Titel 本类后台管理根据条件分页查询按钮列表
     * @Description 本类后台管理根据条件分页查询按钮列表
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 11:20
     */
    @Override
    public ResponseResult findParamPageList(SystemButton param) {
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
            List<SystemButton> list = this.findPageList(param);
            return r.ResponseResultSuccess().setData(list).setCount(count);
        } catch (Exception e) {
            e.printStackTrace();
            return r.ResponseResultFail();
        }
    }

    /**
     * @param param 1 按钮实体类对象
     * @return com.framework.common.response.ResponseResult
     * @Titel 根据条件查询按钮列表, 用于角色，菜单，按钮关联表格
     * @Description 根据条件查询按钮列表, 用于角色，菜单，按钮关联表格
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 13:52
     */
    @Override
    public ResponseResult findParamList(SystemButton param) {
        ResponseResult r = getResponseResult();
        try {
            param.setGtaeOperaterStatus(NumeralUtil.POSITIVE_ONE);
            List<SystemButton> list = this.findByList(param);
            return r.ResponseResultSuccess().setData(list).setCount(Integer.MAX_VALUE);
        } catch (Exception e) {
            e.printStackTrace();
            return r.ResponseResultFail();
        }
    }
}
