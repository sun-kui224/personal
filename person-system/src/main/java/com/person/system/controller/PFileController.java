package com.person.system.controller;

import java.util.ArrayList;
import java.util.List;

import com.person.common.annotation.Log;
import com.person.common.annotation.Page;
import com.person.common.base.controller.BaseController;
import com.person.common.base.domain.AjaxResult;
import com.person.common.domain.dto.PFileDto;
import com.person.common.enums.OperateType;
import com.person.common.domain.PFile;
import com.person.system.domain.FileRename;
import com.person.system.query.FileQuery;
import com.person.system.service.IPFileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 文件Controller
 *
 * @author sk
 * @date 2024-05-17
 */
@Api( tags = "文件")
@RestController
@RequestMapping("/system/file")
public class PFileController extends BaseController
{
    @Autowired
    private IPFileService pFileService;


    @ApiOperation("文件详细信息")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(pFileService.selectPFileById(id));
    }

    @ApiOperation("文件列表")
    @Page
    @GetMapping("/list")
    public AjaxResult list(FileQuery query)
    {
        if(query.getType()==1){
            query.setType(null);
        }
        List<PFile> list = pFileService.selectPFileList(query);
        return success(list);
    }

    @ApiOperation("新增文件集合")
    @Log(title = "新增文件集合", businessType = OperateType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody @Validated PFileDto[] pFileDtos)
    {
        List<PFile>list = new ArrayList<>();
        for(PFileDto pFileDto : pFileDtos){
            PFile pFile = new PFile();
            BeanUtils.copyProperties(pFileDto, pFile);
            list.add(pFile);
        }
        return success(pFileService.insertPFileList(list));
    }

    @ApiOperation("重命名")
    @Log(title = "重命名", businessType = OperateType.UPDATE)
    @PostMapping("/editName")
    public AjaxResult editName(@RequestBody @Validated FileRename requestFile)
    {
        return success(pFileService.updateFileName(requestFile.getId(),requestFile.getName()));
    }

    @ApiOperation("删除文件")
    @Log(title = "删除文件", businessType = OperateType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        if(ids.length == 0){
            return AjaxResult.warn("主键集合不能为空");
        }
        return success(pFileService.deletePFileByIds(ids));
    }
}
