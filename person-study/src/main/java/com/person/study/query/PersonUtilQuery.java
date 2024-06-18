package com.person.study.query;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 个人工具对象 p_person_util
 *
 * @author sk
 * @date 2024-05-17
 */
@Data
public class PersonUtilQuery
{
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("工具名称")
    private String utilName;

}
