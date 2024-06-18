<template>
  <div class="bot-item">
    <div class="bot-card">
      <div class="bot-header">
        <div style="overflow: hidden">
          <div class="bot-title">
            {{util.utilName}}
          </div>
          <div class="bot-author">
            {{util.createDate}}
          </div>
          <div class="bot-desc">
            {{util.context}}
          </div>
        </div>
      </div>
      <div class="bot-footer">
        <div class="bot-index">
          <el-button icon="el-icon-search" type="primary" size="mini" plain>详情</el-button>
          <el-button type="primary" icon="el-icon-edit" size="mini" plain>修改</el-button>
          <el-button type="danger" icon="el-icon-delete" size="mini" plain>删除</el-button>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  name: "utilItem",
  props:{
    util: {
       type: Object,
       required: true
    }
  },
  data() {
    return {

    };
  },
  mounted() {

  },
  methods: {

  }
};
</script>
<style scoped>
.bot-item{
  padding: 0 16px 16px 0;
  width: calc(33.33333% - 16px);
  cursor: pointer;
}
.bot-card {
  padding: 20px;
  border-radius: 4px;
  border: 1px solid #eaedf1;
  background: #fff;
  box-shadow: 0 2px 6px 0 rgba(0,0,0,.05);
}
.bot-card:hover{
  transform: translateY(-5px); /* 鼠标悬停时向上移动 */
  box-shadow: 0 8px 12px rgb(89, 185, 238); /* 鼠标悬停时加深阴影 */
  cursor: pointer; /* 设置鼠标指针为手指形状 */
}
.bot-card .bot-header{
  display: flex;
  height: 136px;
}
.bot-card .bot-footer{
  display: flex;
  justify-content: space-between;
  border-top: 1px solid #eaedf1;
  padding-top: 12px;
}
.bot-index{
  display: flex;
  align-items: center;
  color: #8a919f;
  line-height: 20px;
}
.bot-card .bot-header .bot-title{
  overflow: hidden;
  margin-bottom: 8px;
  text-overflow: ellipsis;
  white-space: nowrap;
  color: #252933;
  font-size: 18px;
  line-height: 22px;
}
.bot-card .bot-header .bot-author {
  margin-bottom: 8px;
  color: #8A919F;
}
.bot-card .bot-header .bot-desc{
  color: #515767;
  font-size: 13px;
  line-height: 22px;
  display: -webkit-box;
  overflow: hidden;
  white-space: normal;
  text-overflow: ellipsis;
  word-wrap: break-word;
  -webkit-line-clamp: 3;
  -webkit-box-orient: vertical;
}
</style>