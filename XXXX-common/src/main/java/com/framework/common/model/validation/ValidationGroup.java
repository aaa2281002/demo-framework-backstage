package com.framework.common.model.validation;

/**
 * @author 龘鵺
 * @version 1.0
 * @className com.framework.common.model.validation
 * @description 校验分组类
 * @date 2022/4/1 11:59
 */
public class ValidationGroup {
    //查询分组
    public interface formPageQuery{};
    public interface formQuery{};
    public interface formQueryItem{};
    //添加分组
    public interface formAdd{};
    public interface formAddItem{};
    //编辑分组
    public interface formEdit{};
    public interface formEditItem{};
    //修改密码分组
    public interface formPassword {}
    //其他分组
    public interface formOther{};
    //上传分组
    public interface formUpload{};
}
