<template>
  <div class="pre-xlsx">
    <section class="viewer-box">
      <vue-office-excel
          :src="url"
          style="height: 100%;"
          :options="options"
          @rendered="renderedHandler"
          @error="errorHandler"
      />
    </section>

    <div class="pre-image-bot">
      <el-tooltip class="item" effect="dark" content="缩小" placement="bottom">
        <el-button class="pre-button" icon="el-icon-minus" size="mini" @click="handle('removeSize')"></el-button>
      </el-tooltip>
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
import VueOfficeExcel from '@vue-office/excel'
import '@vue-office/excel/lib/index.css'
export default {
  name: 'previewXlsx',
  components: {VueOfficeExcel},
  props: {
    url:{
      type: String,
      default: undefined
    }
  },
  data() {
    return {
      options:{
        showContextmenu: true,
      }
    }
  },
  mounted(){

  },
  methods: {

    /*渲染成功回调*/
    renderedHandler() {
    },

    /*渲染失败回调*/
    errorHandler() {
    },

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
.pre-xlsx {
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
.pre-image-bot{
  height: 40px;
  width: 100%;
  padding: 0px;
  margin: 0px;
}
</style>
