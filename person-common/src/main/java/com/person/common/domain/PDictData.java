package com.person.common.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
/**
 * 字典数据对象 p_dict_data
 *
 * @author sk
 * @date 2024-06-04
 */
@Data
@ApiModel("文件关联对象")
public class PDictData
{
    private static final long serialVersionUID = 1L;

    /** 字典编码 */
    private Long id;

    @ApiModelProperty("字典排序")
    private Long dictSort;

    @ApiModelProperty("字典标签")
    private String dictLabel;

    @ApiModelProperty("字典键值")
    private String dictValue;

    @ApiModelProperty("字典类型")
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
