package com.person.common.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 文件关联对象 p_file_corr
 *
 * @author sk
 * @date 2024-05-17
 */
@Data
@ApiModel("文件关联对象")
public class PFileCorr
{
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("文件id")
    private Long fileId;

    @ApiModelProperty("外联主键id")
    private Long outId;

}
