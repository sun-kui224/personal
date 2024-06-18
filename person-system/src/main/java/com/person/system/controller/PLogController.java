package com.person.system.controller;

import java.util.List;

import com.person.common.annotation.Log;
import com.person.common.annotation.Page;
import com.person.common.base.controller.BaseController;
import com.person.common.base.domain.AjaxResult;
import com.person.common.enums.OperateType;
import com.person.common.domain.PLog;
import com.person.system.query.LogQuery;
import com.person.system.service.IPLogService;
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

/**
 * 日志Controller
 *
 * @author sk
 * @date 2024-05-17
 */
@Api( tags = "日志")
@RestController
@RequestMapping("/system/log")
public class PLogController extends BaseController
{
    @Autowired
    private IPLogService pLogService;

    @ApiOperation("日志详细信息")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(pLogService.selectPLogById(id));
    }

    @ApiOperation("日志列表")
    @Page
    @GetMapping("/list")
    public AjaxResult list(LogQuery query)
    {
        List<PLog> list = pLogService.selectPLogList(query);
        return AjaxResult.success(list);
    }

    @ApiOperation("删除日志")
    @Log(title = "删除日志", businessType = OperateType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return success(pLogService.deletePLogByIds(ids));
    }

    @ApiOperation("清空日志")
    @Log(title = "清空日志", businessType = OperateType.DELETE)
    @DeleteMapping("/clean")
    public AjaxResult clean()
    {
        pLogService.deletePLog();
        return AjaxResult.success();
    }

}
