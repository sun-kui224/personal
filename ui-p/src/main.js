import Vue from "vue"
import App from "./App.vue"
import router from "./router"
import store from "./store"
import ElementUI from "element-ui"
import 'element-ui/lib/theme-chalk/index.css'
import './permission'
import './assets/icons' // icon
import './assets/css/global.css' // icon
import './assets/styles/index.scss' // icon

import plugins from './plugins' // plugins
Vue.use(plugins);

import pagination from "@/components/pagination";
import pLayout from "@/components/pLayout";
import CusEdite from "@/components/CusEdite";
Vue.component('pagination', pagination)
Vue.component('pLayout', pLayout)
Vue.component('cusEdite', CusEdite)
Vue.config.productionTip = false
Vue.use(ElementUI)
import { parseTime , addDateRange} from "@/utils/date";
import { handleTree , handleFile} from "@/utils/personal";


Vue.prototype.parseTime = parseTime
Vue.prototype.addDateRange = addDateRange
Vue.prototype.handleTree = handleTree
Vue.prototype.handleFile = handleFile
Vue.prototype.$loading = ElementUI.Loading.service;

new Vue({
  router,
  store,
  render: (h) => h(App),
}).$mount("#app")
