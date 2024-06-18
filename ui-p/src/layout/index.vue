<template>
  <div class="app-wrapper">
    <div class="main-left">
      <Logo />
      <!-- 菜单 -->
      <sidebar class="sidebar-container"/>
    </div>
    <div class="main-right">
      <div class="main-right-header">
        <!-- 头部 -->
        <navbar class="main-right-header-top"/>
        <!-- 已打开的页面 -->
        <tags-view v-if="needTagsView"/>
      </div>
      <div class="main-right-main">
        <!-- 内容区 -->
        <app-main/>
      </div>
    </div>
  </div>
</template>

<script>
import { mapState } from 'vuex'
import { AppMain, Navbar, Sidebar, TagsView } from './components'
import Logo from './components/Logo.vue'
export default {
  name: 'Layout',
  components: { AppMain, Navbar, Sidebar, TagsView , Logo },
  data(){
    return{
    }
  },
  computed: {
    ...mapState({
      needTagsView: state => state.settings.tagsView,
      sidebar: state => state.app.sidebar,
    }),
    classObj() {
      if(this.sidebar.opened){
        return 'sidebar-container'
      }else{
        return 'sidebar-hide'
      }
    },
  },
}
</script>

<style lang="scss" scoped>
body, html {
  margin: 0;
  padding: 0;
  height: 100%; /* 确保 body 和 html 元素占据整个浏览器高度 */
}
.app-wrapper {
  display: flex;
  flex-direction: row;
  overflow: hidden;
  background-color: #f5f7fd;
  height: 100%;
}
.main-left {
  z-index: 1000;
  display: flex;
  flex-direction: column;
  box-sizing: border-box;
  color: #121314;
  background-color: #ffffff; /* 可选，添加背景色以验证高度 */
  border-right: 1px solid #e5e6eb;
  .sidebar-container {
    flex: 1;
    overflow: hidden;
    background-color: inherit;
  }
  //.sidebar-hide{
  //  flex: 1;
  //  overflow: hidden;
  //  width: 60px;
  //  background-color: inherit;
  //}
}

.main-right {
  display: flex;
  flex: 1;
  flex-direction: column;
  margin: 0;
  padding: 0;
  overflow: hidden;
  .main-right-header-top{
    height: 56px;
  }

  .main-right-main{
    width: 100%;
    height: 100%;
    display: flex;
    flex-direction: column;
    overflow: hidden;
    position: relative;
  }
}
</style>
