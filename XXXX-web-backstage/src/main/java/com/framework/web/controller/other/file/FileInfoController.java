package com.framework.web.controller.other.file;

import com.framework.common.annotation.QueryTarget;
import com.framework.common.model.validation.ValidationGroup;
import com.framework.common.response.ResponseResult;
import com.framework.model.other.FileInfo;
import com.framework.service.other.FileInfoService;
import com.framework.web.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author 邋遢龘鵺
 * @version 1.0
 * @className com.framework.web.controller.other.file
 * @description 文件信息请求控制类
 * @datetime 2019/10/11
 */
@Controller
@RequestMapping(value = "/other/file/info")
public class FileInfoController extends BaseController {
    @Autowired
    private FileInfoService fileInfoServiceImpl;

    /**
     * @param param 1 附件实体类
     * @return com.framework.common.response.ResponseResult
     * @title 附件分页查询分页查询
     * @description 附件分页查询分页查询
     * @author 邋遢龘鵺
     * @datetime 2019/12/22 17:58
     */
    // method = RequestMethod.POST,
    @RequestMapping(value = "/find/page/list", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    @QueryTarget
    public ResponseResult findPageList(@Validated(value = {ValidationGroup .formPageQuery.class}) FileInfo param) {
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
    @RequestMapping(value = "/find/param/list", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
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
    @RequestMapping(value = "/save", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseResult save(FileInfo param) {
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
    @RequestMapping(value = "/batch/del", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ResponseBody
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
    @RequestMapping(value = "/del", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseResult del(Long id) {
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
    @RequestMapping(value = "/upload", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseResult upload(FileInfo param) throws Exception {
        return fileInfoServiceImpl.upload(param);
    }

    /**
     * @param param    1 参数对象
     * @param response 2 下载传输对象
     * @title 下载
     * @description 下载
     * @author 邋遢龘鵺
     * @datetime 2019/12/27 15:32
     */
    @RequestMapping(value = "/download", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public void download(FileInfo param, HttpServletResponse response) {

    }
}
