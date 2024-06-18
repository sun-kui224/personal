<template>
  <div class="fileMain">
    <el-row align="middle" class="file-manage" style="margin: 0px;height: 100%" :gutter="20">
      <el-col :span="3">
        <div class="leftMain">
          <el-card class="box-card">
            <div slot="header" class="clearfix">
              <span>文件管理</span>
              <el-switch v-model="showPercent"  style="float: right; padding: 3px 0" />
            </div>
            <el-menu class="el-menu-vertical-demo" @select="handleSelect">
              <el-menu-item v-for="item in typeList" :index="item.value">
                <template  style="height: 40px;display: flex; align-items: center;" >
                  <svg-icon :svgName="item.icon" :size="24" class="svg-menu"></svg-icon>
                  <span slot="title">{{ item.label }}</span>
                </template>
              </el-menu-item>
            </el-menu>
          </el-card>

          <section v-show="showPercent" class="percent">
            <el-row justify="space-between" style="margin-bottom: 10px">
              <el-progress type="circle" :percentage="25" color="#00B42A" :width="70"></el-progress>
              <el-statistic title="剩余容量" :value="512" :value-style="{color: '#5856D6'}" style="width: 50%;display: inline-block;float: right;">
                <template slot="prefix">
                  <i class="el-icon-cloudy" style="color:#5856D6"/>
                </template>
                <template slot="suffix">
                  G
                </template>
              </el-statistic>
            </el-row>

            <el-row class="rowBot">
              <template v-for="item in filePercentList">
                <el-progress type="line" :percentage="item.value" :color="item.color" :width="40"></el-progress>
                <span>{{ item.label }}</span>
              </template>
            </el-row>
          </section>
        </div>
      </el-col>
      <el-col :span="21" style="height: 100%">
        <fileRight ref="fil1"></fileRight>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import fileRight from "./fileRight.vue";
export default {
  name: 'file',
  components: { fileRight },
  data() {
    return {
      showPercent: false,
      typeList:[
        {value: '1',label: '全部',icon: 'menu-file'},
        {value: '2',label: '图片',icon: 'file-image'},
        {value: '3',label: '文档',icon: 'file-txt'},
        {value: '4',label: '视频',icon: 'file-video'},
        {value: '5',label: '音频',icon: 'file-music'},
        {value: '6',label: '其他',icon: 'file-other'},
      ],
      filePercentList: [
        {value: 70,label: '图片', color: '#4F6BF6'},
        {value: 30,label: '文档', color: '#FFA000'},
        {value: 40,label: '视频', color: '#A15FDE'},
        {value: 20,label: '音频', color: '#FD6112'},
        {value: 50,label: '其他', color: '#52B852'}
      ]
    }
  },
  mounted() {
    this.handleSelect()
  },
  methods: {
    /*触发子组件的搜索*/
    handleSelect(key, keyPath) {
      this.$refs.fil1.queryFileList(key);
    }
  }
}
</script>
<style scoped>
.fileMain{
  width: 100%;
  height: 100%;
}
.file-manage {
  flex: 1;
  padding: 14px;
  overflow: hidden;
  width: 100%;
  height: 100%;
}
.percent {
  margin-top: 10px;
  padding: 14px 12px;
  box-sizing: border-box;
  background-color: #ffffff;
}
.rowBot{
  font-size: 14px;
}

.svg-menu{
  margin-right: 16px;
}
/deep/.el-menu {
  border-right: 0px;
}
/deep/.el-progress__text {
  font-size: 15px !important; /* 将16px替换为你希望的字体大小 */
}
</style>
