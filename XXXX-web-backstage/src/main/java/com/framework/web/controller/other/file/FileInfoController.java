package com.framework.web.controller.other.file;

import com.framework.common.response.ResponseResult;
import com.framework.model.entity.other.FileInfo;
import com.framework.service.service.other.FileInfoService;
import com.framework.web.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Author 邋遢龘鵺
 * @ClassName com.framework.web.controller.other.file
 * @Description 文件信息请求控制类
 * @DateTime 2019/10/11
 * @Version 1.0
 */
@Controller
@RequestMapping(value = "/other/file/info")
public class FileInfoController extends BaseController {
    @Autowired
    private FileInfoService fileInfoServiceImpl;

    /**
     * @param param 1 附件实体类
     * @return com.framework.common.response.ResponseResult
     * @Titel 附件分页查询分页查询
     * @Description 附件分页查询分页查询
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/22 17:58
     */
    // method = RequestMethod.POST,
    @RequestMapping(value = "/findPageList", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseResult findPageList(FileInfo param) {
        try {
            return fileInfoServiceImpl.findParamPageList(param);
        } catch (Exception e) {
            e.printStackTrace();
            return getError();
        }
    }

    /**
     * @param param 1 附件实体类
     * @return com.framework.common.response.ResponseResult
     * @Titel 附件查询
     * @Description 附件查询
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/22 17:58
     */
    @RequestMapping(value = "/findParamList", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseResult findParamList(FileInfo param) {
        try {
            return fileInfoServiceImpl.findParamList(param);
        } catch (Exception e) {
            e.printStackTrace();
            return getError();
        }
    }

    /**
     * @param param 1 附件实体类
     * @return com.framework.common.response.ResponseResult
     * @Titel 新增
     * @Description 新增
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/22 17:58
     */
    @RequestMapping(value = "/save", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseResult save(FileInfo param) {
        try {
            return fileInfoServiceImpl.save(param);
        } catch (Exception e) {
            e.printStackTrace();
            return getError();
        }
    }

    /**
     * @param idList 1 附件编号集合
     * @return com.framework.common.response.ResponseResult
     * @Titel 批量删除
     * @Description 批量删除
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/22 18:00
     */
    @RequestMapping(value = "/batchDel", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult batchDel(@RequestParam(value = "idList[]") List<Long> idList) {
        try {
            return fileInfoServiceImpl.batchDeleteList(idList);
        } catch (Exception e) {
            e.printStackTrace();
            return getError();
        }
    }

    /**
     * @param id 1 附件编号
     * @return com.framework.common.response.ResponseResult
     * @Titel 删除
     * @Description 删除
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/22 18:00
     */
    @RequestMapping(value = "/del", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseResult del(Long id) {
        try {
            return fileInfoServiceImpl.delId(id);
        } catch (Exception e) {
            e.printStackTrace();
            return getError();
        }
    }

    /**
     * @param param 1 上传参数对象类
     * @return com.framework.common.response.ResponseResult
     * @Titel 上传
     * @Description 上传
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/27 15:32
     */
    @RequestMapping(value = "/upload", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseResult upload(FileInfo param) {
        try {
            return fileInfoServiceImpl.upload(param);
        } catch (Exception e) {
            e.printStackTrace();
            return getError();
        }
    }

    /**
     * @param param    1 参数对象
     * @param response 2 下载传输对象
     * @Titel 下载
     * @Description 下载
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/27 15:32
     */
    @RequestMapping(value = "/download", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public void download(FileInfo param, HttpServletResponse response) {

    }
}
