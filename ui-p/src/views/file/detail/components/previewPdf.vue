<template>
  <div class="pre-pdf">
    <section class="viewer-box">
      <vue-office-pdf
          :src="url"
          style="height: 100%"
          @rendered="renderedHandler"
          @error="errorHandler"
          :options="options"
      />
    </section>

    <div class="pre-image-bot">
<!--      <el-tooltip class="item" effect="dark" content="打印" placement="bottom">-->
<!--        <el-button class="pre-button" icon="el-icon-right" size="small" @click="handle('print')"></el-button>-->
<!--      </el-tooltip>-->
    </div>
  </div>
</template>

<script>
import VueOfficePdf from '@vue-office/pdf'
export default {
  name: 'previewPdf',
  components: {VueOfficePdf},
  props: {
    url:{
      type: String,
      default: undefined
    }
  },
  data() {
    return {
      options:{

      }
    }
  },
  created(){
  },
  methods: {

    /*渲染成功回调*/
    renderedHandler() {
    },

    /*渲染失败回调*/
    errorHandler() {
    },

    handle(value){
      if (value === 'print') {   //打印
        this.$modal.error("目前还未加入此功能")
      }
    }
  }
}
</script>

<style scoped>
.pre-pdf {
  width: 100%;
  height: 100%;
  overflow: hidden;
}
.viewer-box {
  height: calc(100% - 80px);
  margin-bottom: 20px;
  background-color: #fff;
  padding: 18px;
  overflow: hidden;
}
.pre-image-bot{
  height: 40px;
  width: 100%;
  padding: 0px;
  margin: 0px;

}
/* 清除pdf边框 */
/deep/.vue-office-pdf-wrapper {
  padding-top: 0 !important;
  background-color: transparent !important;
}
</style>
