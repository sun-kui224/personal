package com.person.study.service;

import java.util.List;
import com.person.common.domain.PStudyBook;
import com.person.study.query.BookQuery;

/**
 * 学习书籍Service接口
 *
 * @author sk
 * @date 2024-05-17
 */
public interface IPStudyBookService
{
    /**
     * 查询学习书籍列表
     *
     * @param query
     * @return
     */
    public List<PStudyBook> selectPStudyBookList(BookQuery query);

    /**
     * 新增学习书籍
     *
     * @param pStudyBook
     * @return
     */
    public int insertPStudyBook(PStudyBook pStudyBook);

    /**
     * 修改学习书籍
     *
     * @param pStudyBook
     * @return
     */
    public int updatePStudyBook(PStudyBook pStudyBook);

    /**
     * 删除学习书籍信息
     *
     * @param id 主键
     * @return 结果
     */
    public int deletePStudyBookById(Long id);
}
