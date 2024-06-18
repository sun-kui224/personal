package com.person.system.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.person.common.domain.PPerson;

/**
 * 用户Mapper接口
 *
 * @author sk
 * @date 2024-05-17
 */
@Mapper
public interface PPersonMapper
{
    /**
     * 根据用户id查询用户
     *
     * @param id 主键
     * @return
     */
    public PPerson selectPPersonById(Long id);

    /**
     * 根据用户名查询用户
     * @param loginName
     * @return
     */
    public PPerson selectPPersonByLoginName(String loginName);

    /**
     * 查询用户列表
     *
     * @param pPerson 用户
     * @return 集合
     */
    public List<PPerson> selectPPersonList(PPerson pPerson);

    /**
     * 新增用户
     *
     * @param pPerson
     * @return 结果
     */
    public int insertPPerson(PPerson pPerson);

    /**
     * 修改用户
     *
     * @param pPerson
     * @return 结果
     */
    public int updatePPerson(PPerson pPerson);

    /**
     * 删除用户
     *
     * @param id 主键
     * @return 结果
     */
    public int deletePPersonById(Long id);
}
