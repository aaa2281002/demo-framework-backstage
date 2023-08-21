package com.framework.common.thread;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author 邋遢龘鵺
 * @version 1.0
 * @className com.framework.common.thread
 * @description 案例线程池
 * @date 2021/12/28 9:49
 */
public class DemoThreadPool {


    public static void testPool() {
        ScheduledExecutorService ses = Executors.newScheduledThreadPool(2);

        TestBRunnable testBRunnable = new TestBRunnable();
        TestRunnable testRunnable = new TestRunnable();
        //参数的含义依次为：执行线程、初始化延时、线程调用时间间隔、计时单位
        //如果任务的任何执行遇到异常，则将取消后续执行。 否则，该任务将仅通过取消或终止执行程序而终止
        ses.scheduleAtFixedRate(testRunnable, 2, 6, TimeUnit.SECONDS);
        ses.scheduleAtFixedRate(testBRunnable, 2, 6, TimeUnit.SECONDS);


        //参数的含义依次为：执行线程、初始化延时、第一次线程执行完毕后延迟时间(如：执行线程话费了3秒，加上设置的6秒，就是9秒后才会继续执行)、计时单位
        //如果任务的任何执行遇到异常，则将取消后续执行。 否则，该任务将仅通过取消或终止执行程序而终止
//        ses.scheduleWithFixedDelay(testRunnable, 2, 6,TimeUnit.SECONDS);
//        ses.scheduleWithFixedDelay(testBRunnable, 2, 6,TimeUnit.SECONDS);
        try {
            Thread.sleep(18000);
            TestRunnable.num = 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
//        //
//        ses.schedule(testRunnable, 2, TimeUnit.SECONDS);
//        ses.schedule(testBRunnable, 2, TimeUnit.SECONDS);

    }


    public static void main(String[] args) {
        testPool();
    }

}
