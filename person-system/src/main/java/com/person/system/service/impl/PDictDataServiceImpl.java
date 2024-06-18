package com.person.system.service.impl;

import java.util.List;
import java.util.Optional;

import com.person.common.base.domain.AjaxResult;
import com.person.common.domain.PDictData;
import com.person.common.utils.DateUtils;
import com.person.common.utils.IdUtils;
import com.person.common.utils.SecurityUtils;
import com.person.system.mapper.PDictDataMapper;
import com.person.system.query.PDictDataQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.person.system.service.IPDictDataService;

/**
 * 字典数据Service业务层处理
 *
 * @author sk
 * @date 2024-06-04
 */
@Service
public class PDictDataServiceImpl implements IPDictDataService
{
    @Autowired
    private PDictDataMapper pDictDataMapper;

    @Override
    public PDictData selectPDictDataById(Long id)
    {
        return pDictDataMapper.selectPDictDataById(id);
    }

    @Override
    public List<PDictData> selectPDictDataList(PDictDataQuery query)
    {
        return pDictDataMapper.selectPDictDataList(query);
    }

    @Override
    @Transactional
    public AjaxResult insertPDictData(PDictData pDictData)
    {
        // 排序号
       Optional sort = Optional.ofNullable(pDictDataMapper.selectPDictDataByDictSort(pDictData));
       if(sort.isPresent()){
           return AjaxResult.warn("排序号已存在");
       }
        //校验键值
        Optional value = Optional.ofNullable(pDictDataMapper.selectPDictDataByDictValue(pDictData));
        if(value.isPresent()){
            return AjaxResult.warn("键值已存在");
        }
        // 标签
        Optional label = Optional.ofNullable(pDictDataMapper.selectPDictDataByDictLabel(pDictData));
        if(label.isPresent()){
            return AjaxResult.warn("标签已存在");
        }

        pDictDataMapper.selectPDictDataByDictSort(pDictData);
        pDictData.setId(IdUtils.fastId());
        pDictData.setCreateBy(SecurityUtils.getUsername());
        pDictData.setStatus("0");
        pDictData.setCreateTime(DateUtils.getNowDate());
        return AjaxResult.success(pDictDataMapper.insertPDictData(pDictData));
    }

    @Override
    @Transactional
    public AjaxResult updatePDictData(PDictData pDictData)
    {
        // 排序号
        Optional sort = Optional.ofNullable(pDictDataMapper.selectPDictDataByDictSort(pDictData));
        if(sort.isPresent()){
            return AjaxResult.warn("排序号已存在");
        }
        //校验键值
        Optional value = Optional.ofNullable(pDictDataMapper.selectPDictDataByDictValue(pDictData));
        if(value.isPresent()){
            return AjaxResult.warn("键值已存在");
        }
        // 标签
        Optional label = Optional.ofNullable(pDictDataMapper.selectPDictDataByDictLabel(pDictData));
        if(label.isPresent()){
            return AjaxResult.warn("标签已存在");
        }

        pDictData.setUpdateBy(SecurityUtils.getUsername());
        pDictData.setUpdateTime(DateUtils.getNowDate());
        return AjaxResult.success(pDictDataMapper.updatePDictData(pDictData));
    }

    @Override
    @Transactional
    public int deletePDictDataById(Long id)
    {
        return pDictDataMapper.deletePDictDataById(id);
    }

    @Override
    public int stopPDictDataById(Long id) {
        return pDictDataMapper.stopPDictDataById(id);
    }

    @Override
    public int startPDictDataById(Long id) {
        return pDictDataMapper.startPDictDataById(id);
    }
}
