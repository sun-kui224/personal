package com.person.system.mapper;

import java.util.List;

import com.person.common.domain.PPersonLabel;
import com.person.common.domain.entity.LabelEntity;
import com.person.system.query.LabelQuery;
import org.apache.ibatis.annotations.Mapper;

/**
 * 标签Mapper接口
 *
 * @author sk
 * @date 2024-05-31
 */
@Mapper
public interface PPersonLabelMapper
{
    /**
     * 查询标签
     *
     * @param id 主键
     * @return
     */
    public PPersonLabel selectPPersonLabelById(Long id);

    /**
     * 根据pid查询子标签数量
     * @param pid
     * @return
     */
    public int selectLabelChildCount(Long pid);

    /**
     * 根据名称校验是否唯一
     * @param pPersonLabel
     * @return
     */
    public int selectLabelCountByName(PPersonLabel pPersonLabel);

    /**
     * 查询标签列表
     *
     * @param query 标签
     * @return 集合
     */
    public List<PPersonLabel> selectPPersonLabelList(LabelQuery query);

    /**
     * 查询标签组件列表
     * @return
     */
    public List<LabelEntity> selectLabelListComponent();

    /**
     * 新增标签
     *
     * @param pPersonLabel
     * @return 结果
     */
    public int insertPPersonLabel(PPersonLabel pPersonLabel);

    /**
     * 修改标签
     *
     * @param pPersonLabel
     * @return 结果
     */
    public int updatePPersonLabel(PPersonLabel pPersonLabel);

    /**
     * 删除标签
     *
     * @param id 主键
     * @return 结果
     */
    public int deletePPersonLabelById(Long id);
}
