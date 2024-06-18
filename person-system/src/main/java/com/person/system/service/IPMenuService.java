package com.person.system.service;

import java.util.List;
import com.person.common.domain.PMenu;
import com.person.system.domain.vo.RouterVo;
import com.person.system.query.MenuQuery;

/**
 * 菜单Service接口
 *
 * @author sk
 * @date 2024-05-17
 */
public interface IPMenuService
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
     * @param query
     * @return
     */
    public List<PMenu> selectPMenuALLListTree(MenuQuery query);

    /**
     * 查询菜单列表（树结构）
     *
     * @return
     */
    public List<PMenu> selectPMenuListTree();

    /**
     * 新增菜单
     *
     * @param pMenu
     * @return
     */
    public int insertPMenu(PMenu pMenu);

    /**
     * 修改菜单
     *
     * @param pMenu
     * @return
     */
    public int updatePMenu(PMenu pMenu);

    /**
     * 停用菜单
     * @param id 主键
     * @return 结果
     */
    public int stopPMenuById(Long id);

    /**
     * 启用菜单
     */
    public int startPMenuById(Long id);

    /**
     * 删除菜单信息
     *
     * @param id 主键
     * @return 结果
     */
    public int deletePMenuById(Long id);

    /**
     * 构建路由
     * @param menus
     * @return
     */
    public List<RouterVo> buildMenus(List<PMenu> menus);

}
