<template>
  <el-dialog title="修改菜单" :visible="visible" width="680px" append-to-body @close="handleClose">
    <el-form ref="form" :model="form" :rules="rules" label-width="100px">
      <el-form-item label="字典名称" prop="dictName">
        <el-input v-model="form.dictName" placeholder="请输入菜单名称" />
      </el-form-item>
      <el-form-item label="字典类型" prop="dictType">
        <el-input v-model="form.dictType" placeholder="请输入字典类型" />
      </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button type="primary" @click="cancelForm">确 定</el-button>
      <el-button @click="handleClose">取 消</el-button>
    </div>
  </el-dialog>
</template>
<script>
import { updateDict , getInfo } from '@/api/system/dict'

export default {
  name: "editType",
  props: {
    visible: {
      type: Boolean,
      required: true
    },
    id: {
      type: Number,
      required: true
    }
  },
  data() {
    return {
      form: {
        dictName: undefined,
        dictType: undefined
      },
      rules: {
        dictName: [
          { required: true, message: '请输入字典名称', trigger: 'blur' }
        ],
        dictType: [
          { required: true, message: '请输入字典类型', trigger: 'blur' }
        ]
      }
    };
  },
  mounted() {
    getInfo(this.id).then(res=>{
      if(res.data){
        this.form ={
          dictName: res.data.dictName,
          dictType: res.data.dictType,
        }
      }
    })
  },
  methods: {
    /*关闭弹窗*/
    handleClose(){
      this.form={
        dictName: undefined,
        dictType: undefined
      }
      this.$emit("close")
    },

    cancelForm(){
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.form.id = this.id
          updateDict(this.form).then(res => {
            this.$modal.success("修改成功");
            this.form={
              dictName: undefined,
              dictType: undefined
            }
            this.$emit("ok")
          });
        }
      });
    },


  }
};
</script>
<style scoped>

</style>