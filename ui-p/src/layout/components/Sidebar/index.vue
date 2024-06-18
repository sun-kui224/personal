<template>
  <el-scrollbar wrap-class="scrollbar-wrapper">
    <el-menu style="height: calc(100vh - 90px)"
        mode="vertical"
        background-color="#ffffff"
        :default-active="activeMenu"
        text-color="#5c5c5c"
        active-text-color="#cc9115"
        :collapse="isCollapse"
        :collapse-transition="false"
        class="el-menu-vertical-demo"
        :router="true"
    >
      <menu-item v-for="(route, index) in sidebarRouters" :key="route.path  + index" :item="route"  :base-path="route.path"/>
    </el-menu>
  </el-scrollbar>
</template>

<script>
import { mapGetters } from "vuex";
import menuItem from "./menuItem.vue";
export default {
    components: {menuItem},
    computed: {
      ...mapGetters(["sidebarRouters", "sidebar"]),
      activeMenu() {
        const route = this.$route;
        const {path} = route;
        return path;
      },
      isCollapse() {
        return !this.sidebar.opened;
      }
    },
    methods:{

    }
};
</script>
<style scoped>
.scrollbar-wrapper {
  padding: 4px 8px;
  overflow: auto;
 }

 .el-menu {
   border: none;
   height: 100%;
 }

 .el-menu-item, .el-submenu__title {
   overflow: hidden !important;
   text-overflow: ellipsis !important;
   white-space: nowrap !important;
   height: 40px;
   font-size: 14px;
 }

 .scrollbar-wrapper {
   overflow-x: hidden !important;
 }

 .el-scrollbar__bar.is-vertical {
   right: 0px;
 }

 .el-scrollbar {
   height: calc(100% - 50px);
 }

/deep/.el-menu-vertical-demo:not(.el-menu--collapse) {
  width: 220px;
  min-height: 400px;
}
/deep/.el-menu--collapse {
  width: 60px;
}
</style>

