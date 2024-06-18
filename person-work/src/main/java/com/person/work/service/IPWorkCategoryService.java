package com.person.work.service;

import java.util.List;
import com.person.common.domain.PWorkCategory;

/**
 * 记录分类Service接口
 *
 * @author sk
 * @date 2024-05-17
 */
public interface IPWorkCategoryService
{
    /**
     * 查询记录分类
     *
     * @param id 主键
     * @return
     */
    public PWorkCategory selectPWorkCategoryById(Long id);

    /**
     * 查询记录分类列表
     *
     * @param pWorkCategory
     * @return
     */
    public List<PWorkCategory> selectPWorkCategoryList(PWorkCategory pWorkCategory);

    /**
     * 新增记录分类
     *
     * @param pWorkCategory
     * @return
     */
    public int insertPWorkCategory(PWorkCategory pWorkCategory);

    /**
     * 修改记录分类
     *
     * @param pWorkCategory
     * @return
     */
    public int updatePWorkCategory(PWorkCategory pWorkCategory);

    /**
     * 删除记录分类信息
     *
     * @param id 主键
     * @return 结果
     */
    public int deletePWorkCategoryById(Long id);
}
