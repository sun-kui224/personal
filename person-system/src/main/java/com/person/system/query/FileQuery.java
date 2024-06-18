package com.person.system.query;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author sk
 * @date 2024/6/4
 */
@Data
public class FileQuery {
    @ApiModelProperty("文件名称")
    private String fileName;

    @ApiModelProperty("文件类型，1-全部,2-图片，3-文档，4-视频，5-音频，6-其他")
    private Integer type;
}
