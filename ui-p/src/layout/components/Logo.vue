<template>
  <section class="system-logo">
    <img class="logo" :src="logo" />
    <span v-if="this.sidebar.opened" class="system-name">{{ title }}</span>
  </section>
</template>

<script>
import logoImg from '@/assets/logo/logo.png'
import {mapState} from "vuex";

export default {
  name: 'Logo',
  data(){
    return{
      title: process.env.VUE_APP_TITLE,
      logo: logoImg
    }
  },
  computed: {
    ...mapState({
      sidebar: state => state.app.sidebar,
    }),
  },
}
</script>

<style lang="scss" scoped>
.system-logo {
  height: 56px;
  padding: 0 12px;
  color: #121314;
  font-size: 20px;
  line-height: 1;
  display: flex;
  align-items: center;
  flex-shrink: 0;
  cursor: pointer;
  user-select: none;
  box-sizing: border-box;
  &.collapsed {
    padding: 0;
    display: flex;
    justify-content: center;
    align-items: center;
    .system-name {
      display: none;
    }
  }
  .logo {
    width: 32px;
    height: 32px;
    border-radius: 50%;
    transition: all 0.2s;
    overflow: hidden;
    flex-shrink: 0;
  }
  .system-name {
    padding-left: 10px;
    white-space: nowrap;
    transition: color 0.3s;
    &:hover {
      color: rgb(204, 145, 21) !important;
      cursor: pointer;
    }
  }
}
</style>
