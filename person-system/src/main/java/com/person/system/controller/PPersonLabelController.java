package com.person.system.controller;

import java.util.List;

import com.person.common.annotation.Log;
import com.person.common.annotation.Page;
import com.person.common.base.controller.BaseController;
import com.person.common.base.domain.AjaxResult;
import com.person.common.domain.PLabelCorr;
import com.person.common.domain.PMenu;
import com.person.common.domain.PPersonLabel;
import com.person.common.domain.dto.PLabelCorrDto;
import com.person.common.domain.dto.PPersonLabelDto;
import com.person.common.domain.entity.LabelEntity;
import com.person.common.enums.OperateType;
import com.person.system.query.LabelQuery;
import com.person.system.service.IPPersonLabelService;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.security.access.prepost.PreAuthorize;
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
 * 标签Controller
 *
 * @author sk
 * @date 2024-05-31
 */
@RestController
@RequestMapping("/system/label")
public class PPersonLabelController extends BaseController
{
    @Autowired
    private IPPersonLabelService pPersonLabelService;

    @ApiOperation("标签详细信息")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(pPersonLabelService.selectPPersonLabelById(id));
    }

    @ApiOperation("标签列表-不分页")
    @GetMapping("/list")
    public AjaxResult list(LabelQuery query)
    {
        List<PPersonLabel> list = pPersonLabelService.selectPPersonLabelList(query);
        return AjaxResult.success("成功",list);
    }

    @ApiOperation("仅供标签组件查询")
    @Page
    @GetMapping("/listPage")
    public AjaxResult listPage()
    {
        List<LabelEntity> list = pPersonLabelService.selectLabelListComponent();
        return AjaxResult.success("成功",list);
    }

    @ApiOperation("新增标签")
    @Log(title = "新增标签", businessType = OperateType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody @Validated PPersonLabelDto pPersonLabelDto)
    {
        PPersonLabel pPersonLabel = new PPersonLabel();
        BeanUtils.copyProperties(pPersonLabelDto, pPersonLabel);
        pPersonLabel.setId(null);
        return pPersonLabelService.insertPPersonLabel(pPersonLabel);
    }

    @ApiOperation("新增标签关联")
    @Log(title = "新增标签关联", businessType = OperateType.INSERT)
    @PostMapping("/corr")
    public AjaxResult addCorr(@RequestBody @Validated PLabelCorrDto pPersonLabelDto)
    {
        PLabelCorr pLabelCorr = new PLabelCorr();
        BeanUtils.copyProperties(pPersonLabelDto, pLabelCorr);
        if(pLabelCorr.getLabelIds()!=null && pLabelCorr.getLabelIds().length>5){
           return AjaxResult.warn("标签不能超过5个");
        }
        return pPersonLabelService.insertPLabelCorr(pLabelCorr);
    }

    @ApiOperation("修改标签")
    @Log(title = "修改标签", businessType = OperateType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody @Validated PPersonLabelDto pPersonLabelDto)
    {
        if(ObjectUtils.isEmpty(pPersonLabelDto.getId())){
            return AjaxResult.warn("主键不能为空");
        }
        PPersonLabel pPersonLabel = new PPersonLabel();
        BeanUtils.copyProperties(pPersonLabelDto, pPersonLabel);
        return pPersonLabelService.updatePPersonLabel(pPersonLabel);
    }

    @ApiOperation("删除标签")
    @Log(title = "删除标签", businessType = OperateType.DELETE)
	@DeleteMapping("/{id}")
    public AjaxResult remove(@PathVariable Long id)
    {
        return pPersonLabelService.deletePPersonLabelById(id);
    }
}
