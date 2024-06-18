package com.person.system.service.impl;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.person.common.constant.UserConstant;
import com.person.common.utils.DateUtils;
import com.person.common.utils.IdUtils;
import com.person.common.utils.SecurityUtils;
import com.person.common.utils.compareUtils;
import com.person.system.domain.vo.MetaVo;
import com.person.system.domain.vo.RouterVo;
import com.person.system.query.MenuQuery;
import com.person.system.service.IPMenuService;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.person.system.mapper.PMenuMapper;
import com.person.common.domain.PMenu;

/**
 * 菜单Service业务层处理
 *
 * @author sk
 * @date 2024-05-17
 */
@Service
public class PMenuServiceImpl implements IPMenuService
{
    @Autowired
    private PMenuMapper pMenuMapper;

    @Override
    public PMenu selectPMenuById(Long id)
    {
        return pMenuMapper.selectPMenuById(id);
    }

    @Override
    public List<PMenu> selectPMenuALLListTree(MenuQuery query) {
        List<PMenu> pMenus = pMenuMapper.selectPMenuList(query);
        return buildTree(pMenus);
    }

    @Override
    public List<PMenu> selectPMenuListTree()
    {
        List<PMenu> pMenus = pMenuMapper.selectEffectivePMenuList();
        return buildTree(pMenus);
    }

    @Override
    @Transactional
    public int insertPMenu(PMenu pMenu)
    {
        pMenu.setId(IdUtils.fastId());
        pMenu.setCreateBy(SecurityUtils.getUsername());
        pMenu.setCreateDate(DateUtils.getNowDate());
        pMenu.setDelFlag(0);
        return pMenuMapper.insertPMenu(pMenu);
    }

    @Override
    @Transactional
    public int updatePMenu(PMenu pMenu)
    {
        pMenu.setUpdateBy(SecurityUtils.getUsername());
        pMenu.setUpdateDate(DateUtils.getNowDate());
        return pMenuMapper.updatePMenu(pMenu);
    }

    @Override
    @Transactional
    public int stopPMenuById(Long id) {
        return pMenuMapper.operateMenuList(id,1);
    }

    @Override
    public int startPMenuById(Long id) {
        return pMenuMapper.operateMenuList(id,0);

    }

    @Override
    @Transactional
    public int deletePMenuById(Long id)
    {
        return pMenuMapper.deletePMenuById(id);
    }

    @Override
    public List<RouterVo> buildMenus(List<PMenu> menus)
    {
        List<RouterVo> routers = new LinkedList<>();
        for (PMenu menu : menus) {
            RouterVo router = new RouterVo();
            router.setHidden(compareUtils.integerEqualsToInt(menu.getHide(),1));
            router.setName(getRouteName(menu));
            router.setPath(getRouterPath(menu));
            router.setComponent(getComponent(menu));
            router.setMeta(new MetaVo(menu.getName(), menu.getIcon(), compareUtils.integerEqualsToInt(menu.getIsCache(),1)));
            List<PMenu> cMenus = menu.getChildren();
            if (ObjectUtils.isNotEmpty(cMenus) && UserConstant.TYPE_DIR.equals(menu.getType())) { // 目录
                router.setAlwaysShow(true);
                router.setRedirect("noRedirect");
                router.setChildren(buildMenus(cMenus));
            }else if(isMenuFrame(menu)){ // 一级菜单
                router.setMeta(null);
                List<RouterVo> childrenList = new ArrayList<RouterVo>();
                RouterVo children = new RouterVo();
                children.setPath(menu.getPath());
                children.setComponent(menu.getComponent());
                children.setName(StringUtils.capitalize(menu.getPath()));
                children.setMeta(new MetaVo(menu.getName(), menu.getIcon(), compareUtils.integerEqualsToInt(menu.getIsCache(),1)));
                childrenList.add(children);
                router.setChildren(childrenList);
            }
            routers.add(router);
        }
        return routers;
    }

    /**
     * 判断是否是菜单，且属于第一级
     * @param menu
     * @return
     */
    public boolean isMenuFrame(PMenu menu) {
        return menu.getParentId().intValue() == 0 && UserConstant.TYPE_MENU.equals(menu.getType());
    }

    /**
     * 获取路由名称
     *
     * @param menu 菜单信息
     * @return 路由名称
     */
    public String getRouteName(PMenu menu)
    {
        String routerName = StringUtils.capitalize(menu.getPath());
        // 一级菜单
        if (isMenuFrame(menu))
        {
            routerName = StringUtils.EMPTY;
        }
        return routerName;
    }

    /**
     * 获取组件
     * @param menu
     * @return
     */
    public String getComponent(PMenu menu) {
        String component = UserConstant.LAYOUT;
        if (StringUtils.isNotEmpty(menu.getComponent()) && !isMenuFrame(menu)) {
            component = menu.getComponent();
        }
        return component;
    }

    /**
     * 获取路由地址
     *
     * @param menu 菜单信息
     * @return 路由地址
     */
    public String getRouterPath(PMenu menu)
    {
        String routerPath = menu.getPath();
        // 一级目录
        if (0 == menu.getParentId().intValue() && UserConstant.TYPE_DIR.equals(menu.getType()))
        {
            routerPath = "/" + menu.getPath();
        }
        // 一级菜单
        else if (isMenuFrame(menu))
        {
            routerPath = "/";
        }
        return routerPath;
    }

    public static List<PMenu> buildTree(List<PMenu> pMenus) {
        // 创建一个Map，key为parentId，value为对应的PMenu对象
        Map<Long, List<PMenu>> menuMap = pMenus.stream()
                .collect(Collectors.groupingBy(PMenu::getParentId));
        // 过滤出parentId为null或0的根节点
        List<PMenu> roots = pMenus.stream()
                .filter(menu -> menu.getParentId() == null || menu.getParentId().equals(0L))
                .collect(Collectors.toList());

        // 对每个根节点进行递归构建子树
        for (PMenu root : roots) {
            buildTreeRecursive(menuMap, root);
        }
        return roots;
    }

    private static void buildTreeRecursive(Map<Long, List<PMenu>> menuMap, PMenu parent) {
        List<PMenu> children = menuMap.getOrDefault(parent.getId(), Collections.emptyList());

        // 使用Optional确保parent.getChildren()不为空
        Optional.ofNullable(parent.getChildren())
                .orElseGet(() -> {
                    parent.setChildren(new ArrayList<>());
                    return parent.getChildren();
                })
                .addAll(children);

        for (PMenu child : children) {
            buildTreeRecursive(menuMap, child); // 递归处理子节点
            menuMap.remove(child.getId()); // 移除已添加到子节点的菜单，避免重复
        }
    }
}
