<template>
  <p-layout>
    <div style="background-color: #fff;height: 100%;overflow:auto">
      <div class="header-container">
        <div class="records-name"><span class="record-title">{{recordsName}}</span></div>
        <el-button @click="returnBack()" class="back-button" size="mini">返回</el-button>
      </div>
      <div class="date-container"><h3>出现时间：{{parseTime(appearDate)}}</h3></div>
      <div class="section-container">
        <h3 style="margin-bottom: 10px">问题描述：</h3>
        <div v-html="problemDescription"></div>
      </div>
      <div class="section-container">
        <h3 style="margin-bottom: 10px">解决方案：</h3>
        <div v-html="solveProgramme"></div>
      </div>
    </div>
  </p-layout>
</template>
<script>
import {getInfo} from "@/api/bugRecords"
export default {
  name: 'bugInfo',
  data() {
    return {
      recordsName: undefined,    //记录名称
      appearDate: undefined,     //出现时间
      problemDescription: undefined,  //问题描述
      solveProgramme: undefined,  //解决方案
      id: undefined,
    }
  },
  created() {
    this.id = this.$route.query.id
    getInfo(this.id).then(res=> {
      this.recordsName = res.data.recordsName
      this.appearDate = res.data.appearDate
      this.problemDescription = res.data.problemDescription
      this.solveProgramme = res.data.solveProgramme
    })
  },
  methods: {
    //返回
    returnBack(){
      this.$router.go(-1)
    },
  }
}
</script>
<style scoped>
.header-container {
  display: flex;
  justify-content: space-between;
  align-items: center;
  background-color: #fff;
}
.record-title{
  margin: 0 0 1.3rem;
  font-size: 2.667rem;
  font-weight: 600;
  line-height: 1.31;
  color: #252933;
}
.record-label{
  font-size: 20px;
  line-height: 28px;
}
.records-name {
  text-align: center;
  flex-grow: 1;
}
.date-container, .section-container {
  margin-top: 30px;
}
</style>
