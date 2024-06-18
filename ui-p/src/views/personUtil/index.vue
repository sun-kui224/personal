<template>
  <el-container>
    <el-header height="80px">
      <el-form :model="queryParams" class="demo-form-inline" ref="queryForm" size="small" :inline="true"  label-width="68px" style="text-align: left">
        <el-row>
          <el-col :span="6">
            <el-form-item label="工具名称" prop="utilName">
              <el-input v-model="queryParams.utilName" placeholder="请输入工具名称" clearable />
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-button type="primary" icon="el-icon-search" size="mini" @click="queryList">搜索</el-button>
            <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
            <el-button icon="el-icon-plus" size="mini" @click="addUtils">新增</el-button>
          </el-col>
        </el-row>
      </el-form>
    </el-header>
    <el-main>
      <div class="bots-wrapper" v-if="!loading">
        <util-item v-for="(item, index) in utilList" :key="index" :util="item" />
      </div>
    </el-main>
  </el-container>
</template>

<script>
import utilItem from "./components/utilItem.vue"
import { getUtilList, deleteUtil } from "@/api/personUtil"
export default {
  name: 'personUtil',
  components: {utilItem},
  data() {
    return {
      loading: false,
      queryParams: {
        utilName: undefined
      },
      utilList: [
        {
          id: 1,
          utilName: "代码生产器版本1号",
          context: "从 3.1.500 版本开始，本项目使用 KonaJDK 编译发布。\n" +
              "通过 Maven 获取安装是使用 JAVA SDK 的推荐方法，Maven 是 JAVA 的依赖管理工具，支持您项目所需的依赖项，并将其安装到项目中。您可以参考 Maven 官网获取更详细的信息。以下是使用 Maven 安装的步骤：\n" +
              "1. 请访问 Maven 官网 ，下载对应系统 Maven 安装包进行安装。\n" +
              "2. 为您的项目添加 Maven 依赖项，只需在 Maven pom.xml 文件中添加以下依赖项。\n",
          createDate: '2022-04-22'
        },
        {
          id: 1,
          utilName: "代码生产器版本2号",
          context: "此工具用与后端生产crud简单代码，不支持树子表结构，携带swagger注解。",
          createDate: '2022-04-12'
        }
      ]
    }
  },
  mounted() {
   // this.getUtilList()
  },
  methods: {
    /*查询*/
    queryList(){
      this.loading = true
      getUtilList(this.queryParams).then(res=>{
        this.utilList = res.data
        this.loading = false
      })
    },
    /*重置*/
    resetQuery(){
      this.$refs.queryForm.resetFields()
      this.queryList()
    },
    /*新增*/
    addUtils(){
      this.$modal.confirm('是否确认删除选中的工具['+ item.utilName+']？').then(function() {
        return  deleteUtil(item.id);
      }.bind(this)).then(() => {
        this.$modal.success("删除成功");
        this.queryList()
      });
    }
  }
}
</script>
<style scoped>
.bots-wrapper{
  display: flex;
  flex-wrap: wrap;
  margin-right: -16px;
}
</style>
