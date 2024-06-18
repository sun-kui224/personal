package com.person.common.enums;

/**
 * 操作类型
 * @author sk
 * @date 2024/5/17
 */
public enum OperateType {
    OTHER(0,"其他"),
    INSERT(1,"新增"),
    UPDATE(2,"修改"),
    DELETE(3,"删除");

    private final int type;
    private final String name;

    OperateType(int type ,String name){
        this.type = type;
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public String getName() {
        return name;
    }
}
