package com.framework.service.base;

import com.framework.common.model.auth.IgnoredDataRoleAuth;
import com.framework.common.model.auth.IgnoredRoleAuth;
import com.framework.common.response.ResponseResult;
import com.framework.common.util.array.StringToArrayUtil;
import com.framework.common.util.other.SymbolUtil;
import com.framework.common.util.redis.RedisUtil;
import com.framework.model.login.UserPrincipal;
import com.framework.model.system.SystemUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author 邋遢龘鵺
 * @version 1.0
 * @className com.framework.service.base
 * @description 公共业务处理类
 * @datetime 2019/10/11
 */
public class BaseService {
    private Logger log = LoggerFactory.getLogger(BaseService.class);
    @Autowired
    protected IgnoredDataRoleAuth ignoredDataRoleAuth;
    @Autowired
    protected IgnoredRoleAuth ignoredRoleAuth;
    @Autowired
    protected RedisUtil redisUtil;
    @Autowired
    protected HttpServletRequest request;


    /**
     * @return com.framework.common.response.ResponseResult
     * @titel 创建公共返回对象
     * @description 创建公共返回对象
     * @author 邋遢龘鵺
     * @datetime 2019/10/11
     */
    protected ResponseResult getResponseResult() {
        return new ResponseResult();
    }

    /**
     * @return com.framework.model.system.SystemUser
     * @titel 获取登陆用户信息
     * @description 获取登陆用户信息
     * @author 邋遢龘鵺
     * @datetime 2019/10/11
     */
    protected SystemUser getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            log.error("getUser：进入请登录1");
            throw new BadCredentialsException("请登录!");
        }
        Object obj = authentication.getPrincipal();
//        AnonymousAuthenticationToken [Principal=, Credentials=[PROTECTED], Authenticated=true, Details=WebAuthenticationDetails [RemoteIpAddress=0:0:0:0:0:0:0:1, SessionId=85352be9-0d11-4942-a29c-7c9542173a75], Granted Authorities=[ROLE_ANONYMOUS]]
        if ("anonymousUser".equalsIgnoreCase(obj.toString())) {
            log.error("getUser：进入请登录3");
            throw new BadCredentialsException("请登录!!!");
        }
        UserPrincipal userPrincipal = (UserPrincipal) obj;
        if (userPrincipal == null) {
            log.error("getUser：进入请登录2");
            throw new BadCredentialsException("请登录!");
        }
        SystemUser systemUser = userPrincipal.getSystemUser();
        if (systemUser == null) {
            return null;
        }
        return systemUser;
    }

    /**
     * @return java.lang.Long
     * @titel 获取登陆用户ID
     * @description 获取登陆用户ID
     * @author 邋遢龘鵺
     * @datetime 2019/10/11
     */
    protected Long getUserId() {
        SystemUser u = getUser();
        return u != null ? u.getId() : null;
    }

    /**
     * @param idStr 1   编号字符串
     * @return java.util.List<java.lang.Long> 转long集合
     * @titel 编号字符串转集合
     * @description 编号字符串转集合
     * @author 邋遢龘鵺
     * @datetime 2020/3/24 20:05
     */
    protected List<Long> getIdLongList(String idStr) {
        return StringToArrayUtil.stringToLongList(idStr.split(SymbolUtil.NO_INPUT_METHOD_COMMA));
    }

}
