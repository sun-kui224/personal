package com.person.work.controller;

import java.util.List;

import com.person.common.annotation.Log;
import com.person.common.annotation.Page;
import com.person.common.base.controller.BaseController;
import com.person.common.base.domain.AjaxResult;
import com.person.common.enums.OperateType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.person.common.domain.PWorkReport;
import com.person.work.service.IPWorkReportService;

/**
 * 工作记录Controller
 *
 * @author sk
 * @date 2024-05-17
 */
@Api( tags = "工作记录")
@RestController
@RequestMapping("/system/report")
public class PWorkReportController extends BaseController
{
    @Autowired
    private IPWorkReportService pWorkReportService;

    @ApiOperation("工作记录详细信息")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(pWorkReportService.selectPWorkReportById(id));
    }

    @ApiOperation("工作记录列表")
    @Page
    @GetMapping("/list")
    public List list(PWorkReport pWorkReport)
    {
        List<PWorkReport> List = pWorkReportService.selectPWorkReportList(pWorkReport);
        return List;
    }


    @ApiOperation("新增工作记录")
    @Log(title = "工作记录", businessType = OperateType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PWorkReport pWorkReport)
    {
        return success(pWorkReportService.insertPWorkReport(pWorkReport));
    }

    /**
     * 修改工作记录
     */
    @ApiOperation("修改工作记录")
    @Log(title = "工作记录", businessType = OperateType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PWorkReport pWorkReport)
    {
        return success(pWorkReportService.updatePWorkReport(pWorkReport));
    }

    /**
     * 删除工作记录
     */
    @ApiOperation("删除工作记录")
    @Log(title = "工作记录", businessType = OperateType.DELETE)
	@DeleteMapping("/{id}")
    public AjaxResult remove(@PathVariable Long id)
    {
        return success(pWorkReportService.deletePWorkReportById(id));
    }
}
