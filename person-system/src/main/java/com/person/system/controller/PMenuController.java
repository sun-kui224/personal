package com.person.system.controller;

import java.util.List;

import com.person.common.annotation.Log;
import com.person.common.base.controller.BaseController;
import com.person.common.base.domain.AjaxResult;
import com.person.common.domain.dto.PMenuDto;
import com.person.common.enums.OperateType;
import com.person.common.domain.PMenu;
import com.person.system.query.MenuQuery;
import com.person.system.service.IPMenuService;
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
 * 菜单Controller
 *
 * @author sk
 * @date 2024-05-17
 */
@Api( tags = "菜单")
@RestController
@RequestMapping("/system/menu")
public class PMenuController extends BaseController
{
    @Autowired
    private IPMenuService pMenuService;

    @ApiOperation("菜单详细信息")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(pMenuService.selectPMenuById(id));
    }

    @ApiOperation("菜单列表")
    @GetMapping("/list")
    public AjaxResult list(MenuQuery query)
    {
        List<PMenu> list = pMenuService.selectPMenuALLListTree(query);
        return AjaxResult.success(list);
    }

    @ApiOperation("新增菜单")
    @Log(title = "新增菜单", businessType = OperateType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody @Validated PMenuDto pMenuDto)
    {
        PMenu pmenu = new PMenu();
        BeanUtils.copyProperties(pMenuDto, pmenu);
        pmenu.setId(null);
        return success(pMenuService.insertPMenu(pmenu));
    }

    @ApiOperation("修改菜单")
    @Log(title = "修改菜单", businessType = OperateType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody @Validated PMenuDto pMenuDto)
    {
        if(ObjectUtils.isEmpty(pMenuDto.getId())){
            return AjaxResult.warn("主键不能为空！");
        }
        PMenu pmenu = new PMenu();
        BeanUtils.copyProperties(pMenuDto, pmenu);
        return success(pMenuService.updatePMenu(pmenu));
    }

    /**
     * 删除菜单
     */
    @ApiOperation("停用菜单")
    @Log(title = "停用菜单", businessType = OperateType.UPDATE)
    @PutMapping("/stopMenu/{id}")
    public AjaxResult stopMenu(@PathVariable Long id)
    {
        return success(pMenuService.stopPMenuById(id));
    }

    @ApiOperation("启用菜单")
    @Log(title = "启用菜单", businessType = OperateType.UPDATE)
    @PutMapping("/startMenu/{id}")
    public AjaxResult startMenu(@PathVariable Long id)
    {
        return success(pMenuService.startPMenuById(id));
    }

    /**
     * 删除菜单
     */
    @ApiOperation("删除菜单")
    @Log(title = "删除菜单", businessType = OperateType.DELETE)
	@DeleteMapping("/{id}")
    public AjaxResult remove(@PathVariable Long id)
    {
        return success(pMenuService.deletePMenuById(id));
    }
}
