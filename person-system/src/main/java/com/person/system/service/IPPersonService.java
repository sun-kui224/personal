package com.person.system.service;

import java.util.List;
import com.person.common.domain.PPerson;

/**
 * 用户Service接口
 *
 * @author sk
 * @date 2024-05-17
 */
public interface IPPersonService
{
    /**
     * 查询用户
     *
     * @param id 主键
     * @return
     */
    public PPerson selectPPersonById(Long id);

    /**
     * 根据用户名称查询用户
     * @param loginName
     * @return
     */
    public PPerson selectPPersonByLoginName(String loginName);

    /**
     * 查询用户列表
     *
     * @param pPerson
     * @return
     */
    public List<PPerson> selectPPersonList(PPerson pPerson);

    /**
     * 新增用户
     *
     * @param pPerson
     * @return
     */
    public int insertPPerson(PPerson pPerson);

    /**
     * 修改用户
     *
     * @param pPerson
     * @return
     */
    public int updatePPerson(PPerson pPerson);

    /**
     * 删除用户信息
     *
     * @param id 主键
     * @return 结果
     */
    public int deletePPersonById(Long id);
}
