package com.person.common.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 工作记录对象 p_work_report
 *
 * @author sk
 * @date 2024-05-17
 */
@Data
@ApiModel("工作记录对象")
public class PWorkReport
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    @ApiModelProperty("内容")
    private String context;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty("创建时间")
    private Date createDate;

    @ApiModelProperty("创建人")
    private String createBy;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty("更新时间")
    private Date updateDate;

    @ApiModelProperty(" 0-有效，1-无效")
    private Long delFlag;

    @ApiModelProperty("附件地址")
    private String fileUrl;

}
