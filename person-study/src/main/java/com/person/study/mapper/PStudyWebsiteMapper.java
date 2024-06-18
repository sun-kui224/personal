package com.person.study.mapper;

import java.util.List;

import com.person.study.query.WebsiteQuery;
import org.apache.ibatis.annotations.Mapper;
import com.person.common.domain.PStudyWebsite;

/**
 * 学习网址Mapper接口
 *
 * @author sk
 * @date 2024-05-17
 */
@Mapper
public interface PStudyWebsiteMapper
{
    /**
     * 查询学习网址列表
     *
     * @param query 学习网址
     * @return 集合
     */
    public List<PStudyWebsite> selectPStudyWebsiteList(WebsiteQuery query);

    /**
     * 新增学习网址
     *
     * @param pStudyWebsite
     * @return 结果
     */
    public int insertPStudyWebsite(PStudyWebsite pStudyWebsite);

    /**
     * 修改学习网址
     *
     * @param pStudyWebsite
     * @return 结果
     */
    public int updatePStudyWebsite(PStudyWebsite pStudyWebsite);

    /**
     * 删除学习网址
     *
     * @param id 主键
     * @return 结果
     */
    public int deletePStudyWebsiteById(Long id);
}
