const getters = {
  token: state => state.user.token,
  avatar: state => state.user.avatar,
  nickName: state => state.user.nickName,
  loginName: state => state.user.loginName,
  visitedViews: state => state.tagsView.visitedViews,
  cachedViews: state => state.tagsView.cachedViews,
  sidebar: state => state.app.sidebar,
  size: state => state.app.size,
  device: state => state.app.device,
  menuRouters:state => state.permission.menuRouters,
  sidebarRouters:state => state.permission.sidebarRouters,
}
export default getters
