package com.person.work.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.person.common.domain.PWorkReport;

/**
 * 工作记录Mapper接口
 *
 * @author sk
 * @date 2024-05-17
 */
@Mapper
public interface PWorkReportMapper
{
    /**
     * 查询工作记录
     *
     * @param id 主键
     * @return
     */
    public PWorkReport selectPWorkReportById(Long id);

    /**
     * 查询工作记录列表
     *
     * @param pWorkReport 工作记录
     * @return 集合
     */
    public List<PWorkReport> selectPWorkReportList(PWorkReport pWorkReport);

    /**
     * 新增工作记录
     *
     * @param pWorkReport
     * @return 结果
     */
    public int insertPWorkReport(PWorkReport pWorkReport);

    /**
     * 修改工作记录
     *
     * @param pWorkReport
     * @return 结果
     */
    public int updatePWorkReport(PWorkReport pWorkReport);

    /**
     * 删除工作记录
     *
     * @param id 主键
     * @return 结果
     */
    public int deletePWorkReportById(Long id);
}
