package com.person.study.mapper;

import java.util.List;

import com.person.study.query.BugRecordsQuery;
import org.apache.ibatis.annotations.Mapper;
import com.person.common.domain.PPersonBugRecords;

/**
 * 个人错误记录Mapper接口
 *
 * @author sk
 * @date 2024-05-17
 */
@Mapper
public interface PPersonBugRecordsMapper
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
     * @param bugRecordsQuery 个人错误记录
     * @return 集合
     */
    public List<PPersonBugRecords> selectPPersonBugRecordsList(BugRecordsQuery bugRecordsQuery);

    /**
     * 新增个人错误记录
     *
     * @param pPersonBugRecords
     * @return 结果
     */
    public int insertPPersonBugRecords(PPersonBugRecords pPersonBugRecords);

    /**
     * 修改个人错误记录
     *
     * @param pPersonBugRecords
     * @return 结果
     */
    public int updatePPersonBugRecords(PPersonBugRecords pPersonBugRecords);

    /**
     * 删除个人错误记录
     *
     * @param id 主键
     * @return 结果
     */
    public int deletePPersonBugRecordsById(Long id);
}
