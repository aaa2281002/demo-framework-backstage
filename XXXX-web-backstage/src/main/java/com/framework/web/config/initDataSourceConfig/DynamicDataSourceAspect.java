//package com.framework.web.config.initDataSourceConfig;
//
//import com.framework.common.annotation.TargetDataSource;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.Signature;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.reflect.MethodSignature;
//import org.springframework.stereotype.Component;
//
//import java.lang.reflect.Method;
///**
// * @Author 邋遢龘鵺
// * @ClassName com.framework.web.config.initDataSourceConfig
// * @Description service事物拦截类，用于动态切换数据源
// * @DateTime 2019/10/11
// * @Version 1.0
// */
////切面注解
//@Aspect
////定义Spring管理Bean注解
//@Component
//public class DynamicDataSourceAspect {
//    private Logger logger = LogManager.getLogger(DynamicDataSourceAspect.class);
//
//    /**
//     * 拦截service事物，切换数据源
//     *
//     * @param pjp
//     * @return
//     * @throws Throwable
//     */
//    @Around("execution(* com.framework.*.service..*.*(..))")
//    public Object around(ProceedingJoinPoint pjp) throws Throwable {
//        //获取当前线程service接口类
//        Signature signature = pjp.getSignature();
//        //signature转向子项类，这里是为了获取对应的一些东西，
//        MethodSignature methodSignature = (MethodSignature) signature;
//        //获取对应的方法
//        Method method = methodSignature.getMethod();
//        //判断当前方法上是否存在TargetDataSource注解
//        boolean methodAnnotation = method.isAnnotationPresent(TargetDataSource.class);
//
//        TargetDataSource targetDataSource = null;
//        //存在进入
//        if (methodAnnotation) {
//            //获取对应的注解
//            targetDataSource = method.getAnnotation(TargetDataSource.class);
//        } else {
//            //方法注解不存在的时候， 获取一次类注解， 判断该类是否全局不同的数据源
//            Class clazz[] = pjp.getTarget().getClass().getInterfaces();
//            targetDataSource = (TargetDataSource) clazz[0].getAnnotation(TargetDataSource.class);
//        }
//        //targetDataSource注解不为空， 动态切换指定数据源
//        if (targetDataSource != null) {
//            //写入当前线程所用数据源Key
//            DynamicDataSourceHolder.setDataSource(targetDataSource.dataSource());
//            logger.debug("mybatis接口: " + (method.getDeclaringClass() + "." + method.getName()) + " 设置数据源 key is " + targetDataSource.dataSource());
//        }
//        //执行方法
//        Object result = pjp.proceed();//执行方法
//        //清楚当前线程所写入的数据源Key
//        DynamicDataSourceHolder.clearDataSource();
//        //完毕，返回
//        return result;
//    }
//
//}
