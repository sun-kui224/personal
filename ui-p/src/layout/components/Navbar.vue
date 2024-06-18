<template>
  <div class="navbar">
    <!-- 收起/打开 -->
    <hamburger id="hamburger-container" :is-active="sidebar.opened" class="hamburger-container" @toggleClick="toggleSideBar" />

    <!-- 顶部导航 -->
    <breadcrumb id="breadcrumb-container" class="breadcrumb-container" />
    <top-nav id="topmenu-container" class="topmenu-container" v-if="topNav"/>

    <div class="userInfo">

      <screenfull style="margin-right: 20px"/>

      <el-avatar :size="40" :src="avatar"></el-avatar>
      <el-dropdown>
        <div class="detail">
          <span>{{ nickName }}</span>
          <i class="el-icon-arrow-down"></i>
        </div>
        <template #dropdown>
          <el-dropdown-menu>
            <el-dropdown-item @click.native="userCenter">个人中心</el-dropdown-item>
            <el-dropdown-item @click.native="setting = true">
              <span>布局设置</span>
            </el-dropdown-item>
            <el-dropdown-item divided @click.native="logout">
              <span>退出登录</span>
            </el-dropdown-item>
          </el-dropdown-menu>
        </template>
      </el-dropdown>
    </div>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
import Breadcrumb from '@/components/Breadcrumb'
import TopNav from '@/components/TopNav'
import Hamburger from '@/components/Hamburger'
import Screenfull from '@/components/Screenfull'

export default {
  components: {
    Breadcrumb,
    TopNav,
    Hamburger,
    Screenfull,
  },
  computed: {
    ...mapGetters([
      'sidebar',
      'avatar',
      'nickName'
    ]),
    setting: {
      get() {
        return this.$store.state.settings.showSettings
      },
      set(val) {
        this.$store.dispatch('settings/changeSetting', {
          key: 'showSettings',
          value: val
        })
      }
    },
    topNav: {
      get() {
        return this.$store.state.settings.topNav
      }
    }
  },
  mounted() {
    this.loadUserInfoFromLocalStorage();
  },
  methods: {
    //加载
    loadUserInfoFromLocalStorage() {
      const userInfoStr = window.localStorage.getItem("userInfo");
      if (userInfoStr) {
        const user = JSON.parse(userInfoStr);
        const avatar = (user.avatar == "" || user.avatar == null) ? require("@/assets/images/profile.jpg") : process.env.VUE_APP_BASE_API + user.avatar;
        this.$store.commit("SET_AVATAR", avatar);
        this.$store.commit("SET_LOGINNAME", user.loginName);
        this.$store.commit("SET_NICKNAME", user.nickName);
      }
    },
    toggleSideBar() {
      this.$store.dispatch('app/toggleSideBar')
    },
    userCenter(){
      this.$router.replace({path: "/user/userCenter"})
    },
    async logout() {
      this.$confirm('确定注销并退出系统吗？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$store.dispatch('LogOut').then(() => {
          location.href = '/index';
        })
      }).catch(() => {});
    }
  }
}
</script>

<style lang="scss" scoped>
.navbar {
  height: 50px;
  overflow: hidden;
  position: relative;
  background: #fff;
  box-shadow: 0 1px 4px rgba(0,21,41,.08);

  .hamburger-container {
    line-height: 46px;
    height: 100%;
    float: left;
    cursor: pointer;
    transition: background .3s;
    -webkit-tap-highlight-color:transparent;

    &:hover {
      background: rgba(0, 0, 0, .025)
    }
  }

  .breadcrumb-container {
    float: left;
  }

  .topmenu-container {
    position: absolute;
    left: 50px;
  }
  .userInfo{
    display: flex;
    flex-direction: row;
    align-items: center;
    justify-content: flex-end;
    height: 100%;
    margin-right: 20px;

    .detail{
      margin-left: 10px;
      white-space: nowrap;
      color: #121314
    }
  }

}
</style>
