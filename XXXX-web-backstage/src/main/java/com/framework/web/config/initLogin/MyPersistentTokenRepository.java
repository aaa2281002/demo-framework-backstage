package com.framework.web.config.initLogin;

import com.framework.common.util.other.NumeralUtil;
import com.framework.common.util.redis.RedisKeyUtil;
import com.framework.common.util.redis.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
//import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
//import java.util.concurrent.TimeUnit;

/**
 * @Author 邋遢龘鵺
 * @ClassName com.framework.web.config.initLogin
 * @Description 自定义redis记住我登录，失败
 * @DateTime 2019/12/20 15:13
 * @Version 1.0
 */
@Configuration
public class MyPersistentTokenRepository implements PersistentTokenRepository {
    //token有效天数，单位天
    @Value("${spring.security.token-valid-days}")
    private Integer tokenValidDays;
    //    @Autowired
//    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private RedisUtil redisUtil;

    /**
     * @param token 1 记住我实体类对象
     * @Titel 创建记住我redis信息
     * @Description 创建记住我redis信息
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/22 13:34
     */
    @Override
    public void createNewToken(PersistentRememberMeToken token) {
        String key = RedisKeyUtil.generateTokenKey(token.getSeries());
        Map<String, Object> map = new HashMap<String, Object>(NumeralUtil.POSITIVE_EIGHT);
        map.put("username", token.getUsername());
        map.put("tokenValue", token.getTokenValue());
        map.put("date", String.valueOf(token.getDate().getTime()));
        redisUtil.setHashMap(key, map);
        long time = tokenValidDays * NumeralUtil.POSITIVE_TWO * NumeralUtil.POSITIVE_SIXTY * NumeralUtil.POSITIVE_SIXTY;
        redisUtil.expireTime(key, time);
//        stringRedisTemplate.opsForHash().putAll(key, map);
//        stringRedisTemplate.expire(key, tokenValidDays, TimeUnit.DAYS);
        saveUsernameAndSeries(token.getUsername(), token.getSeries());
    }


    /**
     * @param series     1 浏览器系列
     * @param tokenValue 2 token值
     * @param lastUsed   3 最后操作时间
     * @Titel 根据浏览器系列，更新记住我redis信息，token值，最后操作时间
     * @Description 根据浏览器系列，更新记住我redis信息，token值，最后操作时间
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/22 13:37
     */
    @Override
    public void updateToken(String series, String tokenValue, Date lastUsed) {
        String key = RedisKeyUtil.generateTokenKey(series);
//        Boolean hasSeries = stringRedisTemplate.hasKey(key);
        Boolean hasSeries = redisUtil.existsKey(key);
        if (hasSeries == null || !hasSeries) {
            return;
        }
        Map<String, Object> map = new HashMap<String, Object>(NumeralUtil.POSITIVE_FOUR);
        map.put("tokenValue", tokenValue);
        map.put("date", String.valueOf(lastUsed.getTime()));
        redisUtil.setHashMap(key, map);
        long time = tokenValidDays * NumeralUtil.POSITIVE_TWO * NumeralUtil.POSITIVE_SIXTY * NumeralUtil.POSITIVE_SIXTY;
        redisUtil.expireTime(key, time);
        String username = redisUtil.getString(RedisKeyUtil.generateUsernameAndSeriesKey(series)) + "";
//        stringRedisTemplate.opsForHash().putAll(key, map);
//        stringRedisTemplate.expire(key, tokenValidDays, TimeUnit.DAYS);
//        String username = stringRedisTemplate.opsForValue().get(RedisKeyUtil.generateUsernameAndSeriesKey(series));
        saveUsernameAndSeries(username, series);
    }

    /**
     * @param username 1 账户名
     * @Titel 通过账户名，删除记住我redis信息
     * @Description 通过账户名，删除记住我redis信息
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/22 13:34
     */
    @Override
    public void removeUserTokens(String username) {
//        String series = stringRedisTemplate.opsForValue().get(RedisKeyUtil.generateUsernameAndSeriesKey(username));
        String series = redisUtil.getString(RedisKeyUtil.generateUsernameAndSeriesKey(username)) + "";
        if (series == null || series.trim().length() <= NumeralUtil.POSITIVE_ZERO) {
            return;
        }
        redisUtil.deleteKey(RedisKeyUtil.generateTokenKey(series));
        redisUtil.deleteKey(RedisKeyUtil.generateUsernameAndSeriesKey(username));
        redisUtil.deleteKey(RedisKeyUtil.generateUsernameAndSeriesKey(series));
//        stringRedisTemplate.delete(RedisKeyUtil.generateTokenKey(series));
//        stringRedisTemplate.delete(RedisKeyUtil.generateUsernameAndSeriesKey(username));
//        stringRedisTemplate.delete(RedisKeyUtil.generateUsernameAndSeriesKey(series));
    }

    /**
     * @param seriesId 1 浏览器系列
     * @Titel 根据浏览器系列，获取记住我redis信息
     * @Description 根据浏览器系列，获取记住我redis信息
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/22 13:34
     */
    @Override
    public PersistentRememberMeToken getTokenForSeries(String seriesId) {
        String key = RedisKeyUtil.generateTokenKey(seriesId);
//        Map hashKeyValues = stringRedisTemplate.opsForHash().entries(key);
        Map hashKeyValues = redisUtil.getHashMap(key);
        if (hashKeyValues == null || hashKeyValues.size() == NumeralUtil.POSITIVE_ZERO) {
            return null;
        }
        Object username = hashKeyValues.get("username");
        Object tokenValue = hashKeyValues.get("tokenValue");
        Object date = hashKeyValues.get("date");
        if (null == username || null == tokenValue || null == date) {
            return null;
        }
        long timestamp = Long.valueOf(String.valueOf(date));
        Date time = new Date(timestamp);

        return new PersistentRememberMeToken(String.valueOf(username), seriesId, String.valueOf(tokenValue), time);
    }

    /**
     * @param username 1 账号
     * @param series   2 浏览器系列
     * @Titel 账号和浏览器系列, 互相保存，便于查询
     * @Description 账号和浏览器系列, 互相保存，便于查询
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/28 18:31
     */
    private void saveUsernameAndSeries(String username, String series) {
        long time = tokenValidDays * NumeralUtil.POSITIVE_TWO * NumeralUtil.POSITIVE_TWENTY_FOUR * NumeralUtil.POSITIVE_SIXTY * NumeralUtil.POSITIVE_SIXTY;
        redisUtil.setString(RedisKeyUtil.generateUsernameAndSeriesKey(username), series, time);
        redisUtil.setString(RedisKeyUtil.generateUsernameAndSeriesKey(series), username, time);
//        stringRedisTemplate.opsForValue().set(RedisKeyUtil.generateUsernameAndSeriesKey(username), series, tokenValidDays * 2, TimeUnit.DAYS);
//        stringRedisTemplate.opsForValue().set(RedisKeyUtil.generateUsernameAndSeriesKey(series), username, tokenValidDays * 2, TimeUnit.DAYS);
    }

//    /**
//     * 生成 series token key
//     */
//    private String generateTokenKey(String series) {
//        return "spring:security:rememberMe:token:" + series;
//    }
//
//    /**
//     * 生成 username  key
//     */
//    private String generateUsernameAndSeriesKey(String usernameOrSeries) {
//        return "spring:security:rememberMe:" + usernameOrSeries;
//    }
}
