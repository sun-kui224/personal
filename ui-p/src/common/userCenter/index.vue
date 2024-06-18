<template>
  <div class="container">
    <el-card class="box-left" :style="{ width: '30%' }">
      <div class="centered-content">
        <div class="flex-item">
          <userAvatar :avatar="avatar"/>
        </div>
        <div class="flex-item">
          <div class="name">{{ nickName }}</div>
        </div>
        <div class="flex-item">
          <p class="desc">静下心来，偶尔摸鱼</p>
        </div>
      </div>
      <div>
        <div class="item">
          <i class="el-icon-position"/>
          <span>后端JAVA开发工程师</span>
        </div>
        <div class="item">
          <i class="el-icon-location-outline"/>
          <span>成都市</span>
        </div>
      </div>
      <el-divider></el-divider>
      <div>
        <div style="font-size: 16px;margin-bottom: 10px">标签</div>
        <el-tag type="info" size="mini">vue</el-tag> <el-tag type="info" size="mini">springboot</el-tag> <el-tag type="info" size="mini">mysql</el-tag>
        <el-tag type="info" size="mini">游戏</el-tag> <el-tag type="info" size="mini">小说</el-tag>
      </div>

      <el-descriptions  :column="1" style="margin-top: 20px" >
        <el-descriptions-item label="星座">天平座</el-descriptions-item>
        <el-descriptions-item label="生日">8月22日</el-descriptions-item>
        <el-descriptions-item label="性别">男</el-descriptions-item>
        <el-descriptions-item label="爱好">
          <template v-for="(subTagList, index) in chunkedTagList">
            <el-row :key="index" style="margin-bottom: 10px">
              <template v-for="(tag, i) in subTagList">
                <el-tag size="small" :key="tag" :style="tagColors[i]">{{ tag }}</el-tag>
              </template>
            </el-row>
          </template>
        </el-descriptions-item>
      </el-descriptions>
    </el-card>

    <el-card class="box-right" :style="{ width: '70%' }" >
      <el-form :model="form" :rules="rules" ref="ruleForm" label-width="100px" v-if="isEdit">
        <el-form-item label="用户名称" prop="nickName">
          <el-input v-model="form.nickName"></el-input>
        </el-form-item>
        <el-form-item label="岗位" prop="post">
          <el-input v-model="form.post"></el-input>
        </el-form-item>
        <el-form-item label="出生年月" prop="birthDate">
          <el-time-picker placeholder="选择时间" v-model="form.birthDate" style="width: 100%;"></el-time-picker>
        </el-form-item>
        <el-form-item label="性别" prop="gender">
          <el-input v-model="form.gender"></el-input>
        </el-form-item>
        <el-form-item label="个性签名" prop="signature">
          <el-input v-model="form.signature"></el-input>
        </el-form-item>
        <el-form-item label="兴趣标签" prop="interestTag">
          <template v-for="(tag, i) in form.interestTag">
            <el-tag size="small" :key="tag" >{{ tag }}</el-tag>
          </template>
        </el-form-item>
        <el-form-item  label="个性图片" prop="pictures">
          <template v-for="(tag, i) in form.pictures">
            <el-image style="width: 100px; height: 100px;margin-right: 10px" :src="tag" fit="fill"></el-image>
          </template>
        </el-form-item>
      </el-form>

      <div v-if="!isEdit" class="item-context">
        <div class="edit-tiem">
          <div class="edit-item-left">用户名称</div>
          <div class="edit-item-right">{{ form.nickName }}</div>
        </div>
        <div class="edit-tiem">
          <div class="edit-item-left">岗位</div>
          <div class="edit-item-right">{{ form.post }}</div>
        </div>
        <div class="edit-tiem">
          <div class="edit-item-left">出生年月</div>
          <div class="edit-item-right">{{ form.birthDate }}</div>
        </div>
        <div class="edit-tiem">
          <div class="edit-item-left">性别</div>
          <div class="edit-item-right">{{ form.gender }}</div>
        </div>
        <div class="edit-tiem">
          <div class="edit-item-left">个性签名</div>
          <div class="edit-item-right">{{ form.signature }}</div>
        </div>
        <div class="edit-tiem">
          <div class="edit-item-left">兴趣标签</div>
          <div class="edit-item-right">
            <template v-for="(tag, i) in form.interestTag">
              <el-tag size="small" :key="tag" >{{ tag }}</el-tag>
            </template>
          </div>
        </div>
        <div class="edit-tiem" style=" display: grid;grid-template-columns: 100px auto;grid-gap: 0px;margin-left: -10px">
          <div class="edit-item-left" style=" align-self: start;">个性图片</div>
          <div class="edit-item-right">
            <template v-for="(tag, i) in form.pictures">
              <el-image style="width: 100px; height: 100px;margin-right: 10px" :src="tag" fit="fill"></el-image>
            </template>
          </div>
        </div>
      </div>

      <div style="text-align: right;">
        <div>
          <el-button size="mini" v-if="isEdit" type="primary" @click="onSubmit">保存</el-button>
          <el-button size="mini" v-if="!isEdit" @click="isEdit = true">编辑</el-button>
          <el-button size="mini" v-if="isEdit" @click="isEdit = false">取消</el-button>
        </div>
      </div>


    </el-card>
  </div>
</template>
<script>
import {mapGetters} from "vuex";
import userAvatar from "./userAvatar";
export default {
	name: 'userCenter',
  components: {userAvatar},
	data() {
		return {
      isEdit: false , //是否是编辑状态
      tagColors: [
          {color:'#722ED1',backgroundColor:'#F5E8FF'},
          {color:'#F5319D',backgroundColor:'#fFE8F1'},
          {color:'#42b97d',backgroundColor:'#88eedd'},
      ],
      tagList: ['LoL','率土之滨','遮天','秦时明月','青花瓷','炒股'],
      form: {
        avatar: require("@/assets/images/profile.jpg"),
        nickName: '小米',
        post: 'Java后端开发师',
        birthDate: undefined,
        gender: '男',
        signature: '静下心来，偶尔摸鱼',
        interestTag: ['率土之滨','遮天'],
        pictures: [require("@/assets/images/美女1.png"),require("@/assets/images/美女2.jpg")],
      },
      rules: {},
		}
	},
  mounted() {

  },
  computed: {
    ...mapGetters([
      'avatar',
      'nickName'
    ]),
    chunkedTagList() {
      let result = [];
      for (let i = 0; i < this.tagList.length; i += 3) {
        result.push(this.tagList.slice(i, i + 3));
      }
      return result;
    }
  },
	methods: {
    /* 提交 */
    onSubmit(){

    }
	}
}
</script>
<style scoped>
.container{
  display: flex;
  justify-content: space-between;
}
.box-right {
  box-sizing: border-box;
  padding: 2px; /* 添加内边距 */
  margin-left: 20px;
}
.edit-tiem{
  display: flex; /* 添加这一行以实现并列布局 */
  align-items: center; /* 使内容垂直居中 */
  text-align: left; /* 确保文本左对齐，因为flex布局已处理并列 */
  line-height: 40px;
  padding: 0px;
  margin: 0px;
  margin-bottom: 22px;
}

.edit-item-left {
  width: 88px;
  text-align: right;

}

.edit-item-right {
  margin-left: 20px;
  flex: 1; /* 让右侧内容填充剩余空间 */
}
.item-context {
  color: #606266;
  font-size: 14px;
}
.box-left {
  box-sizing: border-box;
  padding: 2px; /* 添加内边距 */
  font-size: 14px;
}
.centered-content {
  display: flex;
  flex-direction: column; /* 设置主轴方向为垂直 */
  align-items: center;
}

.flex-item {
  height: 100%; /* 充满父元素高度 */
  text-align: center; /* 每个元素内部居中 */
}

.name{
  margin: 0; /* 移除内边距以保持居中 */
  font-size: 20px;
  font-weight: bolder;
  line-height: 1.5;
  margin: 8px;
  color: #121314;
}
.desc {
  margin: 0; /* 移除内边距以保持居中 */
  font-size: 12px;
  color: #909399;
}

.item {
  display: flex; /* 使用flex布局 */
  align-items: center; /* 垂直居中 */
  margin-bottom: 16px;
}

.item > * {
  margin-right: 8px; /* 添加右侧内边距以创建间隔 */
}

.item > p {
  flex: 1; /* 让文本占满剩余空间 */
}
/deep/.el-tag.el-tag--info {
  color: #303133;
}
/deep/ .el-tag{
  margin-right: 10px;
  border: 0px;
}
/deep/.el-descriptions__table .el-tag{
  border: 0px;
}

/deep/.el-descriptions-item__container .el-descriptions-item__content, .el-descriptions-item__container .el-descriptions-item__label {
  display: inline;
}

/deep/.el-card__body, .el-main {
  margin: 0px;
  padding-left: 20px;
  padding-right: 20px;
  padding-top: 20px;
}
</style>
