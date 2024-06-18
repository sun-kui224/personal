package com.person.common.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * 学习网址对象
 *
 * @author sk
 * @date 2024-05-17
 */
@Data
@ApiModel("学习网址对象（接收）")
public class PStudyWebsiteDto
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    @ApiModelProperty("网址图片")
    private String picture;

    @ApiModelProperty("网站名称")
    @NotBlank(message = "网站名称不能为空！")
    @Size(max = 20, message = "网站名称长度不能超过20个字符")
    private String websiteName;

    @ApiModelProperty("公司名称")
    @Size(max = 20, message = "公司名称长度不能超过20个字符")
    private String companyName;

    @ApiModelProperty("网站地址")
    @NotBlank(message = "网站地址不能为空！")
    @Size(max = 100, message = "网站地址长度不能超过100个字符")
    private String websiteUrl;

    @ApiModelProperty("网站描述")
    @Size(max = 250, message = "网站描述长度不能超过250个字符")
    private String websiteDescribe;

}
