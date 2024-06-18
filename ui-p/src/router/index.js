import Vue from "vue"
import Router from "vue-router"
import Layout from "@/layout"
import branchRouter from "@/router/branch";
Vue.use(Router)

export const constantRoutes = [
  {
    path: "/login",
    meta: { title: "登录" },
    component: () => import("@/common/login/index"),
    hidden: true
  },
  {
    path: "/register",
    meta: { title: "注册" },
    component: () => import("@/common/register/index"),
    hidden: true
  },
  {
    path: '',
    component: Layout,
    redirect: 'index',
    hidden: false,
    children: [
      {
        path: 'index',
        component: () => import('@/views/home/index'),
        name: 'Index',
        meta: { title: '首页', icon: 'dashboard', affix: true }
      },
    ]
  },
  {
    path: '/user',
    component: Layout,
    hidden: true,
    redirect: 'noredirect',
    children: [
      {
        path: 'userCenter',
        component: () => import('@/common/userCenter/index'),
        name: 'userCenter',
        meta: { title: '个人中心', icon: 'user' }
      }
    ]
  },
    ...branchRouter
]

// 防止连续点击多次路由报错
let routerPush = Router.prototype.push;
let routerReplace = Router.prototype.replace;
// push
Router.prototype.push = function push(location) {
  return routerPush.call(this, location).catch(err => err)
}
// replace
Router.prototype.replace = function push(location) {
  return routerReplace.call(this, location).catch(err => err)
}

export default new Router({
  mode: 'history', // 去掉url中的#
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRoutes
})
