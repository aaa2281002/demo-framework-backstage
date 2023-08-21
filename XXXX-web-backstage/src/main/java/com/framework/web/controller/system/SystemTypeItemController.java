package com.framework.web.controller.system;

import com.framework.common.annotation.QueryTarget;
import com.framework.common.model.validation.ValidationGroup;
import com.framework.common.response.ResponseResult;
import com.framework.common.util.other.NumeralUtil;
import com.framework.model.system.SystemType;
import com.framework.service.system.SystemTypeService;
import com.framework.web.base.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
@Api(tags = "类型子项", description = "系统综合管理")
@Validated
@Controller
@RequestMapping(value = "/system/type/item")
public class SystemTypeItemController extends BaseController {
    @Autowired
    private SystemTypeService systemTypeServiceImpl;

    /**
     * @param parentId 1 上级编号
     * @return com.framework.common.response.ResponseResult
     * @Title 根据编号查询信息
     * @Description 根据编号查询信息
     * @Author 龘鵺
     * @DateTime 2023/5/15 10:25
     */
    @GetMapping(value = "/get/by/parent/id/info", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    @ApiOperation(value = "根据上级编号查询信息", httpMethod = "GET", produces = "application/json", consumes = "text/html", response = ResponseResult.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "parentId", value = "上级编号", required = true,  paramType = "form"),
    })
    public ResponseResult getByParentIdInfo(
            @NotNull(message = "请选择上级") @Min(value = NumeralUtil.POSITIVE_ONE, message = "上级不存在") Long parentId) {
        return systemTypeServiceImpl.getByParentIdInfo(parentId);
    }

    /**
     * @param id       1 编号
     * @param parentId 2 上级编号
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
            @ApiImplicitParam(name = "parentId", value = "上级编号", required = true,  paramType = "query", example = "1-" + Long.MAX_VALUE),
    })
    public ResponseResult getByIdInfo(@NotNull(message = "请选择类型") @Min(value = NumeralUtil.POSITIVE_ONE, message = "类型不存在") Long id,
                                      @NotNull(message = "请选择上级") @Min(value = NumeralUtil.POSITIVE_ZERO, message = "上级不存在") Long parentId) {
        return systemTypeServiceImpl.getByIdInfo(id, parentId);
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
    @GetMapping(value = "/find/page/list", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    @QueryTarget
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码", required = true, paramType = "query", example = "1"),
            @ApiImplicitParam(name = "limit", value = "条数", required = true, paramType = "query", example = "10"),
            @ApiImplicitParam(name = "startTime", value = "开始时间", required = false, paramType = "query", example = "2023-01-01 00:00:00"),
            @ApiImplicitParam(name = "endTime", value = "结束时间", required = false, paramType = "query", example = "2023-01-30 23:59:59")
    })
    @ApiOperation(value = "分页查询", httpMethod = "GET", produces = "application/json", consumes = "text/html", response = ResponseResult.class)
    public ResponseResult findPageList(@Validated(value = {ValidationGroup.formQueryItem.class}) SystemType param) {
        return systemTypeServiceImpl.findParamPageList(param);
    }

    /**
     * @param param 1 类型实体类对象
     * @return com.framework.common.response.ResponseResult
     * @titel 新增
     * @description 新增
     * @author 邋遢龘鵺
     * @datetime 2019/12/22 17:58
     */
    @PostMapping(value = "/save", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    @ApiOperation(value = "新增", httpMethod = "POST", produces = "application/json", consumes = "application/json", response = ResponseResult.class)
    public ResponseResult save(@RequestBody @Validated(value = {ValidationGroup.formAddItem.class}) SystemType param, HttpServletResponse response) {
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
    @PutMapping(value = "/edit", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    @ApiOperation(value = "修改", httpMethod = "POST", produces = "application/json", consumes = "application/json", response = ResponseResult.class)
    public ResponseResult edit(@RequestBody @Validated(value = {ValidationGroup.formEditItem.class}) SystemType param) {
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
    @DeleteMapping(value = "/batch/del", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "idList[]", value = "编号数组", required = true,  paramType = "query", example = "[1,2,3]"),
            @ApiImplicitParam(name = "parentId", value = "上级编号", required = true,  paramType = "query", example = "1-" + Long.MAX_VALUE),
    })
    @ApiOperation(value = "批量删除", httpMethod = "DELETE", produces = "application/json", consumes = "text/html", response = ResponseResult.class)
    public ResponseResult batchDel(@NotEmpty(message = "请选择类型")
                                   @Size(min = NumeralUtil.POSITIVE_ONE, message = "类型不存在") @RequestParam(value = "idList[]") List<Long> idList,
                                   @NotNull(message = "请选择上级") @Min(value = NumeralUtil.POSITIVE_ONE, message = "上级不存在") Long parentId) {
        return systemTypeServiceImpl.batchDeleteList(idList, parentId);
    }

    /**
     * @param id 1 类型编号
     * @return com.framework.common.response.ResponseResult
     * @titel 删除
     * @description 删除
     * @author 邋遢龘鵺
     * @datetime 2019/12/22 18:00
     */
    @DeleteMapping(value = "/del", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "编号", required = true, paramType = "query", example = "1-" + Long.MAX_VALUE),
            @ApiImplicitParam(name = "parentId", value = "上级编号", required = true,  paramType = "query", example = "1-" + Long.MAX_VALUE),
    })
    //@ApiParam(value = "编号", required = true, example = "1")
    @ApiOperation(value = "删除", httpMethod = "DELETE", produces = "application/json", consumes = "text/html", response = ResponseResult.class)
    public ResponseResult del(@NotNull(message = "请选择类型") @Min(value = NumeralUtil.POSITIVE_ONE, message = "类型不存在")
                              @RequestParam(value = "id") Long id,
                              @NotNull(message = "请选择上级") @Min(value = NumeralUtil.POSITIVE_ONE, message = "上级不存在")
                              @RequestParam(value = "parentId") Long parentId) {
        return systemTypeServiceImpl.delId(id, parentId);
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
//    public ResponseResult isExist(Long id, String typeCode) {
//        return systemTypeServiceImpl.isExist(id, typeCode);
//    }

}