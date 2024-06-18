package com.person.system.service.impl;

import java.util.List;

import com.person.common.base.domain.AjaxResult;
import com.person.common.domain.PLabelCorr;
import com.person.common.domain.PPersonLabel;
import com.person.common.domain.entity.LabelEntity;
import com.person.common.exception.ServiceException;
import com.person.common.utils.DateUtils;
import com.person.common.utils.IdUtils;
import com.person.common.utils.SecurityUtils;
import com.person.system.mapper.PLabelCorrMapper;
import com.person.system.mapper.PPersonLabelMapper;
import com.person.system.query.LabelQuery;
import com.person.system.service.IPPersonLabelService;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 标签Service业务层处理
 *
 * @author sk
 * @date 2024-05-31
 */
@Service
public class PPersonLabelServiceImpl implements IPPersonLabelService
{
    @Autowired
    private PPersonLabelMapper pPersonLabelMapper;

    @Autowired
    private PLabelCorrMapper pLabelCorrMapper;

    @Override
    public PPersonLabel selectPPersonLabelById(Long id)
    {
        return pPersonLabelMapper.selectPPersonLabelById(id);
    }

    @Override
    public List<PPersonLabel> selectPPersonLabelList(LabelQuery query)
    {
        return pPersonLabelMapper.selectPPersonLabelList(query);
    }

    @Override
    public List<LabelEntity> selectLabelListComponent() {
        return pPersonLabelMapper.selectLabelListComponent();
    }

    @Override
    @Transactional
    public AjaxResult insertPPersonLabel(PPersonLabel pPersonLabel)
    {
        //校验名称唯一
        int i = pPersonLabelMapper.selectLabelCountByName(pPersonLabel);
        if(i>0){
            throw new ServiceException("改名称已存在，请重新输入！");
        }
        pPersonLabel.setCreateBy(SecurityUtils.getUsername());
        pPersonLabel.setCreateDate(DateUtils.getNowDate());
        pPersonLabel.setId(IdUtils.fastId());
        pPersonLabelMapper.insertPPersonLabel(pPersonLabel);
        return AjaxResult.success("添加成功");
    }

    @Override
    @Transactional
    public AjaxResult insertPLabelCorr(PLabelCorr pLabelCorr) {
        //删除原有的关联
        pLabelCorrMapper.deleteLabelResource(pLabelCorr.getOutId());
        //新增现有的
        if(pLabelCorr.getLabelIds()!=null && pLabelCorr.getLabelIds().length>0){
            pLabelCorrMapper.insertLabelResource(pLabelCorr);
        }
        return AjaxResult.success("成功");
    }

    @Override
    @Transactional
    public AjaxResult updatePPersonLabel(PPersonLabel pPersonLabel)
    {
        //校验名称唯一
        int i = pPersonLabelMapper.selectLabelCountByName(pPersonLabel);
        if(i>0){
            throw new ServiceException("改名称已存在，请重新输入！");
        }
        pPersonLabel.setUpdateBy(SecurityUtils.getUsername());
        pPersonLabel.setUpdateDate(DateUtils.getNowDate());
        pPersonLabelMapper.updatePPersonLabel(pPersonLabel);
        return AjaxResult.success("修改成功");
    }

    @Override
    @Transactional
    public AjaxResult deletePPersonLabelById(Long id)
    {
        //判断是否存在子标签
        int labelCount = pPersonLabelMapper.selectLabelChildCount(id);
        if(labelCount>0){
            AjaxResult.warn("该标签存在子标签，请先删除子标签");
        }
        //判断是否存在关联资源
        int outCount = pLabelCorrMapper.selectLabelResourceCount(id);
        if(outCount>0){
            AjaxResult.warn("该标签存在关联资源，请先删除关联资源");
        }
        pPersonLabelMapper.deletePPersonLabelById(id);
        return AjaxResult.success("成功");
    }
}
