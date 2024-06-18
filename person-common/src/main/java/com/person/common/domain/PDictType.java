package com.person.common.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
/**
 * 字典类型对象 p_dict_type
 *
 * @author sk
 * @date 2024-06-04
 */
@Data
@ApiModel("字典类型对象")
public class PDictType
{
    private static final long serialVersionUID = 1L;

    private Long id;

    @ApiModelProperty("字典名称")
    private String dictName;

    @ApiModelProperty("字典")
    private String dictType;

    @ApiModelProperty("状态（0正常 1停用）")
    private String status;

    @ApiModelProperty("创建者")
    private String createBy;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("更新者")
    private String updateBy;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty("更新时间")
    private Date updateTime;

}
