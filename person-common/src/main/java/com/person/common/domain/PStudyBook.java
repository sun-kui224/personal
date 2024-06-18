package com.person.common.domain;

import java.math.BigDecimal;
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
 * 学习书籍对象 p_study_book
 *
 * @author sk
 * @date 2024-05-17
 */
@Data
@ApiModel("学习书籍对象")
public class PStudyBook
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    @ApiModelProperty("书籍作者")
    private String bookAuthor;

    @ApiModelProperty("书籍价格")
    private BigDecimal bookPrice;

    @ApiModelProperty("书籍名称")
    private String bookName;

    @ApiModelProperty("书籍字数")
    private Long wordNumber;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty("出版年限")
    private Date publicationPeriod;

    @ApiModelProperty("是否购买，0-未买，1-已买")
    private Integer isBuy;

    @ApiModelProperty("书籍描述")
    private String bookDescribe;

    @ApiModelProperty("在线地址")
    private String onlineAddress;

    @ApiModelProperty("有效-0，无效-1 ")
    private Integer delFlag;

    @ApiModelProperty("封面图片")
    private String coverPhoto;

    @ApiModelProperty("创建人")
    private String createBy;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty("创建时间")
    private Date createDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty("更新时间")
    private Date updateDate;

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
