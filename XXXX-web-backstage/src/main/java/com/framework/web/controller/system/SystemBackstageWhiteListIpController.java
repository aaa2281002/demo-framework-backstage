package com.framework.web.controller.system;

import com.framework.common.annotation.QueryTarget;
import com.framework.common.model.validation.ValidationGroup;
import com.framework.common.response.ResponseResult;
import com.framework.common.util.other.NumeralUtil;
import com.framework.common.util.system.SystemUtil;
import com.framework.model.system.SystemBackstageWhiteListIp;
import com.framework.service.system.SystemBackstageWhiteListIpService;
import com.framework.web.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Arrays;
import java.util.List;

/**
 * @author 邋遢龘鵺
 * @version 1.0
 * @className com.framework.web.controller.system
 * @description 系统后台白名单IP请求控制类
 * @datetime 2019/10/11
 */
@Validated
@Controller
@RequestMapping(value = "/system/backstage/white/list/ip")
public class SystemBackstageWhiteListIpController extends BaseController {
    private String path = "lowerright/system/backstageWhiteListIp/";
    @Autowired
    private SystemBackstageWhiteListIpService systemBackstageWhiteListIpServiceImpl;

    /**
     * @return org.springframework.web.servlet.ModelAndView
     * @titel 分页页面跳转
     * @description 分页页面跳转
     * @author 邋遢龘鵺
     * @datetime 2019/12/14 18:20
     */
    @RequestMapping("/page/list")
    @PreAuthorize("hasPermission('" + SystemUtil.SYSTEM_MENU_NAME + "','SYSTEM_BACKSTAGE_WHITE_LIST_IP_MANAGEMENT')")
    public ModelAndView pageList() {
        return new ModelAndView(path + "backstageWhiteListIpList");
    }

    /**
     * @return org.springframework.web.servlet.ModelAndView
     * @titel 新增页面跳转
     * @description 新增页面跳转
     * @author 邋遢龘鵺
     * @datetime 2019/12/14 18:21
     */
    @RequestMapping("/get/add")
    @PreAuthorize("hasPermission('" + SystemUtil.SYSTEM_BUTTON_NAME + "','SYSTEM_BACKSTAGE_WHITE_LIST_IP_MANAGEMENT:add')")
    public ModelAndView getAdd() {
        ModelAndView mv = new ModelAndView(path + "backstageWhiteListIpAdd");
//        mv.addObject("p", systemBackstageWhiteListIpServiceImpl.selectByPrimaryKey(id));
        return mv;
    }

    /**
     * @param id 1 编号
     * @return org.springframework.web.servlet.ModelAndView
     * @titel 编辑页面跳转
     * @description 编辑页面跳转
     * @author 邋遢龘鵺
     * @datetime 2019/12/14 18:21
     */
    @RequestMapping("/get/edit")
    @PreAuthorize("hasPermission('" + SystemUtil.SYSTEM_BUTTON_NAME + "','SYSTEM_BACKSTAGE_WHITE_LIST_IP_MANAGEMENT:edit')")
    public ModelAndView getEdit(@NotNull(message = "请选择后台白名单") @Min(value = NumeralUtil.POSITIVE_ONE, message = "后台白名单不存在") Long id) {
        ModelAndView mv = new ModelAndView(path + "backstageWhiteListIpEdit");
        mv.addObject("p", systemBackstageWhiteListIpServiceImpl.getByIdParam(id));
        return mv;
    }

    /**
     * @param id 1 编号
     * @return org.springframework.web.servlet.ModelAndView
     * @titel 查看页面跳转
     * @description 查看页面跳转
     * @author 邋遢龘鵺
     * @datetime 2019/12/14 18:21
     */
    @RequestMapping("/get/view")
    @PreAuthorize("hasPermission('" + SystemUtil.SYSTEM_BUTTON_NAME + "','SYSTEM_BACKSTAGE_WHITE_LIST_IP_MANAGEMENT:view')")
    public ModelAndView getView(@NotNull(message = "请选择后台白名单") @Min(value = NumeralUtil.POSITIVE_ONE, message = "后台白名单不存在") Long id) {
        ModelAndView mv = new ModelAndView(path + "backstageWhiteListIpView");
        mv.addObject("p", systemBackstageWhiteListIpServiceImpl.getByIdParam(id));
        return mv;
    }

    /**
     * @param param 1 系统后台白名单IP实体类
     * @return com.framework.common.response.ResponseResult
     * @titel 系统后台白名单IP分页查询
     * @description 系统后台白名单IP分页查询
     * @author 邋遢龘鵺
     * @datetime 2019/12/22 17:58
     */
    // method = RequestMethod.POST,
    @RequestMapping(value = "/find/page/list", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    @PreAuthorize("hasPermission('" + SystemUtil.SYSTEM_MENU_NAME + "','SYSTEM_BACKSTAGE_WHITE_LIST_IP_MANAGEMENT')")
    @QueryTarget
    public ResponseResult findPageList(@Validated(value = {ValidationGroup.formPageQuery.class}) SystemBackstageWhiteListIp param) {
        return systemBackstageWhiteListIpServiceImpl.findParamPageList(param);
    }

    /**
     * @param param 1 系统后台白名单IP实体类
     * @return com.framework.common.response.ResponseResult
     * @titel 新增
     * @description 新增
     * @author 邋遢龘鵺
     * @datetime 2019/12/22 17:58
     */
    @RequestMapping(value = "/save", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    @PreAuthorize("hasPermission('" + SystemUtil.SYSTEM_BUTTON_NAME + "','SYSTEM_BACKSTAGE_WHITE_LIST_IP_MANAGEMENT:add')")
    public ResponseResult save(@Validated(value = {ValidationGroup.formAdd.class}) SystemBackstageWhiteListIp param) {
        return systemBackstageWhiteListIpServiceImpl.save(param);
    }

    /**
     * @param param 1 系统后台白名单IP实体类
     * @return com.framework.common.response.ResponseResult
     * @titel 修改
     * @description 修改
     * @author 邋遢龘鵺
     * @datetime 2019/12/22 17:59
     */
    @RequestMapping(value = "/edit", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    @PreAuthorize("hasPermission('" + SystemUtil.SYSTEM_BUTTON_NAME + "','SYSTEM_BACKSTAGE_WHITE_LIST_IP_MANAGEMENT:edit')")
    public ResponseResult edit(@Validated(value = {ValidationGroup.formEdit.class}) SystemBackstageWhiteListIp param) {
        return systemBackstageWhiteListIpServiceImpl.edit(param);
    }

    /**
     * @param idList 1 系统后台白名单IP编号集合
     * @return com.framework.common.response.ResponseResult
     * @titel 批量删除
     * @description 批量删除
     * @author 邋遢龘鵺
     * @datetime 2019/12/22 18:00
     */
    @RequestMapping(value = "/batch/del", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ResponseBody
    @PreAuthorize("hasPermission('" + SystemUtil.SYSTEM_BUTTON_NAME + "','SYSTEM_BACKSTAGE_WHITE_LIST_IP_MANAGEMENT:batchDel')")
    public ResponseResult batchDel(@NotEmpty(message = "请选择后台白名单") @Size(min = NumeralUtil.POSITIVE_ONE,
            message = "后台白名单不存在") @RequestParam(value = "idList[]") List<Long> idList) {
        return systemBackstageWhiteListIpServiceImpl.batchDeleteList(idList);
    }

    /**
     * @param id 1 系统后台白名单IP编号
     * @return com.framework.common.response.ResponseResult
     * @titel 删除
     * @description 删除
     * @author 邋遢龘鵺
     * @datetime 2019/12/22 18:00
     */
    @RequestMapping(value = "/del", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ResponseBody
    @PreAuthorize("hasPermission('" + SystemUtil.SYSTEM_BUTTON_NAME + "','SYSTEM_BACKSTAGE_WHITE_LIST_IP_MANAGEMENT:del')")
    public ResponseResult del(@NotNull(message = "请选择后台白名单") @Min(value = NumeralUtil.POSITIVE_ONE, message = "后台白名单不存在") Long id) {
        return systemBackstageWhiteListIpServiceImpl.batchDeleteList(Arrays.asList(id));
    }

//    /**
//     * @param id   1 系统后台白名单IP编号
//     * @param code 2 系统后台白名单代码
//     * @param ip   3 系统后台白名单IP
//     * @return com.framework.common.response.ResponseResult
//     * @titel 验证是否重复系统后台白名单IP
//     * @description 验证是否重复系统后台白名单IP
//     * @author 邋遢龘鵺
//     * @datetime 2019/12/22 18:04
//     */
//    @RequestMapping("/isExist")
//    @ResponseBody
//    @PreAuthorize("hasPermission('" + SystemUtil.SYSTEM_BUTTON_NAME + "','SYSTEM_BACKSTAGE_WHITE_LIST_IP_MANAGEMENT:isExist')")
//    public ResponseResult isExist(Long id, String code, String ip) {
//        return systemBackstageWhiteListIpServiceImpl.isExist(id, code, ip);
//    }

}
