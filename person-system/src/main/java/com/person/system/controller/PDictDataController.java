package com.person.system.controller;

import java.util.List;

import com.person.common.annotation.Log;
import com.person.common.annotation.OnlyPro;
import com.person.common.base.controller.BaseController;
import com.person.common.base.domain.AjaxResult;
import com.person.common.domain.PDictData;
import com.person.common.domain.dto.PDictDataDto;
import com.person.common.enums.OperateType;
import com.person.system.query.PDictDataQuery;
import com.person.system.service.IPDictDataService;
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
 * 字典数据Controller
 *
 * @author sk
 * @date 2024-06-04
 */
@RestController
@RequestMapping("/dict/data")
public class PDictDataController extends BaseController
{
    @Autowired
    private IPDictDataService pDictDataService;

    /**
     * 获取字典数据详细信息
     */
    @ApiOperation("字典数据详细信息")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(pDictDataService.selectPDictDataById(id));
    }

    /**
     * 查询字典数据列表
     */
    @ApiOperation("字典数据列表")
    @GetMapping("/list")
    public AjaxResult list(PDictDataQuery query)
    {
        List<PDictData> list = pDictDataService.selectPDictDataList(query);
        return success(list);
    }

    /**
     * 新增字典数据
     */
    @ApiOperation("新增字典数据")
    @Log(title = "字典数据", businessType = OperateType.INSERT)
    @OnlyPro(value = "dictValue")
    @PostMapping
    public AjaxResult add(@RequestBody @Validated PDictDataDto pDictDataDto)
    {
        PDictData pDictData = new PDictData();
        BeanUtils.copyProperties(pDictDataDto, pDictData);
        pDictData.setId(null);
        return pDictDataService.insertPDictData(pDictData);
    }

    @ApiOperation("修改字典数据")
    @Log(title = "字典数据", businessType = OperateType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody @Validated PDictDataDto pDictDataDto)
    {
        PDictData pDictData = new PDictData();
        BeanUtils.copyProperties(pDictDataDto, pDictData);
        if(ObjectUtils.isEmpty(pDictData.getId())){
            return AjaxResult.warn("主键不能为空");
        }
        return pDictDataService.updatePDictData(pDictData);
    }

    @ApiOperation("删除字典数据")
    @Log(title = "字典数据", businessType = OperateType.DELETE)
	@DeleteMapping("/{id}")
    public AjaxResult remove(@PathVariable Long id)
    {
        return success(pDictDataService.deletePDictDataById(id));
    }

    @ApiOperation("停用字典类型")
    @Log(title = "停用字典类型", businessType = OperateType.DELETE)
    @DeleteMapping("/stopDict/{id}")
    public AjaxResult stopDict(@PathVariable Long id)
    {
        return success(pDictDataService.stopPDictDataById(id));
    }

    @ApiOperation("启用字典类型")
    @Log(title = "启用字典类型", businessType = OperateType.DELETE)
    @DeleteMapping("/startDict/{id}")
    public AjaxResult startDict(@PathVariable Long id)
    {
        return success(pDictDataService.startPDictDataById(id));
    }
}
