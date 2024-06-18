package com.person.common.utils;

import org.apache.commons.lang3.ObjectUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间工具
 * @author sk
 * @date 2024/5/17
 */
public class DateUtils {

    /**
     * 获取当前Date型日期
     *
     * @return Date() 当前日期
     */
    public static Date getNowDate()
    {
        return new Date();
    }

    /**
     * 日期格式化为字符串
     * @param format 日期格式字符串，例如："yyyy-MM-dd HH:mm:ss"
     * @param date 需要格式化的日期对象
     * @return 格式化后的日期字符串
     */
    public static final String formatDateToStr(String format, final Date date) {
        if (ObjectUtils.isEmpty(format)) {
            throw new IllegalArgumentException("日期格式字符串不能为空");
        }
        if (date == null) {
            throw new IllegalArgumentException("日期对象不能为空");
        }
        return new SimpleDateFormat(format).format(date);
    }
}
