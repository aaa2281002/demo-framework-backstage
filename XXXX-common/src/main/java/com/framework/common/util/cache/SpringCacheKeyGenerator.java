package com.framework.common.util.cache;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.framework.common.util.other.NumeralUtil;
import com.framework.common.util.other.SymbolUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;

import java.lang.reflect.Method;
import java.net.URI;
import java.net.URL;
import java.util.Date;
import java.util.Locale;

/**
 * @author 邋遢龘鵺
 * @version 1.0
 * @className com.framework.common.util.cache
 * @description 自定义生成缓存键
 * @date 2020/1/13 14:13
 */
@Component(value = "springCacheKeyGenerator")
public class SpringCacheKeyGenerator implements KeyGenerator {
    //秘钥生成对象变量名,用于Cacheable注解中keyGenerator变量赋值
    public final static String KEYGENERATOR = "springCacheKeyGenerator";
    //项目名称,每个项目名都不一样注意修改配置文件
    @Value("${spring.project.name}")
    private String keyPrefix;

    /**
     * @param clazz 1 当前对象参数class
     * @return boolean 成功为true，失败为false
     * @titel 根据对象判断参数类型
     * @description 根据对象判断参数类型
     * @author 邋遢龘鵺
     * @datetime 2020/1/13 18:57
     */
    public static boolean isSimpleValueType(Class<?> clazz) {
        return (ClassUtils.isPrimitiveOrWrapper(clazz)
                || clazz.isEnum()
                || CharSequence.class.isAssignableFrom(clazz)
                || Number.class.isAssignableFrom(clazz)
                || Date.class.isAssignableFrom(clazz)
                || URI.class == clazz
                || URL.class == clazz
                || Locale.class == clazz
                || Class.class == clazz);
    }

    /**
     * @param obj 1 实体类对象变量
     * @return java.lang.String 返回json字符串
     * @titel 缓存方法中实体类转JSON字符串方法
     * @description 缓存方法中实体类转JSON字符串方法
     * @author 邋遢龘鵺
     * @datetime 2020/1/13 18:56
     */
    public static String toJson(Object obj) {
//        return JSON.toJSONString(obj, SerializerFeature.WriteMapNullValue, SerializerFeature.DisableCircularReferenceDetect);
        return JSON.toJSONString(obj, SerializerFeature.WriteMapNullValue);
    }

    /**
     * @param target 1 缓存目标类对象
     * @param method 2 方法对象
     * @param params 3 参数数组对象
     * @return java.lang.Object 返回生成键
     * @titel 根据Cacheable注解生成对应的键
     * @description 根据Cacheable注解生成对应的键
     * @author 邋遢龘鵺
     * @datetime 2020/1/14 9:42
     */
    @Override
    public Object generate(Object target, Method method, Object... params) {
        StringBuilder sb = new StringBuilder(30);
        //配置文件中定义的项目名
        sb.append(keyPrefix);
        sb.append(SymbolUtil.NO_INPUT_METHOD_COLON);
        //类名
        sb.append(target.getClass().getSimpleName());
        sb.append(SymbolUtil.NO_INPUT_METHOD_COLON);
        // 方法名
        sb.append(method.getName());
        sb.append(SymbolUtil.NO_INPUT_METHOD_COLON);
        if (params.length > 0) {
            // 参数值
            for (Object object : params) {
                if (object != null && isSimpleValueType(object.getClass())) {
                    //参数列表处理,把参数列表组装成键
                    sb.append(object);
                } else {
                    //对应实体类处理,转成hash代码
                    sb.append(toJson(object).hashCode());
                }
            }
        } else {
            //参数列表没有直接赋值0为键
            sb.append(NumeralUtil.POSITIVE_ZERO);
        }
        return sb.toString();
    }
}
