package com.person.common.enums;

/**
 * 操作状态
 * @author sk
 * @date 2024/5/17
 */
public enum OperStatus {
    SUCCESS(0,"成功"),
    FAIL(1,"失败");

    private final int code;
    private final String msg;

    OperStatus(int code , String msg){
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
