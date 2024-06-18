package com.person.system.service.impl;

import java.util.List;

import com.person.common.domain.PMenu;
import com.person.system.mapper.PMenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.person.system.mapper.PPersonMapper;
import com.person.common.domain.PPerson;
import com.person.system.service.IPPersonService;

/**
 * 用户Service业务层处理
 *
 * @author sk
 * @date 2024-05-17
 */
@Service
public class PPersonServiceImpl implements IPPersonService
{
    @Autowired
    private PPersonMapper pPersonMapper;

    @Autowired
    private PMenuMapper pMenuMapper;

    @Override
    public PPerson selectPPersonById(Long id)
    {
        return pPersonMapper.selectPPersonById(id);
    }

    @Override
    public PPerson selectPPersonByLoginName(String loginName)
    {
        return pPersonMapper.selectPPersonByLoginName(loginName);
    }

    @Override
    public List<PPerson> selectPPersonList(PPerson pPerson)
    {
        return pPersonMapper.selectPPersonList(pPerson);
    }

    @Override
    @Transactional
    public int insertPPerson(PPerson pPerson)
    {
        return pPersonMapper.insertPPerson(pPerson);
    }

    @Override
    @Transactional
    public int updatePPerson(PPerson pPerson)
    {
        return pPersonMapper.updatePPerson(pPerson);
    }

    @Override
    @Transactional
    public int deletePPersonById(Long id)
    {
        return pPersonMapper.deletePPersonById(id);
    }
}
