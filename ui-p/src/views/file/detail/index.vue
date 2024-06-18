<template>
  <div class="file-detail">
    <el-row align="middle" class="detail-manage" style="height: 100%" :gutter="20">
      <el-col :span="19" style="height: 100%">
        <div class="datail-left">
          <!-- 图片 -->
          <template v-if="fileObj.type === 2">
            <preview-image :url="filePath+'/'+fileObj.fileUrl"/>
          </template>
          <template v-else-if="fileObj.fileExt === 'txt'">
            <previewTxt :url="filePath+'/'+fileObj.fileUrl"/>
          </template>
          <template v-else-if="fileObj.fileExt === 'pdf'">
            <previewPdf :url="filePath+'/'+fileObj.fileUrl"/>
          </template>
          <template v-else-if="fileObj.fileExt === 'xlsx'">
            <previewXlsx :url="filePath+'/'+fileObj.fileUrl"/>
          </template>
          <template v-else-if="fileObj.fileExt === 'docx'">
            <previewDocx :url="filePath+'/'+fileObj.fileUrl"/>
          </template>
          <template v-else>
            <previewOther />
          </template>
        </div>
      </el-col>
      <el-col :span="5">
        <div class="datail-right">
          <el-button type="primary" icon="el-icon-back" size="mini" @click="returnUpLevel()">返回上一级</el-button>
          <el-descriptions title="文件详情"  :column="1" style="margin-top: 20px" >
            <el-descriptions-item label="名称">{{fileObj.fileName}}</el-descriptions-item>
            <el-descriptions-item label="类型">{{getFileLabel()}}</el-descriptions-item>
            <el-descriptions-item label="路径">{{fileObj.fileUrl}}</el-descriptions-item>
            <el-descriptions-item label="文件大小">{{fileObj.size}}</el-descriptions-item>
            <el-descriptions-item label="创建人">{{fileObj.createBy}}</el-descriptions-item>
            <el-descriptions-item label="创建时间">{{parseTime(fileObj.createDate)}}</el-descriptions-item>
            <el-descriptions-item label="标签">
              <el-tag type="info" size="mini">vue</el-tag><el-tag type="info" size="mini">mysql</el-tag>
              <el-tag type="info" size="mini">游戏</el-tag>
            </el-descriptions-item>
            <el-descriptions-item label="描述">{{fileObj.context}}</el-descriptions-item>
          </el-descriptions>
          <div style="text-align: right">
            <el-tooltip class="item" effect="dark" content="下载" placement="bottom">
              <el-button icon="el-icon-download" circle size="mini" @click="downloadFile()"></el-button>
            </el-tooltip>
            <el-tooltip class="item" effect="dark" content="删除" placement="bottom">
              <el-button icon="el-icon-delete" circle size="mini" @click="deleteFile()"></el-button>
            </el-tooltip>
          </div>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import previewImage from "./components/previewImage.vue";
import previewOther from "./components/previewOther.vue";
import previewTxt from "./components/previewTxt.vue";
import previewPdf from "./components/previewPdf.vue";
import previewXlsx from "./components/previewXlsx.vue";
import previewDocx from "./components/previewDocx.vue";
import {deleteFile, queryFileInfo} from '@/api/file/index'
import {downloadFile} from "@/api/common";
export default {
  name: 'detail',
  components: { previewImage , previewOther , previewTxt , previewPdf , previewXlsx , previewDocx},
  data() {
    return {
      id: undefined,         //文件id
      filePath: process.env.VUE_APP_BASE_API,  //系统访问地址
      //文件对象
      fileObj: {
        fileName: undefined,
        fileUrl: undefined,
        context: undefined,
        createBy: undefined,
        createDate: undefined,
        size: undefined,
        type: undefined,
        fileExt: undefined,
      },
      typeList:[
        {value: 1,label: '全部'},
        {value: 2,label: '图片'},
        {value: 3,label: '文档'},
        {value: 4,label: '视频'},
        {value: 5,label: '音频'},
        {value: 6,label: '其他'},
      ],
    }
  },
  created() {
    this.id = this.$route.query.id
    this.queryInfo()
  },
  methods: {

    /*查询详情*/
    queryInfo(){
       queryFileInfo(this.id).then((res)=>{
         this.fileObj = res.data
       })
    },

    /*返回上一级*/
    returnUpLevel(){
      this.$router.go(-1)
    },

    /*下载*/
    downloadFile(){
      let params = {fileUrl: this.fileObj.fileUrl}
      downloadFile(params).then((res)=>{
        this.$modal.success("下载成功");
      }).catch((error) => {
        this.$modal.error("下载文件时出错："+error);
      });
    },

    /*删除*/
    deleteFile(){
      this.$modal.confirm('是否确认删除选中的['+this.fileObj.fileName+']此文件？').then(function() {
        return deleteFile(this.id);
      }.bind(this)).then(() => {
        this.$modal.success("删除成功");
        this.returnUpLevel()
      });
    },

    /*获取类型名称*/
    getFileLabel(){
      for(let item of this.typeList){
        if(item.value === this.fileObj.type){
          return item.label
        }
      }
      return "未知"
    },
  }
}
</script>

<style scoped>
.file-detail{
  width: 100%;
  height: 100%;
}
.detail-manage{
  flex: 1;
  padding: 14px;
  overflow: hidden;
  width: 100%;
  height: 100%;
}
.datail-left{
  height: 100%;
  width: 100%;
  background-color: #ffffff;
  margin-left: 10px;
  padding: 14px;
}
.datail-right{
  width: 100%;
  background-color: #ffffff;
  padding: 18px 20px !important;
}
/deep/.el-descriptions-item__label:not(.is-bordered-label) {
  margin-right: 10px;
  color: #909399;
}
/deep/.el-descriptions-item__container .el-descriptions-item__content {
  flex: 1;
  color: #121314;
}
</style>
