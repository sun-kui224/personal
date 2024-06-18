package com.person.study.controller;

import java.util.List;

import com.person.common.annotation.Log;
import com.person.common.annotation.Page;
import com.person.common.base.controller.BaseController;
import com.person.common.base.domain.AjaxResult;
import com.person.common.domain.dto.PStudyBookDto;
import com.person.common.enums.OperateType;
import com.person.common.domain.PStudyBook;
import com.person.study.query.BookQuery;
import com.person.study.service.IPStudyBookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
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
 * 学习书籍Controller
 *
 * @author sk
 * @date 2024-05-17
 */
@Api( tags = "学习书籍")
@RestController
@RequestMapping("/study/book")
public class PStudyBookController extends BaseController
{
    @Autowired
    private IPStudyBookService pStudyBookService;

    @ApiOperation("学习书籍列表")
    @GetMapping("/list")
    public AjaxResult list(BookQuery query)
    {
        List<PStudyBook> list = pStudyBookService.selectPStudyBookList(query);
        return AjaxResult.success(list);
    }

    @ApiOperation("新增学习书籍")
    @Log(title = "新增学习书籍", businessType = OperateType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody @Validated PStudyBookDto pStudyBookDto)
    {
        PStudyBook pStudyBook = new PStudyBook();
        BeanUtils.copyProperties(pStudyBookDto, pStudyBook);
        pStudyBook.setId(null);
        return success(pStudyBookService.insertPStudyBook(pStudyBook));
    }

    @ApiOperation("修改学习书籍")
    @Log(title = "修改学习书籍", businessType = OperateType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody @Validated PStudyBookDto pStudyBookDto)
    {
        PStudyBook pStudyBook = new PStudyBook();
        BeanUtils.copyProperties(pStudyBookDto, pStudyBook);
        if(ObjectUtils.isEmpty(pStudyBook.getId())){
            return AjaxResult.warn("主键不能为空");
        }
        return success(pStudyBookService.updatePStudyBook(pStudyBook));
    }

    @ApiOperation("删除学习书籍")
    @Log(title = "删除学习书籍", businessType = OperateType.DELETE)
	@DeleteMapping("/{id}")
    public AjaxResult remove(@PathVariable Long id)
    {
        return success(pStudyBookService.deletePStudyBookById(id));
    }
}
