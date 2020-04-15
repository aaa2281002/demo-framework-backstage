package com.framework.web.controller.system;

import com.framework.common.response.ResponseResult;
import com.framework.common.util.system.SystemUtil;
import com.framework.model.entity.system.SystemBlackListIp;
import com.framework.service.service.system.SystemBlackListIpService;
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
 * @Description 前端系统前端操作黑名单IP请求控制类
 * @DateTime 2019/10/11
 * @Version 1.0
 */
@Controller
@RequestMapping(value = "/system/black/list/ip")
public class SystemBlackListIpController extends BaseController {
    private String path = "lowerright/system/blackListIp/";
    @Autowired
    private SystemBlackListIpService systemBlackListIpServiceImpl;

    /**
     * @return org.springframework.web.servlet.ModelAndView
     * @Titel 分页页面跳转
     * @Description 分页页面跳转
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/14 18:20
     */
    @RequestMapping("/page/list")
    @PreAuthorize("hasPermission('" + SystemUtil.SYSTEM_MENU_NAME + "','SYSTEM_BLACK_LIST_IP_MANAGEMENT')")
    public ModelAndView pageList() {
        return new ModelAndView(path + "blackListIpList");
    }

    /**
     * @return org.springframework.web.servlet.ModelAndView
     * @Titel 新增页面跳转
     * @Description 新增页面跳转
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/14 18:21
     */
    @RequestMapping("/get/add")
    @PreAuthorize("hasPermission('" + SystemUtil.SYSTEM_BUTTON_NAME + "','SYSTEM_BLACK_LIST_IP_MANAGEMENT:add')")
    public ModelAndView getAdd() {
        ModelAndView mv = new ModelAndView(path + "blackListIpAdd");
//        mv.addObject("p", systemblackListIpServiceImpl.selectByPrimaryKey(id));
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
    @PreAuthorize("hasPermission('" + SystemUtil.SYSTEM_BUTTON_NAME + "','SYSTEM_BLACK_LIST_IP_MANAGEMENT:edit')")
    public ModelAndView getEdit(Long id) {
        ModelAndView mv = new ModelAndView(path + "blackListIpEdit");
        mv.addObject("p", systemBlackListIpServiceImpl.getByIdParam(id));
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
    @PreAuthorize("hasPermission('" + SystemUtil.SYSTEM_BUTTON_NAME + "','SYSTEM_BLACK_LIST_IP_MANAGEMENT:view')")
    public ModelAndView getView(Long id) {
        ModelAndView mv = new ModelAndView(path + "blackListIpView");
        mv.addObject("p", systemBlackListIpServiceImpl.getByIdParam(id));
        return mv;
    }

    /**
     * @param param 1 系统前端操作黑名单IP实体类
     * @return com.framework.common.response.ResponseResult
     * @Titel 系统前端操作黑名单IP分页查询
     * @Description 系统前端操作黑名单IP分页查询
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/22 17:58
     */
    // method = RequestMethod.POST,
    @RequestMapping(value = "/findPageList", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    @PreAuthorize("hasPermission('" + SystemUtil.SYSTEM_MENU_NAME + "','SYSTEM_BLACK_LIST_IP_MANAGEMENT')")
    public ResponseResult findPageList(SystemBlackListIp param) {
        try {
            return systemBlackListIpServiceImpl.findParamPageList(param);
        } catch (Exception e) {
            e.printStackTrace();
            return getError();
        }
    }

    /**
     * @param param 1 系统前端操作黑名单IP实体类
     * @return com.framework.common.response.ResponseResult
     * @Titel 新增
     * @Description 新增
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/22 17:58
     */
    @RequestMapping(value = "/save", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    @PreAuthorize("hasPermission('" + SystemUtil.SYSTEM_BUTTON_NAME + "','SYSTEM_BLACK_LIST_IP_MANAGEMENT:add')")
    public ResponseResult save(SystemBlackListIp param) {
        try {
            return systemBlackListIpServiceImpl.save(param);
        } catch (Exception e) {
            e.printStackTrace();
            return getError();
        }
    }

    /**
     * @param param 1 系统前端操作黑名单IP实体类
     * @return com.framework.common.response.ResponseResult
     * @Titel 修改
     * @Description 修改
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/22 17:59
     */
    @RequestMapping(value = "/edit", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    @PreAuthorize("hasPermission('" + SystemUtil.SYSTEM_BUTTON_NAME + "','SYSTEM_BLACK_LIST_IP_MANAGEMENT:edit')")
    public ResponseResult edit(SystemBlackListIp param) {
        try {
            return systemBlackListIpServiceImpl.edit(param);
        } catch (Exception e) {
            e.printStackTrace();
            return getError();
        }
    }

    /**
     * @param idList 1 系统前端操作黑名单IP编号集合
     * @return com.framework.common.response.ResponseResult
     * @Titel 批量删除
     * @Description 批量删除
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/22 18:00
     */
    @RequestMapping(value = "/batchDel", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ResponseBody
    @PreAuthorize("hasPermission('" + SystemUtil.SYSTEM_BUTTON_NAME + "','SYSTEM_BLACK_LIST_IP_MANAGEMENT:batchDel')")
    public ResponseResult del(@RequestParam(value = "idList[]") List<Long> idList) {
        try {
            return systemBlackListIpServiceImpl.batchDeleteList(idList);
        } catch (Exception e) {
            e.printStackTrace();
            return getError();
        }
    }

    /**
     * @param id 1 系统前端操作黑名单IP编号
     * @return com.framework.common.response.ResponseResult
     * @Titel 删除
     * @Description 删除
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/22 18:00
     */
    @RequestMapping(value = "/del", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ResponseBody
    @PreAuthorize("hasPermission('" + SystemUtil.SYSTEM_BUTTON_NAME + "','SYSTEM_BLACK_LIST_IP_MANAGEMENT:del')")
    public ResponseResult del(Long id) {
        try {
            return systemBlackListIpServiceImpl.batchDeleteList(Arrays.asList(id));
        } catch (Exception e) {
            e.printStackTrace();
            return getError();
        }
    }

//    /**
//     * @param id   1 系统接口操作白名单IP编号
//     * @param code 2 系统接口操作白名单代码
//     * @param ip   3 系统接口操作白名单IP
//     * @return com.framework.common.response.ResponseResult
//     * @Titel 验证是否重复系统接口操作白名单IP
//     * @Description 验证是否重复系统接口操作白名单IP
//     * @Author 邋遢龘鵺
//     * @DateTime 2019/12/22 18:04
//     */
//    @RequestMapping("/isExist")
//    @ResponseBody
//    @PreAuthorize("hasPermission('" + SystemUtil.SYSTEM_BUTTON_NAME + "','SYSTEM_BLACK_LIST_IP_MANAGEMENT:isExist')")
//    public ResponseResult isExist(Long id, String code, String ip) {
//        return systemBlackListIpServiceImpl.isExist(id, code, ip);
//    }

}
