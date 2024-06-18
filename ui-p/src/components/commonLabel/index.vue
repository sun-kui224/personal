<template>
  <el-dialog :visible="visible" width="800px" append-to-body @close="handleClose" :show-close="false">
    <div>
      <div>已选择标签：</div>
      <div class="choose-center">
        <div class="choose-item-tag" v-for="(item,index) in chooseLabel">
          {{ item.labelName }}
        </div>
      </div>
    </div>
    <div style="margin-top: 20px">
      <div>可选择标签：</div>
      <div class="dialog-bottom">
        <ul style="font-size: 0;white-space: normal;min-height: 38px;">
          <li v-for="(item,index) in lableList"
              :class="{'item-li-tag': true,'li-back': verfityLabel(item)?true:false}"
              @click="handleChoose(item)"
              :key="index"
          >{{ item.labelName }}</li>
        </ul>
      </div>
    </div>
    <div style="margin-top: 20px">
      <el-button @click="handleOk" type="primary" size="mini">确认</el-button>
      <el-button @click="handleClose" size="mini">取消</el-button>
      <el-pagination
          :current-page="pageNum"
          :page-size="pageSize"
          :background="true"
          :total="total"
          :layout="layout"
          @current-change="handleCurrentChange"
          style="float: right"
      />
    </div>
  </el-dialog>
</template>

<script>
import {queryPaginationList} from "@/api/pagination/pagination";
import {addCorr} from "@/api/label";

export default {
  name: 'commonLabel',
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
      pageNum: 1 , //当前页
      pageSize: 20 , //每页大小
      total: 0,    //总数
      pathUrl: '/system/label/listPage', //路径
      layout: 'prev , next',   //布局
      chooseLabel: _.cloneDeep(this.obj.labelList) || [], //采用深拷贝
      lableList: []
    }
  },
  mounted() {
     this.queryData()
  },
  methods: {
    queryData(){
      queryPaginationList(this.pathUrl, { pageNum: this.pageNum, pageSize: this.pageSize }).then(response => {
            const data = response.data
            this.total = data.total
            this.lableList = data.list
          }
      );
    },

    /*判断是否存在标签*/
    verfityLabel(item){
      for(let i=0;i<this.chooseLabel.length;i++){
        if(this.chooseLabel[i].id === item.id){
          return true
        }
      }
      return false
    },

    /*选择标签*/
    handleChoose(item){
      const existingLabel = this.chooseLabel.find(label => label.id === item.id);
      if (existingLabel) {
        this.chooseLabel.splice(this.chooseLabel.indexOf(existingLabel), 1);
      }else{
        if(this.chooseLabel.length >= 5){
          this.$modal.msg("最多选择5个标签")
          return
        }
        this.chooseLabel.push(item)
      }
    },

    /*确认*/
    handleOk(){
      const params={
        labelIds: this.chooseLabel.map(item => item.id),
        outId: this.obj.id
      }
      addCorr(params).then(res=>{
        this.$modal.success("操作成功")
        this.$emit("ok")
      })
    },

    /*关闭弹窗*/
    handleClose(){
      this.chooseLabel = []
      this.$emit("close")
    },

    /*页码改变时触发*/
    handleCurrentChange(val) {
      this.pageNum=val
      this.queryData()
    },

  }
}
</script>

<style scoped>
/deep/.el-dialog__body {
  padding: 18px 20px;
}
/deep/.el-dialog__header {
  padding: 0px;
}
.choose-center{
  display: flex;
  margin-top: 10px;
}
.choose-item-tag{
  align-items: center;
  border: 1px solid #e4e6eb;
  border-radius: 50px;
  color: #0e0101;
  display: flex;
  font-size: 12px;
  font-weight: 400;
  height: 24px;
  margin-right: 6px;
  padding: 6px 8px;
}
.dialog-bottom{
  margin-top: 20px;
  border-radius: 4px;
  border: 1px solid #e4e6eb;
  background: #fff;
  padding: 16px 4px 4px 16px;
}
.item-li-tag{
  cursor: pointer;
  display: inline-block;
  padding: 2px 10px;
  color: #8a919f;
  font-size: 13px;
  font-style: normal;
  font-weight: 400;
  line-height: 22px;
  border-radius: 4px;
  background: #f2f3f5;
  margin-right: 12px;
  margin-bottom: 12px;
  transition: all .2s;
}
.item-li-tag:hover{
  background-color: #aadcf6;
}

.li-back{
  background-color: #aadcf6;
}
a:hover{
  color: #59b9ee;
}
</style>
