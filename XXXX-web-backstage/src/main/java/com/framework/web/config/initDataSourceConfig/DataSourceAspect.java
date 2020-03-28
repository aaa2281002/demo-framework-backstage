package com.framework.web.config.initDataSourceConfig;

import com.framework.common.annotation.TargetDataSource;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @Author 邋遢龘鵺
 * @ClassName com.framework.web.config.initDataSourceConfig
 * @Description service事物拦截类，用于动态切换数据源
 * @DateTime 2019/10/11
 * @Version 1.0
 */
@Aspect
//@Order(-1)//必须加上这个不然会DynamicDataSource比DataSourceAspect先执行
@Component
public class DataSourceAspect {

    ///@Before是在所拦截方法执行之前执行一段逻辑。
    // @After 是在所拦截方法执行之后执行一段逻辑。
    // @Around是可以同时在所拦截方法的前后执行一段逻辑。+
    // 作为@Pointcut的参数，用以定义连接点

    /**
     * @Titel 配置切面目录
     * @Description 配置切面目录
     * @Author 邋遢龘鵺
     * @DateTime 2019/10/11
     */
    @Pointcut("execution(* com.framework.*.service..*.*(..))")
    public void pointcut() {
    }

    /**
     * @param ds 1 数据源注解
     * @Titel 执行方法前更换数据源
     * @Description 执行方法前更换数据源
     * @Author 邋遢龘鵺
     * @DateTime 2019/10/11
     */
    @Before("pointcut() && @annotation(ds)")
    public void beforeDataSource(TargetDataSource ds) {
        String value = ds.dataSource();
        DataSourceContextHolder.setDataSource(value);
    }

    /**
     * @param ds 1 数据源注解
     * @Titel 执行方法后清除数据源设置
     * @Description 执行方法后清除数据源设置
     * @Author 邋遢龘鵺
     * @DateTime 2019/10/11
     */
    @After("pointcut() && @annotation(ds)")
    public void afterDataSource(TargetDataSource ds) {
        DataSourceContextHolder.clearDataSource();
    }
////----------------------注释放开就能用开始-------------------------------
//    /**
//     * @param joinPoint 1 切面点对象
//     * @Titel 设置随机读取数据源
//     * @Description 设置随机读取数据源
//     * @Author 邋遢龘鵺
//     * @DateTime 2019/10/11
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
