<template>
  <el-dialog title="添加字典数据" :visible="visible" width="680px" append-to-body @close="handleClose">
    <el-form ref="form" :model="form" :rules="rules" label-width="100px">
        <el-form-item label="字典标签" prop="dictLabel">
          <el-input v-model="form.dictLabel" placeholder="请输入字典标签" />
        </el-form-item>
        <el-form-item label="字典键值" prop="dictValue">
          <el-input v-model="form.dictValue" placeholder="请输入字典键值" />
        </el-form-item>
        <el-form-item label="字典排序号" prop="dictSort">
          <el-input v-model="form.dictSort" placeholder="请输入字典排序号" />
        </el-form-item>
        <el-form-item label="字典类型" prop="dictType">
          <el-input v-model="form.dictType" placeholder="请输入字典类型"  :disabled="true"/>
        </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button type="primary" @click="cancelForm">确 定</el-button>
      <el-button @click="handleClose">取 消</el-button>
    </div>
  </el-dialog>
</template>
<script>
import {addDictData} from "@/api/system/dictData";
export default {
  name: "addData",
  props: {
    visible: {
      type: Boolean,
      required: true
    },
    dictType:{
      type: String,
      required: true
    }
  },
  data() {
    return {
      form: {
        dictLabel: undefined,
        dictValue: undefined,
        dictSort: undefined,
        dictType: this.dictType
      },
      rules: {
        dictLabel: [
          { required: true, message: '请输入字典标签', trigger: 'blur' }
        ],
        dictValue: [
          { required: true, message: '请输入字典键值', trigger: 'blur' }
        ],
        dictSort: [
          { required: true, message: '请输入字典排序号', trigger: 'blur' }
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
      this.$refs.form.resetFields();
      this.$emit("close")
    },

    cancelForm(){
      this.$refs["form"].validate(valid => {
        if (valid) {
          addDictData(this.form).then(res => {
            this.$modal.success("新增成功")
            this.$refs.form.resetFields();
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