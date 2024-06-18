package com.person.common.utils;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 唯一标识工具类
 * @author sk
 * @date 2024/5/11
 */
public class IdUtils {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
    private static final AtomicLong SEQUENCE = new AtomicLong(0L);
    private static final AtomicInteger SEQUENCE_ID = new AtomicInteger(0);
    private static final long MAX_SEQUENCE = 999999L; // 序列号最大值，占用6位

    /**
     * 生成唯一id
     * @return
     */
    public static long fastId() {
        long currentTimeMillis = LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli();
        long sequence = SEQUENCE.getAndIncrement();

        // 如果序列号达到最大值，则重置序列号，并等待下一毫秒
        if (sequence >= MAX_SEQUENCE) {
            SEQUENCE.set(0L);
            currentTimeMillis = getNextMillisecond(currentTimeMillis);
        }

        // 组合ID：前14位为时间戳（毫秒级别，去除日期时间中的符号），后6位为序列号
        return (currentTimeMillis << 6) | sequence;
    }

    /**
     * 获取下一毫秒的时间戳
     * @param currentMillis 当前毫秒时间戳
     * @return 下一毫秒的时间戳
     */
    private static long getNextMillisecond(long currentMillis) {
        while (true) {
            long nextMillis = LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli();
            if (nextMillis > currentMillis) {
                return nextMillis;
            }
        }
    }

    /**
     * 获取字符串形式的唯一id
     * @param value
     * @return
     */
    public static String uniqueId(String value) {
        LocalDateTime now = LocalDateTime.now();
        String dateTimePart = now.format(DATE_FORMATTER);
        int sequence = SEQUENCE_ID.getAndIncrement(); // 原子递增序列号
        String sequencePart = String.format("%06d", sequence); // 序列号补零至6位

        // 如果序列号达到最大值999999，则重置序列号
        if (sequence >= MAX_SEQUENCE) {
            SEQUENCE.set(0);
        }

        return value+dateTimePart + sequencePart;
    }

    /**
     * 生成32位唯一字符串
     * @return
     */
    public static String fastUUID()
    {
         return UUID.randomUUID().toString().replaceAll("-","");
    }
}
