package com.person.common.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;
import java.util.Date;

/**
 * @author sk
 * @date 2024/5/27
 */
@Data
@ApiModel("文件对象(接收)")
public class PFileDto {

    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    @ApiModelProperty("文件名称")
    @NotBlank(message="文件名称不能为空！")
    @Length(max = 20, message = "文件名称长度不能超过50个字符")
    private String fileName;

    @ApiModelProperty("文件地址")
    @NotBlank(message="文件地址不能为空！")
    @Length(max = 255, message = "文件名称长度不能超过255个字符")
    private String fileUrl;

    @ApiModelProperty("文件描述")
    @Size(max = 255, message = "文件描述长度不能超过255个字符")
    private String context;

    @ApiModelProperty("文件大小（存字节）")
    @NotNull
    @Min(value = 1, message = "文件内容不能为空")
    private Integer size;

    @ApiModelProperty("文件类型，1-全部,2-图片，3-文档，4-视频，5-音频，6-其他")
    @Min(value = 1, message = "文件类型类型不能小于1")
    @Max(value = 6, message = "文件类型类型不能大于6")
    private Integer type;

    @ApiModelProperty("文件后缀")
    @NotBlank(message = "文件后缀不能为空")
    private String fileExt;
}
