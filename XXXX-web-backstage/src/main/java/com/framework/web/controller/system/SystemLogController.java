package com.framework.web.controller.system;

import com.framework.common.annotation.QueryTarget;
import com.framework.common.model.validation.ValidationGroup;
import com.framework.common.response.ResponseResult;
import com.framework.common.util.other.NumeralUtil;
import com.framework.model.system.SystemLog;
import com.framework.service.system.SystemLogService;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author 邋遢龘鵺
 * @version 1.0
 * @className com.framework.web.controller.system
 * @description 登录日志请求控制类
 * @datetime 2019/10/11
 */
@Api(tags = "登录日志", description = "系统综合管理")
@Validated
@Controller
@RequestMapping(value = "/system/log")
public class SystemLogController extends BaseController {
    @Autowired
    private SystemLogService systemLogServiceImpl;

    /**
     * @param id 1 编号
     * @return com.framework.common.response.ResponseResult
     * @Title 根据编号查询信息
     * @Description 根据编号查询信息
     * @Author 龘鵺
     * @DateTime 2023/5/15 10:25
     */
    @GetMapping(value = "/get/by/id/info", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    @ApiOperation(value = "根据编号查询信息", httpMethod = "GET", produces = "application/json", consumes = "text/html", response = ResponseResult.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "编号", required = true, paramType = "query", example = "1-" + Long.MAX_VALUE),
    })
    public ResponseResult getByIdInfo(@NotNull(message = "请选择日志") @Min(value = NumeralUtil.POSITIVE_ONE, message = "日志不存在") Long id) {
        return systemLogServiceImpl.getByIdInfo(id);
    }

    /**
     * @param param 1 日志实体类
     * @return com.framework.common.response.ResponseResult
     * @titel 日志分页查询
     * @description 日志分页查询
     * @author 邋遢龘鵺
     * @datetime 2019/12/22 17:58
     */
    @GetMapping(value = "/find/page/list", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    @QueryTarget(isDefault = false)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码", required = true, paramType = "query", example = "1"),
            @ApiImplicitParam(name = "limit", value = "条数", required = true, paramType = "query", example = "10"),
            @ApiImplicitParam(name = "startTime", value = "开始时间", required = false, paramType = "query", example = "2023-01-01 00:00:00"),
            @ApiImplicitParam(name = "endTime", value = "结束时间", required = false, paramType = "query", example = "2023-01-30 23:59:59")
    })
    @ApiOperation(value = "分页查询", httpMethod = "GET", produces = "application/json", consumes = "text/html", response = ResponseResult.class)
    public ResponseResult findPageList(@Validated(value = {ValidationGroup.formPageQuery.class}) SystemLog param) {
        return systemLogServiceImpl.findParamPageList(param);
    }

}
