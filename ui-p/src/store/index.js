import vue from "vue"
import vuex from "vuex"
import user from './modules/user'
import tagsView from './modules/tagsView'
import getters from './getters'
import settings from './modules/settings'
import app from './modules/app'
import permission from './modules/permission'
vue.use(vuex)

const store = new vuex.Store({
    modules: {
        user,
        tagsView,
        settings,
        app,
        permission
    },
    getters
})
export default store
