<template>
  <el-container>
    <el-header height="80px">
      <el-form :model="queryParams" class="demo-form-inline" ref="queryForm" size="small" :inline="true"  label-width="68px" style="text-align: left">
        <el-row>
          <el-col :span="6">
            <el-form-item label="字典名称" prop="dictName">
              <el-input v-model="queryParams.dictName" placeholder="请输入字典名称" clearable />
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="字典类型" prop="dictType">
              <el-input v-model="queryParams.dictType" placeholder="请输入字典类型" clearable />
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="状态" prop="status">
              <el-select v-model="queryParams.status" placeholder="字典状态"  clearable >
                <el-option label="正常" value="0" />
                <el-option label="停用" value="1" />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-button type="primary" icon="el-icon-search" size="mini" @click="queryList">搜索</el-button>
            <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
          </el-col>
        </el-row>
      </el-form>
    </el-header>
    <el-main>
      <el-row :gutter="10" class="mb8">
        <el-col :span="1.5">
          <el-button
              type="primary"
              plain
              icon="el-icon-plus"
              size="mini"
              @click="handleAdd"
          >新增</el-button>
        </el-col>
        <el-col :span="1.5">
          <el-button
              type="danger"
              plain
              icon="el-icon-refresh"
              size="mini"
              @click="handleRefreshCache"
          >刷新缓存</el-button>
        </el-col>
      </el-row>
      <el-table v-loading="loading" :data="typeList" row-key="id">
        <el-table-column label="序号" type="index" width="50" align="center"/>
        <el-table-column label="字典名称" align="center" prop="dictName" :show-overflow-tooltip="true" />
        <el-table-column label="字典类型" align="center" :show-overflow-tooltip="true">
          <template slot-scope="scope">
             <a style="color: #2894d0" @click="routerData(scope.row)"> {{ scope.row.dictType }} </a>
          </template>
        </el-table-column>
        <el-table-column label="状态" align="center" prop="status">
          <template slot-scope="scope">
            <el-tag v-if="scope.row.status =='0'">正常</el-tag>
            <el-tag v-else type="danger">停用</el-tag>
          </template>
        </el-table-column>
        <el-table-column label="创建时间" align="center" prop="createTime" width="180">
          <template slot-scope="scope">
            <span>{{ parseTime(scope.row.createTime) }}</span>
          </template>
        </el-table-column>
        <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
          <template slot-scope="scope">
            <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)">修改</el-button>
            <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)">删除</el-button>
            <el-button v-if="scope.row.status == '0'" size="mini" type="text" icon="el-icon-delete" @click="handleStop(scope.row)">停用</el-button>
            <el-button v-if="scope.row.status == '1'" size="mini" type="text" icon="el-icon-delete" @click="handleStart(scope.row)">启用</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-main>
    <el-footer height="100px">
      <pagination path-url="/dict/type/list" ref="pag1" @pagination="queryList" style="text-align: right"/>
    </el-footer>

    <!-- 新增 -->
    <addType v-if="addVisible"
             :visible="addVisible"
             @close="addVisible = false"
             @ok="addOk"
           />

    <!-- 修改 -->
    <editType v-if="editVisible"
             :visible="editVisible"
              :id="id"
             @close="editVisible = false"
             @ok="editOk"
    />
  </el-container>
</template>

<script>
import {delDict , stopDict , startDict} from '@/api/system/dict'
import addType from "./components/addType.vue";
import editType from "./components/editType.vue";
export default {
  name: "dict",
  components: { editType , addType },
  data() {
    return {
      id: undefined,
      //控制新增
      addVisible: false,
      //控制修改
      editVisible: false,
      // 查询参数
      queryParams: {
        dictName: undefined,
        dictType: undefined,
        status: undefined
      },
      // 表格遮罩层
      loading: false,
      // 字典表格数据
      typeList: [],
    };
  },
  mounted() {
    this.queryList();
  },
  methods: {
    /** 搜索 */
    queryList() {
      this.loading = true
      this.$refs.pag1.queryData(this.queryParams).then(res=>{
        this.typeList = res;
        this.loading = false
      })
    },
    /** 重置 */
    resetQuery() {
      this.$refs.queryForm.resetFields();
      this.queryList();
    },
    /** 路由跳转 */
    routerData(row){
      this.$router.push({path: '/dictData', query: {dictType: row.dictType}})
    },
    /** 新增 */
    handleAdd(){
      this.addVisible = true
    },
    addOk(){
      this.addVisible = false
      this.queryList();
    },
    /** 修改 */
    handleUpdate(row){
      this.id = row.id
      this.editVisible = true
    },
    editOk(){
      this.editVisible = false
      this.queryList();
    },
    /*删除*/
    handleDelete(row){
      this.$modal.confirm('是否确认删除字典类型为"' + row.dictType + '"的数据吗？').then(function() {
        return delDict(row.id);
      }).then(() => {
        this.queryList();
        this.$modal.success("删除成功");
      }).catch(() => {});
    },
    /*停用*/
    handleStop(row){
      this.$modal.confirm('是否确认停用字典类型为"' + row.dictType + '"的数据吗？').then(function() {
        return stopDict(row.id);
      }).then(() => {
        this.queryList();
        this.$modal.success("停用成功");
      }).catch(() => {});
    },
    /*启用*/
    handleStart(row){
      this.$modal.confirm('是否确认启用字典类型为"' + row.dictType + '"的数据吗？').then(function() {
        return startDict(row.id);
      }).then(() => {
        this.queryList();
        this.$modal.success("启用成功");
      }).catch(() => {});
    },
    /** 刷新缓存 */
    handleRefreshCache(){

    }
  }
};
</script>
<style>

</style>