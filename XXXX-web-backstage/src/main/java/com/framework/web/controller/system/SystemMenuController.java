package com.framework.web.controller.system;

import com.framework.common.response.ResponseResult;
import com.framework.common.util.system.SystemUtil;
import com.framework.model.entity.system.SystemMenu;
import com.framework.service.service.system.SystemMenuService;
import com.framework.web.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.List;

/**
 * @Author 邋遢龘鵺
 * @ClassName com.framework.web.controller.system
 * @Description 菜单请求控制类
 * @DateTime 2019/10/11
 * @Version 1.0
 */
@Controller
@RequestMapping(value = "/system/menu")
public class SystemMenuController extends BaseController {
    private String path = "lowerright/system/menu/";
    @Autowired
    private SystemMenuService systemMenuServiceImpl;

    /**
     * @return org.springframework.web.servlet.ModelAndView
     * @Titel 分页页面跳转
     * @Description 分页页面跳转
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/14 18:20
     */
    @RequestMapping("/page/list")
    @PreAuthorize("hasPermission('" + SystemUtil.SYSTEM_MENU_NAME + "','SYSTEM_MENU_LIST_MANAGEMENT')")
    public ModelAndView pageList() {
        return new ModelAndView(path + "menuList");
    }

    /**
     * @param id 1 编号
     * @return org.springframework.web.servlet.ModelAndView
     * @Titel 新增页面跳转
     * @Description 新增页面跳转
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/14 18:21
     */
    @RequestMapping("/get/add")
    @PreAuthorize("hasPermission('" + SystemUtil.SYSTEM_BUTTON_NAME + "','SYSTEM_MENU_LIST_MANAGEMENT:add')")
    public ModelAndView getAdd(Long id) {
        ModelAndView mv = new ModelAndView(path + "menuAdd");
        mv.addObject("p", systemMenuServiceImpl.selectByPrimaryKey(id));
        return mv;
    }

    /**
     * @param id 1 编号
     * @return org.springframework.web.servlet.ModelAndView
     * @Titel 编辑页面跳转
     * @Description 编辑页面跳转
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/14 18:21
     */
    @RequestMapping("/get/edit")
    @PreAuthorize("hasPermission('" + SystemUtil.SYSTEM_BUTTON_NAME + "','SYSTEM_MENU_LIST_MANAGEMENT:edit')")
    public ModelAndView getEdit(Long id) {
        ModelAndView mv = new ModelAndView(path + "menuEdit");
        mv.addObject("p", systemMenuServiceImpl.getByIdParam(id));
        return mv;
    }

    /**
     * @param id 1 编号
     * @return org.springframework.web.servlet.ModelAndView
     * @Titel 查看页面跳转
     * @Description 查看页面跳转
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/14 18:21
     */
    @RequestMapping("/get/view")
    @PreAuthorize("hasPermission('" + SystemUtil.SYSTEM_BUTTON_NAME + "','SYSTEM_MENU_LIST_MANAGEMENT:view')")
    public ModelAndView getView(Long id) {
        ModelAndView mv = new ModelAndView(path + "menuView");
        mv.addObject("p", systemMenuServiceImpl.getByIdParam(id));
        return mv;
    }

    /**
     * @param param 1 菜单实体类对象
     * @return com.framework.common.response.ResponseResult
     * @Titel 菜单分页查询
     * @Description 菜单分页查询
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/22 17:58
     */
    // method = RequestMethod.POST,
    @RequestMapping(value = "/findPageList", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    @PreAuthorize("hasPermission('" + SystemUtil.SYSTEM_MENU_NAME + "','SYSTEM_MENU_LIST_MANAGEMENT')")
    public ResponseResult findPageList(SystemMenu param) {
        try {
            return systemMenuServiceImpl.findParamPageList(param);
        } catch (Exception e) {
            e.printStackTrace();
            return getError();
        }
    }

    /**
     * @param id      1 编号
     * @param pId     2 上级编号
     * @param keyword 3 模糊搜索
     * @return com.framework.common.response.ResponseResult
     * @Titel 菜单树形查询
     * @Description 菜单树形查询
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/22 19:27
     */
    @RequestMapping(value = "/findMenuTreeList", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    @PreAuthorize("hasPermission('" + SystemUtil.SYSTEM_MENU_NAME + "','SYSTEM_MENU_LIST_MANAGEMENT')")
    public ResponseResult findMenuTreeList(Long id, Long pId, String keyword) {
        try {
            return systemMenuServiceImpl.findMenuTreeList(id, pId, keyword);
        } catch (Exception e) {
            e.printStackTrace();
            return this.getError();
        }
    }

    /**
     * @param param 1 菜单实体类对象
     * @return com.framework.common.response.ResponseResult
     * @Titel 新增
     * @Description 新增
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/22 17:58
     */
    @RequestMapping(value = "/save", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    @PreAuthorize("hasPermission('" + SystemUtil.SYSTEM_BUTTON_NAME + "','SYSTEM_MENU_LIST_MANAGEMENT:add')")
    public ResponseResult save(SystemMenu param) {
        try {
            return systemMenuServiceImpl.save(param);
        } catch (Exception e) {
            e.printStackTrace();
            return getError();
        }
    }

    /**
     * @param param 1 菜单实体类对象
     * @return com.framework.common.response.ResponseResult
     * @Titel 修改
     * @Description 修改
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/22 17:59
     */
    @RequestMapping(value = "/edit", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    @PreAuthorize("hasPermission('" + SystemUtil.SYSTEM_BUTTON_NAME + "','SYSTEM_MENU_LIST_MANAGEMENT:edit')")
    public ResponseResult edit(SystemMenu param) {
        try {
            return systemMenuServiceImpl.edit(param);
        } catch (Exception e) {
            e.printStackTrace();
            return getError();
        }
    }

    /**
     * @param idList 1 菜单编号集合
     * @return com.framework.common.response.ResponseResult
     * @Titel 批量删除
     * @Description 批量删除
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/22 18:00
     */
    @RequestMapping(value = "/batchDel", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ResponseBody
    @PreAuthorize("hasPermission('" + SystemUtil.SYSTEM_BUTTON_NAME + "','SYSTEM_MENU_LIST_MANAGEMENT:batchDel')")
    public ResponseResult del(@RequestParam(value = "idList[]") List<Long> idList) {
        try {
            return systemMenuServiceImpl.batchDeleteList(idList);
        } catch (Exception e) {
            e.printStackTrace();
            return getError();
        }
    }

    /**
     * @param id 1 菜单编号
     * @return com.framework.common.response.ResponseResult
     * @Titel 删除
     * @Description 删除
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/22 18:00
     */
    @RequestMapping(value = "/del", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ResponseBody
    @PreAuthorize("hasPermission('" + SystemUtil.SYSTEM_BUTTON_NAME + "','SYSTEM_MENU_LIST_MANAGEMENT:del')")
    public ResponseResult del(Long id) {
        try {
            return systemMenuServiceImpl.batchDeleteList(Arrays.asList(id));
        } catch (Exception e) {
            e.printStackTrace();
            return getError();
        }
    }

//    /**
//     * @param id       1 菜单编号
//     * @param menuCode 2 菜单代码
//     * @return com.framework.common.response.ResponseResult
//     * @Titel 验证是否重复菜单code
//     * @Description 验证是否重复菜单code
//     * @Author 邋遢龘鵺
//     * @DateTime 2019/12/22 18:00
//     */
//    @RequestMapping("/isExist")
//    @ResponseBody
//    @PreAuthorize("hasPermission('" + SystemUtil.SYSTEM_BUTTON_NAME + "','SYSTEM_MENU_LIST_MANAGEMENT:isExist')")
//    public ResponseResult isExist(Long id, String menuCode) {
//        return systemMenuServiceImpl.isExist(id, menuCode);
//    }


}
