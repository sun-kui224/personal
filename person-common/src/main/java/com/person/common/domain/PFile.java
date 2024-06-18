package com.person.common.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 文件对象 p_file
 *
 * @author sk
 * @date 2024-05-17
 */
@Data
@ApiModel("文件对象")
public class PFile
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    @ApiModelProperty("文件名称")
    private String fileName;

    @ApiModelProperty("文件地址")
    private String fileUrl;

    @ApiModelProperty("文件描述")
    private String context;

    @ApiModelProperty("创建人")
    private String createBy;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty("创建时间")
    private Date createDate;

    @ApiModelProperty("0-有效，1-无效")
    private Integer delFlag;

    @ApiModelProperty("文件大小（存字节）")
    private Long size;

    @ApiModelProperty("文件类型，1-全部,2-图片，3-文档，4-视频，5-音频，6-其他")
    private Integer type;

    @ApiModelProperty("文件后缀")
    private String fileExt;

}
