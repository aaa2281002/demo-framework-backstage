package com.framework.model.other;

import com.framework.common.annotation.QueryParam;
import com.framework.model.base.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.List;

/**
 * @author 邋遢龘鵺
 * @version 1.0
 * @className com.framework.model.other
 * @description 文件信息类
 * @datetime 2019/10/11
 */
@ApiModel(value = "附件", description = "附件", parent = BaseModel.class)
public class FileInfo extends BaseModel implements Serializable {
    private static final long serialVersionUID = -1L;
    //所属编号,对应哪个菜单下数据编号
    @ApiModelProperty(value = "业务编号，对应其他实体类的ID=编号", name = "businessId", dataType = "Long", required = true, allowableValues = "20", hidden = false, example = "1-" + Long.MAX_VALUE, position = 100)
    @QueryParam(code = "p.BUSINESS_ID")
    private Long businessId;
    //唯一代码
    @ApiModelProperty(value = "唯一代码", name = "code", dataType = "String", required = true, allowableValues = "32", hidden = false, example = "xxxxxxxx", position = 101)
    @QueryParam(code = "p.code")
    private String code;
    //文件名
    @ApiModelProperty(value = "原文件名称", name = "fileName", dataType = "String", required = true, allowableValues = "255", hidden = false, example = "文件.doc", position = 102)
    @QueryParam(code = "p.FILE_NAME")
    private String fileName;
    //文件类型(1=txt,2=doc等。需要根据实际环境定义), FileTypeEnum枚举类
    @ApiModelProperty(value = "文件类型:1=txt,2=doc等", name = "fileType", dataType = "Integer", required = true, allowableValues = "11", hidden = false, example = "1", position = 103)
    @QueryParam(code = "p.FILE_TYPE")
    private Integer fileType;
    //文件路径,用了中间件的话，这个存储的就是对应的key
    @ApiModelProperty(value = "文件路径", name = "filePath", dataType = "String", required = true, allowableValues = "255", hidden = false, example = "/path/文件.doc", position = 104)
    @QueryParam(code = "p.FILE_PATH")
    private String filePath;

    //以下临时字段

    //上传文件集合对象
    @ApiModelProperty(value = "上传文件集合对象", name = "file", dataType = "List", required = true, hidden = false, example = "[]")
    private List<MultipartFile> file;

    public Long getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Long businessId) {
        this.businessId = businessId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName == null ? null : fileName.trim();
    }

    public Integer getFileType() {
        return fileType;
    }

    public void setFileType(Integer fileType) {
        this.fileType = fileType;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath == null ? null : filePath.trim();
    }

    public List<MultipartFile> getFile() {
        return file;
    }

    public void setFile(List<MultipartFile> file) {
        this.file = file;
    }
}