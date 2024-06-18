<template>
  <div class="rightMain">
    <div>
      <el-button icon="el-icon-upload el-icon--right" round class="botton-cl" size="small" @click="uploadFile">上传</el-button>

      <el-input placeholder="请输入文件名" v-model="gjzString" style="width: 500px">
        <el-button type="primary" slot="append" icon="el-icon-search" style="background-color: #cc9115;color:#ffffff;border-radius: 0px" @click="queryFileList()">搜索</el-button>
      </el-input>

      <div style="display: inline-block;float: right">
        <el-tooltip class="item" effect="dark" content="删除" placement="bottom" v-if="delVisble">
          <el-button icon="el-icon-delete" @click="deleteFiles" size="small" circle type="danger"/>
        </el-tooltip>
        <el-button @click="delVisble = !delVisble" size="small">{{ !delVisble ?'批量操作':'取消操作'}}</el-button>
        <el-tooltip class="item" effect="dark" content="视图" placement="bottom" >
          <el-button size="small" @click="toggleIcon = !toggleIcon">
            <i :class="toggleIcon?'el-icon-menu':'el-icon-s-operation'" />
          </el-button>
        </el-tooltip>
      </div>
    </div>

    <el-divider></el-divider>

    <div class="main-file">

      <!--网格展示-->
      <fileGrid ref="file1" v-if="toggleIcon" :modeType="delVisble" :dataList="dataList"
                @selected-files-changed="handleSelectedFilesChanged"></fileGrid>

      <!--列表展示-->
      <fileList ref="file1" v-else :modeType="delVisble" :dataList="dataList"
                @selected-files-changed="handleSelectedFilesChanged"></fileList>

    </div>

    <div style="text-align: right">
      <pagination
          path-url="/system/file/list"
          ref="pag1"
          :limit="50"
          @pagination="queryFileList"
      />
    </div>

    <!--上传窗口-->
    <fileUploadModel :visible="fileVisible"
                     @close=" fileVisible = false"
                     @ok="uploadOk()"/>
  </div>
</template>

<script>
import fileGrid from "./components/fileGrid.vue";
import fileUploadModel from "./components/fileUploadModel.vue";
import fileList from "./components/fileList.vue";
import { deleteFile } from "@/api/file"
export default {
  name: 'fileRight',
  components: { fileGrid , fileUploadModel , fileList },
  data() {
    return {
      fileVisible: false,    //控制弹窗
      selectType: 1,         //查询类型
      gjzString: undefined,  //查询关键字
      delVisble: false,      //控制批量
      selectedIds: [],       //子组件中选中的文件id集合
      dataList: [] ,         //文件数据
      typeList:[
        {value: 1,label: '全部',icon: 'menu-file'},
        {value: 2,label: '图片',icon: 'file-image'},
        {value: 3,label: '文档',icon: 'file-txt'},
        {value: 4,label: '视频',icon: 'file-video'},
        {value: 5,label: '音频',icon: 'file-music'},
        {value: 6,label: '其他',icon: 'file-other'},
      ],
      toggleIcon: true,    //是控制网格还是列表
    }
  },
  mounted() {

  },
  methods: {
    /*查询文件数据*/
    queryFileList(key){
      if(key){
        this.selectType = key
      }
      let queryParam = {
        fileName: this.gjzString,
        type: this.selectType
      }
      this.$refs.pag1.queryData(queryParam).then(res=>{
        this.dataList = res
      })
    },

    /*打开文件上传窗口*/
    uploadFile(){
      this.fileVisible = true
    },

    /*批量删除*/
    deleteFiles(){
      if(this.selectedIds!=undefined && this.selectedIds.length>0){
         this.$modal.confirm('是否确认删除选中的文件？').then(function() {
           return deleteFile(this.selectedIds);
         }.bind(this)).then(() => {
           this.$modal.success("删除成功");
           this.queryFileList()
         });
      }else{
        this.$modal.error("请选择文件");
      }
    },

    /*处理子组件选中文件的变化*/
    handleSelectedFilesChanged(selectedIds){
      this.selectedIds = selectedIds;
    },

    /*文件上传成功回调*/
    uploadOk(){
      this.fileVisible = false
      this.queryFileList()
    },

  }
}
</script>
<style scoped>
.rightMain {
  width: 100%;
  height: 100%;
  background-color: #ffffff;
  padding: 18px 20px;
  border-radius: 4px;
}
.botton-cl{
  background-color: #cc9115;
  border: 0px;
  color:#ffffff;
  margin-right: 10px;
}
.rightMain .el-input {
  line-height: 1.5em; /* 调整此值以匹配输入框的高度 */
}

.rightMain .botton-cl {
  line-height: normal; /* 重置line-height，因为按钮的内联内容不需要垂直居中 */
}

/deep/.pagination-container {
  margin-top: 0px;
}
.main-file{
  height: calc(100% - 180px);
}
</style>
