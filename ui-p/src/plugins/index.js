import modal from './modal'
import tab from './tab'
export default {
  install(Vue) {
    // 页签操作
    Vue.prototype.$tab = tab
    // 模态框对象
    Vue.prototype.$modal = modal
  }
}
