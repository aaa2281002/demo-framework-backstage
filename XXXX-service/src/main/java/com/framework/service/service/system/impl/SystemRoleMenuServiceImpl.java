package com.framework.service.service.system.impl;

import com.framework.common.model.Tree;
import com.framework.common.response.ResponseResult;
import com.framework.common.util.TreeUtil;
import com.framework.common.util.other.NumeralUtil;
import com.framework.common.util.other.SymbolUtil;
import com.framework.common.util.redis.RedisKeyUtil;
import com.framework.dao.mapper.system.SystemRoleMenuMapper;
import com.framework.model.entity.system.SystemButton;
import com.framework.model.entity.system.SystemMenu;
import com.framework.model.entity.system.SystemRole;
import com.framework.model.entity.system.SystemRoleMenu;
import com.framework.model.entity.system.SystemRoleMenuButton;
import com.framework.service.base.BaseService;
import com.framework.service.service.system.SystemButtonService;
import com.framework.service.service.system.SystemMenuService;
import com.framework.service.service.system.SystemRoleMenuButtonService;
import com.framework.service.service.system.SystemRoleMenuService;
import com.framework.service.service.system.SystemRoleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * @Author 邋遢龘鵺
 * @ClassName com.framework.service.service.system.impl
 * @Description 角色菜单关联业务接口实现类
 * @DateTime 2019/10/11
 * @Version 1.0
 */
@Service("systemRoleMenuServiceImpl")
public class SystemRoleMenuServiceImpl extends BaseService implements SystemRoleMenuService {
    @Autowired
    private SystemRoleMenuMapper systemRoleMenuMapper;
    @Autowired
    private SystemRoleService systemRoleServiceImpl;
    @Autowired
    private SystemMenuService systemMenuServiceImpl;
    @Autowired
    private SystemButtonService systemButtonServiceImpl;
    @Autowired
    private SystemRoleMenuButtonService systemRoleMenuButtonServiceImpl;


    /**
     * @param record 1 角色菜单关联实体类对象
     * @return int 大于等于1成功， 0失败
     * @Titel 公共根据角色编号和菜单编号集合物理批量删除
     * @Description 公共根据角色编号和菜单编号集合物理批量删除
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 18:07
     */
    @Override
    public int deleteList(SystemRoleMenu record) {
        return systemRoleMenuMapper.deleteList(record);
    }

    /**
     * @param roleId 1 角色编号
     * @return int 大于等于1成功， 0失败
     * @Titel 公共根据角色编号物理删除关联数据
     * @Description 公共根据角色编号物理删除关联数据
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 18:10
     */
    @Override
    public int deleteRoleId(Long roleId) {
        return systemRoleMenuMapper.deleteRoleId(roleId);
    }

    /**
     * @param record 1 角色菜单关联实体类对象
     * @return int 大于0成功，小于等于0失败
     * @Titel 公共添加
     * @Description 公共添加
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 9:31
     */
    @Override
    public int insert(SystemRoleMenu record) {
        return systemRoleMenuMapper.insert(record);
    }

    /**
     * @param record 1 角色菜单关联实体类对象
     * @return int 大于0成功，小于等于0失败
     * @Titel 公共非空字段验证添加
     * @Description 公共非空字段验证添加
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 9:31
     */
    @Override
    public int insertSelective(SystemRoleMenu record) {
        return systemRoleMenuMapper.insertSelective(record);
    }

    /**
     * @param list 1 角色菜单关联批量添加集合
     * @return int 返回成功数量
     * @Titel 公共批量添加
     * @Description 公共批量添加
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 14:12
     */
    @Override
    public int insertList(List<SystemRoleMenu> list) {
        return systemRoleMenuMapper.insertList(list);
    }

    /**
     * @param record 1 角色菜单关联实体类对象
     * @return java.util.List<java.lang.Long>
     * @Titel 公共根据条件查询菜单编号集合
     * @Description 公共根据条件查询菜单编号集合
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 18:19
     */
    public List<Long> findByMenuIdList(SystemRoleMenu record) {
        return systemRoleMenuMapper.findByMenuIdList(record);
    }

    /**
     * @param record 1 角色菜单关联实体类对象
     * @return java.util.List<com.framework.model.entity.system.SystemRoleMenu>
     * @Titel 公共根据条件查询存在集合
     * @Description 公共根据条件查询存在集合
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 18:20
     */
    @Override
    public List<SystemRoleMenu> findByIsExistList(SystemRoleMenu record) {
        return systemRoleMenuMapper.findByIsExistList(record);
    }

    /**
     * @param roleId 1 角色编号
     * @return java.util.List<com.framework.model.entity.system.SystemRoleMenu> 返回集合
     * @Titel 公共根据角色编号查询菜单代码集合
     * @Description 公共根据角色编号查询菜单代码集合
     * @Author 邋遢龘鵺
     * @DateTime 2019/10/11
     */
    public List<SystemRoleMenu> findByRoleIdListMenuCode(Long roleId) {
        return systemRoleMenuMapper.findByRoleIdListMenuCode(roleId);
    }

    /**
     * @param menuId 1 菜单编号
     * @return java.util.List<com.framework.model.entity.system.SystemRoleMenu> 返回集合
     * @Titel 公共根据菜单编号查询角色代码集合
     * @Description 公共根据菜单编号查询角色代码集合
     * @Author 邋遢龘鵺
     * @DateTime 2019/10/11
     */
    public List<SystemRoleMenu> findByMenuIdListRoleCode(Long menuId) {
        return systemRoleMenuMapper.findByMenuIdListRoleCode(menuId);
    }

    /**
     * @param record 1 角色菜单关联实体类对象
     * @return java.util.List<com.framework.model.entity.system.SystemRoleMenu>
     * @Titel 公共根据条件查询集合
     * @Description 公共根据条件查询集合
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 18:20
     */
    @Override
    public List<SystemRoleMenu> findByList(SystemRoleMenu record) {
        return systemRoleMenuMapper.findByList(record);
    }

    /**
     * @param roleId     1 角色编号
     * @param menuIdList 2 菜单编号集合
     * @return com.framework.common.response.ResponseResult
     * @Titel 本类后台管理添加方法
     * @Description 本类后台管理添加方法
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 18:23
     */
    @Override
    public ResponseResult save(Long roleId, List<Long> menuIdList) {
        ResponseResult r = getResponseResult();
        if (roleId == null || roleId.longValue() < NumeralUtil.MULTIPLEXING_LONG_POSITIVE_ONE) {
            return r.ResponseResultFail();
        }
        SystemRole sr = systemRoleServiceImpl.selectByPrimaryKey(roleId);
        if (sr == null) {
            return r.ResponseResultFail().setMsg("角色不存在!");
        }
        if (menuIdList == null) {
            try {
                this.deleteRoleId(roleId);
                redisUtil.setAuthRoleString(sr.getRoleCode(), sr);
                return r.ResponseResultSuccess();
            } catch (Exception e) {
                e.printStackTrace();
                return r.ResponseResultFail().setMsg("角色菜单保存异常!");
            }
        }
        menuIdList.remove(NumeralUtil.MULTIPLEXING_LONG_POSITIVE_ZERO);
        List<SystemRoleMenu> addList = new ArrayList<SystemRoleMenu>();
        for (Long menuId : menuIdList) {
            SystemRoleMenu srm = new SystemRoleMenu();
            srm.setRoleId(roleId);
            srm.setMenuId(menuId);
            addList.add(srm);
        }
        try {
            this.deleteRoleId(roleId);
            this.insertList(addList);
        } catch (Exception e) {
            e.printStackTrace();
            return r.ResponseResultFail().setMsg("角色菜单保存异常!");
        }
        SystemMenu systemMenu = new SystemMenu();
        systemMenu.setGtaeOperaterStatus(NumeralUtil.POSITIVE_ZERO);
        List<SystemMenu> menuList = systemMenuServiceImpl.findByList(systemMenu);
        List<String> menuCodeList = new ArrayList<String>();
        String roleCode = RedisKeyUtil.getPermissionMenuKey(sr.getRoleCode() + SymbolUtil.NO_INPUT_METHOD_COLON + "*");

        //处理菜单关联的按钮
        SystemButton systemButton = new SystemButton();
        systemButton.setGtaeOperaterStatus(NumeralUtil.POSITIVE_ZERO);
        List<SystemButton> buttonList = systemButtonServiceImpl.findByList(systemButton);
        SystemRoleMenuButton srmb = new SystemRoleMenuButton();
        srmb.setRoleId(sr.getId());
        List<SystemRoleMenuButton> roleMenuButtonList = systemRoleMenuButtonServiceImpl.findByList(srmb);
        Map<String, List<String>> rsmbMap = new HashMap<String, List<String>>();
        for (SystemRoleMenuButton rsmb : roleMenuButtonList) {//循环菜单
            String roleMenuIdCode = rsmb.getRoleId() + SymbolUtil.NO_INPUT_METHOD_UNDERLINE + rsmb.getMenuId();
            List<String> list = rsmbMap.get(roleMenuIdCode);//判断当前map集合中的按钮代码集合
            if (list == null) {//按钮代码集合不存在就是第一次循环。
                list = new ArrayList<String>();//创建一个新的集合
            }
            for (SystemButton sb : buttonList) {//循环按钮
                //匹配对应的菜单按钮关联ID
                if (sb.getId() == rsmb.getButtonId() || rsmb.getButtonId().equals(sb.getId())) {
                    //匹配上了以后把按钮代码存入list集合中
                    list.add(sb.getButtonCode());
                    break;
                }
            }
            //保存对应的菜单ID键，按钮代码集合
            rsmbMap.put(roleMenuIdCode, list);
        }

        try {
            Set<String> keys = redisUtil.getKeys(roleCode);
            if (keys != null && keys.size() > NumeralUtil.POSITIVE_ZERO) {
                redisUtil.deleteKey(keys);
            }
            for (SystemMenu sm : menuList) {
                if (menuIdList.contains(sm.getId())) {
                    String roleMenuCode = sr.getRoleCode() + SymbolUtil.NO_INPUT_METHOD_COLON + sm.getMenuCode();
                    menuCodeList.add(sm.getMenuCode());
                    String roleMenuIdCode = sr.getId() + SymbolUtil.NO_INPUT_METHOD_UNDERLINE + sm.getId();
                    List<String> smList = rsmbMap.get(roleMenuIdCode);
                    sm.setButtonCodeList(smList);
                    redisUtil.setAuthMenuString(roleMenuCode, sm);
                }
            }
            //更新Redis缓存
            sr.setMenuCodeList(menuCodeList);
            redisUtil.setAuthRoleString(sr.getRoleCode(), sr);
        } catch (Exception e) {
            e.printStackTrace();
            return r.ResponseResultFail().setMsg("角色菜单缓存异常!");
        }
        return r.ResponseResultSuccess();
    }

    /**
     * @param param 1 角色菜单关联实体类对象
     * @return com.framework.common.response.ResponseResult
     * @Titel 根据条件查询数据集合
     * @Description 根据条件查询数据集合
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 18:23
     */
    @Override
    public ResponseResult findByParamList(SystemRoleMenu param) {
        ResponseResult r = getResponseResult();
        if (param == null || param.getRoleId() == null || param.getRoleId().longValue() < NumeralUtil.MULTIPLEXING_LONG_POSITIVE_ONE) {
            return r.ResponseResultFail();
        }
        try {
            List<SystemRoleMenu> list = this.findByList(param);
            return r.ResponseResultSuccess().setData(list);
        } catch (Exception e) {
            e.printStackTrace();
            return r.ResponseResultError();
        }
    }

    //递归处理树形
    private List<Tree> childrenList(SystemMenu m, List<SystemMenu> list, List<Long> lList) {
        Iterator<SystemMenu> it = list.iterator();
        List<Tree> treeList = new ArrayList<Tree>();
        while (it.hasNext()) {
            SystemMenu sm = it.next();
            if (sm.getParentId() != null && m.getId().longValue() == sm.getParentId().longValue()) {
                Tree z = new Tree();
                z.setId(sm.getId().toString());
                z.setTitle(sm.getMenuName());
                z.setSpread(Boolean.TRUE && lList != null);
//                it.remove();
                z.setChildren(childrenList(sm, list, lList));
                if (z.getChildren().size() == NumeralUtil.POSITIVE_ZERO
                        && lList != null
                        && lList.contains(sm.getId())) {
                    z.setChecked(Boolean.TRUE);
                }
                treeList.add(z);
            }
        }
        return treeList;
    }

    /**
     * @param roleId  1 角色编号
     * @param id      2 菜单编号
     * @param pId     3 菜单上级编号
     * @param keyword 4 搜索条件
     * @return com.framework.common.response.ResponseResult
     * @Titel 根据条件查询树形菜单集合
     * @Description 根据条件查询树形菜单集合
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 18:25
     */
    @Override
    public ResponseResult findMenuTreeList(Long roleId, Long id, Long pId, String keyword) {
        SystemMenu param = new SystemMenu();
        if (StringUtils.isNotEmpty(keyword)) {
            param.setKeyword(keyword);
        } else {
            param.setParentId(id);
        }
        List<SystemMenu> list = systemMenuServiceImpl.findByList(param);
        if (list == null || list.size() == NumeralUtil.POSITIVE_ZERO) {
            return this.getResponseResult().ResponseResultFail();
        }
        List<Long> lList = null;
        if (roleId != null && roleId.longValue() > NumeralUtil.MULTIPLEXING_LONG_POSITIVE_ZERO) {
            SystemRoleMenu systemRoleMenu = new SystemRoleMenu();
            systemRoleMenu.setRoleId(roleId);
            lList = this.findByMenuIdList(systemRoleMenu);
        }

        Iterator<SystemMenu> it = list.iterator();
        List<SystemMenu> rootList = new ArrayList<SystemMenu>();
        while (it.hasNext()) {
            SystemMenu m = it.next();
            if (m.getParentId() == null || m.getParentId().longValue() < NumeralUtil.POSITIVE_ONE) {
                rootList.add(m);
                it.remove();
            }
        }

        List<Tree> treeList = new ArrayList<Tree>();
        Tree ztree = new Tree();
        ztree.setId(String.valueOf(NumeralUtil.POSITIVE_ZERO));
        ztree.setTitle(TreeUtil.TREE_ROOT_NODE_NAME);
//        if (rootList.size() == NumeralUtil.POSITIVE_ZERO
//                && lList != null
//                && lList.size() > NumeralUtil.POSITIVE_ZERO) {
//            ztree.setChecked(Boolean.TRUE);
//        }
        ztree.setSpread(Boolean.TRUE);
        for (SystemMenu m : rootList) {
            Tree z = new Tree();
            z.setId(m.getId().toString());
            z.setTitle(m.getMenuName());
            z.setSpread(Boolean.TRUE);
            z.setChildren(childrenList(m, list, lList));
            if (z.getChildren().size() == NumeralUtil.POSITIVE_ZERO
                    && lList != null
                    && lList.contains(m.getId())) {
                z.setChecked(Boolean.TRUE);
            }
            ztree.getChildren().add(z);
        }
        treeList.add(ztree);
        return this.getResponseResult().ResponseResultSuccess().setData(treeList);
    }

}
