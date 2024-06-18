package com.person.system.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 文件重命名实体类
 * @author sk
 * @date 2024/5/28
 */
@Data
public class FileRename {

    @NotNull(message="文件id不能为空！")
    private Long id;

    @NotBlank(message="文件名称不能为空！")
    private String name;
}
