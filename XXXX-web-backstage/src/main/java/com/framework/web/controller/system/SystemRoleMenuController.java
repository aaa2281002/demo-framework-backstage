package com.framework.web.controller.system;

import com.framework.common.annotation.QueryTarget;
import com.framework.common.model.validation.ValidationGroup;
import com.framework.common.response.ResponseResult;
import com.framework.model.system.SystemRole;
import com.framework.model.system.vo.SystemRoleMenuVo;
import com.framework.service.system.SystemRoleMenuService;
import com.framework.service.system.SystemRoleService;
import com.framework.web.base.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 邋遢龘鵺
 * @version 1.0
 * @className com.framework.web.controller.system
 * @description 角色菜单请求控制类
 * @datetime 2019/10/11
 */
@Api(tags = "角色菜单", description = "权限管理")
@Validated
@Controller
@RequestMapping(value = "/system/role/menu")
public class SystemRoleMenuController extends BaseController {
    @Autowired
    private SystemRoleMenuService systemRoleMenuServiceImpl;
    @Autowired
    private SystemRoleService systemRoleServiceImpl;

    /**
     * @param param 1 角色实体类对象
     * @return com.framework.common.response.ResponseResult
     * @titel 角色分页查询
     * @description 角色分页查询
     * @author 邋遢龘鵺
     * @datetime 2019/12/22 17:58
     */
    @GetMapping(value = "/find/role/param/list", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    @QueryTarget
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码", required = true, paramType = "query", example = "1"),
            @ApiImplicitParam(name = "limit", value = "条数", required = true, paramType = "query", example = "10"),
            @ApiImplicitParam(name = "startTime", value = "开始时间", required = false, paramType = "query", example = "2023-01-01 00:00:00"),
            @ApiImplicitParam(name = "endTime", value = "结束时间", required = false, paramType = "query", example = "2023-01-30 23:59:59")
    })
    @ApiOperation(value = "分页查询", httpMethod = "GET", produces = "application/json", consumes = "text/html", response = ResponseResult.class)
    public ResponseResult findRoleParamList(@Validated(value = {ValidationGroup.formPageQuery.class}) SystemRole param) {
        return systemRoleServiceImpl.findParamPageList(param);
    }

    /**
     * @param id      1 编号
     * @param pId     2 上级编号
     * @param keyword 3 模糊搜索
     * @return com.framework.common.response.ResponseResult
     * @titel 菜单树形查询
     * @description 菜单树形查询
     * @author 邋遢龘鵺
     * @datetime 2019/12/22 18:22
     */
    @GetMapping(value = "/find/menu/tree/list", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roleId", value = "角色编号", required = true, paramType = "query", example = "1-" + Long.MAX_VALUE),
            @ApiImplicitParam(name = "id", value = "编号", required = true, paramType = "query", example = "1-" + Long.MAX_VALUE),
            @ApiImplicitParam(name = "pId", value = "上级编号", required = false, paramType = "query", example = "1-" + Long.MAX_VALUE),
            @ApiImplicitParam(name = "keyword", value = "搜索关键字", required = false, paramType = "query", example = "xxx")
    })
    @ApiOperation(value = "菜单树形查询", httpMethod = "GET", produces = "application/json", consumes = "text/html", response = ResponseResult.class)
    public ResponseResult findMenuTreeList(Long roleId, Long id, Long pId, String keyword) {
        return systemRoleMenuServiceImpl.findMenuTreeList(roleId, id, pId, keyword);
    }

//    /**
//     * @param roleId 1 角色编号
//     * @return com.framework.common.response.ResponseResult
//     * @Titel 根据编号查询关联信息
//     * @Description 根据编号查询关联信息
//     * @Author 龘鵺
//     * @DateTime 23/5/2023 下午6:30
//     */
//    @GetMapping(value = "/find/role/menu/list", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    @ResponseBody
//    public ResponseResult findRoleMenuList(Long roleId) {
//        return systemRoleMenuServiceImpl.findRoleMenuList(roleId);
//    }

//    /**
//     * @param param 1 角色菜单关联实体类
//     * @return com.framework.common.response.ResponseResult
//     * @titel 根据条件查询集合关联选中方法
//     * @description 根据条件查询集合关联选中方法
//     * @author 邋遢龘鵺
//     * @datetime 2019/12/22 18:21
//     */
//    @RequestMapping(value = "/find/by/param/list", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    @ResponseBody
//    public ResponseResult findByParamList(SystemRoleMenu param) {
//        return systemRoleMenuServiceImpl.findByParamList(param);
//    }

    /**
     * @param param 1 对象
     * @return com.framework.common.response.ResponseResult
     * @titel 保存角色关联菜单方法
     * @description 保存角色关联菜单方法
     * @author 邋遢龘鵺
     * @datetime 2019/12/22 17:58
     */
    @PostMapping(value = "/save", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    @ApiOperation(value = "新增", httpMethod = "POST", produces = "application/json", consumes = "application/json", response = ResponseResult.class)
    public ResponseResult save(@RequestBody @Validated(value = {ValidationGroup.formOther.class}) SystemRoleMenuVo param) {
        return systemRoleMenuServiceImpl.save(param);
    }


}
