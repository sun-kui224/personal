package com.person.system.mapper;

import java.util.List;

import com.person.system.query.LogQuery;
import org.apache.ibatis.annotations.Mapper;
import com.person.common.domain.PLog;

/**
 * 日志Mapper接口
 *
 * @author sk
 * @date 2024-05-17
 */
@Mapper
public interface PLogMapper
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
     * @param query 日志
     * @return 集合
     */
    public List<PLog> selectPLogList(LogQuery query);

    /**
     * 新增日志
     *
     * @param pLog
     * @return 结果
     */
    public int insertPLog(PLog pLog);

    /**
     * 删除日志
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
