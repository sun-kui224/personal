package com.person.system.controller;

import com.person.common.annotation.PublicApi;
import com.person.common.base.domain.AjaxResult;
import com.person.common.base.domain.LoginBody;
import com.person.common.base.domain.User;
import com.person.common.constant.HttpConstant;
import com.person.common.domain.PMenu;
import com.person.system.domain.vo.RouterVo;
import com.person.system.service.IPMenuService;
import com.person.system.service.LoginService;
import com.person.common.utils.SecurityUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 登录服务
 * @author sk
 * @date 2024/5/11
 */
@Api(tags = "项目入口")
@RestController
public class SysLoginController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private IPMenuService ipMenuService;

    /**
     * 登录
     * @param loginBody
     * @return
     */
    @ApiOperation("登录")
    @PublicApi
    @PostMapping("login")
    public AjaxResult login(@RequestBody LoginBody loginBody){
        if(loginBody == null){
            return AjaxResult.error("请传递数据");
        }
        AjaxResult ajax = AjaxResult.success();
        // 生成令牌
        String token = loginService.login(loginBody.getUsername(),loginBody.getPassword());
        ajax.put(HttpConstant.TOKEN, token);
        return ajax;
    }

    /**
     * 注册
     * @param loginBody
     * @return
     */
    @ApiOperation("注册")
    @PublicApi
    @PostMapping("register")
    public AjaxResult register(@RequestBody LoginBody loginBody){
        if(loginBody == null){
            return AjaxResult.error("请传递数据");
        }
        loginService.register(loginBody.getUsername(),loginBody.getPassword());
        return AjaxResult.success();
    }

    /**
     * 获取用户信息
     * @return
     */
    @ApiOperation("获取用户信息")
    @GetMapping("getInfo")
    public AjaxResult getInfo()
    {
        User user = SecurityUtils.getLoginUser().getUser();
        AjaxResult ajax = AjaxResult.success();
        ajax.put("user", user);
        return ajax;
    }

    /**
     * 获取路由信息
     *
     * @return 路由信息
     */
    @ApiOperation("获取路由信息")
    @GetMapping("getRouters")
    public AjaxResult getRouters()
    {
        List<PMenu> pMenus = ipMenuService.selectPMenuListTree();
        List<RouterVo> routerVos = ipMenuService.buildMenus(pMenus);
        return AjaxResult.success(routerVos);
    }
}
