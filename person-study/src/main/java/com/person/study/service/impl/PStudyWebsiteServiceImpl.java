package com.person.study.service.impl;

import java.util.List;

import com.person.common.utils.DateUtils;
import com.person.common.utils.IdUtils;
import com.person.common.utils.SecurityUtils;
import com.person.study.mapper.PStudyWebsiteMapper;
import com.person.study.query.WebsiteQuery;
import com.person.study.service.IPStudyWebsiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.person.common.domain.PStudyWebsite;

/**
 * 学习网址Service业务层处理
 *
 * @author sk
 * @date 2024-05-17
 */
@Service
public class PStudyWebsiteServiceImpl implements IPStudyWebsiteService
{
    @Autowired
    private PStudyWebsiteMapper pStudyWebsiteMapper;

    @Override
    public List<PStudyWebsite> selectPStudyWebsiteList(WebsiteQuery query)
    {
        return pStudyWebsiteMapper.selectPStudyWebsiteList(query);
    }

    @Override
    @Transactional
    public int insertPStudyWebsite(PStudyWebsite pStudyWebsite)
    {
        pStudyWebsite.setId(IdUtils.fastId());
        pStudyWebsite.setDelFlag(0);
        pStudyWebsite.setCreateBy(SecurityUtils.getUsername());
        pStudyWebsite.setCreateDate(DateUtils.getNowDate());
        return pStudyWebsiteMapper.insertPStudyWebsite(pStudyWebsite);
    }

    @Override
    @Transactional
    public int updatePStudyWebsite(PStudyWebsite pStudyWebsite)
    {
        pStudyWebsite.setUpdateDate(DateUtils.getNowDate());
        return pStudyWebsiteMapper.updatePStudyWebsite(pStudyWebsite);
    }

    @Override
    @Transactional
    public int deletePStudyWebsiteById(Long id)
    {
        return pStudyWebsiteMapper.deletePStudyWebsiteById(id);
    }
}
