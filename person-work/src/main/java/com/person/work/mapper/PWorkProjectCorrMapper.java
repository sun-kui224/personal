package com.person.work.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.person.common.domain.PWorkProjectCorr;

/**
 * 项目关联Mapper接口
 *
 * @author sk
 * @date 2024-05-17
 */
@Mapper
public interface PWorkProjectCorrMapper
{
    /**
     * 查询项目关联
     *
     * @param companyId 主键
     * @return
     */
    public PWorkProjectCorr selectPWorkProjectCorrByCompanyId(Long companyId);

    /**
     * 查询项目关联列表
     *
     * @param pWorkProjectCorr 项目关联
     * @return 集合
     */
    public List<PWorkProjectCorr> selectPWorkProjectCorrList(PWorkProjectCorr pWorkProjectCorr);

    /**
     * 新增项目关联
     *
     * @param pWorkProjectCorr
     * @return 结果
     */
    public int insertPWorkProjectCorr(PWorkProjectCorr pWorkProjectCorr);

    /**
     * 修改项目关联
     *
     * @param pWorkProjectCorr
     * @return 结果
     */
    public int updatePWorkProjectCorr(PWorkProjectCorr pWorkProjectCorr);

    /**
     * 删除项目关联
     *
     * @param companyId 主键
     * @return 结果
     */
    public int deletePWorkProjectCorrByCompanyId(Long companyId);
}
