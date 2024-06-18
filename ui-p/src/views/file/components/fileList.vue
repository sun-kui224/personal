<template>
  <div class="file-list">
    <el-table :data="dataList" row-key="id" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="50" align="center" v-if="modeType"/>
      <el-table-column prop="fileName" label="名称"  min-width="160">
        <template slot-scope="scope">
          <section class="file-name">
            <div class="file-image">
              <img v-if="scope.row.type===2" :src="filePath+'/'+scope.row.fileUrl" class="file-image" />
              <svg-icon v-else :svg-name="getIcon(scope.row)" style="width: 100%;height: 100%"/>
            </div>
            <span>{{ scope.row.fileName }}</span>
          </section>
        </template>
      </el-table-column>
      <el-table-column prop="size" label="文件大小（字节)"  min-width="160"></el-table-column>
      <el-table-column prop="fileExt" label="文件后缀" align="center" min-width="100" />
      <el-table-column prop="createDate" label="上传时间" min-width="160" >
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createDate) }}</span>
        </template>
      </el-table-column>
      <el-table-column prop="operStatus" label="操作" width="100">
        <template slot-scope="scope">
          <el-dropdown placement="bottom-end">
            <span class="el-dropdown-link">
              <i class="el-icon-more"></i>
            </span>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item>
               <div @click="renameFile(scope.row)">
                 <svg-icon svgName="menu-edit" class="svg-menu"></svg-icon>
                 重命名
               </div>
              </el-dropdown-item>
              <el-dropdown-item>
                <div @click="downloadFile(scope.row)">
                  <svg-icon svgName="menu-download" class="svg-menu"></svg-icon>
                  下载
                </div>
              </el-dropdown-item>
              <el-dropdown-item>
                <div @click="detailFile(scope.row)">
                  <svg-icon svgName="menu-detail" class="svg-menu"></svg-icon>
                  详情
                </div>
              </el-dropdown-item>
              <el-dropdown-item>
                <div @click="deleteFile(scope.row)">
                  <svg-icon svgName="menu-delete" class="svg-menu"></svg-icon>
                  删除
                </div>
              </el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </template>
      </el-table-column>
    </el-table>

    <el-dialog title="重命名" v-if="editVisible" :visible="editVisible" @close="handleClose" width="300px" >
      <el-input  v-model="currentFile.fileName" />
      <div slot="footer" class="dialog-footer">
        <el-button @click="handleClose">取 消</el-button>
        <el-button type="primary" @click="editNameOk">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {iconNames } from "@/assets/icons/index"
import { deleteFile , editName } from "@/api/file";
import { downloadFile } from "@/api/common"
export default {
  name: 'fileList',
  props:{
    modeType: {
      type: Boolean,
      default: false
    },
    dataList: {
      type: Array,
      default: []
    }
  },
  data() {
    return {
      fileIds: [] ,   //文件id
      queryParam: {},   //查询条件
      filePath: process.env.VUE_APP_BASE_API,  //系统访问地址
      localIconNames: [],   //图标数组
      editVisible: false,
      currentFile: undefined ,//当前选中的文件
    }
  },
  created() {
    this.localIconNames = require('@/assets/icons/index').default;
  },
  methods: {
    /*获取图标*/
    getIcon(item){
      const iconName = "file-" + item.fileExt;
      if (this.localIconNames.indexOf(iconName)>-1) {
        return iconName;
      }else if(item.type===3){  //文档
        return "file-wps"
      }else if(item.type===4){  //视频
        return "file-video"
      }else if(item.type===5){  //音频
        return "file-music"
      }else{
        return 'file'// 返回默认图标名称
      }
    },
    handleClick(item){

    },

    /*打开重命名窗口*/
    renameFile(item){
      this.currentFile = item
      this.editVisible = true
      console.log( this.editVisible)

    },

    /*关闭重命名窗口*/
    handleClose(){
      this.currentFile = undefined
      this.editVisible = false
    },

    /*重命名确认事件*/
    editNameOk(){
      editName({id:this.currentFile.id,name:this.currentFile.fileName}).then((res)=>{
        this.$modal.success("重命名成功");
        this.handleClose()
        this.queryFileList()
      })
    },

    /*下载*/
    downloadFile(item){
      let params = {fileUrl: item.fileUrl}
      downloadFile(params).then((res)=>{
        this.$modal.success("下载成功");
      }).catch((error) => {
        this.$modal.error("下载文件时出错："+error);
      });
    },

    /*详情*/
    detailFile(item){
      this.$router.push({path:"/fileDetail",query:{id:item.id}})
    },

    /*删除*/
    deleteFile(item){
      this.$modal.confirm('是否确认删除选中的['+item.fileName+']此文件？').then(function() {
        return deleteFile(item.id);
      }.bind(this)).then(() => {
        this.$modal.success("删除成功");
        this.queryFileList()
      });
    },

    /** 多选框选中数据 */
    handleSelectionChange(selection) {
      this.fileIds = selection.map(item => item.id)
      this.$emit('selected-files-changed', this.fileIds); // 通知父组件选中项已改变
    },
  }
}
</script>

<style scoped>
.svg-menu{
  margin-right: 15px;
}
.file-list {
  width: 100%;
  height: 100%;
  overflow: auto;
}
.file-name {
  height: 100%;
  display: flex;
  align-items: center;
  padding-top: 6px;
  padding-bottom: 6px;
  cursor: pointer;
}

.file-image {
  width: 30px;
  height: 30px;
  margin-right: 10px;
}
</style>
