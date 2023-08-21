package com.framework.service.init.impl;

import com.alibaba.fastjson.JSONObject;
import com.framework.common.response.ResponseResult;
import com.framework.model.system.SystemUser;
import com.framework.service.base.BaseService;
import com.framework.service.init.LoginService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author 龘鵺
 * @ClassName com.framework.service.init.impl
 * @Description 登录业务接口实现类
 * @Date 2022/3/7 18:02
 * @Version 1.0
 */
@Service("loginServiceImpl")
@Transactional
public class LoginServiceImpl extends BaseService implements LoginService {
    private Logger log = LoggerFactory.getLogger(LoginServiceImpl.class);

    /**
     * @return com.framework.common.response.ResponseResult
     * @Titel 退出
     * @Description 退出
     * @Author 龘鵺
     * @DateTime 2022/3/29 15:55
     */
    @Override
    public ResponseResult loginOut() {
        SystemUser systemUser = super.getUser();
        log.info("loginOut:{}", JSONObject.toJSONString(systemUser));
        String token = redisUtil.getLoginUserString(systemUser.getLoginName());
        log.info("loginOut:{}", token);
        if (StringUtils.isNotEmpty(token)) {
            redisUtil.delTokenKey(token);
            redisUtil.delAccountKey(systemUser.getLoginName());
            log.info("loginOut:进入了");
        }
        return super.getResponseResult().success();
    }

}
