package com.person.work.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.person.work.mapper.PWorkCategoryMapper;
import com.person.common.domain.PWorkCategory;
import com.person.work.service.IPWorkCategoryService;

/**
 * 记录分类Service业务层处理
 *
 * @author sk
 * @date 2024-05-17
 */
@Service
public class PWorkCategoryServiceImpl implements IPWorkCategoryService
{
    @Autowired
    private PWorkCategoryMapper pWorkCategoryMapper;

    @Override
    public PWorkCategory selectPWorkCategoryById(Long id)
    {
        return pWorkCategoryMapper.selectPWorkCategoryById(id);
    }

    @Override
    public List<PWorkCategory> selectPWorkCategoryList(PWorkCategory pWorkCategory)
    {
        return pWorkCategoryMapper.selectPWorkCategoryList(pWorkCategory);
    }

    @Override
    @Transactional
    public int insertPWorkCategory(PWorkCategory pWorkCategory)
    {
        return pWorkCategoryMapper.insertPWorkCategory(pWorkCategory);
    }

    @Override
    @Transactional
    public int updatePWorkCategory(PWorkCategory pWorkCategory)
    {
        return pWorkCategoryMapper.updatePWorkCategory(pWorkCategory);
    }

    @Override
    @Transactional
    public int deletePWorkCategoryById(Long id)
    {
        return pWorkCategoryMapper.deletePWorkCategoryById(id);
    }
}
