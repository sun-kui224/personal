<template>
  <el-dialog :visible="visible" append-to-body @close="handleClose" :show-close="false">
    <div class="info-main">
      <div class="info-top">
        <div class="top-text">书籍详情</div>
      </div>
      <div class="info-center">
        <img class="top-img" :src="handleFile(obj.coverPhoto)"/>
        <div class="center-div">
          <div class="center-top">
            <div class="web-name">
              {{obj.bookName}}
            </div>
            <div class="web-label">
              <div class="tag-label" v-for="(item,index) in obj.labelList">{{item.labelName}}</div>
            </div>
          </div>
          <div class="center-cen">字数：{{obj.wordNumber}}</div>
          <div class="center-bottom">{{obj.bookDescribe}}</div>
        </div>
      </div>
      <div class="info-bottom">
        <div>
          <span>作者：</span>
          <span>{{obj.bookAuthor}}</span>
        </div>
        <div>
          <span>价格：</span>
          <span>{{obj.bookPrice}}</span>
        </div>
        <div>
          <span>是否购买：</span>
          <span>{{getBuyLabel(obj.isBuy)}}</span>
        </div>
        <div>
          <span>出版年限：</span>
          <span style="font-weight: 400">{{obj.publicationPeriod}}</span>
        </div>
        <div class="info-bottom-top">
          <span>在线地址：</span>
          <i class="el-icon-link" style="margin-right: 4px"></i>
          <a :href="obj.onlineAddress" target="_blank" class="a-class" @click="handleLinkClick($event)">{{obj.onlineAddress}}</a>
        </div>
      </div>
    </div>
  </el-dialog>
</template>
<script>
export default {
  name: 'bookInfo',
  props: {
    visible: {
      type: Boolean,
      required: true
    },
    obj: {
      type: Object,
      required: true
    }
  },
  data() {
    return {

    }
  },
  methods: {
    /*点击事件*/
    handleLinkClick(event) {
      if (this.obj.onlineAddress === '/') {
        event.preventDefault(); // 阻止默认的跳转行为
        this.$message.warning('链接无效，无法跳转！');
      }
    },

    getBuyLabel(value){
        if(value === 0){
          return "未买"
        }else{
          return "已买"
        }
    },

    /*关闭弹窗*/
    handleClose(){
      this.$emit('close');
    },
  }
}
</script>
<style scoped>
/deep/.el-dialog__body {
  padding: 0px;
}
/deep/.el-dialog__header {
  padding: 0px;
}

.info-main{
  background: #fff;
  border-radius: 16px;
  color: #252933;
  display: flex;
  flex-direction: column;
  font-weight: 500;
  margin: 0 40px;
  max-height: 560px;
  min-height: 320px;
  padding: 20px 24px 40px 24px;
  position: relative;
  width: 800px;
  z-index: 1;
}

.info-top{
  display: flex;
  justify-content: center;
  margin-bottom: 24px;
}
.top-text{
  font-size: 20px;
  font-weight: 500;
  line-height: 28px;
}
.top-img{
  border: 0.5px solid #c2c8d1;
  padding: 4px;
  border-radius: 12px;
  height: 80px;
  margin-right: 16px;
  width: 80px;
}
.info-center{
  background: #f7f8fa;
  border: 1px solid rgba(194,200,209,.6);
  border-radius: 16px;
  display: flex;
  margin-bottom: 20px;
  max-width: 752px;
  padding: 20px;
  position: relative;
}
.center-top{
  align-items: center;
  display: flex;
  margin-bottom: 6px;
}
.web-label{
  display: flex;
}
.tag-label{
  align-items: center;
  border: .5px solid #c2c8d1;
  border-radius: 50px;
  color: #8a919f;
  display: flex;
  font-size: 12px;
  font-weight: 400;
  height: 24px;
  justify-content: center;
  line-height: 12px;
  margin-right: 6px;
  padding: 6px 8px;
}
.center-cen{
  color: #8a919f;
  font-size: 14px;
  font-weight: 400;
  line-height: 22px;
  margin-bottom: 6px;
}
.center-bottom{
  color: #515767;
  font-size: 14px;
  font-weight: 400;
  line-height: 22px;
}
.web-name{
  color: #252933;
  font-size: 18px;
  font-weight: 500;
  line-height: 26px;
  margin-right: 12px;
}
.info-bottom{
  display: flex;
  flex-wrap: wrap; /* 允许内容换行 */
  flex: 1 1;
  max-width: 752px;
  overflow-x: hidden;
  overflow-y: auto;
  text-overflow: ellipsis;
  color: #515767;
}
.info-bottom >div { /* 直接作用于每个div */
  width: 50%; /* 每个div占据50%宽度，实现两个div一排 */
  display: flex; /* 将内部的span和其内容变为Flex布局 */
  font-size: 14px;
  font-weight: 500;
  line-height: 22px;
  margin-bottom: 12px;
}
.info-bottom-top{
  align-items: center;
  color: #515767;
  display: flex;
  font-size: 14px;
  font-weight: 500;
  line-height: 22px;
  margin-bottom: 12px;
  white-space: nowrap;
}
.a-class{
  color: #252933;
  cursor: pointer;
  font-weight: 400;
  max-height: 664px;
  overflow: hidden;
  text-overflow: ellipsis;
}
</style>
