package com.person.system.controller;

import java.util.List;

import com.person.common.annotation.Log;
import com.person.common.base.controller.BaseController;
import com.person.common.base.domain.AjaxResult;
import com.person.common.enums.OperateType;
import com.person.common.domain.PPerson;
import com.person.system.service.IPPersonService;
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
 * 用户Controller
 *
 * @author sk
 * @date 2024-05-17
 */
@Api( tags = "用户")
@RestController
@RequestMapping("/system/person")
public class PPersonController extends BaseController
{
    @Autowired
    private IPPersonService pPersonService;

    @ApiOperation("用户详细信息")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(pPersonService.selectPPersonById(id));
    }

    @ApiOperation("用户列表")
    @GetMapping("/list")
    public List list(PPerson pPerson)
    {
        List<PPerson> list = pPersonService.selectPPersonList(pPerson);
        return list;
    }

    @ApiOperation("新增用户")
    @Log(title = "用户", businessType = OperateType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PPerson pPerson)
    {
        return success(pPersonService.insertPPerson(pPerson));
    }

    @ApiOperation("修改用户")
    @Log(title = "用户", businessType = OperateType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PPerson pPerson)
    {
        return success(pPersonService.updatePPerson(pPerson));
    }

    @ApiOperation("删除用户")
    @Log(title = "用户", businessType = OperateType.DELETE)
	@DeleteMapping("/{id}")
    public AjaxResult remove(@PathVariable Long id)
    {
        return success(pPersonService.deletePPersonById(id));
    }
}
