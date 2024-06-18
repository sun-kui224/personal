package com.person.system.service;

import com.person.common.base.domain.AjaxResult;
import com.person.common.domain.PLabelCorr;
import com.person.common.domain.PPersonLabel;
import com.person.common.domain.entity.LabelEntity;
import com.person.system.query.LabelQuery;

import java.util.List;

/**
 * 标签Service接口
 *
 * @author sk
 * @date 2024-05-31
 */
public interface IPPersonLabelService
{
    /**
     * 查询标签
     *
     * @param id 主键
     * @return
     */
    public PPersonLabel selectPPersonLabelById(Long id);

    /**
     * 查询标签列表
     *
     * @param query
     * @return
     */
    public List<PPersonLabel> selectPPersonLabelList(LabelQuery query);

    /**
     * 仅供标签组件查询
     * @return
     */
    public List<LabelEntity> selectLabelListComponent();

    /**
     * 新增标签
     *
     * @param pPersonLabel
     * @return
     */
    public AjaxResult insertPPersonLabel(PPersonLabel pPersonLabel);

    /**
     * 新增关联
     * @param pLabelCorr
     * @return
     */
    public AjaxResult insertPLabelCorr(PLabelCorr pLabelCorr);

    /**
     * 修改标签
     *
     * @param pPersonLabel
     * @return
     */
    public AjaxResult updatePPersonLabel(PPersonLabel pPersonLabel);

    /**
     * 删除标签信息
     *
     * @param id 主键
     * @return 结果
     */
    public AjaxResult deletePPersonLabelById(Long id);
}
