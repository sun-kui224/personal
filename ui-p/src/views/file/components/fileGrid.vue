<template>
  <div class="pathMain">
    <div class="path-row">
      <div class="path-cell" v-for="item in dataList" :key="item.id">
        <el-popover
            placement="bottom"
            width="100"
            trigger="click">
          <div class="file-grid-item" slot="reference">
            <!-- 初始模式 -->
            <section class="file-grid-item__wrapper" >
              <div class="file-icon">
                <img v-if="item.type===2" :src="filePath+'/'+item.fileUrl" class="file-image" />
                <svg-icon v-else :svg-name="getIcon(item)" style="width: 100%;height: 100%"/>
              </div>
              <p class="gi_line_1 file-name">{{ item.fileName }}</p>
            </section>
            <!-- 勾选模式 -->
            <section v-show="modeType" class="check-mode" :class="{ checked: fileIds.includes(item.id) }"
                     @click.stop="handleCheckFile(item)">
              <el-checkbox class="checkbox" v-if="fileIds.includes(item.id)" :checked="fileIds.includes(item.id)"  @click.stop="handleCheckFile(item)"></el-checkbox>
            </section>
          </div>

          <el-menu class="el-menu-vertical-demo">
            <el-menu-item index="1" @click="renameFile(item)">
              <template  style="height: 34px;display: flex; align-items: center;" >
                <svg-icon svgName="menu-edit" class="svg-menu"></svg-icon>
                <span slot="title">重命名</span>
              </template>
            </el-menu-item>
            <el-menu-item index="2" @click="downloadFile(item)">
              <template  style="height: 34px;display: flex; align-items: center;" >
                <svg-icon svgName="menu-download" class="svg-menu"></svg-icon>
                <span slot="title">下载</span>
              </template>
            </el-menu-item>
            <el-menu-item index="3" @click="detailFile(item)">
              <template style="height: 34px;display: flex; align-items: center;" >
                <svg-icon svgName="menu-detail" class="svg-menu"></svg-icon>
                <span slot="title">详情</span>
              </template>
            </el-menu-item>
            <el-menu-item index="4" @click="deleteFile(item)">
              <template style="height: 34px;display: flex; align-items: center;" >
                <svg-icon svgName="menu-delete" class="svg-menu"></svg-icon>
                <span slot="title">删除</span>
              </template>
            </el-menu-item>
          </el-menu>

        </el-popover>
      </div>
    </div>

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
import {iconNames} from "@/assets/icons/index"
import {deleteFile , editName} from "@/api/file";
import {downloadFile} from "@/api/common"
export default {
  name: 'fileGrid',
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
      fileIds: [ ] ,  //批量下选中的文件id
      queryParam: {},  //查询条件
      filePath: process.env.VUE_APP_BASE_API,   //系统访问地址
      localIconNames: [] , //图标数组
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

    /*打开重命名窗口*/
    renameFile(item){
      this.currentFile = item
      this.editVisible = true
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

    /*选择*/
    handleCheckFile(item){
      const index = this.fileIds.indexOf(item.id);
      if (index !== -1) {
        this.fileIds.splice(index, 1);
      }else {
        this.fileIds.push(item.id);
      }
      this.$emit('selected-files-changed', this.fileIds); // 通知父组件选中项已改变
    }
  }
}
</script>
<style lang="less">
.pathMain {
  flex: 1;
  margin-top: 12px;
  overflow: auto;
  margin: 0;
  padding: 0;
}
.path-row{
  gap: 12px;
  grid-template-columns: repeat(9, minmax(0px, 1fr));
  display: grid;
  margin: 0;
  padding: 0;
}
.path-cell{
  grid-column: span 1 / span 1;
  margin: 0;
  padding: 0;
}
.file-grid-item{
  width: 100%;
  height: 100px;
  display: flex;
  justify-content: center;
  align-items: center;
  position: relative;
  cursor: pointer;
  margin: 0;
  padding: 0;

  &:hover {
    background: rgb(232, 243, 255);
  }

  &:active {

    svg,
    img {
      transform: scale(0.9);
    }
  }
}
.file-grid-item__wrapper{
  width: 76%;
  max-width: 100px;
  height: 100%;
  position: relative;
  overflow: hidden;
  z-index: 1;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}
.file-icon {
  width: 100%;
  height: 60px;
  display: flex;
  justify-content: center;
  overflow: hidden;

  >img {
    width: auto;
    height: 100%;
    transition: all 0.3s;
  }

  >svg {
    height: 100%;
    transition: all 0.3s;
  }
}
.file-name {
  width: 100%;
  font-size: 12px;
  margin-top: 8px;
  padding: 0 5px;
  text-align: center;
  box-sizing: border-box;
}
.gi_line_1{
  overflow: hidden;
  white-space: nowrap;
  text-overflow: ellipsis;
}
.check-mode{
  position: absolute;
  left: 0;
  right: 0;
  top: 0;
  bottom: 0;
  background: rgba(0, 0, 0, 0.1);
  z-index: 9;

  &.checked {
    background: none;
  }
}
.checkbox {
  position: absolute;
  top: 5px;
  left: 5px;
  padding-left: 0;
}
.svg-menu{
  margin-right: 15px;
}
.el-menu {
  border-right: 0px !important;
}
/deep/.el-popover {
  top: 295px;
}
/deep/.el-menu-item, .el-submenu__title {
  height: 34px;
  line-height: 34px;
  list-style: none;
  padding-left: 10px !important;
}
</style>
