package com.person.common.domain;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 菜单对象 p_menu
 *
 * @author sk
 * @date 2024-05-17
 */
@Data
@ApiModel("菜单对象")
public class PMenu
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    @ApiModelProperty("父id")
    private Long parentId;

    @ApiModelProperty("菜单名称")
    private String name;

    @ApiModelProperty("是否缓存 0-缓存，1-不缓存")
    private Integer isCache;

    @ApiModelProperty("是否隐藏 0-隐藏，1-不隐藏")
    private Integer hide;

    @ApiModelProperty("排序号")
    private Integer sort;

    @ApiModelProperty("0-有效，1-无效")
    private Integer delFlag;

    /** 状态 （0正常 1停用） */
    @ApiModelProperty("状态 （0正常 1停用）")
    private Integer status;

    @ApiModelProperty("菜单类型，M-目录，C-菜单")
    private String type;

    @ApiModelProperty("组件路径")
    private String component;

    @ApiModelProperty("菜单路由")
    private String path;

    @ApiModelProperty("菜单图标")
    private String icon;

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

    private List<PMenu> children;

}
