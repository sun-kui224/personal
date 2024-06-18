package com.person.common.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author sk
 * @date 2024-05-17
 */
@Data
@ApiModel("个人错误记录对象(接收)")
public class PPersonBugRecordsDto
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    @ApiModelProperty("问题描述")
    private String problemDescription;

    @ApiModelProperty("解决方案")
    private String solveProgramme;

    @ApiModelProperty("记录名称")
    private String recordsName;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty("出现时间")
    private Date appearDate;

}
