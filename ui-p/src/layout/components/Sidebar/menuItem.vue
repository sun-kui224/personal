<template>
  <div v-if="!item.hidden">
    <template v-if="hasOneShowingChild(item.children,item) && (!onlyOneChild.children||onlyOneChild.noShowingChildren)&&!item.alwaysShow && onlyOneChild.meta">
      <el-menu-item :index="resolvePath(onlyOneChild.path)" :class="{'submenu-title-noDropdown':!isNest}">
        <template  style="height: 40px;display: flex; align-items: center;" >
          <svg-icon v-if="onlyOneChild.meta.icon||(item.meta&&item.meta.icon)"
                    :svgName="onlyOneChild.meta.icon||(item.meta&&item.meta.icon)" :size="24" class="svg-menu"></svg-icon>
          <span slot="title">{{onlyOneChild.meta.title}}</span>
        </template>
      </el-menu-item>
    </template>

    <el-submenu v-else ref="subMenu" :index="resolvePath(item.path)" popper-append-to-body>
      <template slot="title">
        <div v-if="item.meta">
          <svg-icon :svgName="item.meta && item.meta.icon" :size="24" class="svg-menu"></svg-icon>
          <span>{{item.meta.title}}</span>
        </div>
      </template>
      <sidebar-item
          v-for="(child, index) in item.children"
          :key="child.path + index"
          :is-nest="true"
          :item="child"
          :base-path="resolvePath(child.path)"
          class="nest-menu"
      />
    </el-submenu>
  </div>
</template>

<script>
import path from 'path'
import { isExternal } from '@/utils/validate'
import AppLink from './Link'
import FixiOSBug from './FixiOSBug'

export default {
  name: 'SidebarItem',
  components: { AppLink },
  mixins: [FixiOSBug],
  props: {
    item: {
      type: Object,
      required: true
    },
    isNest: {
      type: Boolean,
      default: false
    },
    basePath: {
      type: String,
      default: ''
    }
  },
  data() {
    this.onlyOneChild = null
    return {
    }
  },
  methods: {
    hasOneShowingChild(children = [], parent) {
      if (!children) {
        children = [];
      }
      const showingChildren = children.filter(item => {
        if (item.hidden) {
          return false
        } else {
          // Temp set(will be used if only has one showing child)
          this.onlyOneChild = item
          return true
        }
      })

      // When there is only one child router, the child router is displayed by default
      if (showingChildren.length === 1) {
        return true
      }

      // Show parent if there are no child router to display
      if (showingChildren.length === 0) {
        this.onlyOneChild = { ... parent, path: '', noShowingChildren: true }
        return true
      }

      return false
    },
    resolvePath(routePath, routeQuery) {
      if (isExternal(routePath)) {
        return routePath
      }
      if (isExternal(this.basePath)) {
        return this.basePath
      }
      if (routeQuery) {
        let query = JSON.parse(routeQuery);
        return { path: path.resolve(this.basePath, routePath), query: query }
      }
      return path.resolve(this.basePath, routePath)
    }
  }
}
</script>
<style scoped>
.item-icon{
  margin-right: 16px;
  font-size: 20px;
}
/deep/.is-active {
  background-color: #aadcf6 !important;
}
/deep/li:hover{
  background-color: #aadcf6 !important;
}
.svg-menu{
  margin-right: 16px;
}
</style>
