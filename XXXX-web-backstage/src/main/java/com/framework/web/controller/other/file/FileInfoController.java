package com.framework.web.controller.other.file;

import com.framework.common.annotation.QueryTarget;
import com.framework.common.model.validation.ValidationGroup;
import com.framework.common.response.ResponseResult;
import com.framework.common.util.other.NumeralUtil;
import com.framework.model.other.FileInfo;
import com.framework.service.other.FileInfoService;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author 邋遢龘鵺
 * @version 1.0
 * @className com.framework.web.controller.other.file
 * @description 文件信息请求控制类
 * @datetime 2019/10/11
 */
@Api(tags = "附件", description = "附件")
@Controller
@RequestMapping(value = "/other/file/info")
public class FileInfoController extends BaseController {
    @Autowired
    private FileInfoService fileInfoServiceImpl;

    /**
     * @param param 1 附件实体类
     * @return com.framework.common.response.ResponseResult
     * @title 附件分页查询
     * @description 附件分页查询
     * @author 邋遢龘鵺
     * @datetime 2019/12/22 17:58
     */
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
    public ResponseResult findPageList(@Validated(value = {ValidationGroup.formPageQuery.class}) FileInfo param) {
        return fileInfoServiceImpl.findParamPageList(param);
    }

    /**
     * @param param 1 附件实体类
     * @return com.framework.common.response.ResponseResult
     * @title 附件查询
     * @description 附件查询
     * @author 邋遢龘鵺
     * @datetime 2019/12/22 17:58
     */
    @GetMapping(value = "/find/param/list", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    @ApiOperation(value = "附件查询", httpMethod = "GET", produces = "application/json", consumes = "text/html", response = ResponseResult.class)
    public ResponseResult findParamList(FileInfo param) {
        return fileInfoServiceImpl.findParamList(param);
    }

    /**
     * @param param 1 附件实体类
     * @return com.framework.common.response.ResponseResult
     * @title 新增
     * @description 新增
     * @author 邋遢龘鵺
     * @datetime 2019/12/22 17:58
     */
    @PostMapping(value = "/save", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    @ApiOperation(value = "新增", httpMethod = "POST", produces = "application/json", consumes = "application/json", response = ResponseResult.class)
    public ResponseResult save(@RequestBody FileInfo param) {
        return fileInfoServiceImpl.save(param);
    }

    /**
     * @param idList 1 附件编号集合
     * @return com.framework.common.response.ResponseResult
     * @title 批量删除
     * @description 批量删除
     * @author 邋遢龘鵺
     * @datetime 2019/12/22 18:00
     */
    @DeleteMapping(value = "/batch/del", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "idList[]", value = "编号数组", required = true, paramType = "query", example = "[1,2,3]"),
    })
    @ApiOperation(value = "批量删除", httpMethod = "DELETE", produces = "application/json", consumes = "text/html", response = ResponseResult.class)
    public ResponseResult batchDel(@RequestParam(value = "idList[]") List<Long> idList) {
        return fileInfoServiceImpl.batchDeleteList(idList);
    }

    /**
     * @param id 1 附件编号
     * @return com.framework.common.response.ResponseResult
     * @title 删除
     * @description 删除
     * @author 邋遢龘鵺
     * @datetime 2019/12/22 18:00
     */
    @DeleteMapping(value = "/del", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "编号", required = true, paramType = "query", example = "1-" + Long.MAX_VALUE),
    })
    //@ApiParam(value = "编号", required = true, example = "1")
    @ApiOperation(value = "删除", httpMethod = "DELETE", produces = "application/json", consumes = "text/html", response = ResponseResult.class)
    public ResponseResult del(@NotNull(message = "请选择附件") @Min(value = NumeralUtil.POSITIVE_ONE, message = "附件不存在")
                              @RequestParam(value = "id") Long id) {
        return fileInfoServiceImpl.delId(id);
    }

    /**
     * @param param 1 上传参数对象类
     * @return com.framework.common.response.ResponseResult
     * @title 上传
     * @description 上传
     * @author 邋遢龘鵺
     * @datetime 2019/12/27 15:32
     */
    @PostMapping(value = "/upload", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    @ApiOperation(value = "上传", httpMethod = "POST", produces = "application/json", consumes = "application/json", response = ResponseResult.class)
    public ResponseResult upload(FileInfo param) throws Exception {
        return fileInfoServiceImpl.upload(param);
    }

//    /**
//     * @param param    1 参数对象
//     * @param response 2 下载传输对象
//     * @title 下载
//     * @description 下载
//     * @author 邋遢龘鵺
//     * @datetime 2019/12/27 15:32
//     */
//    @ApiOperation(value = "下载", httpMethod = "POST", produces = "application/json", consumes = "application/json", response = ResponseResult.class)
//    @PostMapping(value = "/download")
//    @ResponseBody
//    public void download(FileInfo param, HttpServletResponse response) {
//
//    }
}
