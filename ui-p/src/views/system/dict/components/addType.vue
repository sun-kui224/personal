<template>
  <el-dialog title="添加字典" :visible="visible" width="680px" append-to-body @close="handleClose">
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
import {addDict} from "@/api/system/dict";
export default {
  name: "addType",
  props: {
    visible: {
      type: Boolean,
      required: true
    },
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
          addDict(this.form).then(res => {
            this.$modal.success("新增成功")
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