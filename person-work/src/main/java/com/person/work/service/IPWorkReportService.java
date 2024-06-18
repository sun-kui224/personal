package com.person.work.service;

import java.util.List;
import com.person.common.domain.PWorkReport;

/**
 * 工作记录Service接口
 *
 * @author sk
 * @date 2024-05-17
 */
public interface IPWorkReportService
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
     * @param pWorkReport
     * @return
     */
    public List<PWorkReport> selectPWorkReportList(PWorkReport pWorkReport);

    /**
     * 新增工作记录
     *
     * @param pWorkReport
     * @return
     */
    public int insertPWorkReport(PWorkReport pWorkReport);

    /**
     * 修改工作记录
     *
     * @param pWorkReport
     * @return
     */
    public int updatePWorkReport(PWorkReport pWorkReport);

    /**
     * 删除工作记录信息
     *
     * @param id 主键
     * @return 结果
     */
    public int deletePWorkReportById(Long id);
}
