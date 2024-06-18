package com.person.study.mapper;

import java.util.List;

import com.person.study.query.PersonUtilQuery;
import org.apache.ibatis.annotations.Mapper;
import com.person.common.domain.PPersonUtil;

/**
 * 个人工具Mapper接口
 *
 * @author sk
 * @date 2024-05-17
 */
@Mapper
public interface PPersonUtilMapper
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
     * @param query 个人工具
     * @return 集合
     */
    public List<PPersonUtil> selectPPersonUtilList(PersonUtilQuery query);

    /**
     * 新增个人工具
     *
     * @param pPersonUtil
     * @return 结果
     */
    public int insertPPersonUtil(PPersonUtil pPersonUtil);

    /**
     * 修改个人工具
     *
     * @param pPersonUtil
     * @return 结果
     */
    public int updatePPersonUtil(PPersonUtil pPersonUtil);

    /**
     * 删除个人工具
     *
     * @param id 主键
     * @return 结果
     */
    public int deletePPersonUtilById(Long id);
}
