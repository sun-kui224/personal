package com.person.system.query;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author ruoyi
 * @date 2024/6/4
 */
@Data
public class DictTypeQuery {

    @ApiModelProperty("字典名称")
    private String dictName;

    @ApiModelProperty("字典")
    private String dictType;

    @ApiModelProperty("状态（0正常 1停用）")
    private String status;
}
