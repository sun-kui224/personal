package com.person.common.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 公司项目对象 p_work_company_project
 *
 * @author sk
 * @date 2024-05-17
 */
@Data
@ApiModel("公司项目对象")
public class PWorkCompanyProject
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    @ApiModelProperty("项目名称")
    private String projectName;

    @ApiModelProperty("项目描述")
    private String projectDescribe;

    @ApiModelProperty("项目地址")
    private String projectPlace;

    @ApiModelProperty("创建人")
    private String createBy;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty("创建时间")
    private Date createDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty("更新时间")
    private Date updateDate;

    @ApiModelProperty("有效-0，无效-1")
    private Long delFlag;

}
