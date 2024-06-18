package com.person.common.base.controller;

import com.github.pagehelper.PageHelper;
import com.person.common.base.domain.AjaxResult;
import com.person.common.base.domain.PageParam;
import com.person.common.base.domain.PageResult;
import com.person.common.utils.PageUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * web层通用功能
 * @author sk
 * @date 2024/5/11
 */
public class BaseController {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 分页
     */
    public void startPage(PageParam pageParam){
        PageUtils.pageStart(pageParam);
    }

    /**
     * 封装分页数据
     * @param list
     * @return
     */
    public PageResult endPage(List<?> list){
       return PageUtils.pageEnd(list);
    }

    public AjaxResult success(Object data){
        return AjaxResult.success("成功",data);
    }

    public AjaxResult error(String mes){
        return AjaxResult.success(mes);
    }
}
