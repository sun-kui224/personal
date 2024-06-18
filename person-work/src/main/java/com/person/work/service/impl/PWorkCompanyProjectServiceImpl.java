package com.person.work.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.person.work.mapper.PWorkCompanyProjectMapper;
import com.person.common.domain.PWorkCompanyProject;
import com.person.work.service.IPWorkCompanyProjectService;

/**
 * 公司项目Service业务层处理
 *
 * @author sk
 * @date 2024-05-17
 */
@Service
public class PWorkCompanyProjectServiceImpl implements IPWorkCompanyProjectService
{
    @Autowired
    private PWorkCompanyProjectMapper pWorkCompanyProjectMapper;

    @Override
    public PWorkCompanyProject selectPWorkCompanyProjectById(Long id)
    {
        return pWorkCompanyProjectMapper.selectPWorkCompanyProjectById(id);
    }

    @Override
    public List<PWorkCompanyProject> selectPWorkCompanyProjectList(PWorkCompanyProject pWorkCompanyProject)
    {
        return pWorkCompanyProjectMapper.selectPWorkCompanyProjectList(pWorkCompanyProject);
    }

    @Override
    @Transactional
    public int insertPWorkCompanyProject(PWorkCompanyProject pWorkCompanyProject)
    {
        return pWorkCompanyProjectMapper.insertPWorkCompanyProject(pWorkCompanyProject);
    }

    @Override
    @Transactional
    public int updatePWorkCompanyProject(PWorkCompanyProject pWorkCompanyProject)
    {
        return pWorkCompanyProjectMapper.updatePWorkCompanyProject(pWorkCompanyProject);
    }

    @Override
    @Transactional
    public int deletePWorkCompanyProjectById(Long id)
    {
        return pWorkCompanyProjectMapper.deletePWorkCompanyProjectById(id);
    }
}
