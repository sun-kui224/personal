package com.person.common.domain.entity;

import com.person.common.utils.file.MimeTypeUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 文件实体
 * @author sk
 * @date 2024/5/27
 */
@Data
@ApiModel("文件对象(发送)")
public class FileEntity {

    @ApiModelProperty("文件名称")
    private String fileName;

    @ApiModelProperty("文件地址")
    private String fileUrl;

    @ApiModelProperty("文件大小（存字节）")
    private Long size;

    @ApiModelProperty("文件类型")
    private Integer type;

    @ApiModelProperty("文件后缀")
    private String fileExt;
}
