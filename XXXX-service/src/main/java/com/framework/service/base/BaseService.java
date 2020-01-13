package com.framework.service.base;

import com.framework.common.response.ResponseResult;
import com.framework.common.util.redis.RedisUtil;
import com.framework.model.entity.login.UserPrincipal;
import com.framework.model.entity.system.SystemUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author 邋遢龘鵺
 * @ClassName com.framework.service.base
 * @Description 公共业务处理类
 * @DateTime 2019/10/11
 * @Version 1.0
 */
public class BaseService {
    @Value("${spring.security.auth-list}")
    protected List<String> authList = new ArrayList<String>();
    @Autowired
    protected RedisUtil redisUtil;
    @Autowired
    private HttpServletRequest request;

    /**
     * @Titel 创建公共返回对象
     * @Description 创建公共返回对象
     * @Author 邋遢龘鵺
     * @DateTime 2019/10/11
     * @return com.framework.common.response.ResponseResult
     */
    protected ResponseResult getResponseResult() {
        return new ResponseResult();
    }

    /**
     * @return com.framework.model.entity.system.SystemUser
     * @Titel 获取登陆用户信息
     * @Description 获取登陆用户信息
     * @Author 邋遢龘鵺
     * @DateTime 2019/10/11
     */
    protected SystemUser getUser() {
        if (SecurityContextHolder.getContext().getAuthentication() == null) return null;
        UserPrincipal up = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (up != null) {
            return up.getSystemUser();
        }
        return null;
    }

    /**
     * @return java.lang.Long
     * @Titel 获取登陆用户ID
     * @Description 获取登陆用户ID
     * @Author 邋遢龘鵺
     * @DateTime 2019/10/11
     */
    protected Long getUserId() {
        SystemUser u = getUser();
        return u != null ? u.getId() : null;
    }
}
