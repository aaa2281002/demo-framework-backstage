package com.framework.web.controller.system;

import com.framework.common.annotation.QueryTarget;
import com.framework.common.model.validation.ValidationGroup;
import com.framework.common.response.ResponseResult;
import com.framework.common.util.other.NumeralUtil;
import com.framework.common.util.system.SystemUtil;
import com.framework.model.system.SystemType;
import com.framework.service.system.SystemTypeService;
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

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * @author 邋遢龘鵺
 * @version 1.0
 * @className com.framework.web.controller.system
 * @description 类型请求控制类
 * @datetime 2019/10/11
 */
@Validated
@Controller
@RequestMapping(value = "/system/type")
public class SystemTypeController extends BaseController {
    private String path = "lowerright/system/type/";
    @Autowired
    private SystemTypeService systemTypeServiceImpl;

    /**
     * @return org.springframework.web.servlet.ModelAndView
     * @titel 分页页面跳转
     * @description 分页页面跳转
     * @author 邋遢龘鵺
     * @datetime 2019/12/14 18:20
     */
    @RequestMapping("/page/list")
    @PreAuthorize("hasPermission('" + SystemUtil.SYSTEM_MENU_NAME + "','SYSTEM_TYPE_LIST_MANAGEMENT')")
    public ModelAndView pageList() {
        return new ModelAndView(path + "typeList");
    }

    /**
     * @return org.springframework.web.servlet.ModelAndView
     * @titel 新增页面跳转
     * @description 新增页面跳转
     * @author 邋遢龘鵺
     * @datetime 2019/12/14 18:21
     */
    @RequestMapping("/get/add")
    @PreAuthorize("hasPermission('" + SystemUtil.SYSTEM_BUTTON_NAME + "','SYSTEM_TYPE_LIST_MANAGEMENT:add')")
    public ModelAndView getAdd() {
        ModelAndView mv = new ModelAndView(path + "typeAdd");
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
    @PreAuthorize("hasPermission('" + SystemUtil.SYSTEM_BUTTON_NAME + "','SYSTEM_TYPE_LIST_MANAGEMENT:edit')")
    public ModelAndView getEdit(@NotNull(message = "请选择类型") @Min(value = NumeralUtil.POSITIVE_ONE, message = "类型不存在") Long id) {
        ModelAndView mv = new ModelAndView(path + "typeEdit");
        mv.addObject("p", systemTypeServiceImpl.getByIdParam(id));
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
    @PreAuthorize("hasPermission('" + SystemUtil.SYSTEM_BUTTON_NAME + "','SYSTEM_TYPE_LIST_MANAGEMENT:view')")
    public ModelAndView getView(@NotNull(message = "请选择类型") @Min(value = NumeralUtil.POSITIVE_ONE, message = "类型不存在") Long id) {
        ModelAndView mv = new ModelAndView(path + "typeView");
        mv.addObject("p", systemTypeServiceImpl.getByIdParam(id));
        return mv;
    }

    /**
     * @param param 1 类型实体类对象
     * @return com.framework.common.response.ResponseResult
     * @titel 类型分页查询
     * @description 类型分页查询
     * @author 邋遢龘鵺
     * @datetime 2019/12/22 17:58
     */
    // method = RequestMethod.POST,
    @RequestMapping(value = "/find/page/list", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    @PreAuthorize("hasPermission('" + SystemUtil.SYSTEM_MENU_NAME + "','SYSTEM_TYPE_LIST_MANAGEMENT')")
    @QueryTarget
    public ResponseResult findPageList(@Validated(value = {ValidationGroup.formPageQuery.class}) SystemType param) {
        return systemTypeServiceImpl.findParamPageListParent(param);
    }

    /**
     * @param param 1 类型实体类对象
     * @return com.framework.common.response.ResponseResult
     * @titel 新增
     * @description 新增
     * @author 邋遢龘鵺
     * @datetime 2019/12/22 17:58
     */
    @RequestMapping(value = "/save", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    @PreAuthorize("hasPermission('" + SystemUtil.SYSTEM_BUTTON_NAME + "','SYSTEM_TYPE_LIST_MANAGEMENT:add')")
    public ResponseResult save(@Validated(value = {ValidationGroup.formAdd.class}) SystemType param, HttpServletResponse response) {
        return systemTypeServiceImpl.save(param);
    }

    /**
     * @param param 1 类型实体类对象
     * @return com.framework.common.response.ResponseResult
     * @titel 修改
     * @description 修改
     * @author 邋遢龘鵺
     * @datetime 2019/12/22 17:59
     */
    @RequestMapping(value = "/edit", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    @PreAuthorize("hasPermission('" + SystemUtil.SYSTEM_BUTTON_NAME + "','SYSTEM_TYPE_LIST_MANAGEMENT:edit')")
    public ResponseResult edit(@Validated(value = {ValidationGroup.formEdit.class}) SystemType param) {
        return systemTypeServiceImpl.edit(param);
    }

    /**
     * @param idList 1 类型编号集合
     * @return com.framework.common.response.ResponseResult
     * @titel 批量删除
     * @description 批量删除
     * @author 邋遢龘鵺
     * @datetime 2019/12/22 18:00
     */
    @RequestMapping(value = "/batch/del", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ResponseBody
    @PreAuthorize("hasPermission('" + SystemUtil.SYSTEM_BUTTON_NAME + "','SYSTEM_TYPE_LIST_MANAGEMENT:batchDel')")
    public ResponseResult batchDel(@NotEmpty(message = "请选择类型") @Size(min = NumeralUtil.POSITIVE_ONE,
            message = "类型不存在") @RequestParam(value = "idList[]") List<Long> idList) {
        return systemTypeServiceImpl.batchDeleteList(idList, NumeralUtil.MULTIPLEXING_LONG_POSITIVE_ZERO);
    }

    /**
     * @param id 1 类型编号
     * @return com.framework.common.response.ResponseResult
     * @titel 删除
     * @description 删除
     * @author 邋遢龘鵺
     * @datetime 2019/12/22 18:00
     */
    @RequestMapping(value = "/del", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ResponseBody
    @PreAuthorize("hasPermission('" + SystemUtil.SYSTEM_BUTTON_NAME + "','SYSTEM_TYPE_LIST_MANAGEMENT:del')")
    public ResponseResult del(@NotNull(message = "请选择类型") @Min(value = NumeralUtil.POSITIVE_ONE, message = "类型不存在") Long id) {
        return systemTypeServiceImpl.delId(id, NumeralUtil.MULTIPLEXING_LONG_POSITIVE_ZERO);
    }

//    /**
//     * @param id       1 类型编号
//     * @param typeCode 2 类型代码
//     * @return com.framework.common.response.ResponseResult
//     * @titel 验证是否重复类型code
//     * @description 验证是否重复类型code
//     * @author 邋遢龘鵺
//     * @datetime 2019/12/22 18:00
//     */
//    @RequestMapping("/isExist")
//    @ResponseBody
//    @PreAuthorize("hasPermission('" + SystemUtil.SYSTEM_BUTTON_NAME + "','SYSTEM_TYPE_LIST_MANAGEMENT:isExist')")
//    public ResponseResult isExist(Long id, String typeCode) {
//        return systemTypeServiceImpl.isExist(id, typeCode);
//    }

}
