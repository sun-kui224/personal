package com.person.common.base.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author sk
 * @date 2024/5/14
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageParam {

    /**
     * 页码
     */
    private int pageNum ;

    /**
     * 每页大小
     */
    private int pageSize ;
}
