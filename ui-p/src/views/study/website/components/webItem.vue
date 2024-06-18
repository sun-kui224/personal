<template>
  <div class="web-item-main" v-if="obj">
    <div class="item-cardTop">
      <img class="img-class" :src="handleFile(obj.picture)">
      <div class="cardTop-text">
        <div class="text-top">{{obj.websiteName}}</div>
        <div class="text-bot">{{obj.companyName}}</div>
      </div>
    </div>
    <div class="item-descripte">{{obj.websiteDescribe}}</div>
    <div class="item-cardBot">
      <template v-for="(lab, index) in obj.labelList">
        <div class="item-tag" :key="index">{{lab.labelName}}</div>
      </template>
    </div>
  </div>
</template>

<script>
export default {
  name: 'webItem',
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
    }
  },
  computed: {

  },
  mounted() {

  },
  methods: {

  }
}
</script>
<style scoped>
.web-item-main {
  display: block;
  width: 357px;
  height: 190px;
  background: #fff;
  border: 1px solid rgba(194,200,209,.6);
  border-radius: 16px;
  margin-bottom: 20px;
  margin-right: 20px;
  padding: 20px;
}

.web-item-main:hover {
  transform: translateY(-5px); /* 鼠标悬停时向上移动 */
  box-shadow: 0 8px 12px rgb(89, 185, 238); /* 鼠标悬停时加深阴影 */
  cursor: pointer; /* 设置鼠标指针为手指形状 */
}

.item-cardTop{
  display: flex;
  margin-bottom: 16px;
}
.cardTop-text{
  display: flex;
  flex-direction: column;
}
.img-class{
  height: 48px;
  margin-right: 16px;
  object-fit: fill;
  width: 48px;
}
.text-top{
  color: #252933;
  font-size: 16px;
  font-weight: 500;
  margin-bottom: 4px;
  max-width: 253px;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}

.text-bot{
  color: #8a919f;
  font-size: 13px;
  font-weight: 400;
  line-height: 22px;
  max-width: 253px;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}
.item-descripte{
  -webkit-box-orient: vertical;
  -webkit-line-clamp: 2;
  color: #515767;
  display: -webkit-box;
  font-size: 14px;
  font-weight: 400;
  height: 44px;
  line-height: 22px;
  margin-bottom: 16px;
  overflow: hidden;
}

.item-cardBot{
  display: flex;
}
.item-tag{
  align-items: center;
  border: 1px solid #e4e6eb;
  border-radius: 50px;
  color: #8a919f;
  display: flex;
  font-size: 12px;
  font-weight: 400;
  height: 24px;
  margin-right: 6px;
  padding: 6px 8px;
}
</style>
