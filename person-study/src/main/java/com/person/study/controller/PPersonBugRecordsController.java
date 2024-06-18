package com.person.study.controller;

import java.util.List;

import com.person.common.annotation.Log;
import com.person.common.annotation.Page;
import com.person.common.base.controller.BaseController;
import com.person.common.base.domain.AjaxResult;
import com.person.common.domain.dto.PPersonBugRecordsDto;
import com.person.common.enums.OperateType;
import com.person.common.domain.PPersonBugRecords;
import com.person.study.query.BugRecordsQuery;
import com.person.study.service.IPPersonBugRecordsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
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
 * 个人错误记录Controller
 *
 * @author sk
 * @date 2024-05-17
 */
@Api( tags = "个人错误记录")
@RestController
@RequestMapping("/study/records")
public class PPersonBugRecordsController extends BaseController
{
    @Autowired
    private IPPersonBugRecordsService pPersonBugRecordsService;

    @ApiOperation("个人错误记录详细信息")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(pPersonBugRecordsService.selectPPersonBugRecordsById(id));
    }

    @ApiOperation("个人错误记录列表")
    @Page
    @GetMapping("/list")
    public AjaxResult list(BugRecordsQuery bugRecordsQuery)
    {
        List<PPersonBugRecords> list = pPersonBugRecordsService.selectPPersonBugRecordsList(bugRecordsQuery);
        return AjaxResult.success("成功",list);
    }

    @ApiOperation("新增个人错误记录")
    @Log(title = "新增个人错误记录", businessType = OperateType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody @Validated PPersonBugRecordsDto pPersonBugRecordsDto)
    {
        PPersonBugRecords pPersonBugRecords = new PPersonBugRecords();
        BeanUtils.copyProperties(pPersonBugRecordsDto, pPersonBugRecords);
        pPersonBugRecords.setId(null);
        return success(pPersonBugRecordsService.insertPPersonBugRecords(pPersonBugRecords));
    }

    @ApiOperation("修改个人错误记录")
    @Log(title = "修改个人错误记录", businessType = OperateType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody @Validated PPersonBugRecordsDto pPersonBugRecordsDto)
    {
        PPersonBugRecords pPersonBugRecords = new PPersonBugRecords();
        BeanUtils.copyProperties(pPersonBugRecordsDto, pPersonBugRecords);
        if(ObjectUtils.isEmpty(pPersonBugRecords.getId())){
            return AjaxResult.warn("主键不能为空");
        }
        return success(pPersonBugRecordsService.updatePPersonBugRecords(pPersonBugRecords));
    }

    @ApiOperation("删除个人错误记录")
    @Log(title = "删除个人错误记录", businessType = OperateType.DELETE)
	@DeleteMapping("/{id}")
    public AjaxResult remove(@PathVariable Long id)
    {
        return success(pPersonBugRecordsService.deletePPersonBugRecordsById(id));
    }
}
