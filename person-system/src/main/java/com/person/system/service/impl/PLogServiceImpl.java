package com.person.system.service.impl;

import java.util.List;

import com.person.system.query.LogQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.person.system.mapper.PLogMapper;
import com.person.common.domain.PLog;
import com.person.system.service.IPLogService;

/**
 * 日志Service业务层处理
 *
 * @author sk
 * @date 2024-05-17
 */
@Service
public class PLogServiceImpl implements IPLogService
{
    @Autowired
    private PLogMapper pLogMapper;

    @Override
    public PLog selectPLogById(Long id)
    {
        return pLogMapper.selectPLogById(id);
    }

    @Override
    public List<PLog> selectPLogList(LogQuery query)
    {
        return pLogMapper.selectPLogList(query);
    }

    @Override
    @Transactional
    public int insertPLog(PLog pLog)
    {
        return pLogMapper.insertPLog(pLog);
    }

    @Override
    @Transactional
    public int deletePLogByIds(Long[] ids)
    {
        return pLogMapper.deletePLogByIds(ids);
    }

    @Override
    public void deletePLog() {
        pLogMapper.deletePLog();
    }
}
