<template>
  <div class="pre-image">
    <section class="viewer-box">
      <VueViewer  ref="viewer" class="viewer" :options="options" @inited="inited">
        <img :src="url" class="image" />
      </VueViewer>
    </section>

    <div class="pre-image-bot">
      <el-tooltip class="item" effect="dark" content="左旋" placement="bottom">
        <el-button class="pre-button" icon="el-icon-refresh-left" size="small" @click="handle('rotateLeft')"></el-button>
      </el-tooltip>
      <el-tooltip class="item" effect="dark" content="右旋" placement="bottom">
        <el-button class="pre-button" icon="el-icon-refresh-right" size="mini" @click="handle('rotateRight')"></el-button>
      </el-tooltip>
      <el-tooltip class="item" effect="dark" content="缩小" placement="bottom">
        <el-button class="pre-button" icon="el-icon-minus" size="mini" @click="handle('scalePlus')"></el-button>
      </el-tooltip>
      <el-input class="zoom-proportion" v-model="zoomProportion" />
      <el-tooltip class="item" effect="dark" content="放大" placement="bottom">
        <el-button class="pre-button" icon="el-icon-plus" size="mini" @click="handle('scaleMinus')"></el-button>
      </el-tooltip>
      <el-tooltip class="item" effect="dark" content="全屏" placement="bottom">
        <el-button class="pre-button" icon="el-icon-full-screen" size="mini" @click="handle('fullScreen')"></el-button>
      </el-tooltip>
      <el-tooltip class="item" effect="dark" content="还原" placement="bottom">
        <el-button class="pre-button" icon="el-icon-rank" size="mini" @click="handle('resetZoom')"></el-button>
      </el-tooltip>
    </div>

  </div>
</template>

<script>
import 'viewerjs/dist/viewer.css'
import {component as VueViewer} from 'v-viewer'
export default {
  name: 'previewImage',
  components: { VueViewer },
  props: {
    url:{
      type: String,
      default: undefined
    }
  },
  data() {
    return {
      zoomProportion: '', // 将zoomProportion放入data中
      viewer: null,
      options: {
        fullscreen: false, // 是否全屏模式
        inline: true, // 是否行内
        toolbar: false, // 是否显示工具栏
        navbar: false, // 是否显示导航栏
        rotatable: true, // 是否能够缩放
        backdrop: true, // 是否显示遮罩背景
        button: true, // 是否显示右上角全屏按钮
        title: false, // 是否显示标题
        zoom: this.handleZoom
      }
    }
  },
  mounted() {

  },
  methods: {
    handleZoom(e) {
      this.zoomProportion = `${Number.parseInt(String(e.detail.ratio * 100))}%`;
    },

    /*初始化*/
    inited(viewer) {
      this.viewer = viewer
    },

    handle(value) {
      if (!this.viewer) return
      if (value === 'rotateLeft') {   //左旋
        this.viewer.rotate(-90)
      }
      if (value === 'rotateRight') {  //右旋
        this.viewer.rotate(90)
      }
      if (value === 'scalePlus') {   //缩小
        this.viewer.zoom(0.1)
      }
      if (value === 'scaleMinus') {  //放大
        this.viewer.zoom(-0.1)
      }
      if (value === 'fullScreen') {  //全屏
        this.viewer.full()
      }
      if (value === 'resetZoom') {  //还原
        this.viewer.reset()
        this.handleZoom()
      }
    },
  }
}
</script>

<style scoped>
/deep/.viewer img {
  display: none;
}
/deep/.viewer-container:not(.viewer-fixed)  {
  background: none;
}
.viewer-box {
  height: calc(100% - 80px);
  margin-bottom: 20px;
  background-color: #fff;
}
.zoom-proportion {
  padding: 0 12px;
  width: 100px;
  height: 40px;
  color: #fff;
  border-radius: 4px;
  display: inline-block;
}
.pre-image {
  width: 100%;
  height: 100%;
  overflow: hidden;
}
.pre-image-bot{
  height: 40px;
  width: 100%;
  padding: 0px;
  margin: 0px;
}
.pre-button{
  color: #cc9115;
  height: 40px;
}
</style>
