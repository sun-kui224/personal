package com.person.common.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * 字典数据对象 p_dict_data
 *
 * @author sk
 * @date 2024-06-04
 */
@Data
@ApiModel("文件关联对象(接收)")
public class PDictDataDto
{
    private static final long serialVersionUID = 1L;

    /** 字典编码 */
    private Long id;

    @ApiModelProperty("字典排序")
    @NotNull(message = "字典排序不能为空")
    private Long dictSort;

    @ApiModelProperty("字典标签")
    @NotBlank(message = "字典标签不能为空")
    @Size(max = 20, message = "字典标签长度不能超过20个字符")
    private String dictLabel;

    @ApiModelProperty("字典键值")
    @NotBlank(message = "字典键值不能为空")
    @Size(max = 100, message = "字典键值长度不能超过100个字符")
    private String dictValue;

    @ApiModelProperty("字典类型")
    @NotBlank(message = "字典类型不能为空")
    @Size(max = 50, message = "字典类型长度不能超过50个字符")
    private String dictType;

}
