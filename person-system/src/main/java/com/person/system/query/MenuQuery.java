package com.person.system.query;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author sk
 * @date 2024/6/4
 */
@Data
public class MenuQuery {

    @ApiModelProperty("菜单名称")
    private String name;

    @ApiModelProperty("菜单路由")
    private String path;
}
