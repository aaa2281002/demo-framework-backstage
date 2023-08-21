package com.framework.web.config.initAspect;

import com.framework.common.annotation.QueryParam;
import com.framework.common.annotation.QueryTarget;
import com.framework.common.util.other.NumeralUtil;
import com.framework.common.util.sql.SortUtil;
import com.framework.model.base.BaseModel;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author 龘鵺
 * @version 1.0
 * @className com.framework.web.config.initAspect
 * @description 查询切面处理类
 * @date 2022/4/20 10:59
 */
@Aspect
@Component
public class QueryAspect {
    private Logger log = LoggerFactory.getLogger(QueryAspect.class);

    ///@Before 是在所拦截方法执行之前执行一段逻辑。
    // @After 是在所拦截方法执行之后执行一段逻辑。
    // @Around 是可以同时在所拦截方法的前后执行一段逻辑。+
    // 作为@Pointcut的参数，用以定义连接点


    /**
     * @titel 配置切面目录
     * @description 配置切面目录
     * @author 邋遢龘鵺
     * @datetime 2019/10/11
     */
    @Pointcut("@annotation(com.framework.common.annotation.QueryTarget)")
    public void pointcut() {
        log.info("pointcut");
    }

    /**
     * @param joinPoint 1
     * @titel 执行方法前
     * @description 执行方法前
     * @author 邋遢龘鵺
     * @datetime 2019/10/11
     */
    @Around("pointcut()")
    public Object before(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("before=start");
        //获取传入目标方法的参数
        Object[] args = joinPoint.getArgs();
        //获取代理方法的参数
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        //获取参数名
        String[] argsName = methodSignature.getParameterNames();
        //获取当前方法信息
        Method method = methodSignature.getMethod();
        //获取当前方法对应注解信息
        QueryTarget queryTarget = method.getAnnotation(QueryTarget.class);
        if (argsName != null) {
//            if ("param".equalsIgnoreCase(argsName[NumeralUtil.POSITIVE_ZERO])
//                    || "row".equalsIgnoreCase(argsName[NumeralUtil.POSITIVE_ZERO])) {
            /**
             *getFields()=获取本类、本类以上继承的类、实现的接口的被public 修饰的 变量
             *
             *getDeclaredFields()=获取仅本类的所有变量，不限制是否被 public 修饰
             */
            Object obj = args[NumeralUtil.POSITIVE_ZERO];
            BaseModel baseModel = (BaseModel) obj;
            //获取本类的成员变量注解
            Field field = getField(obj, baseModel.getSort(), queryTarget);
            if (field != null) {
                if (field.isAnnotationPresent(QueryParam.class)) {
                    QueryParam queryParam = field.getAnnotation(QueryParam.class);
                    baseModel.setSort(queryParam.code());
                }
            } else {
                baseModel.setSort("p.ID");
            }
            if (StringUtils.isNotEmpty(baseModel.getSort())
                    && (StringUtils.isEmpty(baseModel.getOrder())
                    || (!SortUtil.SORT_DESC.equalsIgnoreCase(baseModel.getOrder())
                    && !SortUtil.SORT_ASC.equalsIgnoreCase(baseModel.getOrder())))) {
                baseModel.setOrder(SortUtil.SORT_DESC);
            }
        }
//        }
        log.info("before=end");
        return joinPoint.proceed();
    }

    /**
     * @param obj         1 类对象
     * @param fieldName   2 变量名
     * @param queryTarget 3 分页注解类对象
     * @return java.lang.reflect.Field
     * @title 获取对象中的字段
     * @description 获取对象中的字段
     * @author 龘鵺
     * @datetime 2023/5/10 15:36
     */
    protected Field getField(Object obj, String fieldName, QueryTarget queryTarget) throws NoSuchFieldException {
        Class clzz = obj.getClass();
        Field[] fields = clzz.getDeclaredFields();
        Field dest = null;
        while (!hasField(fields, fieldName) && !clzz.getName().equalsIgnoreCase("java.lang.Object")) {
            clzz = clzz.getSuperclass();
            if (!clzz.getName().equalsIgnoreCase("java.lang.Object")) {
                fields = clzz.getDeclaredFields();
            }
        }
        if (StringUtils.isNotEmpty(fieldName)) {
//            if (hasField(fields, fieldName)) {
            dest = clzz.getDeclaredField(fieldName);
//            }
        } else {
            dest = hasField(fields, queryTarget);
        }
        return dest;
    }

    /**
     * @param fields      1 类的字段数组
     * @param queryTarget 2 分页注解类对象
     * @return java.lang.reflect.Field
     * @title 判断对象中是否有要找的字段
     * @description 判断对象中是否有要找的字段
     * @author 龘鵺
     * @datetime 2023/5/10 15:36
     */
    private Field hasField(Field[] fields, QueryTarget queryTarget) {
        for (int i = 0; i < fields.length; i++) {
            QueryParam queryParam = fields[i].getAnnotation(QueryParam.class);
            if (queryParam != null && queryParam.isDefault() && queryTarget.isDefault()) {
                return fields[i];
            }
        }
        return null;
    }

    /**
     * @param fields    1 类的字段数组
     * @param fieldName 2 分页注解类对象
     * @return boolean
     * @title 判断对象中是否有要找的字段
     * @description 判断对象中是否有要找的字段
     * @author 龘鵺
     * @datetime 2023/5/10 15:37
     */
    private boolean hasField(Field[] fields, String fieldName) {
        for (int i = 0; i < fields.length; i++) {
            if (fields[i].getName().equals(fieldName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * @titel 执行方法后
     * @description 执行方法后
     * @author 邋遢龘鵺
     * @datetime 2019/10/11
     */
    @After("pointcut()")
    public void after() {
        log.info("after");
    }
////----------------------注释放开就能用开始-------------------------------
//    /**
//     * @param joinPoint 1 切面点对象
//     * @titel 设置随机读取数据源
//     * @description 设置随机读取数据源
//     * @author 邋遢龘鵺
//     * @datetime 2019/10/11
//     */
//    @Before(value = "pointcut()")
//    public void beforeRandomReadDataSource(JoinPoint joinPoint) {
//        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
//        //获取当前切点方法对象
//        Method method = methodSignature.getMethod();
//        if (method.getDeclaringClass().isInterface()) {//判断是否为借口方法
//            try {
//                //获取实际类型的方法对象
//                method = joinPoint.getTarget().getClass()
//                        .getDeclaredMethod(joinPoint.getSignature().getName(), method.getParameterTypes());
//            } catch (NoSuchMethodException e) {
//                e.printStackTrace();
//            }
//        }
//        if (null == method.getAnnotation(TargetDataSource.class)) {
//            DataSourceContextHolder.setRead();
//        }
//
//    }
////----------------------注释放开就能用结束-------------------------------


//    /**
//     * 拦截service事物，切换数据源
//     *
//     * @param pjp
//     * @return
//     * @throws Throwable
//     */
//    @Around("execution(* com.framework.*.service..*.*(..))")
//    public void around(ProceedingJoinPoint pjp){
//    }
//    @After("@annotation(ds)")
//    public void afterDataSource(TargetDataSource ds){
//        DataSourceContextHolder.clearDataSource();
//    }
}
