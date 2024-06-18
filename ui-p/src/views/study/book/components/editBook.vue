<template>
  <el-dialog title="修改书籍" :visible="visible" width="800px" append-to-body @close="handleClose">
    <div class="dialog-main">
      <div class="dialog-left">
        <div class="left-top">
          <div class="img-div">
            <img :src="previewImageUrl" style="width: 100%;height: 100%"/>
          </div>
        </div>
        <div class="left-bottom">
          <input type="file" id="fileInput" ref="fileInput" style="display:none;" @change="handleFileChange"  >
          <el-button type="primary" @click="choosePicture" style="margin-top: 20px">选中封面</el-button>
          <el-button @click="returePicture" style="margin-top: 20px">还原</el-button>
        </div>
      </div>
      <div class="dialog-right">
        <el-form ref="form" :model="form" :rules="rules" label-width="100px">
          <el-row>
            <el-col :span="24">
              <el-form-item label="书籍名称" prop="bookName">
                <el-input v-model="form.bookName" placeholder="请输入书籍名称" />
              </el-form-item>
            </el-col>
            <el-col :span="24">
              <el-form-item label="书籍作者" prop="bookAuthor">
                <el-input v-model="form.bookAuthor" placeholder="请输入书籍作者" />
              </el-form-item>
            </el-col>
            <el-col :span="24">
              <el-form-item label="书籍价格" prop="bookPrice">
                <el-input v-model="form.bookPrice" placeholder="请输入书籍价格" type="number" />
              </el-form-item>
            </el-col>
            <el-col :span="24">
              <el-form-item label="书籍字数" prop="wordNumber">
                <el-input v-model="form.wordNumber" placeholder="请输入书籍字数" type="number"/>
              </el-form-item>
            </el-col>
            <el-col :span="24">
              <el-form-item label="出版年限" prop="publicationPeriod">
                <el-date-picker v-model="form.publicationPeriod" type="date" placeholder="请选择出版年限" format="yyyy-MM-dd"/>
              </el-form-item>
            </el-col>
            <el-col :span="24">
              <el-form-item label="在线浏览地址" prop="onlineAddress">
                <el-input v-model="form.onlineAddress" placeholder="请输入在线浏览地址" />
              </el-form-item>
            </el-col>
            <el-col :span="24">
              <el-form-item label="是否购买" prop="isBuy">
                <el-radio-group v-model="form.isBuy">
                  <el-radio :label="0">未买</el-radio>
                  <el-radio :label="1">已买</el-radio>
                </el-radio-group>
              </el-form-item>
            </el-col>
            <el-col :span="24">
              <el-form-item label="书籍描述" prop="bookDescribe">
                <el-input v-model="form.bookDescribe"
                          placeholder="请输入书籍描述"
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
import {handleFile} from "@/utils/personal";
import {upload} from "@/api/common";
import {editBook} from "@/api/book";

export default {
  name: 'editBook',
  props: {
    visible: {
      type: Boolean,
      required: true
    },
    obj: {
      type: Object,
      required: true
    }
  },
  data() {
    return {
      previewImageUrl: undefined,   //预览地址
      fileObj: undefined,           //上传文件对象
      form: {                       //form表单
        coverPhoto: undefined,
        bookName: undefined,
        bookAuthor: undefined,
        bookPrice: undefined,
        wordNumber: undefined,
        publicationPeriod: undefined,
        onlineAddress: undefined,
        isBuy: undefined,
        bookDescribe: undefined,
      },
      rules: {                       //表单校验规则
        bookName: [
          { required: true, message: "书籍名称不能为空", trigger: "blur" }
        ],
        bookAuthor: [
          { required: true, message: "书籍作者不能为空", trigger: "blur" }
        ],
        bookPrice: [
          { required: true, message: "书籍价格不能为空", trigger: "blur" }
        ],
        publicationPeriod: [
          { required: true, message: "出版年限不能为空", trigger: "blur" }
        ],
        isBuy: [
          { required: true, message: "是否购买不能为空", trigger: "blur" }
        ],
        bookDescribe: [
          { required: true, message: "书籍描述不能为空", trigger: "blur" }
        ],
      }
    }
  },
  mounted() {
    this.form = {
      coverPhoto: this.obj.coverPhoto,
      bookName: this.obj.bookName,
      bookAuthor: this.obj.bookAuthor,
      bookPrice: this.obj.bookPrice,
      wordNumber: this.obj.wordNumber,
      publicationPeriod: this.obj.publicationPeriod,
      onlineAddress: this.obj.onlineAddress,
      isBuy: this.obj.isBuy,
      bookDescribe: this.obj.bookDescribe,
    }
    this.previewImageUrl = handleFile(this.obj.coverPhoto)
  },
  methods: {
    /*选中图片*/
    choosePicture(){
      this.$refs.fileInput.click();
    },

    /*还原图片*/
    returePicture(){
      this.previewImageUrl = handleFile(this.obj.coverPhoto)
      this.fileObj = undefined
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
              this.form.coverPhoto = res.data.fileUrl
              this.updateBook()
            }).catch((error)=>{
              this.$modal.error("上传失败")
              return
            })
          }else{
            this.updateBook()
          }
        }
      });
    },

    updateBook(){
      this.form.id = this.obj.id
      editBook(this.form).then(res=>{
        this.$modal.success("修改成功")
        this.clearContext()
        this.$emit("ok")
      })
    },

    /*清空内容*/
    clearContext(){
      this.form= {
        coverPhoto: undefined,
        bookName: undefined,
        bookAuthor: undefined,
        bookPrice: undefined,
        wordNumber: undefined,
        publicationPeriod: undefined,
        onlineAddress: undefined,
        isBuy: undefined,
        bookDescribe: undefined,
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
