package com.person.common.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 标签关联对象 p_label_corr
 *
 * @author sk
 * @date 2024-05-17
 */
@Data
@ApiModel("标签关联对象")
public class PLabelCorrDto
{
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("标签id数组")
    private Long[] labelIds;

    @ApiModelProperty("外联主键id")
    @NotNull(message = "外联主键id不能为空！")
    private Long outId;

}
