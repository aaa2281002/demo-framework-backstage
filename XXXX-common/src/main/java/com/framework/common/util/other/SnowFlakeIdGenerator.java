package com.framework.common.util.other;


import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.SystemUtils;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.lang.reflect.Field;
import java.net.Inet4Address;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

/**
 * @Author 龘鵺
 * @ClassName com.framework.common.util.other
 * @Description 雪花算法工具类
 * @Date 10/8/2023 下午5:35
 * @Version 1.0
 */
public class SnowFlakeIdGenerator {
    //开始时间截 (2023-08-11)
    private final static long START_TIME_STAMP = 1691724596000L;

    /**
     * 每一部分占用的位数
     */
    // 毫秒内自增位数
    private final static long SEQUENCE_BIT = 12L;
    // 机器标识位数
    private final static long MACHINE_BIT = 5L;
    // 数据中心标识位数
    private final static long DATACENTER_BIT = 5L;

    /**
     * 每一部分的最大值
     */
    //最大数据中心数量，结果是31
    private final static long MAX_DATACENTER_NUM = -1L ^ (-1L << DATACENTER_BIT);
    //最大机器数量，结果是31 (这个移位算法可以很快的计算出几位二进制数所能表示的最大十进制数)
    private final static long MAX_MACHINE_NUM = -1L ^ (-1L << MACHINE_BIT);
    //最大序列，这里为4095 (0b111111111111=0xfff=4095)
    private final static long MAX_SEQUENCE = -1L ^ (-1L << SEQUENCE_BIT);

    /**
     * 每一部分向左的位移
     */
    // 机器ID向左移12位
    private final static long MACHINE_ID_LEFT = SEQUENCE_BIT;
    // 数据中心id向左移17位(12+5)
    private final static long DATACENTER_ID_LEFT = SEQUENCE_BIT + MACHINE_BIT;
    // 时间截向左移22位(5+5+12)
    private final static long TIME_STAMP_LEFT = SEQUENCE_BIT + MACHINE_BIT + DATACENTER_BIT;

    //数据中心ID(0~31)
    private long datacenterId;
    //机器ID(0~31)
    private long machineId;
    //序列号 { 毫秒内序列(0~4095)}
    private long sequence = 0L;
    //上一次时间戳
    private long lastTimestamp = -1L;

    private static SnowFlakeIdGenerator snowFlakeIdGenerator = null;

    //加载初始化参数
    static {
        snowFlakeIdGenerator = new SnowFlakeIdGenerator();
    }

    //装载初始化参数
    private SnowFlakeIdGenerator() {
        //获取机器编码
        this.machineId = this.getMachineNum();
        //获取进程编码
        RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
        this.datacenterId = Long.valueOf(runtimeMXBean.getName().split("@")[0]).longValue();
        //避免编码超出最大值
        this.machineId = machineId & MAX_MACHINE_NUM;
        this.datacenterId = datacenterId & MAX_DATACENTER_NUM;
    }

    /**
     * @return long
     * @Titel 生成ID
     * @Description 生成ID
     * @Author 龘鵺
     * @DateTime 11/8/2023 上午11:36
     */
    public static synchronized long nextId() {
        return snowFlakeIdGenerator.getNextId();
    }

    /**
     * @return long
     * @Titel 生成ID
     * @Description 生成ID
     * @Author 龘鵺
     * @DateTime 11/8/2023 上午11:55
     */
    public synchronized long getNextId() {
        //获取时间戳
        long timestamp = getCurrentTimeStamp();
        //如果时间戳小于上次时间戳则报错
        if (timestamp < lastTimestamp) {
            try {
                throw new Exception("SnowFlakeIdGenerator = Clock moved backwards.  Refusing to generate id for " + (lastTimestamp - timestamp) + " milliseconds");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //如果时间戳与上次时间戳相同
        if (lastTimestamp == timestamp) {
            // 当前毫秒内，则+1，与sequenceMask确保sequence不会超出上限
            sequence = (sequence + 1) & MAX_SEQUENCE;
            if (sequence == 0) {
                // 当前毫秒内计数满了，则等待下一秒
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            sequence = 0;
        }
        lastTimestamp = timestamp;
        // ID偏移组合生成最终的ID，并返回ID
        long nextId = ((timestamp - START_TIME_STAMP) << TIME_STAMP_LEFT) | (datacenterId << DATACENTER_ID_LEFT) | (machineId << MACHINE_ID_LEFT) | sequence;
        return nextId;
    }

    /**
     * @param lastTimestamp 1 上次生成ID的时间截
     * @return long
     * @Titel 再次获取时间戳直到获取的时间戳与现有的不同
     * @Description 再次获取时间戳直到获取的时间戳与现有的不同
     * @Author 龘鵺
     * @DateTime 11/8/2023 上午11:57
     */
    private long tilNextMillis(final long lastTimestamp) {
        long timestamp = this.getCurrentTimeStamp();
        while (timestamp <= lastTimestamp) {
            timestamp = this.getCurrentTimeStamp();
        }
        return timestamp;
    }

    /**
     * @return long
     * @Titel 返回以毫秒为单位的当前时间
     * @Description 返回以毫秒为单位的当前时间
     * @Author 龘鵺
     * @DateTime 11/8/2023 上午11:34
     */
    protected long getCurrentTimeStamp() {
        return System.currentTimeMillis();
    }

    /**
     * @return long
     * @Titel 获取机器编码
     * @Description 获取机器编码
     * @Author 龘鵺
     * @DateTime 11/8/2023 上午11:58
     */
    private long getMachineNum() {
        long machinePiece;
        StringBuilder sb = new StringBuilder();
        Enumeration<NetworkInterface> e = null;
        try {
            e = NetworkInterface.getNetworkInterfaces();
        } catch (SocketException e1) {
            e1.printStackTrace();
        }
        while (e.hasMoreElements()) {
            NetworkInterface ni = e.nextElement();
            sb.append(ni.toString());
        }
        machinePiece = sb.toString().hashCode();
        return machinePiece;
    }

    /**
     * @return java.lang.Long
     * @Titel 生成机器编码(0 - 31) 方法2
     * @Description 生成机器编码(0 - 31) 方法2
     * @Author 龘鵺
     * @DateTime 11/8/2023 上午11:58
     */
    private static Long getMachineId() {
        try {
            String hostAddress = Inet4Address.getLocalHost().getHostAddress();
            int[] ints = StringUtils.toCodePoints(hostAddress);
            int sums = 0;
            for (int b : ints) {
                sums += b;
            }
            return (long) (sums % 32);
        } catch (UnknownHostException e) {
            // 如果获取失败，则使用随机数备用
            return RandomUtils.nextLong(0, 31);
        }
    }

    /**
     * @return java.lang.Long
     * @Titel 生成数据中心编码(0 - 31)
     * @Description 生成数据中心编码(0 - 31)
     * @Author 龘鵺
     * @DateTime 11/8/2023 上午11:58
     */
    private static Long getDataCenterId() {
        int[] ints = StringUtils.toCodePoints(SystemUtils.getHostName());
        int sums = 0;
        for (int i : ints) {
            sums += i;
        }
        return (long) (sums % 32);
    }

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException {
        for (int i = 0; i < 11; i++) {
            Long id = SnowFlakeIdGenerator.nextId();
            System.out.println();
            System.out.println("id：" + id);
            System.out.println("id：" + id.toString().length());
//            System.out.println();
            Class cla = Class.forName(SnowFlakeIdGenerator.class.getName());
            Field[] fields = cla.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);

                if (field.getName().equals("machineId")) {
                    Object value = field.get(new SnowFlakeIdGenerator());
                    System.out.println(field.getName() + " " + value);
                }
                if (field.getName().equals("datacenterId")) {
                    Object value = field.get(new SnowFlakeIdGenerator());
                    System.out.println(field.getName() + " " + value);
                }
                if (field.getName().equals("lastTimestamp")) {
                    Object value = field.get(new SnowFlakeIdGenerator());
                    System.out.println(field.getName() + " " + value);
                }
            }
        }
    }
}