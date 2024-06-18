package com.person.common.base.domain;

import com.person.common.constant.HttpConstant;

import java.util.HashMap;

/**
 * 响应数据
 * @author sk
 * @date 2024/5/11
 */
public class AjaxResult extends HashMap<String,Object> {

    private static final long serialVersionUID = 1L;

    /** 状态码 */
    public static final String CODE = "code";

    /** 返回内容 */
    public static final String MSG = "msg";

    /** 数据对象 */
    public static final String DATA = "data";

    public AjaxResult(){

    }

    /**
     * 成功
     * @return
     */
    public static AjaxResult success(){
        return success("成功");
    }

    public static AjaxResult success(Object data){
        return success("操作成功",data);
    }

    public static AjaxResult success(String msg,Object data){
        AjaxResult result = new AjaxResult();
        result.put(CODE, HttpConstant.SUCCESS);
        result.put(MSG, msg);
        result.put(DATA, data);
        return result;
    }

    /**
     * 失败
     * @return
     */
    public static AjaxResult error(){
        return error("失败");
    }

    public static AjaxResult error(String msg){
        return error(msg,null);
    }

    public static AjaxResult error(String msg,Object data){
        AjaxResult result = new AjaxResult();
        result.put(CODE, HttpConstant.ERROR);
        result.put(MSG, msg);
        result.put(DATA, data);
        return result;
    }

    public static AjaxResult error(int code,String msg){
        AjaxResult result = new AjaxResult();
        result.put(CODE, code);
        result.put(MSG, msg);
        result.put(DATA, null);
        return result;
    }

    /**
     * 警告
     */
    public static AjaxResult warn(String msg){
        return warn(msg,null);
    }

    public static AjaxResult warn(String msg,Object data){
        AjaxResult result = new AjaxResult();
        result.put(CODE, HttpConstant.WARN);
        result.put(MSG, msg);
        result.put(DATA, data);
        return result;
    }
    @Override
    public AjaxResult put(String key, Object value)
    {
        super.put(key, value);
        return this;
    }
}
