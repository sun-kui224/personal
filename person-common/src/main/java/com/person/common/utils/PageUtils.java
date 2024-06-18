package com.person.common.utils;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.page.PageMethod;
import com.person.common.base.domain.PageParam;
import com.person.common.base.domain.PageResult;

import java.util.List;

/**
 * 分页工具类
 * @author sk
 * @date 2024/5/14
 */
public class PageUtils {

    /**
     * 分页
     * @param pageParam
     */
    public static void pageStart(PageParam pageParam){
        PageHelper.startPage(pageParam.getPageNum(), pageParam.getPageSize());
    }

    /**
     * 封装分页结果集
     * @param list
     */
    public static PageResult pageEnd(List<?> list){
        // 将查询结果给 pageInfo 处理
        PageInfo pageInfo = new PageInfo<>(list);
        // 创建结果集对象
        PageResult result = new PageResult();
        // 将结果封装到结果集对象，当前页
        result.setCurrentPage(pageInfo.getPageNum());
        // 每页数
        result.setPageSize(pageInfo.getPageSize());
        // 总页数
        result.setPageTotal(pageInfo.getPages());
        // 总条数
        result.setTotal(pageInfo.getTotal());
        // 结果集
        result.setList(list);
        return result;
    }
}
