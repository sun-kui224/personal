package com.person.study.service.impl;

import java.util.List;

import com.person.common.utils.DateUtils;
import com.person.common.utils.IdUtils;
import com.person.common.utils.SecurityUtils;
import com.person.study.mapper.PPersonUtilMapper;
import com.person.study.query.PersonUtilQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.person.common.domain.PPersonUtil;
import com.person.study.service.IPPersonUtilService;

/**
 * 个人工具Service业务层处理
 *
 * @author sk
 * @date 2024-05-17
 */
@Service
public class PPersonUtilServiceImpl implements IPPersonUtilService
{
    @Autowired
    private PPersonUtilMapper pPersonUtilMapper;

    @Override
    public PPersonUtil selectPPersonUtilById(Long id)
    {
        return pPersonUtilMapper.selectPPersonUtilById(id);
    }

    @Override
    public List<PPersonUtil> selectPPersonUtilList(PersonUtilQuery query)
    {
        return pPersonUtilMapper.selectPPersonUtilList(query);
    }

    @Override
    @Transactional
    public int insertPPersonUtil(PPersonUtil pPersonUtil)
    {
        pPersonUtil.setId(IdUtils.fastId());
        pPersonUtil.setDelFlag(0);
        pPersonUtil.setCreateBy(SecurityUtils.getUsername());
        pPersonUtil.setCreateDate(DateUtils.getNowDate());
        return pPersonUtilMapper.insertPPersonUtil(pPersonUtil);
    }

    @Override
    @Transactional
    public int updatePPersonUtil(PPersonUtil pPersonUtil)
    {
        return pPersonUtilMapper.updatePPersonUtil(pPersonUtil);
    }

    @Override
    @Transactional
    public int deletePPersonUtilById(Long id)
    {
        return pPersonUtilMapper.deletePPersonUtilById(id);
    }
}
