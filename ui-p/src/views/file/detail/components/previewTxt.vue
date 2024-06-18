<template>
  <div class="pre-txt">
    <section class="viewer-box">
      <pre class="txt-content" :style="{ fontSize: `${fileSize}px` }">{{ fileContent }}</pre>
    </section>

    <div class="pre-image-bot">
      <el-tooltip class="item" effect="dark" content="缩小" placement="bottom">
        <el-button class="pre-button" icon="el-icon-minus" size="mini" @click="handle('removeSize')"></el-button>
      </el-tooltip>
      <el-input class="zoom-proportion" v-model="fileSize" />
      <el-tooltip class="item" effect="dark" content="放大" placement="bottom">
        <el-button class="pre-button" icon="el-icon-plus" size="mini" @click="handle('addSize')"></el-button>
      </el-tooltip>
      <el-tooltip class="item" effect="dark" content="还原" placement="bottom">
        <el-button class="pre-button" icon="el-icon-rank" size="mini" @click="handle('resetSize')"></el-button>
      </el-tooltip>
      <el-tooltip class="item" effect="dark" content="复制" placement="bottom">
        <el-button class="pre-button" icon="el-icon-document-copy" size="mini" @click="handle('copyContext')"></el-button>
      </el-tooltip>
    </div>
  </div>
</template>

<script>
import axios from 'axios';
export default {
  name: 'previewTxt',
  props: {
    url:{
      type: String,
      default: undefined
    }
  },
  data() {
    return {
      fileContent: undefined ,  //文件内容
      fileSize: 14              //字体大小
    }
  },
  mounted(){
    axios.get(this.url)
        .then(response => {
          this.fileContent = response.data;
        })
        .catch(error => {
          this.fileContent= '文件内容获取失败' + error
        });
  },
  methods: {
    handle(value){
      if (value === 'removeSize') {   //缩小
        this.fileSize = this.fileSize - 2
      }
      if (value === 'addSize') {     //放大
        this.fileSize = this.fileSize + 2
      }
      if (value === 'resetSize') {   //还原
        this.fileSize = 14
      }
      if (value === 'copyContext') {   //复制
        navigator.clipboard.writeText(this.fileContent)
            .then(() => {
              this.$message.success('内容已复制到剪贴板');
            })
            .catch(err => {
              this.$message.error('复制失败，请重试');
            });
      }
    }
  }
}
</script>

<style scoped>
.pre-txt {
  width: 100%;
  height: 100%;
  overflow: hidden;
}
.viewer-box {
  height: calc(100% - 80px);
  margin-bottom: 20px;
  background-color: #fff;
  padding: 18px;
  overflow: auto;
}
.pre-button{
  color: #cc9115;
  height: 40px;
}
.zoom-proportion {
  padding: 0 12px;
  width: 100px;
  height: 40px;
  color: #fff;
  border-radius: 4px;
  display: inline-block;
}
.pre-image-bot{
  height: 40px;
  width: 100%;
  padding: 0px;
  margin: 0px;
}
</style>
