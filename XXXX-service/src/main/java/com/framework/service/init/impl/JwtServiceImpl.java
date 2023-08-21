package com.framework.service.init.impl;

import com.framework.common.util.date.DateUtil;
import com.framework.common.util.other.NumeralUtil;
import com.framework.common.model.security.JwtConstant;
import com.framework.service.base.BaseService;
import com.framework.service.init.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Date;

/**
 * @author 龘鵺
 * @version 1.0
 * @className com.framework.common.model.security
 * @description jwt业务工具接口实现类
 * @datetime 2023/5/19 10:18
 */
@Service("jwtServiceImpl")
public class JwtServiceImpl extends BaseService implements JwtService {
    private Logger log = LoggerFactory.getLogger(JwtServiceImpl.class);
    @Autowired
    private JwtConstant jwtConstant;

    /**
     * @return javax.crypto.SecretKey
     * @Title 生成签名key
     * @Description 生成签名key
     * @Author 龘鵺
     * @DateTime 2023/5/19 11:08
     */
    private SecretKey getSecretKey() {
        String stringKey = jwtConstant.getJwtSignKey();
        byte[] encodedKey = Base64.encodeBase64(stringKey.getBytes(StandardCharsets.UTF_8));
        return new SecretKeySpec(encodedKey, 0, encodedKey.length, SignatureAlgorithm.HS512.getJcaName());
    }

    /**
     * @param account 1 账户名
     * @return java.lang.String
     * @Title 生成token
     * @Description 生成token
     * @Author 龘鵺
     * @DateTime 2023/5/19 14:20
     */
    public String createToken(String account) {
        Date nowDate = new Date();
        Date expireDate = new Date(nowDate.getTime() + jwtConstant.getExpire().intValue() * NumeralUtil.POSITIVE_ONE_THOUSAND * NumeralUtil.POSITIVE_SIXTY);//过期时间
        System.out.println(DateUtil.getDateToString(nowDate, null));
        System.out.println(DateUtil.getDateToString(expireDate, null));
        return jwtConstant.getTokenSplit() + Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setSubject(account)
                .setIssuedAt(nowDate)
                .setExpiration(expireDate)
                .signWith(getSecretKey(), SignatureAlgorithm.HS512)
                .compact();
    }


    /**
     * @param token 1 token字符串
     * @return io.jsonwebtoken.Claims
     * @Title 获取token中用户信息
     * @Description 获取token中用户信息
     * @Author 龘鵺
     * @DateTime 2023/5/19 14:19
     */
    public Claims getTokenClaim(String token) {
        return Jwts.parserBuilder().setSigningKey(getSecretKey()).build().parseClaimsJws(token).getBody();
    }

    /**
     * @param expirationTime 1 当前时间
     * @return boolean
     * @Title 验证token是否过期失效
     * @Description 验证token是否过期失效
     * @Author 龘鵺
     * @DateTime 2023/5/19 11:09
     */
    public boolean isTokenExpired(Date expirationTime) {
        return expirationTime.before(new Date());
    }

    /**
     * @param token 1 token字符串
     * @return java.util.Date
     * @Title 获取token失效时间
     * @Description 获取token失效时间
     * @Author 龘鵺
     * @DateTime 2023/5/19 11:09
     */
    public Date getExpirationDateFromToken(String token) {
        return getTokenClaim(token).getExpiration();
    }

    /**
     * @param token 1 token字符串
     * @return java.lang.String
     * @Title 获取用户名从token中
     * @Description 获取用户名从token中
     * @Author 龘鵺
     * @DateTime 2023/5/19 11:10
     */
    public String getUsernameFromToken(String token) {
        return getTokenClaim(token).getSubject();
    }

    /**
     * @param token 1 token字符串
     * @return java.util.Date
     * @Title 获取jwt发布时间
     * @Description 获取jwt发布时间
     * @Author 龘鵺
     * @DateTime 2023/5/19 11:10
     */
    public Date getIssuedAtDateFromToken(String token) {
        return getTokenClaim(token).getIssuedAt();
    }

}
