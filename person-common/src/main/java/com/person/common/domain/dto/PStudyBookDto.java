package com.person.common.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 学习书籍对象 p_study_book
 *
 * @author sk
 * @date 2024-05-17
 */
@Data
@ApiModel("学习书籍对象（接收）")
public class PStudyBookDto
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    @ApiModelProperty("书籍作者")
    @NotBlank(message = "书籍作者不能为空！")
    @Size(max = 50, message = "书籍作者长度不能超过50个字符")
    private String bookAuthor;

    @ApiModelProperty("书籍价格")
    @NotNull(message = "书籍价格不能为空！")
    private BigDecimal bookPrice;

    @ApiModelProperty("书籍名称")
    @NotBlank(message = "书籍名称不能为空！")
    @Size(max = 50, message = "书籍名称长度不能超过50个字符")
    private String bookName;

    @ApiModelProperty("书籍字数")
    private Long wordNumber;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty("出版年限")
    @NotNull(message = "出版年限不能为空！")
    private Date publicationPeriod;

    @ApiModelProperty("是否购买，0-未买，1-已买")
    @NotNull(message = "是否购买不能为空！")
    @Min(value = 0,message = "该字段只能为0或1！")
    @Max(value = 1,message = "该字段只能为0或1！")
    private Integer isBuy;

    @ApiModelProperty("书籍描述")
    @NotBlank(message = "书籍名称不能为空！")
    @Size(max = 250, message = "书籍名称长度不能超过250个字符")
    private String bookDescribe;

    @ApiModelProperty("在线地址")
    @Size(max = 200, message = "在线地址长度不能超过200个字符")
    private String onlineAddress;

    @ApiModelProperty("封面图片")
    private String coverPhoto;

}
