package com.person.common.domain;

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
 * 个人错误记录对象 p_person_bug_records
 *
 * @author sk
 * @date 2024-05-17
 */
@Data
@ApiModel("个人错误记录对象")
public class PPersonBugRecords
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    @ApiModelProperty("问题描述")
    private String problemDescription;

    @ApiModelProperty("解决方案")
    private String solveProgramme;

    @ApiModelProperty("记录名称")
    private String recordsName;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty("出现时间")
    private Date appearDate;

    @ApiModelProperty("创建人")
    private String createdBy;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty("创建时间")
    private Date createdDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty("更新时间")
    private Date updateDate;

    @ApiModelProperty("有效-0，无效-1")
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
