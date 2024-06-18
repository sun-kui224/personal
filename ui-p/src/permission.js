import router from './router'
import store from './store'
import NProgress from 'nprogress'
import 'nprogress/nprogress.css'
import { getToken } from '@/utils/auth'
NProgress.configure({ showSpinner: false })

const whiteList = ['/login', '/register']

router.beforeEach((to, from, next) => {
  NProgress.start()
  // 获取路由 meta 中的title，并设置给页面标题
  document.title = process.env.VUE_APP_TITLE
  if (getToken()) {
    /* 已有token请求登录页直接跳转首页 */
    if (to.path === '/login') {
      next({ path: '/' })
      NProgress.done()
    } else {
      if(store.getters.loginName === '' || store.getters.loginName === null){
        //加载个人信息
        store.dispatch('GetInfo').then((res) => {
          window.localStorage.setItem("userInfo", JSON.stringify(res.user))
          //获取路由
          store.dispatch('GenerateRoutes').then((res) => {
            router.addRoutes(res)
            next({ ...to, replace: true })
          })
        })
      }else{
        next()
      }
    }
  } else {
    // 没有token
    if (whiteList.indexOf(to.path) !== -1) {
      // 在免登录白名单，直接进入
      next()
    } else {
      next(`/login?redirect=${encodeURIComponent(to.fullPath)}`) // 否则全部重定向到登录页
      NProgress.done()
    }
  }
})

router.afterEach(() => {
  NProgress.done()
})

