package com.person.common.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 个人工具对象 p_person_util
 *
 * @author sk
 * @date 2024-05-17
 */
@Data
@ApiModel("个人工具对象")
public class PPersonUtil
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    @ApiModelProperty("工具名称")
    private String utilName;

    @ApiModelProperty("工具文件地址")
    private String depositUrl;

    @ApiModelProperty("操作文档地址")
    private String operationUrl;

    @ApiModelProperty("工具描述")
    private String context;

    @ApiModelProperty("创建人")
    private String createBy;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty("创建时间")
    private Date createDate;

    /** 0-有效，1-无效 */
    private int delFlag;

    @ApiModelProperty("访问地址")
    private String accessUrl;

}
