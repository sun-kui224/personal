package com.person.study.mapper;

import java.util.List;

import com.person.study.query.BookQuery;
import org.apache.ibatis.annotations.Mapper;
import com.person.common.domain.PStudyBook;

/**
 * 学习书籍Mapper接口
 *
 * @author sk
 * @date 2024-05-17
 */
@Mapper
public interface PStudyBookMapper
{
    /**
     * 查询学习书籍列表
     *
     * @param query 学习书籍
     * @return 集合
     */
    public List<PStudyBook> selectPStudyBookList(BookQuery query);

    /**
     * 新增学习书籍
     *
     * @param pStudyBook
     * @return 结果
     */
    public int insertPStudyBook(PStudyBook pStudyBook);

    /**
     * 修改学习书籍
     *
     * @param pStudyBook
     * @return 结果
     */
    public int updatePStudyBook(PStudyBook pStudyBook);

    /**
     * 删除学习书籍
     *
     * @param id 主键
     * @return 结果
     */
    public int deletePStudyBookById(Long id);
}
