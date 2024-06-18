package com.person.study.controller;

import java.util.List;

import com.person.common.annotation.Log;
import com.person.common.annotation.Page;
import com.person.common.base.controller.BaseController;
import com.person.common.base.domain.AjaxResult;
import com.person.common.domain.PPersonLabel;
import com.person.common.domain.dto.PStudyWebsiteDto;
import com.person.common.enums.OperateType;
import com.person.common.domain.PStudyWebsite;
import com.person.study.query.WebsiteQuery;
import com.person.study.service.IPStudyWebsiteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
 * 学习网址Controller
 *
 * @author sk
 * @date 2024-05-17
 */
@Api( tags = "学习网址")
@RestController
@RequestMapping("/study/website")
public class PStudyWebsiteController extends BaseController
{
    @Autowired
    private IPStudyWebsiteService pStudyWebsiteService;

    @ApiOperation("学习网址列表")
    @GetMapping("/list")
    public AjaxResult list(WebsiteQuery query)
    {
        List<PStudyWebsite> list = pStudyWebsiteService.selectPStudyWebsiteList(query);
        return AjaxResult.success(list);
    }

    @ApiOperation("新增学习网址")
    @Log(title = "新增学习网址", businessType = OperateType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody @Validated PStudyWebsiteDto pStudyWebsiteDto)
    {
        PStudyWebsite pStudyWebsite = new PStudyWebsite();
        BeanUtils.copyProperties(pStudyWebsiteDto, pStudyWebsite);
        pStudyWebsite.setId(null);
        return success(pStudyWebsiteService.insertPStudyWebsite(pStudyWebsite));
    }

    @ApiOperation("修改学习网址")
    @Log(title = "修改学习网址", businessType = OperateType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody @Validated PStudyWebsiteDto pStudyWebsiteDto)
    {
        PStudyWebsite pStudyWebsite = new PStudyWebsite();
        BeanUtils.copyProperties(pStudyWebsiteDto, pStudyWebsite);
        if(ObjectUtils.isEmpty(pStudyWebsite.getId())){
            return AjaxResult.warn("主键不能为空");
        }
        return success(pStudyWebsiteService.updatePStudyWebsite(pStudyWebsite));
    }

    @ApiOperation("删除学习网址")
    @Log(title = "删除学习网址", businessType = OperateType.DELETE)
	@DeleteMapping("/{id}")
    public AjaxResult remove(@PathVariable Long id)
    {
        return success(pStudyWebsiteService.deletePStudyWebsiteById(id));
    }
}
