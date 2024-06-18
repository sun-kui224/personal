package com.person.common.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 公司对象 p_work_company
 *
 * @author sk
 * @date 2024-05-17
 */
@Data
@ApiModel("公司对象")
public class PWorkCompany
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    @ApiModelProperty("公司全称")
    private String companyName;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty("入职时间")
    private Date employmentDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty("离职时间")
    private Date resignationDate;

    @ApiModelProperty("公司地点")
    private String companyPlace;

    @ApiModelProperty("公司人数")
    private Long companyPeople;

    @ApiModelProperty("是否上市")
    private Long upMarket;

    @ApiModelProperty("公司打分")
    private Long companyScore;

    @ApiModelProperty("创建人")
    private String createBy;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty("创建时间")
    private Date createDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty("修改时间")
    private Date updateDate;

    @ApiModelProperty("有效-0，无效-1")
    private Long delFlag;

}
