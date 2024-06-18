package com.person.common.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 标签对象 p_person_label
 *
 * @author sk
 * @date 2024-05-31
 */
@Data
@ApiModel("标签对象（接收）")
public class PPersonLabelDto
{
    private static final long serialVersionUID = 1L;

    private Long id;

    @ApiModelProperty("父id")
    private Long pid;

    @ApiModelProperty("标签名称")
    @NotBlank(message = "标签名称不能为空！")
    @Length(max = 10, message = "菜单名称长度不能超过10个字符")
    private String labelName;

    @ApiModelProperty("状态，0-正常，1-停用")
    @NotNull(message = "状态不能为空")
    @Min(value = 0, message = "状态只能是0或者1")
    @Max(value = 1, message = "状态只能是0或者1")
    private Integer status;

}
