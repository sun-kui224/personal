<template>
  <el-dialog title="新增网址" :visible="visible" width="800px" append-to-body @close="handleClose">
    <div class="dialog-main">
      <div class="dialog-left">
         <div class="left-top">
           <div class="img-div">
             <img :src="previewImageUrl" style="width: 100%;height: 100%"/>
           </div>
         </div>
         <div class="left-bottom">
           <input type="file" id="fileInput" ref="fileInput" style="display:none;" @change="handleFileChange"  >
           <el-button type="primary" @click="choosePicture" style="margin-top: 20px">选中图片</el-button>
         </div>
      </div>
      <div class="dialog-right">
        <el-form ref="form" :model="form" :rules="rules" label-width="100px">
          <el-row>
            <el-col :span="24">
              <el-form-item label="网址名称" prop="websiteName">
                <el-input v-model="form.websiteName" placeholder="请输入网址名称" />
              </el-form-item>
            </el-col>
            <el-col :span="24">
              <el-form-item label="公司名称" prop="companyName">
                <el-input v-model="form.companyName" placeholder="请输入公司名称" />
              </el-form-item>
            </el-col>
            <el-col :span="24">
              <el-form-item label="网址地址" prop="websiteUrl">
                <el-input v-model="form.websiteUrl" placeholder="请输入网址地址" />
              </el-form-item>
            </el-col>
            <el-col :span="24">
              <el-form-item label="网址描述" prop="websiteDescribe">
                <el-input v-model="form.websiteDescribe"
                          placeholder="请输入网址描述"
                          type="textarea"
                          :rows="3"/>
              </el-form-item>
            </el-col>
          </el-row>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button type="primary" @click="cancelForm" style="margin-left: 32px">确 定</el-button>
          <el-button @click="handleClose">取 消</el-button>
        </div>
      </div>
    </div>
  </el-dialog>
</template>

<script>
import {addWeb} from "@/api/website";
import {upload} from "@/api/common";
export default {
  name: 'addWeb',
  props: {
    visible: {
      type: Boolean,
      required: true
    },
  },
  data(){
    return {
      previewImageUrl: undefined,   //预览地址
      fileObj: undefined,           //上传文件对象
      form: {                       //form表单
        picture: undefined,
        websiteName: undefined,
        companyName: undefined,
        websiteUrl: undefined,
        websiteDescribe: undefined,
      },
      rules: {                       //表单校验规则
        websiteName: [
          { required: true, message: "网址名称不能为空", trigger: "blur" }
        ],
        websiteUrl: [
          { required: true, message: "网址地址不能为空", trigger: "blur" }
        ],
      }
    }
  },
  methods: {
    /*选中图片*/
    choosePicture(){
      this.$refs.fileInput.click();
    },

    handleFileChange(event){
      const file = event.target.files[0];
      if (file) {
        this.fileObj = file
        const reader = new FileReader();
        reader.onload = (event) => {
          this.previewImageUrl = event.target.result;
        };
        reader.readAsDataURL(file);
      }
    },

    /*关闭弹窗*/
    handleClose(){
      this.clearContext()
      this.$emit('close');
    },

    /*确定*/
    cancelForm(){
      this.$refs["form"].validate(valid => {
        if (valid) {
          //先上传图片获取地址
          if(this.fileObj){
            upload(this.fileObj).then((res)=>{
              this.form.picture = res.data.fileUrl
              this.insertWeb()
            }).catch((error)=>{
              this.$modal.error("上传失败")
              return
            })
          }else{
            this.insertWeb()
          }
        }
      });
    },

    insertWeb(){
      addWeb(this.form).then(res=>{
        this.$modal.success("添加成功")
        this.clearContext()
        this.$emit("ok")
      })
    },

    /*清空内容*/
    clearContext(){
      this.form= {
        picture: undefined,
        websiteName: undefined,
        companyName: undefined,
        websiteUrl: undefined,
        websiteDescribe: undefined,
      }
      this.fileObj = undefined
      this.previewImageUrl = undefined
    }
  }
}
</script>

<style scoped>
.dialog-main{
  display: flex;
}
.dialog-left{
  width: 200px;
  position: relative; /* 添加这一行，使得按钮定位参照此元素 */
}
.left-top { /* 选择第一个子div，即包含图片的div */
  display: flex;
  justify-content: center;
  align-items: center;
  height: 80%; /* 确保图片容器撑满剩余空间 */
}
.img-div{
  width: 180px;
  height: 180px;
  border: 1px solid #e4e6eb
}
.left-bottom { /* 选择最后一个子div，即包含按钮的div */
  position: absolute;
  bottom: 0;
  width: 100%;
  text-align: center; /* 使按钮居中 */
}
.dialog-right{
  flex: 1; /* 自动填充剩余空间 */
  margin-left: 20px;
  display: flex;
  flex-wrap: wrap;
}
</style>
