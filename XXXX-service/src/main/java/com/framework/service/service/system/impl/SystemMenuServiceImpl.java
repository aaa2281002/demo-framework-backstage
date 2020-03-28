package com.framework.service.service.system.impl;

import com.framework.common.model.MenuUtil;
import com.framework.common.model.Tree;
import com.framework.common.response.ResponseResult;
import com.framework.common.util.TreeUtil;
import com.framework.common.util.hump.HumpOrLineUtil;
import com.framework.common.util.other.NumeralUtil;
import com.framework.common.util.other.SymbolUtil;
import com.framework.common.util.redis.RedisKeyUtil;
import com.framework.dao.mapper.system.SystemMenuMapper;
import com.framework.model.entity.system.SystemMenu;
import com.framework.model.entity.system.SystemRole;
import com.framework.model.entity.system.SystemRoleMenu;
import com.framework.model.entity.system.SystemRoleMenuButton;
import com.framework.service.base.BaseService;
import com.framework.service.service.system.SystemMenuService;
import com.framework.service.service.system.SystemRoleMenuButtonService;
import com.framework.service.service.system.SystemRoleMenuService;
import com.framework.service.service.system.SystemRoleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * @Author 邋遢龘鵺
 * @ClassName com.framework.service.service.system.impl
 * @Description 菜单业务接口实现类
 * @DateTime 2019/10/11
 * @Version 1.0
 */
@Service("systemMenuServiceImpl")
public class SystemMenuServiceImpl extends BaseService implements SystemMenuService {
    @Autowired
    private SystemMenuMapper systemMenuMapper;
    @Autowired
    private SystemRoleMenuButtonService systemRoleMenuButtonServiceImpl;
    @Autowired
    private SystemRoleMenuService systemRoleMenuServiceImpl;
    @Autowired
    private SystemRoleService systemRoleServiceImpl;

    /**
     * @param record 1 菜单实体类对象
     * @return int 大于等于1成功， 0失败
     * @Titel 公共根据条件逻辑删除数据
     * @Description 公共根据条件逻辑删除数据
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 11:04
     */
    @Override
    public int deleteList(SystemMenu record) {
        return systemMenuMapper.deleteList(record);
    }

    /**
     * @param record 1 菜单实体类对象
     * @return long 返回主键ID大于0成功，小于等于0失败
     * @Titel 公共添加
     * @Description 公共添加
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 9:31
     */
    @Override
    public long insert(SystemMenu record) {
        return systemMenuMapper.insert(record);
    }

    /**
     * @param record 1 菜单实体类对象
     * @return long 返回主键ID大于0成功，小于等于0失败
     * @Titel 公共非空字段验证添加
     * @Description 公共非空字段验证添加
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 9:31
     */
    @Override
    public long insertSelective(SystemMenu record) {
        return systemMenuMapper.insertSelective(record);
    }

    /**
     * @param list 1 菜单批量添加集合
     * @return int 返回成功数量
     * @Titel 公共批量添加
     * @Description 公共批量添加
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 14:12
     */
    @Override
    public int insertList(List<SystemMenu> list) {
        return systemMenuMapper.insertList(list);
    }

    /**
     * @param record 1 菜单实体类对象
     * @return int 0失败，1成功
     * @Titel 公共根据编号修改
     * @Description 公共根据编号修改
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 9:35
     */
    @Override
    public int updateByPrimaryKey(SystemMenu record) {
        return systemMenuMapper.updateByPrimaryKey(record);
    }

    /**
     * @param record 1 菜单实体类对象
     * @return int 0失败，1成功
     * @Titel 公共根据编号非空字段验证修改
     * @Description 公共根据编号非空字段验证修改
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 9:35
     */
    @Override
    public int updateByPrimaryKeySelective(SystemMenu record) {
        return systemMenuMapper.updateByPrimaryKeySelective(record);
    }

    /**
     * @param list 1 菜单批量修改集合
     * @return int 返回成功数量
     * @Titel 公共批量根据非空验证编号修改
     * @Description 公共批量根据非空验证编号修改
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 13:59
     */
    @Override
    public int updateList(List<SystemMenu> list) {
        return systemMenuMapper.updateList(list);
    }

    /**
     * @param id 1 编号
     * @return com.framework.model.entity.system.SystemMenu
     * @Titel 公共根据编号查询数据
     * @Description 公共根据编号查询数据
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 9:33
     */
    @Override
    public SystemMenu selectByPrimaryKey(Long id) {
        return systemMenuMapper.selectByPrimaryKey(id);
    }

    /**
     * @param record 1 菜单实体类对象
     * @return int 0不存在， 大于等于1存在
     * @Titel 公共根据条件查询是否重复存在
     * @Description 公共根据条件查询是否重复存在
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 9:34
     */
    @Override
    public int isExist(SystemMenu record) {
        return systemMenuMapper.isExist(record);
    }


    /**
     * @param record 1 菜单实体类对象
     * @return java.util.List<com.framework.model.entity.system.SystemMenu>
     * @Titel 公共根据条件分页查询
     * @Description 公共根据条件分页查询
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 9:41
     */
    @Override
    public List<SystemMenu> findPageList(SystemMenu record) {
        return systemMenuMapper.findPageList(record);
    }

    /**
     * @param record 1 菜单实体类对象
     * @return int 返回分页总数
     * @Titel 公共根据条件分页查询总数
     * @Description 公共根据条件分页查询总数
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 9:41
     */
    @Override
    public int findPageListCount(SystemMenu record) {
        return systemMenuMapper.findPageListCount(record);
    }

    /**
     * @param record 1 菜单实体类对象
     * @return java.util.List<com.framework.model.entity.system.SystemMenu>
     * @Titel 公共根据条件查询集合
     * @Description 公共根据条件查询集合
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 11:01
     */
    @Override
    public List<SystemMenu> findByList(SystemMenu record) {
        return systemMenuMapper.findByList(record);
    }

    /**
     * @param record 1 菜单实体类对象
     * @return java.util.List<com.framework.model.entity.system.SystemMenu>
     * @Titel 公共根据条件查询树形集合
     * @Description 公共根据条件查询树形集合
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 11:01
     */
    public List<SystemMenu> findByTreeList(SystemMenu record) {
        return systemMenuMapper.findByTreeList(record);
    }

    /**
     * @param record 1 菜单实体类对象
     * @return com.framework.common.response.ResponseResult
     * @Titel 本类后台管理添加方法
     * @Description 本类后台管理添加方法
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 11:13
     */
    @Override
//    @TargetDataSource(dataSource = DataSourceUtil.WRITE_DATA_SOURCE)
    public ResponseResult save(SystemMenu record) {
        ResponseResult r = getResponseResult();
        if (record == null) {
            return r.ResponseResultFail();
        }
        int num = this.isExist(record);
        if (num > NumeralUtil.POSITIVE_ZERO) {
            return r.ResponseResultFailRepeat();
        }
        if (record.getParentId() != null && record.getParentId().longValue() > NumeralUtil.MULTIPLEXING_LONG_POSITIVE_ZERO) {
            SystemMenu sm = this.selectByPrimaryKey(record.getParentId());
            if (sm == null) {
                return r.ResponseResultAddFail();
            }
            record.setMenuLevel(sm.getMenuLevel() + 1);
        } else {
            record.setMenuLevel(NumeralUtil.POSITIVE_ZERO);
            record.setParentId(NumeralUtil.MULTIPLEXING_LONG_POSITIVE_ZERO);
        }
        Date date = new Date();
        Long userId = getUserId();
        record.setOperaterTime(date);
        record.setCreateId(userId);
        record.setOperaterId(userId);
        record.setCreateTime(date);
        record.setOperaterStatus(NumeralUtil.POSITIVE_ONE);
        record.setTarget(MenuUtil.MENU_STR_SELF);
        try {
            long is = this.insert(record);
            if (is > NumeralUtil.MULTIPLEXING_LONG_POSITIVE_ZERO) {
                super.redisUtil.setAuthMenuString(record.getMenuCode(), record);
                return r.ResponseResultSuccess();
            }
            return r.ResponseResultAddFail();
        } catch (Exception e) {
            e.printStackTrace();
            return r.ResponseResultError();
        }
    }

    /**
     * @param record 1 菜单实体类对象
     * @return com.framework.common.response.ResponseResult
     * @Titel 本类后台管理修改方法
     * @Description 本类后台管理修改方法
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 11:13
     */
    @Override
    public ResponseResult edit(SystemMenu record) {
        ResponseResult r = getResponseResult();
        if (record == null || record.getId() == null || record.getId().longValue() < NumeralUtil.MULTIPLEXING_LONG_POSITIVE_ONE) {
            return r.ResponseResultFail();
        }
        int num = this.isExist(record);
        if (num > NumeralUtil.POSITIVE_ZERO) {
            return r.ResponseResultFailRepeat();
        }
        SystemMenu sm = this.selectByPrimaryKey(record.getId());
        //SystemMenu p = this.selectByPrimaryKey(record.getId());
        if (sm == null) {
            return r.ResponseResultFail().setMsg("修改菜单不存在!");
        }
        record.setMenuLevel(null);
        record.setParentId(null);
//        sm.setMenuName(record.getMenuName());
//        sm.setMenuCode(record.getMenuCode());
//        sm.setUrlPath(record.getUrlPath());
//        sm.setIcon(record.getIcon());
//        sm.setAdminType(record.getAdminType());
//        sm.setIsEnable(record.getIsEnable());
        Long userId = getUserId();
        Date date = new Date();
        record.setOperaterId(userId);
        record.setOperaterTime(date);
        record.setOperaterStatus(NumeralUtil.POSITIVE_TWO);
        try {
            int is = this.updateByPrimaryKeySelective(record);
            if (is > NumeralUtil.POSITIVE_ZERO) {
                //获取菜单代码
                List<SystemRoleMenuButton> mbList = systemRoleMenuButtonServiceImpl.findByMenuIdListButtonCode(sm.getId());
                List<String> buttonCodeList = new ArrayList<String>();
                for (SystemRoleMenuButton srm : mbList) {
                    buttonCodeList.add(srm.getButtonCode());
                }
                record.setButtonCodeList(buttonCodeList);
                //判断菜单代码是否修改
                if (!sm.getMenuCode().equals(record.getMenuCode())) {
//                    //验证用户关联角色是否存在
//                    SystemRoleMenu systemRoleMenu = new SystemRoleMenu();
//                    List<Long> idList = new ArrayList<Long>();
//                    idList.add(record.getId());
//                    systemRoleMenu.setIdList(idList);
//                    systemRoleMenu.setMenuId(NumeralUtil.MULTIPLEXING_LONG_POSITIVE_ONE);
//                    List<SystemRoleMenu> surList = systemRoleMenuServiceImpl.findByIsExistList(systemRoleMenu);
//                    //刷新角色的menuCode菜单代码 开始
//                    List<Long> roleIdList = new ArrayList<Long>();
//                    for (SystemRoleMenu rm : surList) {
//                        roleIdList.add(rm.getRoleId());
//                    }
////                    if(roleIdList.size() > 0){
//                    //查询角色集合
//                    SystemRole systemRole = new SystemRole();
//                    systemRole.setIdList(roleIdList);
//                    List<SystemRole> suList = systemRoleServiceImpl.findByList(systemRole);
                    List<SystemRoleMenu> rmList = systemRoleMenuServiceImpl.findByMenuIdListRoleCode(sm.getId());
                    for (SystemRoleMenu sr : rmList) {
                        Object obj = super.redisUtil.getAuthRoleString(sr.getRoleCode());
                        if (obj != null) {
                            SystemRole redisSR = (SystemRole) obj;
                            boolean isSR = redisSR.getMenuCodeList() != null && redisSR.getMenuCodeList().size() > NumeralUtil.POSITIVE_ZERO && redisSR.getMenuCodeList().remove(sm.getMenuCode());
                            if (isSR) {
                                redisSR.getMenuCodeList().add(record.getMenuCode());
                                super.redisUtil.setAuthRoleString(redisSR.getRoleCode(), redisSR);
                            }
                        }
                    }
//                    }
                    //刷新用户的roleCode角色代码 结束
                    super.redisUtil.deleteKey(RedisKeyUtil.getPermissionMenuKey(sm.getMenuCode()));
                }
                super.redisUtil.setAuthMenuString(record.getMenuCode(), record);
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
        //验证菜单关联菜单是否存在
        SystemRoleMenuButton systemMenuButton = new SystemRoleMenuButton();
        systemMenuButton.setIdList(idList);
        systemMenuButton.setMenuId(NumeralUtil.MULTIPLEXING_LONG_POSITIVE_ONE);
        List<SystemRoleMenuButton> smbList = systemRoleMenuButtonServiceImpl.findByIsExistList(systemMenuButton);
        if (smbList != null && smbList.size() > NumeralUtil.POSITIVE_ZERO) {
            StringBuilder sb = new StringBuilder("请先清除角色菜单菜单关联管理以下关联菜单:");
            for (SystemRoleMenuButton smb : smbList) {
                if (sb.length() > NumeralUtil.POSITIVE_TWENTY_ONE) {
                    sb.append(SymbolUtil.NO_INPUT_METHOD_COMMA).append(smb.getMenuName());
                } else {
                    sb.append(smb.getMenuName());
                }
            }
            return r.ResponseResultFail().setMsg(sb.toString());
        }
        //验证角色关联菜单是否存在
        SystemRoleMenu systemRoleMenu = new SystemRoleMenu();
        systemRoleMenu.setMenuId(NumeralUtil.MULTIPLEXING_LONG_POSITIVE_ONE);
        systemRoleMenu.setIdList(idList);
        List<SystemRoleMenu> srbList = systemRoleMenuServiceImpl.findByIsExistList(systemRoleMenu);
        if (srbList != null && srbList.size() > NumeralUtil.POSITIVE_ZERO) {
            StringBuilder sb = new StringBuilder("请先清除角色菜单关联管理以下关联菜单:");
            for (SystemRoleMenu srm : srbList) {
                if (sb.length() > NumeralUtil.POSITIVE_NINETEEN) {
                    sb.append(SymbolUtil.NO_INPUT_METHOD_COMMA).append(srm.getRoleName());
                } else {
                    sb.append(srm.getRoleName());
                }
            }
            return r.ResponseResultFail().setMsg(sb.toString());
        }
        SystemMenu record = new SystemMenu();
        record.setIdList(idList);
        List<SystemMenu> menuList = this.findByList(record);
        String[] delKey = new String[menuList.size()];
        for (int i = NumeralUtil.POSITIVE_ZERO; i < menuList.size(); i++) {
            delKey[i] = RedisKeyUtil.getPermissionMenuKey(menuList.get(i).getMenuCode());
        }
        Long userId = getUserId();
        record.setOperaterStatus(NumeralUtil.NEGATIVE_ONE);
        record.setOperaterTime(new Date());
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
     * @param id       1 编号
     * @param menuCode 2 菜单代码
     * @return com.framework.common.response.ResponseResult
     * @Titel 本类后台管理查询是否存在，只是适合本身的一些方法处理。
     * @Description boolean false不存在， true存在
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 11:14
     */
    @Override
    public ResponseResult isExist(Long id, String menuCode) {
        if (StringUtils.isEmpty(menuCode)) {
            return this.getResponseResult().ResponseResultFail().setData(Boolean.TRUE);
        }
        SystemMenu param = new SystemMenu();
        param.setId(id);
        param.setMenuCode(menuCode);
        try {
            Boolean is = this.isExist(param) > NumeralUtil.POSITIVE_ZERO ? Boolean.FALSE : Boolean.TRUE;
            return this.getResponseResult().ResponseResultSuccess().setData(is);
        } catch (Exception e) {
            return this.getResponseResult().ResponseResultFail().setData(Boolean.FALSE).setMsg(e.getMessage());
        }
    }

    /**
     * @param id 1 编号
     * @return com.framework.model.entity.system.SystemMenu
     * @Titel 本类后台管理根据ID查询数据信息
     * @Description 本类后台管理根据ID查询数据信息
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 11:16
     */
    @Override
    public SystemMenu getByIdParam(Long id) {
        if (id == null || id.longValue() < NumeralUtil.MULTIPLEXING_LONG_POSITIVE_ONE) {
            return null;
        }
        return this.selectByPrimaryKey(id);
    }


    /**
     * @param param 1 菜单实体类对象
     * @return com.framework.common.response.ResponseResult
     * @Titel 本类后台管理根据条件分页查询菜单列表
     * @Description 本类后台管理根据条件分页查询菜单列表
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 11:20
     */
    @Override
    public ResponseResult findParamPageList(SystemMenu param) {
        ResponseResult r = getResponseResult();
        if (param == null || param.getParentId() == null) {
            return r.ResponseResultSuccess().setCount(NumeralUtil.POSITIVE_ZERO);
        }
        if (StringUtils.isNotEmpty(param.getSort()) && StringUtils.isNotEmpty(param.getOrder())) {
            if ("parentName".equals(param.getSort())) {
                param.setSort("p2.menu_name");
            } else {
                param.setSort("p." + HumpOrLineUtil.humpToLine(param.getSort()));
            }
        } else {
            param.setSort("p.create_Time");
            param.setOrder("desc");
        }
        try {
            param.setGtaeOperaterStatus(NumeralUtil.POSITIVE_ONE);
            int count = this.findPageListCount(param);
            List<SystemMenu> list = this.findPageList(param);
            return r.ResponseResultSuccess().setData(list).setCount(count);
        } catch (Exception e) {
            e.printStackTrace();
            return r.ResponseResultFail();
        }
    }

    //组装树形集合
    private List<Tree> childrenList(SystemMenu m, List<SystemMenu> list) {
        Iterator<SystemMenu> it = list.iterator();
        List<Tree> treeList = new ArrayList<Tree>();
        while (it.hasNext()) {
            SystemMenu sm = it.next();
            if (sm.getParentId() != null && m.getId().longValue() == sm.getParentId().longValue()) {
                Tree z = new Tree();
                z.setId(sm.getId().toString());
                z.setTitle(sm.getMenuName());
                //一定要在删除后在执行递归，不然会出现并发修改操作BUG
//                it.remove();
                z.setChildren(childrenList(sm, list));
                treeList.add(z);
            }
        }
        return treeList;
    }

    /**
     * @param id      编号
     * @param pId     上级编号
     * @param keyword 搜索菜单名称
     * @return com.framework.common.response.ResponseResult
     * @Titel 根据条件查询树形菜单集合
     * @Description 根据条件查询树形菜单集合
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 13:52
     */
    @Override
    public ResponseResult findMenuTreeList(Long id, Long pId, String keyword) {
        SystemMenu param = new SystemMenu();
        if (StringUtils.isNotEmpty(keyword)) {
            param.setKeyword(keyword);
        } else {
            param.setParentId(id);
        }
        List<SystemMenu> list = this.findByList(param);
        if (list == null || list.size() == NumeralUtil.POSITIVE_ZERO) {
            return this.getResponseResult().ResponseResultFail();
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
//        ztree.setDisabled(Boolean.TRUE);
        ztree.setSpread(Boolean.TRUE);
//        ztree.setChecked(Boolean.TRUE);
        treeList.add(ztree);

        for (SystemMenu m : rootList) {
            Tree z = new Tree();
            z.setId(m.getId().toString());
            z.setTitle(m.getMenuName());
            z.setChildren(childrenList(m, list));
//            treeList.add(z);
            ztree.getChildren().add(z);
        }
        return this.getResponseResult().ResponseResultSuccess().setData(treeList);
    }


}
