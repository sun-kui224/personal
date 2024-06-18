package com.person.system.mapper;

import com.person.common.domain.PLabelCorr;
import org.apache.ibatis.annotations.Mapper;

/**
 * 标签关联Mapper接口
 *
 * @author sk
 * @date 2024-05-17
 */
@Mapper
public interface PLabelCorrMapper
{
    /**
     * 根据外联主键id查询数量
     */
    public int selectLabelResourceCount(Long outId);

    /**
     * 新增关联
     * @param pLabelCorr
     * @return
     */
    public int insertLabelResource(PLabelCorr pLabelCorr);

    /**
     * 根据外联主键id删除关联资源
     * @param outId
     * @return
     */
    public int deleteLabelResource(Long outId);

}
