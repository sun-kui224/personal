package com.person.work.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.person.common.domain.PWorkCategory;

/**
 * 记录分类Mapper接口
 *
 * @author sk
 * @date 2024-05-17
 */
@Mapper
public interface PWorkCategoryMapper
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
     * @param pWorkCategory 记录分类
     * @return 集合
     */
    public List<PWorkCategory> selectPWorkCategoryList(PWorkCategory pWorkCategory);

    /**
     * 新增记录分类
     *
     * @param pWorkCategory
     * @return 结果
     */
    public int insertPWorkCategory(PWorkCategory pWorkCategory);

    /**
     * 修改记录分类
     *
     * @param pWorkCategory
     * @return 结果
     */
    public int updatePWorkCategory(PWorkCategory pWorkCategory);

    /**
     * 删除记录分类
     *
     * @param id 主键
     * @return 结果
     */
    public int deletePWorkCategoryById(Long id);
}
