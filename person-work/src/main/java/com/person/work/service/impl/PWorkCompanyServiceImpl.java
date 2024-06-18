package com.person.work.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.person.work.mapper.PWorkCompanyMapper;
import com.person.common.domain.PWorkCompany;
import com.person.work.service.IPWorkCompanyService;

/**
 * 公司Service业务层处理
 *
 * @author sk
 * @date 2024-05-17
 */
@Service
public class PWorkCompanyServiceImpl implements IPWorkCompanyService
{
    @Autowired
    private PWorkCompanyMapper pWorkCompanyMapper;

    @Override
    public PWorkCompany selectPWorkCompanyById(String id)
    {
        return pWorkCompanyMapper.selectPWorkCompanyById(id);
    }

    @Override
    public List<PWorkCompany> selectPWorkCompanyList(PWorkCompany pWorkCompany)
    {
        return pWorkCompanyMapper.selectPWorkCompanyList(pWorkCompany);
    }

    @Override
    @Transactional
    public int insertPWorkCompany(PWorkCompany pWorkCompany)
    {
        return pWorkCompanyMapper.insertPWorkCompany(pWorkCompany);
    }

    @Override
    @Transactional
    public int updatePWorkCompany(PWorkCompany pWorkCompany)
    {
        return pWorkCompanyMapper.updatePWorkCompany(pWorkCompany);
    }

    @Override
    @Transactional
    public int deletePWorkCompanyById(Long id)
    {
        return pWorkCompanyMapper.deletePWorkCompanyById(id);
    }
}
