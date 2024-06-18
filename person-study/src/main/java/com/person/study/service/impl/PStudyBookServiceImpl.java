package com.person.study.service.impl;

import java.util.List;

import com.person.common.utils.DateUtils;
import com.person.common.utils.IdUtils;
import com.person.common.utils.SecurityUtils;
import com.person.study.query.BookQuery;
import com.person.study.mapper.PStudyBookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.person.common.domain.PStudyBook;
import com.person.study.service.IPStudyBookService;

/**
 * 学习书籍Service业务层处理
 *
 * @author sk
 * @date 2024-05-17
 */
@Service
public class PStudyBookServiceImpl implements IPStudyBookService
{
    @Autowired
    private PStudyBookMapper pStudyBookMapper;

    @Override
    public List<PStudyBook> selectPStudyBookList(BookQuery query)
    {
        return pStudyBookMapper.selectPStudyBookList(query);
    }

    @Override
    @Transactional
    public int insertPStudyBook(PStudyBook pStudyBook)
    {
        pStudyBook.setId(IdUtils.fastId());
        pStudyBook.setCreateBy(SecurityUtils.getUsername());
        pStudyBook.setCreateDate(DateUtils.getNowDate());
        pStudyBook.setDelFlag(0);
        return pStudyBookMapper.insertPStudyBook(pStudyBook);
    }

    @Override
    @Transactional
    public int updatePStudyBook(PStudyBook pStudyBook)
    {
        pStudyBook.setUpdateDate(DateUtils.getNowDate());
        return pStudyBookMapper.updatePStudyBook(pStudyBook);
    }

    @Override
    @Transactional
    public int deletePStudyBookById(Long id)
    {
        return pStudyBookMapper.deletePStudyBookById(id);
    }
}
