package com.person.system.mapper;

import java.util.List;

import com.person.system.query.MenuQuery;
import org.apache.ibatis.annotations.Mapper;
import com.person.common.domain.PMenu;
import org.apache.ibatis.annotations.Param;

/**
 * 菜单Mapper接口
 *
 * @author sk
 * @date 2024-05-17
 */
@Mapper
public interface PMenuMapper
{
    /**
     * 查询菜单
     *
     * @param id 主键
     * @return
     */
    public PMenu selectPMenuById(Long id);

    /**
     * 查询菜单列表
     *
     * @param query 菜单
     * @return 集合
     */
    public List<PMenu> selectPMenuList(MenuQuery query);

    /**
     * 查询有效路由
     * @return
     */
    public List<PMenu> selectEffectivePMenuList();

    /**
     * 新增菜单
     *
     * @param pMenu
     * @return 结果
     */
    public int insertPMenu(PMenu pMenu);

    /**
     * 修改菜单
     *
     * @param pMenu
     * @return 结果
     */
    public int updatePMenu(PMenu pMenu);

    /**
     * 停用菜单
     * @param id
     * @param status
     * @return
     */
    public int operateMenuList(@Param("id") Long id, @Param("status") int status);

    /**
     * 删除菜单
     *
     * @param id 主键
     * @return 结果
     */
    public int deletePMenuById(Long id);
}
