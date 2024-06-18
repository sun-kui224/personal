package com.person.study.service;

import java.util.List;
import com.person.common.domain.PPersonBugRecords;
import com.person.study.query.BugRecordsQuery;

/**
 * 个人错误记录Service接口
 *
 * @author sk
 * @date 2024-05-17
 */
public interface IPPersonBugRecordsService
{
    /**
     * 查询个人错误记录
     *
     * @param id 主键
     * @return
     */
    public PPersonBugRecords selectPPersonBugRecordsById(Long id);

    /**
     * 查询个人错误记录列表
     *
     * @param bugRecordsQuery
     * @return
     */
    public List<PPersonBugRecords> selectPPersonBugRecordsList(BugRecordsQuery bugRecordsQuery);

    /**
     * 新增个人错误记录
     *
     * @param pPersonBugRecords
     * @return
     */
    public int insertPPersonBugRecords(PPersonBugRecords pPersonBugRecords);

    /**
     * 修改个人错误记录
     *
     * @param pPersonBugRecords
     * @return
     */
    public int updatePPersonBugRecords(PPersonBugRecords pPersonBugRecords);

    /**
     * 删除个人错误记录信息
     *
     * @param id 主键
     * @return 结果
     */
    public int deletePPersonBugRecordsById(Long id);
}
