<template>
   <a class="book-main">
     <div style="display: flex">
        <div class="book-top">
          <img :src="handleFile(obj.coverPhoto)" style="display: block;height: 100%;width: 100%;" loading="lazy">
        </div>
        <div class="book-center">
          <div class="book-name">
            <span>{{obj.bookName}}</span>
          </div>
          <span class="book-desc">{{obj.bookDescribe}}</span>
          <div class="book-author"><i class="el-icon-user" style="margin-right: 10px"/>{{obj.bookAuthor}}</div>
          <div class="book-price">
            <span style="font-size: 12px;transform: translateY(2px);">￥</span>{{obj.bookPrice}}
          </div>
          <div class="item-cardBot">
            <template v-for="(lab, index) in obj.labelList">
              <div class="item-tag" :key="index">{{lab.labelName}}</div>
            </template>
          </div>
        </div>
     </div>
   </a>
</template>
<script>
export default {
  name: 'bookItem',
  props: {
    obj: {
      type: Object,
      required: true
    }
  },
  mounted() {
  },
  data() {
    return {

    }
  }
}
</script>
<style scoped>
.book-main{
  display: block;
  width: 525px;
  height: 190px;
  background: #fff;
  border: 1px solid rgba(194,200,209,.6);
  border-radius: 16px;
  margin-bottom: 20px;
  margin-right: 20px;
  padding: 20px;
}
.book-main:hover {
  transform: translateY(-5px); /* 鼠标悬停时向上移动 */
  box-shadow: 0 8px 12px rgb(89, 185, 238); /* 鼠标悬停时加深阴影 */
  cursor: pointer; /* 设置鼠标指针为手指形状 */
}

.book-top{
  width: 103px;
  height: 147px;
  flex: 0 0 auto;
  margin-right: 16px;
  position: relative;
  overflow: hidden;
  background-color: rgba(228,230,235,0.5);
  border-radius: 4px;
}
.book-center{
  position: relative;
  flex: 1;
  overflow: hidden;
}
.book-name{
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
  font-size: 16px;
  line-height: 28px;
  color: #252933;
}
.book-desc{
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
  display: block;
  font-size: 12px;
  line-height: 22px;
  color: #515767;
  margin-top: 4px;
}

.book-author{
  line-height: 22px;
  font-size: 12px;
  margin-top: 8px;
  display: flex;
  align-items: center;
}

.book-price{
  margin-top: 8px;
  line-height: 22px;
  font-size: 15px;
  font-weight: 500;
}
.item-cardBot{
  display: flex;
  margin-top: 8px;
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
