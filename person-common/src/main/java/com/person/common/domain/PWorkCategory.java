package com.person.common.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 记录分类对象 p_work_category
 *
 * @author sk
 * @date 2024-05-17
 */
@Data
@ApiModel("记录分类对象")
public class PWorkCategory
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    @ApiModelProperty("创建人")
    private String createBy;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty("创建时间")
    private Date createDate;

    @ApiModelProperty("分类名称")
    private String categoryName;

    @ApiModelProperty("有效-0，无效-1")
    private Long delFlag;

}
