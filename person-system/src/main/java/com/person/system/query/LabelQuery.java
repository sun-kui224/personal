package com.person.system.query;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author sk
 * @date 2024/6/4
 */
@Data
public class LabelQuery {

    @ApiModelProperty("标签名称")
    private String labelName;
}
