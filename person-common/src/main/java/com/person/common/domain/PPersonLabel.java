package com.person.common.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 标签对象 p_person_label
 *
 * @author sk
 * @date 2024-05-31
 */
@Data
@ApiModel("标签对象")
public class PPersonLabel
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    @ApiModelProperty("父id")
    private Long pid;

    @ApiModelProperty("标签名称")
    private String labelName;

    @ApiModelProperty("状态，0-正常，1-停用")
    private Integer status;

    @ApiModelProperty("创建人")
    private String createBy;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty("创建时间")
    private Date createDate;

    @ApiModelProperty("修改人")
    private String updateBy;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty("修改时间")
    private Date updateDate;

    @ApiModelProperty("关联数量")
    private Integer resourceNumber;

}
