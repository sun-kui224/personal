<template>
  <p-layout>
    <!--顶部表单-->
    <el-form :model="queryParams" class="demo-form-inline" ref="queryForm" size="small" :inline="true" label-width="68px" style="text-align:left;">
      <el-row>
        <el-col :span="6">
          <el-form-item label="标题名称" prop="title">
            <el-input
                v-model="queryParams.title"
                placeholder="请输入标题名称"
                clearable
            />
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="操作类型" prop="operType">
            <el-select v-model="queryParams.operType" placeholder="请选择操作类型" clearable >
              <el-option  v-for="item in typeList"
                  :key="item.code"
                  :label="item.message"
                  :value="item.code"
              />
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="请求方式" prop="requestMethod">
            <el-select v-model="queryParams.requestMethod" placeholder="请选择请求方式" clearable >
              <el-option  v-for="item in requestList" :key="item" :label="item" :value="item"/>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="操作人员名称" prop="operName" label-width="100px">
            <el-input
                v-model="queryParams.operName"
                placeholder="请输入请求方式"
                clearable
            />
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="操作状态" prop="operStatus">
            <el-select v-model="queryParams.operStatus" placeholder="请选择操作状态" clearable >
              <el-option :value="0">成功</el-option>
              <el-option :value="1">失败</el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="操作时间">
            <el-date-picker
                v-model="dateRange"
                style="width: 240px"
                value-format="yyyy-MM-dd HH:mm:ss"
                type="daterange"
                range-separator="-"
                start-placeholder="开始日期"
                end-placeholder="结束日期"
                :default-time="['00:00:00', '23:59:59']"
            ></el-date-picker>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item>
            <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery" style="margin-left: 20px">搜索</el-button>
            <el-button icon="el-icon-refresh" size="mini" @click="resetQuery" style="margin-left: 20px">重置</el-button>
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>

    <el-row :gutter="10" class="mb8" style="margin-top: 20px">
      <el-col :span="1.5">
        <el-button
            type="danger"
            plain
            icon="el-icon-delete"
            size="mini"
            :disabled="multiple"
            @click="handleDelete"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
            type="danger"
            plain
            icon="el-icon-delete"
            size="mini"
            @click="handleClean"
        >清空</el-button>
      </el-col>
    </el-row>

    <el-table v-loading="loading" :data="infoList" row-key="id" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="50" align="center" />
      <el-table-column prop="id" label="日志编号"  width="160"></el-table-column>
      <el-table-column prop="title" label="标题"  width="160"></el-table-column>
      <el-table-column prop="operType" label="操作类型" align="center" width="100">
        <template slot-scope="scope">
          <el-tag :color="typeList[scope.row.operType].color">{{ typeList[scope.row.operType].message }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="requestMethod" label="请求方式" width="160">
        <template slot-scope="scope">
          <el-tag type="success">{{scope.row.requestMethod}}</el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="operName" label="操作人员名称" :show-overflow-tooltip="true" width="160"></el-table-column>
      <el-table-column prop="operIp" label="主机地址" :show-overflow-tooltip="true" width="160"></el-table-column>
      <el-table-column prop="operStatus" label="操作状态" width="100">
        <template slot-scope="scope">
          <template v-if="scope.row.operStatus == 0">
            <el-tag type="success">成功</el-tag>
          </template>
          <template v-else>
            <el-tag type="danger">失败</el-tag>
          </template>
        </template>
      </el-table-column>
      <el-table-column label="操作时间" prop="operTime" width="160">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.operTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="耗时时间" prop="costTime" width="160"></el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width" width="160">
        <template slot-scope="scope">
          <el-button
              size="mini"
              type="text"
              icon="el-icon-view"
              @click="handleView(scope.row)"
          >详细</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
        path-url="/system/log/list"
        ref="pag1"
        @pagination="handleQuery"
        style="float: right"
        />
  </p-layout>
</template>

<script>
import {delLog, cleanLog} from "@/api/system/operLog";

export default {
  name: 'logInfo',
  components: {  },
  data(){
    return {
      infoList: [],
      loading: false,
      multiple: true,
      // 日期范围
      dateRange: [],
      queryParams: {
        title: undefined,
        operType: undefined,
        requestMethod: undefined,
        operName: undefined,
        operStatus: undefined,
        beginTime: undefined,
        endTime: undefined
      },
      //操作类型
      typeList: [
        { code: 0, message: "其他" , color: "#c0de85" },
        { code: 1, message: "新增", color: "#efdcde"  },
        { code: 2, message: "修改", color: "#bfeaae"  },
        { code: 3, message: "删除" , color: "#76e7cc" },
      ],
      //请求方式
      requestList: [
        'GET', 'HEAD','POST', 'PUT', 'PATCH', 'DELETE', 'OPTIONS', 'TRACE'
      ],
      ids: []
    }
  },
  mounted() {
    this.handleQuery()
  },
  methods:{
    /** 重置按钮操作 */
    resetQuery() {
      this.$refs["queryForm"].resetFields();
      this.dateRange = [];
      this.queryParams = {
        title: undefined,
        operType: undefined,
        requestMethod: undefined,
        operName: undefined,
        operStatus: undefined,
      }
      this.handleQuery();
    },
    handleQuery() {
      this.loading = true
      const params = this.addDateRange(this.queryParams,this.dateRange)
      this.$refs.pag1.queryData(params).then(res=>{
        this.infoList = res;
        this.loading = false
      })
    },

    /** 多选框选中数据 */
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.multiple = !selection.length
    },

    /*详情*/
    handleView(){

    },

    /*删除*/
    handleDelete(){
      const operIds = this.ids;
      this.$modal.confirm('是否确认删除日志编号为"' + operIds + '"的日志？').then(function() {
        return delLog(operIds);
      }).then(() => {
        this.handleQuery();
        this.$modal.success("删除成功");
      }).catch(() => {});
    },

    /*清空*/
    handleClean(){
      this.$modal.confirm('是否确认清空所有日志？').then(function() {
        return cleanLog();
      }).then(() => {
        this.handleQuery();
        this.$modal.success("清空成功");
      }).catch(() => {});
    },
  }
}
</script>

<style scoped>
/deep/.el-form-item--mini.el-form-item, .el-form-item--small.el-form-item {
  margin-bottom: 10px;
}
</style>
