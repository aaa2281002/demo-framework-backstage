package com.framework.service.service.system.impl;

import com.framework.common.response.ResponseResult;
import com.framework.common.util.other.NumeralUtil;
import com.framework.common.util.redis.RedisUtil;
import com.framework.common.util.other.SymbolUtil;
import com.framework.dao.mapper.system.SystemRoleMenuButtonMapper;
import com.framework.model.entity.system.SystemButton;
import com.framework.model.entity.system.SystemMenu;
import com.framework.model.entity.system.SystemRoleMenuButton;
import com.framework.model.entity.system.SystemRole;
import com.framework.service.base.BaseService;
import com.framework.service.service.system.SystemButtonService;
import com.framework.service.service.system.SystemRoleMenuButtonService;
import com.framework.service.service.system.SystemMenuService;
import com.framework.service.service.system.SystemRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author 邋遢龘鵺
 * @ClassName com.framework.service.service.system.impl
 * @Description 角色菜单按钮关联业务接口实现类
 * @DateTime 2019/10/11
 * @Version 1.0
 */
@Service("systemRoleMenuButtonServiceImpl")
public class SystemRoleMenuButtonServiceImpl extends BaseService implements SystemRoleMenuButtonService {
    @Autowired
    private SystemRoleMenuButtonMapper systemRoleMenuButtonMapper;
    @Autowired
    private SystemRoleService systemRoleServiceImpl;
    @Autowired
    private SystemMenuService systemMenuServiceImpl;
    @Autowired
    private SystemButtonService systemButtonServiceImpl;
    @Autowired
    private RedisUtil redisUtil;

    /**
     * @param record 1 角色菜单按钮关联类对象
     * @return int 大于等于1成功， 0失败
     * @Titel 公共根据角色编号、菜单编号、按钮编号集合物理批量删除
     * @Description 公共根据角色编号、菜单编号、按钮编号集合物理批量删除
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 17:21
     */
    @Override
    public int deleteList(SystemRoleMenuButton record) {
        return systemRoleMenuButtonMapper.deleteList(record);
    }

    /**
     * @param idList 1 角色编号集合
     * @return int 大于等于1成功， 0失败
     * @Titel 公共根据角色编号集合物理删除关联按钮
     * @Description 公共根据角色编号集合物理删除关联按钮
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 17:18
     */
    @Override
    public int deleteRoleList(List<Long> idList) {
        return systemRoleMenuButtonMapper.deleteRoleList(idList);
    }

    /**
     * @param map 1 参数键值对集合
     * @return int 大于等于1成功， 0失败
     * @Titel 公共根据角色、菜单编号物理删除关联按钮
     * @Description 公共根据角色、菜单编号物理删除关联按钮
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 17:18
     */
    @Override
    public int deleteRoleMenuId(Map<String, Long> map) {
        return systemRoleMenuButtonMapper.deleteRoleMenuId(map);
    }

    /**
     * @param record 1 角色菜单按钮关联类对象
     * @return int 大于0成功，小于等于0失败
     * @Titel 公共添加
     * @Description 公共添加
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 9:31
     */
    @Override
    public int insert(SystemRoleMenuButton record) {
        return systemRoleMenuButtonMapper.insert(record);
    }

    /**
     * @param record 1 角色菜单按钮关联类对象
     * @return int 大于0成功，小于等于0失败
     * @Titel 公共非空字段验证添加
     * @Description 公共非空字段验证添加
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 9:31
     */
    @Override
    public int insertSelective(SystemRoleMenuButton record) {
        return systemRoleMenuButtonMapper.insertSelective(record);
    }

    /**
     * @param list 1 角色菜单按钮关联类批量添加集合
     * @return int 返回成功数量
     * @Titel 公共批量添加
     * @Description 公共批量添加
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 14:12
     */
    @Override
    public int insertList(List<SystemRoleMenuButton> list) {
        return systemRoleMenuButtonMapper.insertList(list);
    }

    /**
     * @param record 1 角色菜单按钮关联类对象
     * @return java.util.List<com.framework.model.entity.system.SystemRoleMenuButton>
     * @Titel 公共根据条件查询集合
     * @Description 公共根据条件查询集合
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 17:15
     */
    @Override
    public List<SystemRoleMenuButton> findByList(SystemRoleMenuButton record) {
        return systemRoleMenuButtonMapper.findByList(record);
    }

    /**
     * @param menuId 1 菜单编号
     * @return java.util.List<com.framework.model.entity.system.SystemMenuButton> 返回集合
     * @Titel 公共根据菜单编号查询按钮代码集合
     * @Description 公共根据菜单编号查询按钮代码集合
     * @Author 邋遢龘鵺
     * @DateTime 2019/10/11
     */
    @Override
    public List<SystemRoleMenuButton> findByMenuIdListButtonCode(Long menuId) {
        return systemRoleMenuButtonMapper.findByMenuIdListButtonCode(menuId);
    }

    /**
     * @param record 1 角色菜单按钮关联类对象
     * @return java.util.List<com.framework.model.entity.system.SystemRoleMenuButton>
     * @Titel 公共根据条件查询存在集合
     * @Description 公共根据条件查询存在集合
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 17:11
     */
    @Override
    public List<SystemRoleMenuButton> findByIsExistList(SystemRoleMenuButton record) {
        return systemRoleMenuButtonMapper.findByIsExistList(record);
    }

    /**
     * @param buttonId 1 菜单编号
     * @return java.util.List<com.framework.model.entity.system.SystemMenuButton> 返回集合
     * @Titel 公共根据按钮编号查询菜单代码集合
     * @Description 公共根据按钮编号查询菜单代码集合
     * @Author 邋遢龘鵺
     * @DateTime 2019/10/11
     */
    @Override
    public List<SystemRoleMenuButton> findByButtonIdListRoleCodeAndMenuCode(Long buttonId) {
        return systemRoleMenuButtonMapper.findByButtonIdListRoleCodeAndMenuCode(buttonId);
    }

    /**
     * @param roleId       1 角色编号
     * @param menuId       2 菜单编号
     * @param buttonIdList 3 按钮编号集合
     * @return com.framework.common.response.ResponseResult
     * @Titel 本类后台管理添加方法, 根据角色编号，菜单编号，按钮编号集合
     * @Description 本类后台管理添加方法, 根据角色编号，菜单编号，按钮编号集合
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 17:22
     */
    @Override
    public ResponseResult save(Long roleId, Long menuId, List<Long> buttonIdList) {
        ResponseResult r = getResponseResult();
        if (menuId == null || menuId < NumeralUtil.MULTIPLEXING_LONG_POSITIVE_ONE) {
            return r.ResponseResultFail();
        }
        SystemRole sr = systemRoleServiceImpl.selectByPrimaryKey(roleId);
        if (sr == null) {
            return r.ResponseResultFail().setMsg("角色不存在!");
        }
        SystemMenu sm = systemMenuServiceImpl.selectByPrimaryKey(menuId);
        if (sm == null) {
            return r.ResponseResultFail().setMsg("菜单不存在!");
        }
        Map<String, Long> map = new HashMap<String, Long>();
        map.put("menuId", menuId);
        map.put("roleId", roleId);
        String roleMenuCode = sr.getRoleCode() + SymbolUtil.NO_INPUT_METHOD_COLON + sm.getMenuCode();
        if (buttonIdList == null) {
            //清除菜单所有按钮。
            try {
                this.deleteRoleMenuId(map);
                redisUtil.setAuthMenuString(roleMenuCode, sm);
                return r.ResponseResultSuccess();
            } catch (Exception e) {
                return r.ResponseResultFail().setMsg("菜单按钮保存异常!");
            }
        }
        List<SystemRoleMenuButton> addList = new ArrayList<SystemRoleMenuButton>();
        for (Long buttonId : buttonIdList) {
            SystemRoleMenuButton srm = new SystemRoleMenuButton();
            srm.setRoleId(roleId);
            srm.setMenuId(menuId);
            srm.setButtonId(buttonId);
            addList.add(srm);
        }
        try {
            this.deleteRoleMenuId(map);
            this.insertList(addList);
        } catch (Exception e) {
            return r.ResponseResultFail().setMsg("菜单按钮保存异常!");
        }
        SystemButton systemButton = new SystemButton();
        systemButton.setGtaeOperaterStatus(NumeralUtil.POSITIVE_ZERO);
        List<SystemButton> buttonList = systemButtonServiceImpl.findByList(systemButton);
        List<String> buttonCodeList = new ArrayList<String>();
        for (SystemButton sb : buttonList) {
            if (buttonIdList.contains(sb.getId())) {
                buttonCodeList.add(sb.getButtonCode());
            }
        }
        try {
            //更新Redis缓存
            sm.setButtonCodeList(buttonCodeList);
            redisUtil.setAuthMenuString(roleMenuCode, sm);
        } catch (Exception e) {
            return r.ResponseResultFail().setMsg("菜单按钮缓存异常!");
        }
        return r.ResponseResultSuccess();
    }

    /**
     * @param param 1 角色菜单按钮关联类对象
     * @return com.framework.common.response.ResponseResult
     * @Titel 根据条件查询数据集合
     * @Description 根据条件查询数据集合
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 17:23
     */
    @Override
    public ResponseResult findByParamList(SystemRoleMenuButton param) {
        ResponseResult rr = getResponseResult();
        if (param == null || param.getMenuId() == null) {
            return rr.ResponseResultFail();
        }
        try {
            List<SystemRoleMenuButton> list = this.findByList(param);
            return rr.ResponseResultSuccess().setData(list);
        } catch (Exception e) {
            e.printStackTrace();
            return rr.ResponseResultError();
        }
    }

}
