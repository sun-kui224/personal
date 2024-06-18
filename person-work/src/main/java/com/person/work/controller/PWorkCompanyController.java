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
import com.person.common.domain.PWorkCompany;
import com.person.work.service.IPWorkCompanyService;

/**
 * 公司Controller
 *
 * @author sk
 * @date 2024-05-17
 */
@Api( tags = "公司")
@RestController
@RequestMapping("/system/company")
public class PWorkCompanyController extends BaseController
{
    @Autowired
    private IPWorkCompanyService pWorkCompanyService;

    @ApiOperation("公司详细信息")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return success(pWorkCompanyService.selectPWorkCompanyById(id));
    }

    @ApiOperation("公司列表")
    @Page
    @GetMapping("/list")
    public List list(PWorkCompany pWorkCompany)
    {
        List<PWorkCompany> list = pWorkCompanyService.selectPWorkCompanyList(pWorkCompany);
        return list;
    }

    @ApiOperation("新增公司")
    @Log(title = "公司", businessType = OperateType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PWorkCompany pWorkCompany)
    {
        return success(pWorkCompanyService.insertPWorkCompany(pWorkCompany));
    }

    @ApiOperation("修改公司")
    @Log(title = "公司", businessType = OperateType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PWorkCompany pWorkCompany)
    {
        return success(pWorkCompanyService.updatePWorkCompany(pWorkCompany));
    }

    @ApiOperation("删除公司")
    @Log(title = "公司", businessType = OperateType.DELETE)
	@DeleteMapping("/{id}")
    public AjaxResult remove(@PathVariable Long id)
    {
        return success(pWorkCompanyService.deletePWorkCompanyById(id));
    }
}
