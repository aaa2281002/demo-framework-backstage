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
public class TestBRunnable implements Runnable {

    @Override
    public void run() {
        System.out.println("===========================================");
        try {
            System.out.println("进入测试线程B===" + DateUtil.getDateToString(new Date(), null));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("执行线程完毕B");
        }
    }
}
