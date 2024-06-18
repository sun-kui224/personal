package com.person.work.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.person.common.domain.PWorkCompanyProject;

/**
 * 公司项目Mapper接口
 *
 * @author sk
 * @date 2024-05-17
 */
@Mapper
public interface PWorkCompanyProjectMapper
{
    /**
     * 查询公司项目
     *
     * @param id 主键
     * @return
     */
    public PWorkCompanyProject selectPWorkCompanyProjectById(Long id);

    /**
     * 查询公司项目列表
     *
     * @param pWorkCompanyProject 公司项目
     * @return 集合
     */
    public List<PWorkCompanyProject> selectPWorkCompanyProjectList(PWorkCompanyProject pWorkCompanyProject);

    /**
     * 新增公司项目
     *
     * @param pWorkCompanyProject
     * @return 结果
     */
    public int insertPWorkCompanyProject(PWorkCompanyProject pWorkCompanyProject);

    /**
     * 修改公司项目
     *
     * @param pWorkCompanyProject
     * @return 结果
     */
    public int updatePWorkCompanyProject(PWorkCompanyProject pWorkCompanyProject);

    /**
     * 删除公司项目
     *
     * @param id 主键
     * @return 结果
     */
    public int deletePWorkCompanyProjectById(Long id);
}
