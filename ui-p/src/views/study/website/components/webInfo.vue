<template>
  <el-dialog :visible="visible" append-to-body @close="handleClose" :show-close="false">
    <div class="info-main">
       <div class="info-top">
         <div class="top-text">网址详情</div>
       </div>
       <div class="info-center">
         <img class="top-img" :src="handleFile(obj.picture)"/>
         <div class="center-div">
           <div class="center-top">
             <div class="web-name">
               {{obj.websiteName}}
             </div>
             <div class="web-label">
               <div class="tag-label" v-for="(item,index) in obj.labelList">{{item.labelName}}</div>
             </div>
           </div>
           <div class="center-cen">所属组织：{{obj.companyName}}</div>
           <div class="center-bottom">{{obj.websiteDescribe}}</div>
         </div>
       </div>
       <div class="info-bottom">
         <div class="info-bottom-top">
           <span>网址地址：</span>
           <i class="el-icon-link" style="margin-right: 4px"></i>
           <a :href="obj.websiteUrl" target="_blank" class="a-class" @click="handleLinkClick($event)">{{obj.websiteUrl}}</a>
         </div>
         <div class="info-bottom-center">
           <span>创建时间：</span>
           <span style="font-weight: 400">{{obj.createDate}}</span>
         </div>
         <div class="info-bottom-bottom">
           <span>所属公司：</span>
           <span>{{obj.companyName}}</span>
         </div>
       </div>
    </div>
  </el-dialog>
</template>

<script>

export default {
  name: 'webInfo',
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

    handleClose(){
      this.$emit("close")
    }
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
.web-name{
  color: #252933;
  font-size: 18px;
  font-weight: 500;
  line-height: 26px;
  margin-right: 12px;
}
.web-label{
  display: flex;
}
.center-cen{
  color: #8a919f;
  font-size: 14px;
  font-weight: 400;
  line-height: 22px;
  margin-bottom: 6px;
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
.center-bottom{
  color: #515767;
  font-size: 14px;
  font-weight: 400;
  line-height: 22px;
}
.info-bottom{
  display: flex;
  flex: 1 1;
  flex-direction: column;
  max-width: 752px;
  overflow-x: hidden;
  overflow-y: auto;
  text-overflow: ellipsis;
}
.info-bottom-center{
  align-items: center;
  color: #515767;
  display: flex;
  font-size: 14px;
  font-weight: 500;
  line-height: 22px;
  margin-bottom: 12px;
  white-space: nowrap;
}
.info-bottom-bottom{
  align-items: center;
  color: #515767;
  display: flex;
  font-size: 14px;
  font-weight: 500;
  line-height: 22px;
  margin-bottom: 12px;
  white-space: nowrap;
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
