package com.person.work.service;

import java.util.List;
import com.person.common.domain.PWorkCompanyProject;

/**
 * 公司项目Service接口
 *
 * @author sk
 * @date 2024-05-17
 */
public interface IPWorkCompanyProjectService
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
     * @param pWorkCompanyProject
     * @return
     */
    public List<PWorkCompanyProject> selectPWorkCompanyProjectList(PWorkCompanyProject pWorkCompanyProject);

    /**
     * 新增公司项目
     *
     * @param pWorkCompanyProject
     * @return
     */
    public int insertPWorkCompanyProject(PWorkCompanyProject pWorkCompanyProject);

    /**
     * 修改公司项目
     *
     * @param pWorkCompanyProject
     * @return
     */
    public int updatePWorkCompanyProject(PWorkCompanyProject pWorkCompanyProject);

    /**
     * 删除公司项目信息
     *
     * @param id 主键
     * @return 结果
     */
    public int deletePWorkCompanyProjectById(Long id);
}
