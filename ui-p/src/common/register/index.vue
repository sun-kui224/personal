<template>
	<div class="login-wrapper">
		<div class="modal">
			<el-form :model="user" status-icon :rules="rules" ref="userForm">
				<div class="title">注册</div>
				<el-form-item prop="username">
					<el-input type="text" prefix-icon="el-icon-user" placeholder="请输入用户名" v-model="user.username" />
				</el-form-item>
				<el-form-item prop="password">
					<el-input type="password" prefix-icon="el-icon-view" placeholder="请输入密码" v-model="user.password" />
				</el-form-item>
        <el-form-item prop="password">
          <el-input type="password" prefix-icon="el-icon-view" placeholder="请确认密码" v-model="user.newPassword" />
        </el-form-item>
				<el-form-item>
					<el-button type="primary" class="btn-left" @click="register">注册</el-button>
					<el-button type="primary" class="btn-right" @click="login">登录</el-button>
				</el-form-item>
			</el-form>
		</div>
	</div>
</template>
<script>

export default {
	name: 'register',
	data() {
		return {
			user: {
				username: "",
				password: "",
        newPassword: ""
			},
			rules: {
				username: [
					{ required: true, message: '请输入用户名', trigger: 'blur' }
				],
				password: [
					{ required: true, message: '请输入密码', trigger: 'blur' }
				],
        newPassword: [
          { required: true, message: '请确认密码', trigger: 'blur' }
        ]
			}
		}
	},
	methods: {
    register() {
			this.$refs.userForm.validate(( valid ) => {
			    if(valid) {
            if(this.user['password'] !== this.user['newPassword']){
              this.$modal.error("两次密码不一致");
              return false;
            }
            this.$store.dispatch('Register', { username: this.user['username'], password: this.user['password'] })
                .then(() => {
                  //注册成功跳转登录页
                  this.$router.replace({ path: "/login",query: {username: this.user['username'], password: this.user['password']} })
                })
                .catch((error) => {
                  this.$modal.error("注册失败");
                });
			    }
          else return false
			})
		},
    login(){
      this.$router.replace({ path: "/login" })
    }
	}
}
</script>
<style scoped>
.login-wrapper {
	display: flex;
	flex-direction: row;
	align-items: center;
	justify-content: center;
	background-color: #fff;
  background-image: url('../../assets/images/long.png'); /* 添加这一行 */
  background-size: cover; /* 保持图片填充背景 */
	width: 100vw;
	height: 100vh;
}
.modal {
	width: 360px;
	height: 380px;
	box-shadow: 0 0 10px 5px #ddd;
	padding: 50px;
	border-radius: 5px;
}
.title {
	width: 100%;
	text-align: center;
	line-height: 1.5;
	font-size: 50px;
	margin-bottom: 30px;
}
.btn-left {
  width: 30%;
  float: left;
  margin-left: 10%;
}
.btn-right {
  width: 30%;
  float: right;
  margin-right: 10%;
}
.toast{
	width: 100%;
	display: flex;
	flex-direction: row;
	align-items: center;
	justify-content: space-between;
	height: 50px;
}
</style>
