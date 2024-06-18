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
import com.person.common.domain.PWorkCompanyProject;
import com.person.work.service.IPWorkCompanyProjectService;

/**
 * 公司项目Controller
 *
 * @author sk
 * @date 2024-05-17
 */
@Api( tags = "公司项目")
@RestController
@RequestMapping("/system/project")
public class PWorkCompanyProjectController extends BaseController
{
    @Autowired
    private IPWorkCompanyProjectService pWorkCompanyProjectService;

    @ApiOperation("公司项目详细信息")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(pWorkCompanyProjectService.selectPWorkCompanyProjectById(id));
    }

    @ApiOperation("公司项目列表")
    @Page
    @GetMapping("/list")
    public List list(PWorkCompanyProject pWorkCompanyProject)
    {
        List<PWorkCompanyProject> list = pWorkCompanyProjectService.selectPWorkCompanyProjectList(pWorkCompanyProject);
        return list;
    }

    @ApiOperation("新增公司项目")
    @Log(title = "公司项目", businessType = OperateType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PWorkCompanyProject pWorkCompanyProject)
    {
        return success(pWorkCompanyProjectService.insertPWorkCompanyProject(pWorkCompanyProject));
    }

    @ApiOperation("修改公司项目")
    @Log(title = "公司项目", businessType = OperateType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PWorkCompanyProject pWorkCompanyProject)
    {
        return success(pWorkCompanyProjectService.updatePWorkCompanyProject(pWorkCompanyProject));
    }

    @ApiOperation("删除公司项目")
    @Log(title = "公司项目", businessType = OperateType.DELETE)
	@DeleteMapping("/{id}")
    public AjaxResult remove(@PathVariable Long id)
    {
        return success(pWorkCompanyProjectService.deletePWorkCompanyProjectById(id));
    }
}
