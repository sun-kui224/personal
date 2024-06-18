package com.person.system.query;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class PDictDataQuery {

    @ApiModelProperty("字典标签")
    private String dictLabel;

    @ApiModelProperty("状态（0正常 1停用）")
    private String status;

    @ApiModelProperty("字典类型")
    private String dictType;
}
