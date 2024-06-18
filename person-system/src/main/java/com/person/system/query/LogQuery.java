package com.person.system.query;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author sk
 * @date 2024/6/4
 */
@Data
public class LogQuery {

    @ApiModelProperty("标题")
    private String title;

    @ApiModelProperty("操作类型")
    private Integer operType;

    @ApiModelProperty("请求方式")
    private String requestMethod;

    @ApiModelProperty("操作人员名称")
    private String operName;

    @ApiModelProperty("操作状态 0-正常，1--异常")
    private Integer operStatus;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty("开始时间")
    private Date beginTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty("结束时间")
    private Date endTime;
}
