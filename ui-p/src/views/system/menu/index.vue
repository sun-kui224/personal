<template>
    <p-layout>
      <!--顶部表单-->
      <el-form :model="queryParams" class="demo-form-inline" ref="queryForm" size="small" :inline="true" label-width="68px" style="text-align:left;">
          <el-form-item label="菜单名称" prop="name">
            <el-input
                v-model="queryParams.name"
                placeholder="请输入菜单名称"
                clearable
            />
          </el-form-item>
          <el-form-item label="菜单路径" prop="path" class="item-class">
            <el-input
                v-model="queryParams.path"
                placeholder="请输入菜单路径"
                clearable
            />
          </el-form-item>
        <el-form-item>
          <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery" style="margin-left: 20px">搜索</el-button>
          <el-button icon="el-icon-refresh" size="mini" @click="resetQuery" style="margin-left: 20px">重置</el-button>
        </el-form-item>
      </el-form>

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
              type="info"
              plain
              icon="el-icon-sort"
              size="mini"
              @click="toggleExpandAll"
          >展开/折叠</el-button>
        </el-col>
      </el-row>

      <el-table  v-if="refreshTable" v-loading="loading" :data="menuList" row-key="id" :default-expand-all="isExpandAll"
                :tree-props="{children: 'children', hasChildren: 'hasChildren'}">
        <el-table-column prop="name" label="菜单名称" :show-overflow-tooltip="true" width="160"></el-table-column>
        <el-table-column prop="icon" label="图标" align="center" width="100">
          <template slot-scope="scope">
            <svg-icon v-if="scope.row.icon" :svgName="scope.row.icon" />
          </template>
        </el-table-column>
        <el-table-column prop="sort" label="排序" width="60"></el-table-column>
        <el-table-column prop="path" label="菜单路由" :show-overflow-tooltip="true"></el-table-column>
        <el-table-column prop="component" label="组件路径" :show-overflow-tooltip="true"></el-table-column>
        <el-table-column prop="status" label="状态" width="80">
          <template slot-scope="scope">
            <template v-if="scope.row.status == 0">
              <el-tag type="success">正常</el-tag>
            </template>
            <template v-else>
              <el-tag type="danger">停用</el-tag>
            </template>
          </template>
        </el-table-column>
        <el-table-column label="创建时间" align="center" prop="create_date">
          <template slot-scope="scope">
            <span>{{ parseTime(scope.row.createDate) }}</span>
          </template>
        </el-table-column>
        <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
          <template slot-scope="scope">
            <el-button
                size="mini"
                type="text"
                icon="el-icon-edit"
                @click="handleUpdate(scope.row)"
            >修改</el-button>
            <el-button
                v-if="scope.row.status == 0"
                size="mini"
                type="text"
                icon="el-icon-video-pause"
                @click="handleStop(scope.row)"
            >停用</el-button>
            <el-button
                v-if="scope.row.status == 1"
                size="mini"
                type="text"
                icon="el-icon-video-play"
                @click="handleEnable(scope.row)"
            >启用</el-button>
            <el-button
                size="mini"
                type="text"
                icon="el-icon-delete"
                @click="handleDelete(scope.row)"
            >删除</el-button>
          </template>
        </el-table-column>
      </el-table>

      <!-- 新增抽屉 -->
      <add-menu v-if="addVisible"
                :visible="addVisible"
                :menuList="menuList"
                @close-drawer="addVisible = false"
                @ok="addOk()"/>

      <!-- 修改抽屉 -->
      <edit-menu v-if="editVisible"
                :visible="editVisible"
                :menuList="menuList"
                @close-drawer="editVisible = false"
                 :id="keyId"
                @ok="editOk()"/>
    </p-layout>
</template>

<script>
import { queryMenuList , delMenu , stopMenu , startMenu} from "@/api/system/menu";
import addMenu from "./components/addMenu.vue";
import editMenu from "./components/editMenu.vue";
export default {
  name: 'index',
  components: {addMenu , editMenu},
  data(){
    return {
      keyId: undefined, //主键
      queryParams:{
        name: undefined,
        path: undefined
      },
      menuList: [],  //表格数据
      loading: false, //遮罩
      refreshTable: true, //是否重新渲染表格
      isExpandAll: false,// 是否展开，默认全部折叠
      addVisible: false,
      editVisible: false,
    }
  },
  mounted() {
    this.handleQuery()
  },
  methods:{
    /** 重置按钮操作 */
    resetQuery() {
      this.$refs["queryForm"].resetFields();
      this.handleQuery();
    },
    handleQuery() {
      this.loading = true
      queryMenuList(this.queryParams).then(res=>{
        this.menuList = res.data;
        this.loading = false
      })
    },

    /*展开/折叠*/
    toggleExpandAll(){
      this.refreshTable = false;
      this.isExpandAll = !this.isExpandAll;
      this.$nextTick(() => {
        this.refreshTable = true;
      });
    },

    /*新增*/
    handleAdd(){
      this.addVisible = true
    },

    /*添加回调*/
    addOk(){
      this.addVisible = false;
      this.handleQuery();
    },

    /*修改*/
    handleUpdate(row){
      this.keyId = row.id
      this.editVisible = true
    },

    /*修改回调*/
    editOk(){
      this.editVisible = false;
      this.handleQuery();
    },

    /*停用*/
    handleStop(row){
      this.$modal.confirm('是否确认停用菜单名称为"' + row.name + '"的数据项？').then(function() {
        return stopMenu(row.id);
      }).then(() => {
        this.handleQuery();
        this.$modal.success("停用成功");
      }).catch(() => {});
    },

    /*启用*/
    handleEnable(row){
      this.$modal.confirm('是否确认启用菜单名称为"' + row.name + '"的数据项？').then(function() {
        return startMenu(row.id);
      }).then(() => {
        this.handleQuery();
        this.$modal.success("启用用成功");
      }).catch(() => {});
    },

    /*删除*/
    handleDelete(row){
      this.$modal.confirm('是否确认删除菜单名称为"' + row.name + '"的数据项？').then(function() {
        return delMenu(row.id);
      }).then(() => {
        this.handleQuery();
        this.$modal.success("删除成功");
      }).catch(() => {});
    }
  }
}
</script>

<style scoped>

</style>
