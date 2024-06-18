package com.person.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 类型比较工具
 * @author sk
 * @date 2024/5/22
 */
public class compareUtils {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * Integer与int比较
     */
    public static boolean integerEqualsToInt(Integer value1, int value2) {
        if(value1 == null){
            return false;
        }
        return value1.intValue() == value2;
    }
}
