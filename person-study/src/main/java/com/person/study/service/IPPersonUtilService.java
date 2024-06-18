package com.person.study.service;

import java.util.List;
import com.person.common.domain.PPersonUtil;
import com.person.study.query.PersonUtilQuery;

/**
 * 个人工具Service接口
 *
 * @author sk
 * @date 2024-05-17
 */
public interface IPPersonUtilService
{
    /**
     * 查询个人工具
     *
     * @param id 主键
     * @return
     */
    public PPersonUtil selectPPersonUtilById(Long id);

    /**
     * 查询个人工具列表
     *
     * @param query
     * @return
     */
    public List<PPersonUtil> selectPPersonUtilList(PersonUtilQuery query);

    /**
     * 新增个人工具
     *
     * @param pPersonUtil
     * @return
     */
    public int insertPPersonUtil(PPersonUtil pPersonUtil);

    /**
     * 修改个人工具
     *
     * @param pPersonUtil
     * @return
     */
    public int updatePPersonUtil(PPersonUtil pPersonUtil);

    /**
     * 删除个人工具信息
     *
     * @param id 主键
     * @return 结果
     */
    public int deletePPersonUtilById(Long id);
}
