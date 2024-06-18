<template>
  <div class="pre-docx">
    <section class="viewer-box">
      <div class="box" v-loading="loading" element-loading-text="正在加载中，请稍候！" ref="docx"></div>
    </section>

    <div class="pre-image-bot">
<!--      <el-tooltip class="item" effect="dark" content="缩小" placement="bottom">-->
<!--        <el-button class="pre-button" icon="el-icon-minus" size="mini" @click="handle('removeSize')"></el-button>-->
<!--      </el-tooltip>-->
<!--      <el-tooltip class="item" effect="dark" content="放大" placement="bottom">-->
<!--        <el-button class="pre-button" icon="el-icon-plus" size="mini" @click="handle('addSize')"></el-button>-->
<!--      </el-tooltip>-->
<!--      <el-tooltip class="item" effect="dark" content="还原" placement="bottom">-->
<!--        <el-button class="pre-button" icon="el-icon-rank" size="mini" @click="handle('resetSize')"></el-button>-->
<!--      </el-tooltip>-->
<!--      <el-tooltip class="item" effect="dark" content="复制" placement="bottom">-->
<!--        <el-button class="pre-button" icon="el-icon-document-copy" size="mini" @click="handle('copyContext')"></el-button>-->
<!--      </el-tooltip>-->
    </div>
  </div>
</template>

<script>
import jsPreviewDocx from "@js-preview/docx";
import '@js-preview/docx/lib/index.css'
export default {
  name: 'previewDocx',
  props: {
    url:{
      type: String,
      default: undefined
    }
  },
  data() {
    return {
      loading: true,
      previewer: undefined
    }
  },
  mounted(){
    this.previewer = jsPreviewDocx.init(this.$refs.docx)
    this.handlePreview()
  },
  methods: {
    handlePreview() {
      this.loading = true
      this.previewer.preview(this.url).then(res=>{
        this.loading = false
      }).catch(e => {
        if(e.status === 404) {
          this.$modal.error('文件不存在')
        }
      })
    }
  }
}
</script>

<style scoped>
.pre-docx {
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

.pre-image-bot{
  height: 40px;
  width: 100%;
  padding: 0px;
  margin: 0px;
}
.box {
  width: 100%;
  height: 100%;
  ::v-deep .vue-office-docx-main{
    height: 100%;
    .docx-wrapper{
      width: 100%;
      overflow: auto;
      padding-bottom: 30px;
    }
  }
}
</style>
