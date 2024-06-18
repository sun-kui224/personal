<template>
  <el-dialog title="上传文件" :modal="true" :visible="visible" width="680px" append-to-body @close="handleClose">
    <input type="file" id="fileInput" ref="fileInput" multiple style="display:none;" @change="handleFileChange"  >
    <el-button size="small" type="primary" @click="chooseFile">选取文件</el-button>
    <el-button style="margin-left: 10px;" size="small" type="success" @click="handleUploadSuccess">上传到服务器</el-button>
    <el-button style="margin-left: 10px;" size="small" @click="handleClose">取消</el-button>

    <el-table :data="fileList" height="350" v-if="refreshTable">
      <el-table-column prop="name" label="名称"  min-width="160"></el-table-column>
      <el-table-column prop="size" label="文件大小"  min-width="160"></el-table-column>
      <el-table-column prop="operStatus" label="操作" width="150">
        <template slot-scope="scope">
          <el-button
              size="mini"
              type="text"
              icon="el-icon-delete"
              @click="handleDelete(scope.row)"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>
  </el-dialog>
</template>

<script>
import { addFile } from "@/api/file"
import { uploadFiles } from "@/api/common"
export default {
  name: 'fieUploadModel',
  props: {
    visible: {
      type: Boolean,
      required: true
    },
  },
  data() {
    return {
      fileList: [],
      refreshTable: true, //是否重新渲染表格

    }
  },
  mounted() {

  },
  methods: {
    /*选择文件*/
    chooseFile(){
      this.$refs.fileInput.click();
    },

    handleFileChange(event) {
      const files = event.target.files;
      for (let i = 0; i < files.length; i++) {
        files[i].operStatus = '上传中'
        this.fileList[i] = files[i]
      }
      this.refreshTable = false;
      this.$nextTick(() => {
        this.refreshTable = true;
      });
    },

    /*上传*/
    handleUploadSuccess() {
      const formData = new FormData();
      for (let i = 0; i < this.fileList.length; i++) {
        formData.append('file', this.fileList[i]);
      }
      uploadFiles(formData).then((res)=>{
        //再请求后端保存数据库
        addFile(res.data).then((response)=>{
          this.$modal.success("上传成功")
          this.$emit("ok")
        }).catch((error)=>{
          this.$modal.error("上传失败")
        })
      }).catch((error)=>{
        this.$modal.error("上传失败")
      })
    },

    /*删除*/
    handleDelete(row){
      const index = this.fileList.indexOf(row);
      if (index !== -1) {
        this.fileList.splice(index, 1);
      }
    },

    /*关闭弹窗*/
    handleClose(){
      this.fileList = []
      this.$emit("close")
    },
  }
}
</script>
<style scoped>
</style>
