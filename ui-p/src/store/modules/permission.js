import { getRouters } from '@/api/login'
import Layout from '@/layout/index'
import { constantRoutes } from '@/router'

const permission = {
  state: {
    menuRouters: [],
    sidebarRouters:[]
  },
  mutations: {
    SET_MENU_ROUTERS: (state, routes) => {
      state.menuRouters = constantRoutes.concat(routes)
    },
    SET_SIDEBAR_ROUTERS: (state, routes) => {
      state.sidebarRouters = routes
    },
  },
  actions: {
    // 生成路由
    GenerateRoutes({ commit }) {
      return new Promise(resolve => {
        // 向后端请求路由数据
        getRouters().then(res => {
          const sdata = JSON.parse(JSON.stringify(res.data))
          const rdata = JSON.parse(JSON.stringify(res.data))
          const sidebarRoutes = filterAsyncRouter(sdata)
          const menuRouters = filterAsyncRouter(rdata, false, true)
          menuRouters.push({ path: '*', redirect: '/404', hidden: true })
          commit('SET_MENU_ROUTERS',  menuRouters)
          commit('SET_SIDEBAR_ROUTERS', constantRoutes.concat(sidebarRoutes))
          resolve(menuRouters)
        })
      })
    }
  }
}

// 遍历后台传来的路由字符串，转换为组件对象
function filterAsyncRouter(asyncRouterMap, lastRouter = false, type = false) {
  return asyncRouterMap.filter(route => {
    if (type && route.children) {
      route.children = filterChildren(route.children)
    }
    if (route.component) {
      if (route.component === 'Layout') {
        route.component = Layout
      }else{
        route.component = loadView(route.component)
      }
    }
    if (route.children != null && route.children && route.children.length) {
      route.children = filterAsyncRouter(route.children, route, type)
    } else {
      delete route['children']
      delete route['redirect']
    }
    return true
  })
}


function filterChildren(childrenMap, lastRouter = false) {
  var children = []
  childrenMap.forEach((el, index) => {
    children = children.concat(el)
  })
  return children
}

export const loadView = (view) => {
  return (resolve) => require([`@/views/${view}`], resolve)
}


export default permission
