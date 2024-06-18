package com.person.common.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 项目关联对象 p_work_project_corr
 *
 * @author sk
 * @date 2024-05-17
 */
@Data
@ApiModel("项目关联对象")
public class PWorkProjectCorr
{
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("公司id")
    private Long companyId;

    @ApiModelProperty("项目id")
    private Long projectId;

}
