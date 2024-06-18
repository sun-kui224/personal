package com.person.study.controller;

import java.util.List;

import com.person.common.annotation.Log;
import com.person.common.annotation.Page;
import com.person.common.base.controller.BaseController;
import com.person.common.base.domain.AjaxResult;
import com.person.common.domain.dto.PPersonUtilDto;
import com.person.common.enums.OperateType;
import com.person.common.domain.PPersonUtil;
import com.person.study.query.PersonUtilQuery;
import com.person.study.service.IPPersonUtilService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 个人工具Controller
 *
 * @author sk
 * @date 2024-05-17
 */
@Api( tags = "个人工具")
@RestController
@RequestMapping("/study/util")
public class PPersonUtilController extends BaseController
{
    @Autowired
    private IPPersonUtilService pPersonUtilService;

    /**
     * 获取个人工具详细信息
     */
    @ApiOperation("个人工具详细信息")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(pPersonUtilService.selectPPersonUtilById(id));
    }

    @ApiOperation("个人工具列表")
    @GetMapping("/list")
    public AjaxResult list(PersonUtilQuery query)
    {
        List<PPersonUtil> list = pPersonUtilService.selectPPersonUtilList(query);
        return AjaxResult.success(list);
    }

    @ApiOperation("新增个人工具")
    @Log(title = "新增个人工具", businessType = OperateType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody @Validated PPersonUtilDto pPersonUtilDto)
    {
        PPersonUtil pPersonUtil = new PPersonUtil();
        BeanUtils.copyProperties(pPersonUtilDto, pPersonUtil);
        pPersonUtil.setId(null);
        return success(pPersonUtilService.insertPPersonUtil(pPersonUtil));
    }

    @ApiOperation("修改个人工具")
    @Log(title = "修改个人工具", businessType = OperateType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody @Validated PPersonUtilDto pPersonUtilDto)
    {
        PPersonUtil pPersonUtil = new PPersonUtil();
        BeanUtils.copyProperties(pPersonUtilDto, pPersonUtil);
        if(ObjectUtils.isEmpty(pPersonUtil.getId())){
            return AjaxResult.warn("主键不能为空");
        }
        return success(pPersonUtilService.updatePPersonUtil(pPersonUtil));
    }

    @ApiOperation("删除个人工具")
    @Log(title = "删除个人工具", businessType = OperateType.DELETE)
	@DeleteMapping("/{id}")
    public AjaxResult remove(@PathVariable Long id)
    {
        return success(pPersonUtilService.deletePPersonUtilById(id));
    }
}
