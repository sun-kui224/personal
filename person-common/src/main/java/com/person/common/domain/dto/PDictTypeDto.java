package com.person.common.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * 字典类型对象
 *
 * @author sk
 * @date 2024-06-04
 */
@Data
@ApiModel("字典类型对象(接收)")
public class PDictTypeDto
{
    private static final long serialVersionUID = 1L;

    private Long id;

    @ApiModelProperty("字典名称")
    @NotBlank(message="字典名称不能为空！")
    @Size(max = 20, message = "字典名称长度不能超过20个字符")
    private String dictName;

    @ApiModelProperty("字典类型")
    @NotBlank(message="字典类型不能为空！")
    @Size(max = 50, message = "字典类型长度不能超过50个字符")
    private String dictType;

}
