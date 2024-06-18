package com.person.study.query;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class BugRecordsQuery {

    @ApiModelProperty("记录名称")
    private String recordsName;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty("开始时间")
    private Date startTime;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty("结束时间")
    private Date endTime;
}
