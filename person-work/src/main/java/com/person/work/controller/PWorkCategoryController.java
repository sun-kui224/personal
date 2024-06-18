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
import com.person.common.domain.PWorkCategory;
import com.person.work.service.IPWorkCategoryService;

/**
 * 记录分类Controller
 *
 * @author sk
 * @date 2024-05-17
 */
@Api( tags = "记录分类")
@RestController
@RequestMapping("/system/category")
public class PWorkCategoryController extends BaseController
{
    @Autowired
    private IPWorkCategoryService pWorkCategoryService;

    @ApiOperation("记录分类详细信息")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(pWorkCategoryService.selectPWorkCategoryById(id));
    }

    @ApiOperation("记录分类列表")
    @Page
    @GetMapping("/list")
    public List list(PWorkCategory pWorkCategory)
    {
        List<PWorkCategory> list = pWorkCategoryService.selectPWorkCategoryList(pWorkCategory);
        return list;
    }

    @ApiOperation("新增记录分类")
    @Log(title = "记录分类", businessType = OperateType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PWorkCategory pWorkCategory)
    {
        return success(pWorkCategoryService.insertPWorkCategory(pWorkCategory));
    }

    @ApiOperation("修改记录分类")
    @Log(title = "记录分类", businessType = OperateType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PWorkCategory pWorkCategory)
    {
        return success(pWorkCategoryService.updatePWorkCategory(pWorkCategory));
    }

    @ApiOperation("删除记录分类")
    @Log(title = "记录分类", businessType = OperateType.DELETE)
	@DeleteMapping("/{id}")
    public AjaxResult remove(@PathVariable Long id)
    {
        return success(pWorkCategoryService.deletePWorkCategoryById(id));
    }
}
