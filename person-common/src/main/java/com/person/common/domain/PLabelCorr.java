package com.person.common.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 标签关联对象 p_label_corr
 *
 * @author sk
 * @date 2024-05-17
 */
@Data
@ApiModel("标签关联对象")
public class PLabelCorr
{
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("标签id")
    private Long labelId;

    @ApiModelProperty("外联主键id")
    private Long outId;

    @ApiModelProperty("标签id数组")
    private Long[] labelIds;
}
