package com.zhouyouwu.utils;

/**
 * @author Administrator
 */
public class SnowflakeUtil {
    // ==============================Fields===========================================
    /** 机器id所占的位数 */
    private final long workerIdBits = 2L;
    /** 数据标识id所占的位数 */
    private final long datacenterIdBits = 2L;
    /** 工作机器ID(0~31) */
    private long workerId;
    /** 数据中心ID(0~31) */
    private long datacenterId;
    /** 毫秒内序列(0~4095) */
    private long sequence = 0L;
    /** 上次生成ID的时间截 */
    private long lastTimestamp = -1L;
    private static SnowflakeUtil idWorker;
    /**
     * 以SnowFlake算法，获取唯一有序id
     * @return
     */
    public static long getSnowflakeId() {
        if(idWorker == null) {
            synchronized (SnowflakeUtil.class) {
                if(idWorker == null) {
                    idWorker = new SnowflakeUtil(0, 0);
                }
            }
        }
        return idWorker.nextId();
    }
    // ==============================Methods==========================================
    private SnowflakeUtil() {
    }
    //==============================Constructors=====================================
    /**
     * 构造函数
     * @param workerId 工作ID (0~31)
     * @param datacenterId 数据中心ID (0~31)
     */
    private SnowflakeUtil(long workerId, long datacenterId) {
        /* 支持的最大机器id，结果是31 (这个移位算法可以很快的计算出几位二进制数所能表示的最大十进制数) */
        long maxWorkerId = ~(-1L << workerIdBits);
        if (workerId > maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0", maxWorkerId));
        }
        /* 支持的最大数据标识id，结果是31 */
        long maxDatacenterId = ~(-1L << datacenterIdBits);
        if (datacenterId > maxDatacenterId || datacenterId < 0) {
            throw new IllegalArgumentException(String.format("datacenter Id can't be greater than %d or less than 0", maxDatacenterId));
        }
        this.workerId = workerId;
        this.datacenterId = datacenterId;
    }
    /**
     * 阻塞到下一个毫秒，直到获得新的时间戳
     * @param lastTimestamp 上次生成ID的时间截
     * @return 当前时间戳
     */
    protected long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }
    /**
     * 返回以毫秒为单位的当前时间
     * @return 当前时间(毫秒)
     */
    protected long timeGen() {
        return System.currentTimeMillis();
    }
    /**
     * 获得下一个ID (该方法是线程安全的)
     * @return SnowflakeId
     */
    protected synchronized long nextId() {
        long timestamp = timeGen();
        //如果当前时间小于上一次ID生成的时间戳，说明系统时钟回退过这个时候应当抛出异常
        if (timestamp < lastTimestamp) {
            throw new RuntimeException(
                    String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds", lastTimestamp - timestamp));
        }
        //如果是同一时间生成的，则进行毫秒内序列
        /* 序列在id中占的位数 */
        long sequenceBits = 10L;
        if (lastTimestamp == timestamp) {
            /* 生成序列的掩码，这里为4095 (0b111111111111=0xfff=4095) */
            long sequenceMask = ~(-1L << sequenceBits);
            sequence = (sequence + 1) & sequenceMask;
            //毫秒内序列溢出
            if (sequence == 0) {
                //阻塞到下一个毫秒,获得新的时间戳
                timestamp = tilNextMillis(lastTimestamp);
            }
        }
        //时间戳改变，毫秒内序列重置
        else {
            sequence = 0L;
        }
        //上次生成ID的时间截
        lastTimestamp = timestamp;
        //移位并通过或运算拼到一起组成64位的ID
        /* 时间截向左移22位(5+5+12) */
        long timestampLeftShift = sequenceBits + workerIdBits + datacenterIdBits;
        /* 数据标识id向左移17位(12+5) */
        long datacenterIdShift = sequenceBits + workerIdBits;
        /* 机器ID向左移12位 */
        /* 开始时间截 (2015-01-01) */
        long twepoch = 1420041600000L;
        return ((timestamp - twepoch) << timestampLeftShift)
                | (datacenterId << datacenterIdShift)
                | (workerId << sequenceBits)
                | sequence;
    }

    public static void main(String[] args) {
        System.out.println(SnowflakeUtil.getSnowflakeId());
    }
}
