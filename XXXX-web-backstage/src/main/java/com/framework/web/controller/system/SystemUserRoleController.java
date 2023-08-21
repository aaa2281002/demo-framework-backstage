package com.framework.web.controller.system;

import com.framework.common.annotation.QueryTarget;
import com.framework.common.response.ResponseResult;
import com.framework.common.util.other.NumeralUtil;
import com.framework.model.system.SystemRole;
import com.framework.model.system.SystemUserRole;
import com.framework.service.system.SystemRoleService;
import com.framework.service.system.SystemUserRoleService;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * @author 邋遢龘鵺
 * @version 1.0
 * @className com.framework.web.controller.system
 * @description 用户角色关联请求控制类
 * @datetime 2019/10/11
 */
@Api(tags = "用户角色", description = "用户管理")
@Validated
@Controller
@RequestMapping(value = "/system/user/role")
public class SystemUserRoleController extends BaseController {
    private String path = "lowerright/system/userRole/";
    @Autowired
    private SystemUserRoleService systemUserRoleServiceImpl;
    @Autowired
    private SystemRoleService systemRoleServiceImpl;
//    @Autowired
//    private SystemUserService systemUserServiceImpl;

//    /**
//     * @param param 1 用户实体类对象
//     * @return com.framework.common.response.ResponseResult
//     * @title 用户分页查询
//     * @description 用户分页查询
//     * @author 邋遢龘鵺
//     * @datetime 2019/12/22 17:58
//     */
//    // method = RequestMethod.POST,
//    @GetMapping(value = "/find/user/role/user/page/list", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    @ResponseBody
//    @QueryTarget
//    public ResponseResult findUserRoleUserPageList(@Validated(value = {ValidationGroup.formPageQuery.class}) SystemUserVo param) {
//        return systemUserServiceImpl.findParamPageList(param);
//    }

    /**
     * @param param 1 用户实体类对象
     * @return com.framework.common.response.ResponseResult
     * @title 角色分页查询
     * @description 角色分页查询
     * @author 邋遢龘鵺
     * @datetime 2019/12/22 17:58
     */
    // method = RequestMethod.POST,
    @GetMapping(value = "/find/user/role/role/page/list", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    @QueryTarget
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码", required = true, paramType = "query", example = "1"),
            @ApiImplicitParam(name = "limit", value = "条数", required = true, paramType = "query", example = "10"),
            @ApiImplicitParam(name = "startTime", value = "开始时间", required = false, paramType = "query", example = "2023-01-01 00:00:00"),
            @ApiImplicitParam(name = "endTime", value = "结束时间", required = false, paramType = "query", example = "2023-01-30 23:59:59")
    })
    @ApiOperation(value = "角色分页查询", httpMethod = "GET", produces = "application/json", consumes = "text/html", response = ResponseResult.class)
    public ResponseResult findUserRoleRolePageList(SystemRole param) {
        return systemRoleServiceImpl.findUserByRolePageList(param);
    }

    /**
     * @param param 1 用户角色关联实体类
     * @return com.framework.common.response.ResponseResult
     * @title 根据条件查询集合关联选中方法
     * @description 根据条件查询集合关联选中方法
     * @author 邋遢龘鵺
     * @datetime 2019/12/22 18:21
     */
    @GetMapping(value = "/find/by/param/list", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户编号", required = false,  paramType = "query", example = "1-" + Long.MAX_VALUE),
            @ApiImplicitParam(name = "roleId", value = "角色编号", required = false,  paramType = "query", example = "1-" + Long.MAX_VALUE)
    })
    @ApiOperation(value = "根据条件查询集合关联选中", httpMethod = "GET", produces = "application/json", consumes = "text/html", response = ResponseResult.class)
    public ResponseResult findByParamList(SystemUserRole param) {
        return systemUserRoleServiceImpl.findByParamList(param);
    }

    /**
     * @param userId     1 用户编号
     * @param roleIdList 2 角色编号集合
     * @return com.framework.common.response.ResponseResult
     * @title 保存用户关联角色方法
     * @description 保存用户关联角色方法
     * @author 邋遢龘鵺
     * @datetime 2019/12/22 17:58
     */
    @PostMapping(value = "/save", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    @ApiOperation(value = "新增", httpMethod = "POST", produces = "application/json", consumes = "application/json", response = ResponseResult.class)
    public ResponseResult save(@NotNull(message = "请选择用户") @Min(value = NumeralUtil.POSITIVE_ONE, message = "用户不存在")
                               @RequestParam(value = "userId") Long userId,
                               @Size(max = NumeralUtil.POSITIVE_ONE, message = "最多只能关联一个角色")
                               @RequestParam(value = "roleIdList[]", required = false) List<Long> roleIdList) {
        return systemUserRoleServiceImpl.save(userId, roleIdList);
    }

}
