<template>
  <p-layout>
    <el-input placeholder="请输入标签名称" v-model="gjsString" class="input-with-select" style="width: 300px;margin-left: 20px">
      <el-button slot="append" icon="el-icon-search"></el-button>
    </el-input>
    <el-button :style="{marginLeft: '20px',backgroundColor:visible?'#f19aaa':'#85cdf1',color:'#ffffff'}" @click="openLabel" >
      {{visible?"取消操作":"新建标签"}}
    </el-button>
    <div class="container">
      <div class="label-container">
        <template v-for="(item, index) in labelList">
          <el-popover
              placement="bottom"
              width="100"
              trigger="click">
            <div class="file-grid-item" slot="reference">
              <label-item :obj="item" :style="{ margin: '20px' }"/>
            </div>

            <el-menu class="el-menu-vertical-demo">
              <el-menu-item index="1" @click="openEditLabel(item)">
                <template  style="height: 34px;display: flex; align-items: center;" >
                  <svg-icon svgName="menu-edit" class="svg-menu"></svg-icon>
                  <span slot="title">修改</span>
                </template>
              </el-menu-item>
              <el-menu-item index="2" @click="delLabel(item)">
                <template style="height: 34px;display: flex; align-items: center;" >
                  <svg-icon svgName="menu-delete" class="svg-menu"></svg-icon>
                  <span slot="title">删除</span>
                </template>
              </el-menu-item>
            </el-menu>
          </el-popover>
        </template>
      </div>

      <div class="label-right" v-if="visible">
        <el-form ref="form" :model="form" label-width="80px">
          <el-form-item label="标签名称" prop="labelName">
            <el-input placeholder="请输入标签名称" v-model="form.labelName"/>
          </el-form-item>
          <el-form-item label="父级标签" prop="parentId">
            <el-select v-model="form.pid" placeholder="请选择" style="width: 100%" filterable>
              <el-option v-for="item in labelList"
                         v-if="!labelObj || labelObj.id !== item.id"
                         :key="item.id"
                         :label="item.labelName"
                         :value="item.id" />
            </el-select>
          </el-form-item>
          <el-form-item label="标签状态" prop="status">
            <el-radio-group v-model="form.status">
              <el-radio :label="0">正常</el-radio>
              <el-radio :label="1">停用</el-radio>
            </el-radio-group>
          </el-form-item>
          <el-form-item prop="parentId">
            <el-button type="primary" @click="submit">确 定</el-button>
            <el-button @click="visible = false">取 消</el-button>
          </el-form-item>
        </el-form>
      </div>
    </div>
  </p-layout>
</template>

<script>
import labelItem from "./components/labelItem.vue";
import {queryLabelList, addLabel, deleteLabel, editLabel} from '@/api/label'
export default {
  name: 'label',
  components: { labelItem },
  data() {
    return {
      gjsString: undefined,   //查询关键字
      visible: false,         //控制右侧显示
      form: {
        labelName: undefined,
        pid: undefined,
        status: undefined
      },
      labelList: [],        //标签集合
      labelObj: undefined,
    }
  },
  mounted() {
    this.queryList()
  },
  methods: {

    /*查询标签列表*/
    queryList(){
      queryLabelList({ labelName: this.gjsString }).then(res => {
        this.labelList = res.data
      })
    },

    /*新增/修改标签*/
    submit(){
      if(this.labelObj){
        this.form.id = this.labelObj.id
        editLabel(this.form).then(res=>{
          this.$modal.success("修改成功")
          this.submitOk()
        })
      }else{
        addLabel(this.form).then(res=>{
          this.$modal.success("新增成功")
          this.submitOk()
        })
      }

    },

    submitOk(){
      this.visible = false
      this.form = {
        labelName: undefined,
        pid: undefined,
        status: undefined
      }
      this.labelObj = undefined
      this.queryList()
    },

    /*打开修改标签*/
    openEditLabel(item){
      this.labelObj = item
      this.form = {
        labelName: item.labelName,
        pid: item.pid,
        status: item.status
      }
      this.visible = true
    },

    /*删除标签*/
    delLabel(item){
      this.$modal.confirm('是否确认删除选中的标签['+ item.labelName+']？').then(function() {
        return deleteLabel(item.id);
      }.bind(this)).then(() => {
        this.$modal.success("删除成功");
        this.queryList()
      });
    },

    /*打开右侧*/
    openLabel(){
      this.visible = !this.visible
      this.form = {
        labelName: undefined,
        pid: undefined,
        status: undefined
      }
      this.labelObj = undefined
    },
  }
}
</script>
<style scoped>
.container {
  display: flex;
}
.label-container {
  flex: 1; /* 自动填充剩余空间 */
  margin-right: 20px;
  display: flex;
  flex-wrap: wrap;
}
.label-right {
  width: 400px;
  padding: 20px;
}
</style>
