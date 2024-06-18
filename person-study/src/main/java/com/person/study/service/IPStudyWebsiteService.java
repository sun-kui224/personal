package com.person.study.service;

import java.util.List;
import com.person.common.domain.PStudyWebsite;
import com.person.study.query.WebsiteQuery;

/**
 * 学习网址Service接口
 *
 * @author sk
 * @date 2024-05-17
 */
public interface IPStudyWebsiteService
{
    /**
     * 查询学习网址列表
     *
     * @param query
     * @return
     */
    public List<PStudyWebsite> selectPStudyWebsiteList(WebsiteQuery query);

    /**
     * 新增学习网址
     *
     * @param pStudyWebsite
     * @return
     */
    public int insertPStudyWebsite(PStudyWebsite pStudyWebsite);

    /**
     * 修改学习网址
     *
     * @param pStudyWebsite
     * @return
     */
    public int updatePStudyWebsite(PStudyWebsite pStudyWebsite);

    /**
     * 删除学习网址信息
     *
     * @param id 主键
     * @return 结果
     */
    public int deletePStudyWebsiteById(Long id);
}
