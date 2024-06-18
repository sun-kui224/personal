package com.person.common.domain;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 用户对象 p_person
 *
 * @author sk
 * @date 2024-05-17
 */
@Data
@ApiModel("用户对象")
public class PPerson
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    @ApiModelProperty("账户")
    private String loginName;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("名称")
    private String nickName;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty("创建时间")
    private Date createDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty("更新时间")
    private Date updateDate;

    @ApiModelProperty("头像")
    private String avatar;

    @ApiModelProperty("0-未删除，1-已删除")
    private Integer delFlag;

    @ApiModelProperty("状态0-有效，1-无效")
    private Integer status;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty("出生年月")
    private Date birthDate;

    @ApiModelProperty("个性签名")
    private String signature;

    @ApiModelProperty("岗位")
    private String post;

}
