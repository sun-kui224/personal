package com.person.work.service;

import java.util.List;
import com.person.common.domain.PWorkCompany;

/**
 * 公司Service接口
 *
 * @author sk
 * @date 2024-05-17
 */
public interface IPWorkCompanyService
{
    /**
     * 查询公司
     *
     * @param id 主键
     * @return
     */
    public PWorkCompany selectPWorkCompanyById(String id);

    /**
     * 查询公司列表
     *
     * @param pWorkCompany
     * @return
     */
    public List<PWorkCompany> selectPWorkCompanyList(PWorkCompany pWorkCompany);

    /**
     * 新增公司
     *
     * @param pWorkCompany
     * @return
     */
    public int insertPWorkCompany(PWorkCompany pWorkCompany);

    /**
     * 修改公司
     *
     * @param pWorkCompany
     * @return
     */
    public int updatePWorkCompany(PWorkCompany pWorkCompany);

    /**
     * 删除公司信息
     *
     * @param id 主键
     * @return 结果
     */
    public int deletePWorkCompanyById(Long id);
}
