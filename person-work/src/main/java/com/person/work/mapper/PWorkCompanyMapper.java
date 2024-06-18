package com.person.work.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.person.common.domain.PWorkCompany;

/**
 * 公司Mapper接口
 *
 * @author sk
 * @date 2024-05-17
 */
@Mapper
public interface PWorkCompanyMapper
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
     * @param pWorkCompany 公司
     * @return 集合
     */
    public List<PWorkCompany> selectPWorkCompanyList(PWorkCompany pWorkCompany);

    /**
     * 新增公司
     *
     * @param pWorkCompany
     * @return 结果
     */
    public int insertPWorkCompany(PWorkCompany pWorkCompany);

    /**
     * 修改公司
     *
     * @param pWorkCompany
     * @return 结果
     */
    public int updatePWorkCompany(PWorkCompany pWorkCompany);

    /**
     * 删除公司
     *
     * @param id 主键
     * @return 结果
     */
    public int deletePWorkCompanyById(Long id);
}
