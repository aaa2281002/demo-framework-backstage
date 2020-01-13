package com.framework.web.controller.system;

import com.framework.common.response.ResponseResult;
import com.framework.common.util.system.SystemUtil;
import com.framework.model.entity.system.SystemType;
import com.framework.service.service.system.SystemTypeService;
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

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Author 邋遢龘鵺
 * @ClassName com.framework.web.controller.system
 * @Description 类型请求控制类
 * @DateTime 2019/10/11
 * @Version 1.0
 */
@Controller
@RequestMapping(value = "/system/type")
public class SystemTypeController extends BaseController {
    private String path = "lowerright/system/type/";
    @Autowired
    private SystemTypeService systemTypeServiceImpl;

    /**
     * @return org.springframework.web.servlet.ModelAndView
     * @Titel 分页页面跳转
     * @Description 分页页面跳转
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/14 18:20
     */
    @RequestMapping("/page/list")
    @PreAuthorize("hasPermission('" + SystemUtil.SYSTEM_MENU_NAME + "','SYSTEM_TYPE_MANAGEMENT')")
    public ModelAndView pageList() {
        return new ModelAndView(path + "typeList");
    }

    /**
     * @return org.springframework.web.servlet.ModelAndView
     * @Titel 新增页面跳转
     * @Description 新增页面跳转
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/14 18:21
     */
    @RequestMapping("/get/add")
    @PreAuthorize("hasPermission('" + SystemUtil.SYSTEM_BUTTON_NAME + "','SYSTEM_TYPE_MANAGEMENT:add')")
    public ModelAndView getAdd() {
        ModelAndView mv = new ModelAndView(path + "typeAdd");
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
    @PreAuthorize("hasPermission('" + SystemUtil.SYSTEM_BUTTON_NAME + "','SYSTEM_TYPE_MANAGEMENT:edit')")
    public ModelAndView getEdit(Long id) {
        ModelAndView mv = new ModelAndView(path + "typeEdit");
        mv.addObject("p", systemTypeServiceImpl.getByIdParam(id));
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
    @PreAuthorize("hasPermission('" + SystemUtil.SYSTEM_BUTTON_NAME + "','SYSTEM_TYPE_MANAGEMENT:view')")
    public ModelAndView getView(Long id) {
        ModelAndView mv = new ModelAndView(path + "typeView");
        mv.addObject("p", systemTypeServiceImpl.getByIdParam(id));
        return mv;
    }

    /**
     * @param param 1 类型实体类对象
     * @return com.framework.common.response.ResponseResult
     * @Titel 类型分页查询
     * @Description 类型分页查询
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/22 17:58
     */
    // method = RequestMethod.POST,
    @RequestMapping(value = "/findPageList", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    @PreAuthorize("hasPermission('" + SystemUtil.SYSTEM_MENU_NAME + "','SYSTEM_TYPE_MANAGEMENT')")
    public ResponseResult findPageList(SystemType param) {
        try {
            return systemTypeServiceImpl.findParamPageList(param);
        } catch (Exception e) {
            e.printStackTrace();
            return getError();
        }
    }

    /**
     * @param param 1 类型实体类对象
     * @return com.framework.common.response.ResponseResult
     * @Titel 新增
     * @Description 新增
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/22 17:58
     */
    @RequestMapping(value = "/save", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    @PreAuthorize("hasPermission('" + SystemUtil.SYSTEM_BUTTON_NAME + "','SYSTEM_TYPE_MANAGEMENT:add')")
    public ResponseResult save(SystemType param, HttpServletResponse response) {
        try {
            return systemTypeServiceImpl.save(param);
        } catch (Exception e) {
            e.printStackTrace();
            return getError();
        }
    }

    /**
     * @param param 1 类型实体类对象
     * @return com.framework.common.response.ResponseResult
     * @Titel 修改
     * @Description 修改
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/22 17:59
     */
    @RequestMapping(value = "/edit", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    @PreAuthorize("hasPermission('" + SystemUtil.SYSTEM_BUTTON_NAME + "','SYSTEM_TYPE_MANAGEMENT:edit')")
    public ResponseResult edit(SystemType param) {
        try {
            return systemTypeServiceImpl.edit(param);
        } catch (Exception e) {
            e.printStackTrace();
            return getError();
        }
    }

    /**
     * @param idList 1 类型编号集合
     * @return com.framework.common.response.ResponseResult
     * @Titel 删除或批量删除
     * @Description 删除或批量删除
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/22 18:00
     */
    @RequestMapping(value = "/batchDel", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ResponseBody
    @PreAuthorize("hasPermission('" + SystemUtil.SYSTEM_BUTTON_NAME + "','SYSTEM_TYPE_MANAGEMENT:batchDel') or hasPermission('" + SystemUtil.SYSTEM_BUTTON_NAME + "','SYSTEM_TYPE_MANAGEMENT:del')")
    public ResponseResult del(@RequestParam(value = "idList[]") List<Long> idList) {
        try {
            return systemTypeServiceImpl.batchDeleteList(idList);
        } catch (Exception e) {
            e.printStackTrace();
            return getError();
        }
    }

    /**
     * @param id       1 类型编号
     * @param typeCode 2 类型代码
     * @return com.framework.common.response.ResponseResult
     * @Titel 验证是否重复类型code
     * @Description 验证是否重复类型code
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/22 18:00
     */
    @RequestMapping("/isExist")
    @ResponseBody
    @PreAuthorize("hasPermission('" + SystemUtil.SYSTEM_BUTTON_NAME + "','SYSTEM_TYPE_MANAGEMENT:isExist')")
    public ResponseResult isExist(Long id, String typeCode) {
        return systemTypeServiceImpl.isExist(id, typeCode);
    }

}
