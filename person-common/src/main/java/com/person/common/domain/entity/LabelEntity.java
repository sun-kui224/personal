package com.person.common.domain.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @author sk
 * @date 2024/6/3
 */
@Data
@ApiModel("标签对象(发送)")
public class LabelEntity {

    /**
     * 标签id
     */
    private Long id;

    /**
     * 标签名称
     */
    private String labelName;
}
