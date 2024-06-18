package com.person.system.service;

import java.util.List;
import com.person.common.domain.PLog;
import com.person.system.query.LogQuery;

/**
 * 日志Service接口
 *
 * @author sk
 * @date 2024-05-17
 */
public interface IPLogService
{
    /**
     * 查询日志
     *
     * @param id 主键
     * @return
     */
    public PLog selectPLogById(Long id);

    /**
     * 查询日志列表
     *
     * @param query
     * @return
     */
    public List<PLog> selectPLogList(LogQuery query);

    /**
     * 新增日志
     *
     * @param pLog
     * @return
     */
    public int insertPLog(PLog pLog);

    /**
     * 删除日志信息
     *
     * @param ids 主键
     * @return 结果
     */
    public int deletePLogByIds(Long[] ids);

    /**
     * 情况日志
     * @return
     */
    public void deletePLog();
}
