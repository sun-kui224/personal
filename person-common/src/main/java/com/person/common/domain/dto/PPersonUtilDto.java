package com.person.common.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * 个人工具对象 p_person_util
 *
 * @author sk
 * @date 2024-05-17
 */
@Data
@ApiModel("个人工具对象")
public class PPersonUtilDto
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    @ApiModelProperty("工具名称")
    @NotBlank(message = "工具名称不能为空")
    private String utilName;

    @ApiModelProperty("工具文件地址")
    @NotBlank(message = "工具文件地址不能为空")
    private String depositUrl;

    @ApiModelProperty("操作文档地址")
    @NotBlank(message = "操作文档地址不能为空")
    private String operationUrl;

    @ApiModelProperty("工具描述")
    private String context;

    @ApiModelProperty("访问地址")
    private String accessUrl;

}
