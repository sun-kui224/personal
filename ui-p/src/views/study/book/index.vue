<template>
  <p-layout>
    <!-- 顶部 -->
    <el-input placeholder="请输入书籍名称" v-model="gjzString" style="width: 300px;margin-left: 20px">
      <el-button type="primary" slot="append" icon="el-icon-search" style="background-color: #cc9115;color:#ffffff;border-radius: 0px" @click="queryList()">搜索</el-button>
    </el-input>
    <el-button type="primary" style="margin-left: 20px" @click="openAdd()">新增</el-button>

    <el-divider></el-divider>

    <div class="container">
      <div class="book-container">
        <template v-for="(item, index) in bookList">
          <el-popover
              placement="bottom"
              width="100"
              trigger="hover">
            <div class="file-grid-item" slot="reference"  @click="openInfo(item)">
              <bookItem :obj="item"/>
            </div>

            <el-menu class="el-menu-vertical-demo">
              <el-menu-item index="1" @click="openEdit(item)">
                <template  style="height: 34px;display: flex; align-items: center;" >
                  <svg-icon svgName="menu-edit" class="svg-menu"></svg-icon>
                  <span slot="title">修改</span>
                </template>
              </el-menu-item>
              <el-menu-item index="3" @click="openInfo(item)">
                <template style="height: 34px;display: flex; align-items: center;" >
                  <svg-icon svgName="menu-detail" class="svg-menu"></svg-icon>
                  <span slot="title">详情</span>
                </template>
              </el-menu-item>
              <el-menu-item index="3" @click="openLabel(item)">
                <template style="height: 34px;display: flex; align-items: center;" >
                  <svg-icon svgName="menu-data" class="svg-menu"></svg-icon>
                  <span slot="title">标签</span>
                </template>
              </el-menu-item>
              <el-menu-item index="2" @click="delLabel(item)">
                <template style="height: 34px;display: flex; align-items: center;" >
                  <svg-icon svgName="menu-delete" class="svg-menu"></svg-icon>
                  <span slot="title">删除</span>
                </template>
              </el-menu-item>
            </el-menu>
          </el-popover>
        </template>
      </div>
    </div>

    <!-- 新增 -->
    <addBook v-if="addVisible"
             :visible="addVisible"
             @close="addVisible = false"
             @ok="addOk"/>

    <!-- 修改 -->
    <editBook v-if="editVisible"
             :visible="editVisible"
             :obj="currentObj"
             @close="editVisible = false"
             @ok="editOk"/>

    <!-- 详情 -->
    <bookInfo v-if="infoVisible"
             :visible="infoVisible"
             :obj="currentObj"
             @close="infoVisible = false"/>

    <!-- 标签 -->
    <commonLabel v-if="labelVisible"
                 :visible="labelVisible"
                 :obj="currentObj"
                 @close="labelVisible = false"
                 @ok="commonOk"/>

  </p-layout>
</template>

<script>
import bookItem from "./components/bookItem.vue";
import addBook from "./components/addBook.vue";
import editBook from "./components/editBook.vue";
import bookInfo from "./components/bookInfo.vue";
import commonLabel from "@/components/commonLabel/index.vue";
import {deleteBook , getBookList} from "@/api/book";
export default {
	name: 'book',
  components: {commonLabel, bookItem , addBook , editBook , bookInfo},
	data() {
		return {
      gjzString: undefined ,   //查询关键字
      addVisible: false,       //控制新增弹窗
      editVisible: false,      //控制修改弹窗
      infoVisible: false,      //控制详情弹窗
      labelVisible: false,     //控制标签弹窗
      currentObj: undefined,   //选中的对象
      bookList:[],
		}
	},
  mounted() {
    this.queryList()
  },
  methods: {

    /*搜索*/
    queryList(){
      getBookList({bookName: this.gjzString}).then(res=>{
        this.bookList = res.data;
      })
    },

    /*打开新增弹窗*/
    openAdd(){
      this.addVisible = true
    },

    addOk(){
      this.addVisible = false
      this.queryList()
    },

    /*打开修改弹窗*/
    openEdit(item){
      this.currentObj = item
      this.editVisible = true
    },

    editOk(){
      this.editVisible = false
      this.queryList()
    },

    /*打开详情弹窗*/
    openInfo(item){
      this.currentObj = item
      this.infoVisible = true
    },

    /*打开标签弹窗*/
    openLabel(item){
      this.currentObj = item
      this.labelVisible = true
    },

    commonOk(){
      this.labelVisible = false
      this.queryList()
    },

    /*删除*/
    delLabel(item){
      this.$modal.confirm('是否确认删除选中的书籍['+ item.labelName+']？').then(function() {
        return  deleteBook(item.id);
      }.bind(this)).then(() => {
        this.$modal.success("删除成功");
        this.queryList()
      });
    }
	}
}
</script>
<style scoped>
.container {
  display: flex;
}
.book-container {
  display: flex;
  flex-wrap: wrap;
  justify-self: start;
  margin-bottom: 72px;
  padding-left: 24px;
}
</style>
