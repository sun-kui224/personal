package com.person.system.controller;

import java.util.List;

import com.person.common.annotation.Log;
import com.person.common.annotation.Page;
import com.person.common.base.controller.BaseController;
import com.person.common.base.domain.AjaxResult;
import com.person.common.domain.PDictType;
import com.person.common.domain.dto.PDictTypeDto;
import com.person.common.enums.OperateType;
import com.person.system.query.DictTypeQuery;
import com.person.system.service.IPDictTypeService;
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
 * 字典类型Controller
 *
 * @author sk
 * @date 2024-06-04
 */
@RestController
@RequestMapping("/dict/type")
public class PDictTypeController extends BaseController
{
    @Autowired
    private IPDictTypeService pDictTypeService;

    @ApiOperation("字典类型详细")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(pDictTypeService.selectPDictTypeById(id));
    }

    @ApiOperation("字典类型列表")
    @Page
    @GetMapping("/list")
    public AjaxResult list(DictTypeQuery query)
    {
        List<PDictType> list = pDictTypeService.selectPDictTypeList(query);
        return success(list);
    }

    @ApiOperation("新增字典类型")
    @Log(title = "新增字典类型", businessType = OperateType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody @Validated PDictTypeDto pDictTypeDto)
    {
        PDictType pDictType = new PDictType();
        BeanUtils.copyProperties(pDictTypeDto, pDictType);
        pDictType.setId(null);
        return success(pDictTypeService.insertPDictType(pDictType));
    }

    @ApiOperation("修改字典类型")
    @Log(title = "修改字典类型", businessType = OperateType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody @Validated PDictTypeDto pDictTypeDto)
    {
        PDictType pDictType = new PDictType();
        BeanUtils.copyProperties(pDictTypeDto, pDictType);
        if(ObjectUtils.isEmpty(pDictType.getId())){
            return AjaxResult.warn("主键不能为空");
        }
        return pDictTypeService.updatePDictType(pDictType);
    }

    @ApiOperation("删除字典类型")
    @Log(title = "删除字典类型", businessType = OperateType.DELETE)
	@DeleteMapping("/{id}")
    public AjaxResult remove(@PathVariable Long id)
    {
        return pDictTypeService.deletePDictTypeById(id);
    }

    @ApiOperation("停用字典类型")
    @Log(title = "停用字典类型", businessType = OperateType.DELETE)
    @DeleteMapping("/stopDict/{id}")
    public AjaxResult stopDict(@PathVariable Long id)
    {
        return success(pDictTypeService.stopPDictTypeById(id));
    }

    @ApiOperation("启用字典类型")
    @Log(title = "启用字典类型", businessType = OperateType.DELETE)
    @DeleteMapping("/startDict/{id}")
    public AjaxResult startDict(@PathVariable Long id)
    {
        return success(pDictTypeService.startPDictTypeById(id));
    }
}
