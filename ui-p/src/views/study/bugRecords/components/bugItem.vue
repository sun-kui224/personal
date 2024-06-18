<template>
  <div class="content-main">
    <a :title="obj.recordsName" class="title-row">{{obj.recordsName}}</a>
    <div class="abstract">
      <a class="abstract-link">
        {{ convertToPlainText(obj.problemDescription) }}
      </a>
    </div>
    <div class="entry-footer">
      <div class="entry-footer-date">
        {{obj.appearDate}}
      </div>
      <div class="entry-footer-tags">
        <template v-for="item in obj.labelList">
          <a class="footer-tag">{{item.labelName}}</a>
        </template>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  name: 'bugItem',
  props: {
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
    convertToPlainText(html){
      // 使用DOMParser来解析HTML字符串并提取文本内容
      const parser = new DOMParser();
      const doc = parser.parseFromString(html, 'text/html');
      return doc.body.textContent || '';
    }
  },
}
</script>
<style scoped>
.content-main {
  border: 1px solid rgba(194, 200, 209, .6);
  margin-bottom: 20px;
  margin-right: 20px;
  border-radius: 16px;
  padding: 20px;
  display: block;
  width: 750px;
  height: 120px;
}
.content-main:hover {
  transform: translateY(-5px); /* 鼠标悬停时向上移动 */
  box-shadow: 0 8px 12px rgb(89, 185, 238); /* 鼠标悬停时加深阴影 */
  cursor: pointer; /* 设置鼠标指针为手指形状 */
}
.title-row{
  font-weight: 600;
  font-size: 16px;
  line-height: 24px;
  color: #252933;
  width: 100%;
  display: -webkit-box;
  overflow: hidden;
  text-overflow: ellipsis;
  -webkit-box-orient: vertical;
  -webkit-line-clamp: 1;
  text-decoration: none;
}
.abstract{
  margin-bottom: 4px;
  font-weight: 400;
}
.abstract-link {
  color: #8a919f;
  font-size: 13px;
  line-height: 22px;
  display: -webkit-box;
  overflow: hidden;
  text-overflow: ellipsis;
  -webkit-box-orient: vertical;
  -webkit-line-clamp: 1;
}
.entry-footer-tags {
  display: flex;
  flex-direction: row;
  align-items: center;
}
.entry-footer-date{
  color: #8a919f;
  font-size: 13px;
  line-height: 22px;
  display: -webkit-box;
  overflow: hidden;
  text-overflow: ellipsis;
  -webkit-box-orient: vertical;
  -webkit-line-clamp: 1;
}
.entry-footer{
  margin-top: 10px;
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: space-between;
  width: 100%;
  flex-wrap: wrap;
}
.footer-tag {
  background-color: #f2f3f5;
  padding: 0 6px;
  border-radius: 2px;
  max-width: 120px;
  box-sizing: border-box;
  margin-left: 6px;
  color: #8a919f;
  text-overflow: ellipsis;
  overflow: hidden;
  white-space: nowrap;
  min-height: 18px;
  line-height: 18px;
}
</style>
