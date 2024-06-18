<template>
  <p-layout >
    <!-- 顶部 -->
    <el-input placeholder="请输入记录名称" v-model="gjzString" style="width: 300px;margin-left: 20px">
      <el-button type="primary" slot="append" icon="el-icon-search" style="background-color: #cc9115;color:#ffffff;border-radius: 0px" @click="queryList()">搜索</el-button>
    </el-input>
    <el-button type="primary" style="margin-left: 20px" @click="openAdd()">新增</el-button>

    <el-divider></el-divider>

    <div class="container">
      <div class="bug-container">
        <template v-for="(item, index) in bugList">
          <el-popover
              placement="bottom"
              width="100"
              trigger="hover">
            <div class="bug-grid-item" slot="reference"  @click="openInfo(item)">
              <bugItem :obj="item"/>
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
              <el-menu-item index="3" @click="openBug(item)">
                <template style="height: 34px;display: flex; align-items: center;" >
                  <svg-icon svgName="menu-data" class="svg-menu"></svg-icon>
                  <span slot="title">标签</span>
                </template>
              </el-menu-item>
              <el-menu-item index="2" @click="delBug(item)">
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
    <div style="text-align: right">
      <pagination path-url="/study/records/list" ref="pag1" @pagination="queryList" />
    </div>

    <!-- 标签 -->
    <commonLabel v-if="labelVisible"
                 :visible="labelVisible"
                 :obj="currentObj"
                 @close="labelVisible = false"
                 @ok="commonOk"/>
  </p-layout>
</template>

<script>
import bugItem from "./components/bugItem.vue";
import {deleteBugRecord} from "@/api/bugRecords";
import commonLabel from "@/components/commonLabel/index.vue";

export default {
	name: 'bugRecords',
  components: {commonLabel, bugItem},
	data() {
		return {
      gjzString: undefined ,   //查询关键字
      labelVisible: false,     //控制标签弹窗
      currentObj: undefined,   //选中的对象
      bugList: [],
		}
	},
  mounted() {
    this.queryList();
  },
  methods: {
    /*查询列表*/
    queryList(){
      const params = {}
      this.$refs.pag1.queryData(params).then(res=>{
        this.bugList = res
      })
    },

    /*打开新增弹窗*/
    openAdd(){
      this.$router.push({ path: "operBug"});
    },

    /*打开修改弹窗*/
    openEdit(item){
      this.$router.push({ path: "operBug" , query: {id:item.id}});
    },

    /*详情*/
    openInfo(item){
      this.$router.push({ path: "bugInfo" , query: {id:item.id}});
    },

    /*标签*/
    openBug(item){
      this.currentObj = item
      this.labelVisible = true
    },

    commonOk(){
      this.labelVisible = false
      this.queryList()
    },

    /*删除*/
    delBug(item){
      this.$modal.confirm('是否确认删除选中的记录['+ item.recordsName+']？').then(function() {
        return  deleteBugRecord(item.id);
      }.bind(this)).then(() => {
        this.$modal.success("删除成功");
        this.queryList()
      });
    }
	}
}
</script>
<style scoped>
.container{
  display: flex;
}
.bug-container{
  display: flex;
  flex-wrap: wrap;
  justify-self: start;
  margin-bottom: 72px;
  padding-left: 24px;
}
</style>
