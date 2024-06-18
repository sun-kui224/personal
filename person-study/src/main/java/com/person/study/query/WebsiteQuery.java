package com.person.study.query;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author sk
 * @date 2024/6/4
 */
@Data
public class WebsiteQuery {

    @ApiModelProperty("网站名称")
    private String websiteName;
}
