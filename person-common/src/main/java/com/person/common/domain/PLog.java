package com.person.common.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 日志对象 p_log
 *
 * @author sk
 * @date 2024-05-17
 */
@Data
@ApiModel("日志对象")
public class PLog
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    @ApiModelProperty("标题")
    private String title;

    @ApiModelProperty("操作类型")
    private Integer operType;

    @ApiModelProperty("方法名称")
    private String method;

    @ApiModelProperty("请求方式")
    private String requestMethod;

    @ApiModelProperty("操作人员名称")
    private String operName;

    @ApiModelProperty("请求URL")
    private String reqUrl;

    @ApiModelProperty("主机地址")
    private String operIp;

    @ApiModelProperty("请求参数")
    private String reqParam;

    @ApiModelProperty("响应数据")
    private String resResult;

    @ApiModelProperty("操作状态 0-正常，1--异常")
    private Integer operStatus;

    @ApiModelProperty("异常信息")
    private String errorMsg;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty("操作时间")
    private Date operTime;

    @ApiModelProperty("耗时时间")
    private Long costTime;
}
