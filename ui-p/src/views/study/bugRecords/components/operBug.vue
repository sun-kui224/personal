<template>
  <p-layout>
    <!-- 顶部 -->
    <el-form ref="form" :model="form" :rules="rules" label-width="100px">
      <el-row>
        <el-col :span="12">
          <el-form-item label="记录名称" prop="recordsName">
            <el-input placeholder="请输入记录名称" v-model="form.recordsName" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="出现时间" prop="appearDate">
            <el-date-picker  v-model="form.appearDate" type="date"  placeholder="请选择出现时间" />
          </el-form-item>
        </el-col>
      </el-row>
      <el-row style="margin-top: 20px">
        <el-col :span="12">
          <el-form-item label="问题描述" prop="problemDescription">
            <cusEdite :height="500" v-model="form.problemDescription"/>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="解决方案" prop="solveProgramme">
            <cusEdite :height="500" v-model="form.solveProgramme"/>
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>
    <div style="height: 100px;text-align: center;padding-top: 50px">
      <el-button type="primary" @click="saveok()">保存</el-button>
      <el-button @click="returnBack()">返回</el-button>
    </div>
  </p-layout>
</template>
<script>
import {getInfo , addBugRecord , editBugRecord} from "@/api/bugRecords"
export default {
  name: 'operBug',
  data() {
    return {
      form: {
        recordsName: undefined,    //记录名称
        appearDate: undefined,     //出现时间
        problemDescription: undefined,  //问题描述
        solveProgramme: undefined,  //解决方案
      },
      id: undefined,
      rules: {
        recordsName: [
          { required: true, message: "记录名称不能为空", trigger: "blur" }
        ],
        appearDate: [
          { required: true, message: "出现时间不能为空", trigger: "blur" }
        ],
        problemDescription: [
          { required: true, message: "问题描述不能为空", trigger: "blur" }
        ],
        solveProgramme: [
          { required: true, message: "解决方案不能为空", trigger: "blur" }
        ],
      }
    }
  },
  created() {
    this.id = this.$route.query.id
    if(this.id){
      getInfo(this.id).then(res=> {
        if (res.data) {
          this.form = {
            recordsName: res.data.recordsName,
            appearDate: res.data.appearDate,
            problemDescription: res.data.problemDescription,
            solveProgramme: res.data.solveProgramme,
          }
        }
      })
    }
  },
  methods: {
    //保存
    saveok(){
      this.$refs.form.validate((valid) => {
        if (valid) {
          if(this.id){
            let param = this.form
            param.id = this.id
            editBugRecord(param).then(res=>{
              this.$modal.success("修改成功")
              this.returnBack()
            })
          }else{
            addBugRecord(this.form).then(res=>{
              this.$modal.success("新增成功")
              this.returnBack()
            })
          }
        } else {
          return false;
        }
      });
    },
    //返回
    returnBack(){
      this.$router.go(-1)
    },
  }
}
</script>
<style scoped>
/deep/.el-form-item {
  margin-bottom: 0px;
  font-size: 14px;
}
</style>
