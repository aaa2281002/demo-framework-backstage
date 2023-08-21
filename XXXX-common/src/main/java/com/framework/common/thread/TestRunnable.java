package com.framework.common.thread;

import com.framework.common.util.date.DateUtil;

import java.util.Date;

/**
 * @author 邋遢龘鵺
 * @version 1.0
 * @className com.framework.common.thread
 * @description 测试线程
 * @date 2021/12/28 9:47
 */
public class TestRunnable implements Runnable {
    public volatile static int num = 0;

    @Override
    public void run() {
        try {
            System.out.println("进入测试线程1===" + DateUtil.getDateToString(new Date(), null));
//            Thread.sleep(3000);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("执行线程完毕1");
        }
        if (num > 0) {
            throw new RuntimeException("测试抛异常停止");
        }
    }
}
