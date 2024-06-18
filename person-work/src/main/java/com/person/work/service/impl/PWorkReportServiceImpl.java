package com.person.work.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.person.work.mapper.PWorkReportMapper;
import com.person.common.domain.PWorkReport;
import com.person.work.service.IPWorkReportService;

/**
 * 工作记录Service业务层处理
 *
 * @author sk
 * @date 2024-05-17
 */
@Service
public class PWorkReportServiceImpl implements IPWorkReportService
{
    @Autowired
    private PWorkReportMapper pWorkReportMapper;

    @Override
    public PWorkReport selectPWorkReportById(Long id)
    {
        return pWorkReportMapper.selectPWorkReportById(id);
    }

    @Override
    public List<PWorkReport> selectPWorkReportList(PWorkReport pWorkReport)
    {
        return pWorkReportMapper.selectPWorkReportList(pWorkReport);
    }

    @Override
    @Transactional
    public int insertPWorkReport(PWorkReport pWorkReport)
    {
        return pWorkReportMapper.insertPWorkReport(pWorkReport);
    }

    @Override
    @Transactional
    public int updatePWorkReport(PWorkReport pWorkReport)
    {
        return pWorkReportMapper.updatePWorkReport(pWorkReport);
    }

    @Override
    @Transactional
    public int deletePWorkReportById(Long id)
    {
        return pWorkReportMapper.deletePWorkReportById(id);
    }
}
