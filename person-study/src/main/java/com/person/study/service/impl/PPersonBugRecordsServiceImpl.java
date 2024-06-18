package com.person.study.service.impl;

import java.util.List;

import com.person.common.utils.DateUtils;
import com.person.common.utils.IdUtils;
import com.person.common.utils.SecurityUtils;
import com.person.study.mapper.PPersonBugRecordsMapper;
import com.person.study.query.BugRecordsQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.person.common.domain.PPersonBugRecords;
import com.person.study.service.IPPersonBugRecordsService;

/**
 * 个人错误记录Service业务层处理
 *
 * @author sk
 * @date 2024-05-17
 */
@Service
public class PPersonBugRecordsServiceImpl implements IPPersonBugRecordsService
{
    @Autowired
    private PPersonBugRecordsMapper pPersonBugRecordsMapper;

    @Override
    public PPersonBugRecords selectPPersonBugRecordsById(Long id)
    {
        return pPersonBugRecordsMapper.selectPPersonBugRecordsById(id);
    }

    @Override
    public List<PPersonBugRecords> selectPPersonBugRecordsList(BugRecordsQuery bugRecordsQuery)
    {
        return pPersonBugRecordsMapper.selectPPersonBugRecordsList(bugRecordsQuery);
    }

    @Override
    @Transactional
    public int insertPPersonBugRecords(PPersonBugRecords pPersonBugRecords)
    {
        pPersonBugRecords.setDelFlag(0);
        pPersonBugRecords.setCreatedBy(SecurityUtils.getUsername());
        pPersonBugRecords.setCreatedDate(DateUtils.getNowDate());
        pPersonBugRecords.setId(IdUtils.fastId());
        return pPersonBugRecordsMapper.insertPPersonBugRecords(pPersonBugRecords);
    }

    @Override
    @Transactional
    public int updatePPersonBugRecords(PPersonBugRecords pPersonBugRecords)
    {
        pPersonBugRecords.setUpdateDate(DateUtils.getNowDate());
        return pPersonBugRecordsMapper.updatePPersonBugRecords(pPersonBugRecords);
    }

    @Override
    @Transactional
    public int deletePPersonBugRecordsById(Long id)
    {
        return pPersonBugRecordsMapper.deletePPersonBugRecordsById(id);
    }
}
