package com.framework.common.util.redis;

import com.framework.common.util.other.NumeralUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.DataType;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author 邋遢龘鵺
 * @version 1.0
 * @className com.framework.common.util.redis
 * @description redis工具类
 * @datetime 2019/10/11
 */
@Component
public final class RedisUtil {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /**
     * @param key 1 键值
     * @return java.util.Set<java.lang.String>
     * @titel 根据key规则获取键集合
     * @description 根据key规则获取键集合
     * @author 邋遢龘鵺
     * @datetime 2020/1/14 11:18
     */
    public Set<String> getKeys(String key) {
        try {
            return redisTemplate.keys(key);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @param key  1 key值
     * @param time 2 缓存时间=秒单位
     * @titel 根据key, 指定缓存失效时间
     * @description 根据key, 指定缓存失效时间
     * @author 邋遢龘鵺
     * @datetime 2019/10/11
     */
    public void expireTime(String key, long time) {
        if (time > NumeralUtil.POSITIVE_ZERO) {
            redisTemplate.expire(key, time, TimeUnit.SECONDS);
        } else {
            throw new RuntimeException("设置的时间不能为0或者小于0！！");
        }
    }

    /**
     * @param key 1 键
     * @return long
     * @titel 根据KEY得到失效时间
     * @description 根据KEY得到失效时间
     * @author 邋遢龘鵺
     * @datetime 2019/10/11
     */
    public long getExpire(String key) {
        try {
            return redisTemplate.getExpire(key, TimeUnit.SECONDS);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * @param key 1 传入key值
     * @return java.lang.Boolean true=存在,false=不存在
     * @titel 根据key, 判断key是否存在
     * @description 根据Key, 判断Key是否存在
     * @author 邋遢龘鵺
     * @datetime 2019/10/11
     */
    public Boolean existsKey(String key) {
        try {
            return redisTemplate.hasKey(key);
        } catch (Exception e) {
            e.printStackTrace();
            return Boolean.FALSE;
        }
    }

    /**
     * @param oldKey 1 旧键
     * @param newKey 2 新键
     * @return boolean true成功，false失败
     * @titel 根据旧key, 修改当前key值
     * @description 根据旧key, 修改当前key值
     * @author 邋遢龘鵺
     * @datetime 2019/10/11
     */
    public boolean updKey(String oldKey, String newKey) {
        try {
            redisTemplate.rename(oldKey, newKey);
            return Boolean.TRUE;
        } catch (Exception e) {
            e.printStackTrace();
            return Boolean.FALSE;
        }

    }


    /**
     * @param oldKey 1 旧键
     * @param newKey 2 新键
     * @return boolean true成功，false失败
     * @titel 新key值不存在的时候, 根据旧key, 修改当前key值
     * @description 新key值不存在的时候, 根据旧key, 修改当前key值
     * @author 邋遢龘鵺
     * @datetime 2019/10/11
     */
    public Boolean updKeyIf(String oldKey, String newKey) {
        try {
            return redisTemplate.renameIfAbsent(oldKey, newKey);
        } catch (Exception e) {
            e.printStackTrace();
            return Boolean.FALSE;
        }
    }

    /**
     * @param key 1 key值
     * @return org.springframework.data.redis.connection.DataType[none、string、list、set、zset、hash]
     * @titel 根据key, 判断key存储的值类型
     * @description 根据key, 判断key存储的值类型
     * @author 邋遢龘鵺
     * @datetime 2019/10/11
     */
    public DataType typeKey(String key) {
        return redisTemplate.type(key);
    }

    /**
     * @param key 1 key值
     * @return java.lang.Boolean=true删除成功，false失败
     * @titel 根据key, 删除指定的一个数据
     * @description 根据key, 删除指定的一个数据
     * @author 邋遢龘鵺
     * @datetime 2019/10/11
     */
    public Boolean deleteKey(String key) {
        try {
            return redisTemplate.delete(key);
        } catch (Exception e) {
            e.printStackTrace();
            return Boolean.FALSE;
        }
    }

    /**
     * @param keys 1 key数组
     * @return java.lang.Boolean=true删除成功，false删除失败
     * @titel 根据key数组, 删除多个数据
     * @description 根据key数组, 删除多个数据
     * @author 邋遢龘鵺
     * @datetime 2019/10/11
     */
    public Boolean deleteKey(String... keys) {
        try {
            Long l = redisTemplate.delete(Arrays.asList(keys));
            return l != null && l.longValue() != NumeralUtil.POSITIVE_ZERO;
        } catch (Exception e) {
            e.printStackTrace();
            return Boolean.FALSE;
        }
    }

    /**
     * @param keys 1 key的Collection对象
     * @return java.lang.Boolean=true删除成功，false删除失败
     * @titel 根据key, 删除多个数据
     * @description 根据key, 删除多个数据
     * @author 邋遢龘鵺
     * @datetime 2019/10/11
     */
    public Boolean deleteKey(Collection<String> keys) {
        try {
            Long l = redisTemplate.delete(keys);
            return l != null && l.longValue() != NumeralUtil.POSITIVE_ZERO;
        } catch (Exception e) {
            e.printStackTrace();
            return Boolean.FALSE;
        }
    }

    //-------------------- String字符串存储取 开始 ----------------------------

    /**
     * @param key   1
     * @param value 2
     * @return boolean 已经存在返回false,不存在返回true
     * @titel 根据key不存在的时候写入redis，存在返回false
     * @description 根据key不存在的时候写入redis，存在返回false
     * @author 邋遢龘鵺
     * @datetime 2019/10/11
     */
    public boolean setStringIf(String key, String value) {
        try {
            return redisTemplate.opsForValue().setIfAbsent(key, value);
        } catch (Exception e) {
            e.printStackTrace();
            return Boolean.FALSE;
        }
    }

    /**
     * @param key   1 键
     * @param value 2 值
     * @param time  2 时间值，(单位：秒)
     * @return boolean true成功，false失败
     * @titel 登录token存储, 30分钟
     * @description 登录token存储, 30分钟
     * @author 邋遢龘鵺
     * @datetime 2019/10/11
     */
    public boolean setStringIf(String key, String value, long time) {
        try {
            boolean is = redisTemplate.opsForValue().setIfAbsent(key, value);
            if (is & time > NumeralUtil.POSITIVE_ZERO) {
                expireTime(key, time);
            }
            return is;
        } catch (Exception e) {
            e.printStackTrace();
            return Boolean.FALSE;
        }
    }


    /**
     * @param key   1 键
     * @param value 2 值
     * @return boolean=true成功,false失败
     * @titel 普通缓存放入
     * @description 普通缓存放入
     * @author 邋遢龘鵺
     * @datetime 2019/10/11
     */
    public boolean setString(String key, String value) {
        try {
            redisTemplate.opsForValue().set(key, value);
            return Boolean.TRUE;
        } catch (Exception e) {
            e.printStackTrace();
            return Boolean.FALSE;
        }
    }

    /**
     * @param key   1 键
     * @param value 2 值
     * @param time  3 时间=秒为单位
     * @return boolean  true成功,false失败
     * @titel 普通缓存放入, 设置缓存存在时间
     * @description 普通缓存放入, 设置缓存存在时间
     * @author 邋遢龘鵺
     * @datetime 2019/10/11
     */
    public boolean setString(String key, String value, long time) {
        try {
            redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
            return Boolean.TRUE;
        } catch (Exception e) {
            e.printStackTrace();
            return Boolean.FALSE;
        }
    }

    /**
     * @param key 1 键
     * @return java.lang.String 值
     * @titel 普通, 根据key获取缓存
     * @description 普通, 根据key获取缓存
     * @author 邋遢龘鵺
     * @datetime 2019/10/11
     */
    public String getString(String key) {
        return key == null ? null : redisTemplate.opsForValue().get(key);
    }


    //-------------------- String字符串存储取 结束 ----------------------------
    //----------------------------- list集合存储取 开始 ------------------------------

    /**
     * @param key   1 键
     * @param value 2 值
     * @return boolean true成功,false失败
     * @titel 根据key存放List集合, 将list放入缓存
     * @description 根据key存放List集合, 将list放入缓存
     * @author 邋遢龘鵺
     * @datetime 2019/10/11
     */
    public boolean setList(String key, String value) {
        try {
            redisTemplate.opsForList().rightPush(key, value);
            return Boolean.TRUE;
        } catch (Exception e) {
            e.printStackTrace();
            return Boolean.FALSE;
        }
    }

    /**
     * @param key   1 键
     * @param value 2 值
     * @param time  3 时间=秒单位
     * @return boolean=true成功,false失败
     * @titel 将String字符串数据放入List缓存，并设置时间
     * @description 将String字符串数据放入List缓存，并设置时间
     * @author 邋遢龘鵺
     * @datetime 2019/10/11
     */
    public boolean setList(String key, String value, long time) {
        try {
            if (time > NumeralUtil.POSITIVE_ZERO) {
                redisTemplate.opsForList().rightPush(key, value);
                this.expireTime(key, time);
                return Boolean.TRUE;
            }
            return Boolean.FALSE;
        } catch (Exception e) {
            e.printStackTrace();
            return Boolean.FALSE;
        }
    }

    /**
     * @param key   1 键
     * @param value 2 值
     * @param time  3  时间=秒单位
     * @return boolean=true成功,false失败
     * @titel 将list集合放入List缓存，并设置时间
     * @description
     * @author 邋遢龘鵺
     * @datetime 2019/10/11
     */
    public boolean setListAll(String key, String value, long time) {
        try {
            if (time > NumeralUtil.POSITIVE_ZERO) {
                redisTemplate.opsForList().rightPushAll(key, value);
                this.expireTime(key, time);
                return Boolean.TRUE;
            }
            return Boolean.FALSE;
        } catch (Exception e) {
            e.printStackTrace();
            return Boolean.FALSE;
        }
    }

    /**
     * @param key   1 键
     * @param start 2 索引开始
     * @param end   3 索引结束,0到-1代表所有值
     * @return java.util.List<java.lang.String>=成功返回集合对象，失败返回NULL
     * @titel 根据key和索引获取缓存List中的内容
     * @description 根据key和索引获取缓存List中的内容
     * @author 邋遢龘鵺
     * @datetime 2019/10/11
     */
    public List<String> getList(String key, long start, long end) {
        try {
            return redisTemplate.opsForList().range(key, start, end);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @param key   1 键
     * @param count 2 移除多少个
     * @param value 3 可以传null或传入存入的Value的值
     * @return long 返回删除了多少个
     * @titel 根据key, 删除List缓存中多个list数据
     * @description 根据key, 删除List缓存中多个list数据
     * @author 邋遢龘鵺
     * @datetime 2019/10/11
     */
    public long deleteListIndex(String key, long count, String value) {
        try {
            return redisTemplate.opsForList().remove(key, count, value);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }

    }

    /**
     * @param key 1 键
     * @return long 返回数字
     * @titel 根据key获取List缓存的数据长度
     * @description 根据key获取List缓存的数据长度
     * @author 邋遢龘鵺
     * @datetime 2019/10/11
     */
    public long getListSize(String key) {
        try {
            return redisTemplate.opsForList().size(key);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    //----------------------------- list集合存储取 结束 ------------------------------
    //----------------------------- set集合存储取 开始 ------------------------------
    //因为没用到所以没写
    //----------------------------- set集合存储取 结束 ------------------------------
    //----------------------------- map集合存储取 开始 ------------------------------

    /**
     * @param key    1 redis键
     * @param mapKey 2 map键
     * @return java.lang.Object 返回map值
     * @titel 根据redis键和map键获取map值
     * @description 根据redis键和map键获取map值
     * @author 邋遢龘鵺
     * @datetime 2019/10/11
     */
    public Object getHashMapValue(String key, String mapKey) {
        try {
            return redisTemplate.opsForHash().get(key, mapKey);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @param key 1 redis键
     * @return java.util.Map 返回map集合
     * @titel 根据redis键获得一个map集合
     * @description 根据redis键获得一个map集合
     * @author 邋遢龘鵺
     * @datetime 2019/10/11
     */
    public Map<Object, Object> getHashMap(String key) {
        try {
            return redisTemplate.opsForHash().entries(key);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @param key    1 redis的键
     * @param mapKey 2 map集合键
     * @param value  3 map集合值
     * @return boolean true成功，false失败
     * @titel 根据redis键, 存储map集合键和值
     * @description 根据redis键, 存储map集合键和值
     * @author 邋遢龘鵺
     * @datetime 2019/10/11
     */
    public boolean setHashMap(String key, String mapKey, String value) {
        try {
            this.redisTemplate.opsForHash().put(key, mapKey, value);
            return Boolean.TRUE;
        } catch (Exception e) {
            e.printStackTrace();
            return Boolean.FALSE;
        }
    }

    /**
     * @param key 1 redis的键
     * @param map 2 map集合键
     * @return boolean true成功，false失败
     * @titel 根据redis键, 存储map集合
     * @description 根据redis键, 存储map集合
     * @author 邋遢龘鵺
     * @datetime 2019/10/11
     */
    public boolean setHashMap(String key, Map<String, Object> map) {
        try {
            this.redisTemplate.opsForHash().putAll(key, map);
            return Boolean.TRUE;
        } catch (Exception e) {
            e.printStackTrace();
            return Boolean.FALSE;
        }
    }

    /**
     * @param key  1 redis的键
     * @param map  2 map集合键
     * @param time 3 存在时间(秒)
     * @return boolean true成功，false失败
     * @titel 根据redis键, 存储map集合, 设置存在时间
     * @description 根据redis键, 存储map集合, 设置存在时间
     * @author 邋遢龘鵺
     * @datetime 2019/10/11
     */
    public boolean setHashMapTime(String key, Map<String, Object> map, long time) {
        try {
            this.redisTemplate.opsForHash().putAll(key, map);
            if (time > NumeralUtil.POSITIVE_ZERO) {
                expireTime(key, time);
            }
            return Boolean.TRUE;
        } catch (Exception e) {
            e.printStackTrace();
            return Boolean.FALSE;
        }
    }


    /**
     * @param key    1 redis的键
     * @param mapKey 2 map集合键
     * @param value  3 map集合值
     * @param time   4 存在时间(秒)
     * @return boolean true成功，false失败
     * @titel 根据redis键, 存储map集合键和值, 设置存在时间
     * @description 根据redis键, 存储map集合键和值, 设置存在时间
     * @author 邋遢龘鵺
     * @datetime 2019/10/11
     */
    public boolean setHashMapTime(String key, String mapKey, String value, long time) {
        try {
            this.redisTemplate.opsForHash().put(key, mapKey, value);
            if (time > NumeralUtil.POSITIVE_ZERO) {
                expireTime(key, time);
            }
            return Boolean.TRUE;
        } catch (Exception e) {
            e.printStackTrace();
            return Boolean.FALSE;
        }
    }

    /**
     * @param key    1 redis键
     * @param mapKey 2 map键
     * @return boolean true成功，false失败
     * @titel 根据redis的键删除map集合键, map集合键可以多个
     * @description 根据redis的键删除map集合键, map集合键可以多个
     * @author 邋遢龘鵺
     * @datetime 2019/10/11
     */
    public boolean delKeyToMapKeyArray(String key, Object... mapKey) {
        try {
            redisTemplate.opsForHash().delete(key, mapKey);
            return Boolean.TRUE;
        } catch (Exception e) {
            e.printStackTrace();
            return Boolean.FALSE;
        }
    }

    /**
     * @param key    1 redis键
     * @param mapKey 2 map键
     * @return boolean
     * @titel 根据redis键和map键判断是否存在，键不能为null
     * @description 根据redis键和map键判断是否存在，键不能为null
     * @author 邋遢龘鵺
     * @datetime 2019/10/11
     */
    public boolean isRedisHashMap(String key, String mapKey) {
        try {
            return redisTemplate.opsForHash().hasKey(key, mapKey);
        } catch (Exception e) {
            e.printStackTrace();
            return Boolean.FALSE;
        }
    }
    //----------------------------- map集合存储取 结束 ------------------------------


    //----------------------------- 用户token操作 开始 ------------------------------

    /**
     * @param oldKey 1 旧键
     * @param newKey 2 新键
     * @return boolean true成功，false失败
     * @titel 新key值不存在的时候, 根据旧key, 修改当前key值
     * @description 新key值不存在的时候, 根据旧key, 修改当前key值
     * @author 邋遢龘鵺
     * @datetime 2019/10/11
     */
    public Boolean updLoginTokenKeyIf(String oldKey, String newKey) {
        String ok = RedisKeyUtil.getLoginTokenKey(oldKey);
        String nk = RedisKeyUtil.getLoginTokenKey(newKey);
        try {
            boolean is = redisTemplate.renameIfAbsent(ok, nk);
            if (is) {
                expireTime(nk, NumeralUtil.POSITIVE_THIRTY * NumeralUtil.POSITIVE_SIXTY);
            }
            return is;
        } catch (Exception e) {
            e.printStackTrace();
            return Boolean.FALSE;
        }
    }

    /**
     * @param key   1 键
     * @param value 2 值
     * @return boolean true成功，false失败
     * @titel 登录token存储, 30分钟
     * @description 登录token存储, 30分钟
     * @author 邋遢龘鵺
     * @datetime 2019/10/11
     */
    public boolean setLoginTokenStringIfLogin(String key, String value) {
        String k = RedisKeyUtil.getLoginTokenKey(key);
        return this.setStringIf(k, value, NumeralUtil.POSITIVE_THIRTY * NumeralUtil.POSITIVE_SIXTY);
    }

    /**
     * @param key 1 键
     * @return java.lang.String 值
     * @titel 登陆redis方法, 根据key获取缓存
     * @description 登陆redis方法, 根据key获取缓存
     * @author 邋遢龘鵺
     * @datetime 2019/10/11
     */
    public String getLoginTokenString(String key) {
        return this.getString(RedisKeyUtil.getLoginTokenKey(key));
    }

    /**
     * @param key   1 键
     * @param value 2 值
     * @return boolean=true成功,false失败
     * @titel 登陆redis方法，缓存放入
     * @description 登陆redis方法，缓存放入
     * @author 邋遢龘鵺
     * @datetime 2019/10/11
     */
    public boolean setLoginSystemUserString(String key, String value) {
        return this.setString(RedisKeyUtil.getLoginSystemUserKey(key), value);
    }

    /**
     * @param key 1 键
     * @return java.lang.String 值
     * @titel 登陆redis方法, 根据key获取缓存
     * @description 登陆redis方法, 根据key获取缓存
     * @author 邋遢龘鵺
     * @datetime 2019/10/11
     */
    public String getLoginSystemUserString(String key) {
        return this.getString(RedisKeyUtil.getLoginSystemUserKey(key));
    }

    /**
     * @param key   1 键
     * @param value 2 值
     * @return boolean=true成功,false失败
     * @titel 权限角色redis方法，缓存放入
     * @description 权限角色redis方法，缓存放入
     * @author 邋遢龘鵺
     * @datetime 2019/10/11
     */
    public boolean setAuthRoleString(String key, String value) {
        return this.setString(RedisKeyUtil.getPermissionRoleKey(key), value);
    }

    /**
     * @param key 1 键
     * @return java.lang.String 值
     * @titel 权限角色redis方法, 根据key获取缓存
     * @description 权限角色redis方法, 根据key获取缓存
     * @author 邋遢龘鵺
     * @datetime 2019/10/11
     */
    public String getAuthRoleString(String key) {
        return this.getString(RedisKeyUtil.getPermissionRoleKey(key));
    }

    /**
     * @param key   1 键
     * @param value 2 值
     * @return boolean=true成功,false失败
     * @titel 权限菜单redis方法，缓存放入
     * @description 权限菜单redis方法，缓存放入
     * @author 邋遢龘鵺
     * @datetime 2019/10/11
     */
    public boolean setAuthMenuString(String key, String value) {
        return this.setString(RedisKeyUtil.getPermissionMenuKey(key), value);
    }

    /**
     * @param key 1 键
     * @return java.lang.String 值
     * @titel 权限菜单redis方法, 根据key获取缓存
     * @description 权限菜单redis方法, 根据key获取缓存
     * @author 邋遢龘鵺
     * @datetime 2019/10/11
     */
    public String getAuthMenuString(String key) {
        return this.getString(RedisKeyUtil.getPermissionMenuKey(key));
    }


    /**
     * @param key   1 键
     * @param value 2 值
     * @return boolean=true成功,false失败
     * @titel 权限按钮redis方法，缓存放入
     * @description 权限按钮redis方法，缓存放入
     * @author 邋遢龘鵺
     * @datetime 2019/10/11
     */
    public boolean setAuthButtonString(String key, String value) {
        return this.setString(RedisKeyUtil.getPermissionButtonKey(key), value);
    }

    /**
     * @param key 1 键
     * @return java.lang.String 值
     * @titel 权限按钮redis方法, 根据key获取缓存
     * @description 权限按钮redis方法, 根据key获取缓存
     * @author 邋遢龘鵺
     * @datetime 2019/10/11
     */
    public String getAuthButtonString(String key) {
        return this.getString(RedisKeyUtil.getPermissionButtonKey(key));
    }
    //----------------------------- 用户token操作 结束s ------------------------------

}
