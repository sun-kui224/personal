package com.person.system.service.impl;

import java.util.List;
import java.util.Optional;

import com.person.common.base.domain.AjaxResult;
import com.person.common.domain.PDictType;
import com.person.common.utils.DateUtils;
import com.person.common.utils.IdUtils;
import com.person.common.utils.SecurityUtils;
import com.person.system.mapper.PDictDataMapper;
import com.person.system.mapper.PDictTypeMapper;
import com.person.system.query.DictTypeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.person.system.service.IPDictTypeService;

/**
 * 字典类型Service业务层处理
 *
 * @author sk
 * @date 2024-06-04
 */
@Service
public class PDictTypeServiceImpl implements IPDictTypeService
{
    @Autowired
    private PDictTypeMapper pDictTypeMapper;

    @Autowired
    private PDictDataMapper pDictDataMapper;

    @Override
    public PDictType selectPDictTypeById(Long id)
    {
        return pDictTypeMapper.selectPDictTypeById(id);
    }

    @Override
    public List<PDictType> selectPDictTypeList(DictTypeQuery query)
    {
        return pDictTypeMapper.selectPDictTypeList(query);
    }

    @Override
    @Transactional
    public AjaxResult insertPDictType(PDictType pDictType)
    {
        //校验类型是否唯一
        Optional optional = Optional.ofNullable(pDictTypeMapper.selectPDictTypeByDictType(pDictType));
        if(optional.isPresent()){
            return AjaxResult.warn("该类型已经存在");
        }
        pDictType.setId(IdUtils.fastId());
        pDictType.setCreateBy(SecurityUtils.getUsername());
        pDictType.setCreateTime(DateUtils.getNowDate());
        pDictType.setStatus("0");
        return AjaxResult.success(pDictTypeMapper.insertPDictType(pDictType));
    }

    @Override
    @Transactional
    public AjaxResult updatePDictType(PDictType pDictType)
    {
        //校验类型是否唯一
        Optional optional = Optional.ofNullable(pDictTypeMapper.selectPDictTypeByDictType(pDictType));
        if(optional.isPresent()){
            return AjaxResult.warn("该类型已经存在");
        }
        pDictType.setUpdateBy(SecurityUtils.getUsername());
        pDictType.setUpdateTime(DateUtils.getNowDate());
        return AjaxResult.success(pDictTypeMapper.updatePDictType(pDictType));
    }

    @Override
    @Transactional
    public AjaxResult deletePDictTypeById(Long id)
    {
        //校验是否存在字典数据
        int i = pDictDataMapper.selectPDictDataListByTypeId(id);
        if(i > 0){
            return AjaxResult.warn("该字典类型下存在字典数据");
        }
        return AjaxResult.success(pDictTypeMapper.deletePDictTypeById(id));
    }

    @Override
    public int stopPDictTypeById(Long id) {
        return pDictTypeMapper.stopPDictTypeById(id);
    }

    @Override
    public int startPDictTypeById(Long id) {
        return pDictTypeMapper.startPDictTypeById(id);
    }
}
