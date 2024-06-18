<template>
  <div class="item-main" v-if="obj">
    <el-tooltip v-if="shouldShowTooltip" class="item" effect="dark" :content="obj.labelName" placement="top-start">
      <span>{{ truncatedLabel }}</span>
    </el-tooltip>
    <div v-else>{{ truncatedLabel }}</div>
    <div class="truncate-text">{{ obj.resourceNumber }}</div>
  </div>
</template>

<script>
export default {
  name: 'labelItem',
  props: {
    obj: {
      type: Object,
      default() {
        return {}
      }
    }
  },
  data() {
    return {
      charWidths: {
        'en': 10,
        'zh': 20
      }
    }
  },
  computed: {
    shouldShowTooltip() {
      return this.truncatedLabel !== this.obj.labelName;
    },
    truncatedLabel() {
      return  this.calculateWidth(this.obj.labelName)
    }
  },
  mounted() {

  },
  methods: {
    /*计算字符宽度*/
    calculateWidth(str) {
      let totalWidth = 0;
      let lastIndex = 0;
      for (let i = 0; i < str.length; i++) {
        const char = str[i];
        if (/[\u4e00-\u9fa5]/.test(char)) {
          totalWidth += this.charWidths['zh'];
        } else {
          totalWidth += this.charWidths['en'];
        }
        if (totalWidth > 100) {
          lastIndex = i - 1
          break
        }
      }
      if (lastIndex === 0) {
        return str;
      }
      return str.slice(0, lastIndex + 1) + "...";
    }
  }
}
</script>
<style scoped>
.item-main {
  display: inline-block;
  width: 120px;
  height: 80px;
  border: 1px solid #d8dce5;
  font-size: 15px;
  padding: 10px;
  overflow: hidden; /* 隐藏超出的内容 */
  text-overflow: ellipsis; /* 显示省略号 */
  white-space: nowrap; /* 不换行 */
}
.item-main:hover {
  transform: translateY(-5px); /* 鼠标悬停时向上移动 */
  box-shadow: 0 8px 12px rgb(89, 185, 238); /* 鼠标悬停时加深阴影 */
  cursor: pointer; /* 设置鼠标指针为手指形状 */
}
.truncate-text{
  font-size: 15px;
  color: #d8dce5;
  margin-top: 20px;
}
</style>
