package com.person.common.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;
import java.util.Date;

/**
 * @author sk
 * @date 2024/5/22
 */
@Data
@ApiModel("菜单对象(接收)")
public class PMenuDto {

    private static final long serialVersionUID = 1L;

    private Long id;

    @ApiModelProperty("父id")
    @NotNull(message = "上级菜单不能为空！")
    private Long parentId;

    @ApiModelProperty("菜单名称")
    @NotBlank(message="菜单名称不能为空！")
    @Length(min = 2, max = 20, message = "菜单名称长度应在2到20个字符之间")
    private String name;

    @ApiModelProperty("是否缓存 0-缓存，1-不缓存")
    @NotNull(message = "isCache不能为空")
    @Min(value = 0, message = "isCache必须为0或1")
    @Max(value = 1, message = "isCache必须为0或1")
    private Integer isCache;

    @ApiModelProperty("是否隐藏 0-隐藏，1-不隐藏")
    @NotNull(message = "hide不能为空")
    @Min(value = 0, message = "hide必须为0或1")
    @Max(value = 1, message = "hide必须为0或1")
    private Integer hide;

    @ApiModelProperty("排序号")
    @NotNull(message = "排序号不能为空")
    @Min(value = 0, message = "排序号必须大于0")
    @Max(value = 999, message = "排序号必须小于999")
    private Integer sort;

    /** 状态 （0正常 1停用） */
    @ApiModelProperty("状态 （0正常 1停用）")
    @NotNull(message = "状态不能为空")
    @Min(value = 0, message = "状态必须为0或1")
    @Max(value = 1, message = "状态必须为0或1")
    private Integer status;

    @ApiModelProperty("菜单类型，M-目录，C-菜单")
    @NotBlank(message = "菜单类型不能为空")
    @Pattern(regexp = "[MC]", message = "菜单类型必须为目录（M）或菜单（C）")
    private String type;

    @ApiModelProperty("组件路径")
    @Size(max = 255, message = "组件路径长度不能超过255个字符")
    private String component;

    @ApiModelProperty("菜单路由")
    @Size(max = 255, message = "菜单路由长度不能超过255个字符")
    private String path;

    @ApiModelProperty("菜单图标")
    @Size(max = 32, message = "菜单图标长度不能超过32个字符")
    private String icon;
}
