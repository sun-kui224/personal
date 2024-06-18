package com.person.common.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.alibaba.fastjson2.JSON;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.person.common.domain.entity.LabelEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 学习网址对象 p_study_website
 *
 * @author sk
 * @date 2024-05-17
 */
@Data
@ApiModel("学习网址对象")
public class PStudyWebsite
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    @ApiModelProperty("网址图片")
    private String picture;

    @ApiModelProperty("网站名称")
    private String websiteName;

    @ApiModelProperty("公司名称")
    private String companyName;

    @ApiModelProperty("网站地址")
    private String websiteUrl;

    @ApiModelProperty("网站描述")
    private String websiteDescribe;

    @ApiModelProperty("创建人")
    private String createBy;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty("创建时间")
    private Date createDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty("更新时间")
    private Date updateDate;

    @ApiModelProperty("删除标识，有效-0，无效-1")
    private Integer delFlag;

    @ApiModelProperty("标签集合")
    private List<LabelEntity> labelList;

    public List<LabelEntity> getLabelList() {
        return labelList;
    }

    public void setLabelList(String labelString) {
        List<LabelEntity> labelEntities = JSON.parseArray(labelString, LabelEntity.class);
        this.labelList = labelEntities.stream()
                                      .filter(labelEntity -> labelEntity.getId() != null)
                                      .collect(Collectors.toList());
    }
}
