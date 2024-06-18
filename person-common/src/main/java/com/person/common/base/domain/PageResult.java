package com.person.common.base.domain;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 分页返回结果
 * @author sk
 * @date 2024/5/14
 */
@Data
public class PageResult {

    /**
     * 当前页
     */
    private int currentPage;

    /**
     * 每页大小
     */
    private int pageSize;

    /**
     * 所有页数
     */
    private int pageTotal;

    /**
     * 总条数
     */
    private Long total;

    /**
     * 结果集
     */
    private List<?> list;

}
