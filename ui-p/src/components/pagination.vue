<template>
  <div :class="{'hidden':hidden}" class="pagination-container">
    <el-pagination
        :current-page="pageNum"
        :page-size="pageSize"
        :page-sizes="pageSizes"
        :pager-count="pagerCount"
        :layout="layout"
        :background="background"
        :total="total"
        v-bind="$attrs"
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
    />
  </div>
</template>

<script>
import { queryPaginationList } from "@/api/pagination/pagination.js"
import {scrollTo} from "@/utils/scroll-to";

export default {
  name: 'pagination',
  props: {
    pathUrl: {      //路径
      type: String,
      required: true
    },
    page: {  //当前页
      type: Number,
      default: 1
    },
    limit: {  // 每页大小
      type: Number,
      default: 10
    },
    pageSizes: {  //每页个数选项
      type: Array,
      default() {
        return [10, 20, 30, 50]
      }
    },
    pagerCount: {  //当分页达到7就折叠
      type: Number,
      default: 7
    },
    layout: {  //展示样式
      type: String,
      default: 'total, sizes, prev, pager, next, jumper'
    },
    background: {    //是否为按钮添加背景色 --添加
      type: Boolean,
      default: true
    },
    autoScroll: {   //是否滚动 --滚动
      type: Boolean,
      default: true
    },
    hidden: {      //是否隐藏 -默认不隐藏
      type: Boolean,
      default: false
    }
  },
  data() {
    return {
      total: 0,
      pageNum: this.page,  // 将 prop 的 'page' 赋值给 'pageNum'
      pageSize: this.limit  // 将 prop 的 'limit' 赋值给 'pageSize'
    };
  },
  methods: {
    //每页大小改变时出发
    handleSizeChange(val) {
      this.pageNum=1
      this.pageSize = val
      this.$emit("pagination")
      if (this.autoScroll) {
        scrollTo(0, 800)
      }
    },
    //页码改变时触发
    handleCurrentChange(val) {
      this.pageNum=val
      this.$emit("pagination")
      if (this.autoScroll) {
        scrollTo(0, 800)
      }
    },
    queryData(params){
      return queryPaginationList(this.pathUrl, { ...params, pageNum: this.pageNum, pageSize: this.pageSize }).then(res => {
            this.total = res.data.total;
            return res.data.list
          }
      );
    }
  }
}
</script>

<style scoped>
.pagination-container {
  background: #fff;
  padding: 32px 16px;
}
.pagination-container.hidden {
  display: none;
}
.pagination-container {
  margin-top: 0px;
}
</style>
