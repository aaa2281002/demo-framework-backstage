package com.framework.web.controller.system;

import com.framework.common.response.ResponseResult;
import com.framework.common.util.system.SystemUtil;
import com.framework.model.entity.system.SystemLog;
import com.framework.service.service.system.SystemLogService;
import com.framework.web.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Author 邋遢龘鵺
 * @ClassName com.framework.web.controller.system
 * @Description 日志请求控制类
 * @DateTime 2019/10/11
 * @Version 1.0
 */
@Controller
@RequestMapping(value = "/system/log")
public class SystemLogController extends BaseController {
    private String path = "lowerright/system/logs/";
    @Autowired
    private SystemLogService systemLogServiceImpl;

    /**
     * @return org.springframework.web.servlet.ModelAndView
     * @Titel 分页页面跳转
     * @Description 分页页面跳转
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/14 18:20
     */
    @RequestMapping("/page/list")
    @PreAuthorize("hasPermission('" + SystemUtil.SYSTEM_MENU_NAME + "','SYSTEM_LOG_LIST_MANAGEMENT')")
    public ModelAndView pageList() {
        return new ModelAndView(path + "logList");
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
    @PreAuthorize("hasPermission('" + SystemUtil.SYSTEM_BUTTON_NAME + "','SYSTEM_LOG_LIST_MANAGEMENT:view')")
    public ModelAndView getView(Long id) {
        ModelAndView mv = new ModelAndView(path + "logView");
        mv.addObject("p", systemLogServiceImpl.getByIdParam(id));
        return mv;
    }

    /**
     * @param param 1 日志实体类
     * @return com.framework.common.response.ResponseResult
     * @Titel 日志分页查询
     * @Description 日志分页查询
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/22 17:58
     */
    // method = RequestMethod.POST,
    @RequestMapping(value = "/findPageList", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    @PreAuthorize("hasPermission('" + SystemUtil.SYSTEM_MENU_NAME + "','SYSTEM_LOG_LIST_MANAGEMENT')")
    public ResponseResult findPageList(SystemLog param) {
        return systemLogServiceImpl.findParamPageList(param);
    }

}
