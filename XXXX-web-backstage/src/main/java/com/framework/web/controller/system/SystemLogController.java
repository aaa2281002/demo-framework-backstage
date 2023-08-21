package com.framework.web.controller.system;

import com.framework.common.annotation.QueryTarget;
import com.framework.common.model.validation.ValidationGroup;
import com.framework.common.response.ResponseResult;
import com.framework.common.util.other.NumeralUtil;
import com.framework.common.util.system.SystemUtil;
import com.framework.model.system.SystemLog;
import com.framework.service.system.SystemLogService;
import com.framework.web.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author 邋遢龘鵺
 * @version 1.0
 * @className com.framework.web.controller.system
 * @description 日志请求控制类
 * @datetime 2019/10/11
 */
@Validated
@Controller
@RequestMapping(value = "/system/log")
public class SystemLogController extends BaseController {
    private String path = "lowerright/system/logs/";
    @Autowired
    private SystemLogService systemLogServiceImpl;

    /**
     * @return org.springframework.web.servlet.ModelAndView
     * @titel 分页页面跳转
     * @description 分页页面跳转
     * @author 邋遢龘鵺
     * @datetime 2019/12/14 18:20
     */
    @RequestMapping("/page/list")
    @PreAuthorize("hasPermission('" + SystemUtil.SYSTEM_MENU_NAME + "','SYSTEM_LOG_LIST_MANAGEMENT')")
    public ModelAndView pageList() {
        return new ModelAndView(path + "logList");
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
    @PreAuthorize("hasPermission('" + SystemUtil.SYSTEM_BUTTON_NAME + "','SYSTEM_LOG_LIST_MANAGEMENT:view')")
    public ModelAndView getView(@NotNull(message = "请选择日志") @Min(value = NumeralUtil.POSITIVE_ONE, message = "日志不存在") Long id) {
        ModelAndView mv = new ModelAndView(path + "logView");
        mv.addObject("p", systemLogServiceImpl.getByIdParam(id));
        return mv;
    }

    /**
     * @param param 1 日志实体类
     * @return com.framework.common.response.ResponseResult
     * @titel 日志分页查询
     * @description 日志分页查询
     * @author 邋遢龘鵺
     * @datetime 2019/12/22 17:58
     */
    // method = RequestMethod.POST,
    @RequestMapping(value = "/find/page/list", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    @PreAuthorize("hasPermission('" + SystemUtil.SYSTEM_MENU_NAME + "','SYSTEM_LOG_LIST_MANAGEMENT')")
    @QueryTarget(isDefault = false)
    public ResponseResult findPageList(@Validated(value = {ValidationGroup.formPageQuery.class}) SystemLog param) {
        return systemLogServiceImpl.findParamPageList(param);
    }

}
