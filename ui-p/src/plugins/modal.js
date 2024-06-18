import { Message, MessageBox, Loading } from 'element-ui'

let loadingInstance;

export default {
    // 消息提示
    msg(content) {
        Message.info(content)
    },
    // 错误消息
    error(content) {
        Message.error(content)
    },
    // 成功消息
    success(content) {
        Message.success(content)
    },
    // 警告消息
    warn(content){
        Message.warning(content)
    },
    // 确认窗体
    confirm(content) {
        return MessageBox.confirm(content, "系统提示", {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: "warning",
        })
    },
    // 提交内容
    prompt(content) {
        return MessageBox.prompt(content, "系统提示", {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: "warning",
        })
    },
    // 打开遮罩层
    loading(content) {
        loadingInstance = Loading.service({
            lock: true,
            text: content,
            spinner: "el-icon-loading",
            background: "rgba(0, 0, 0, 0.7)",
        })
    },
    // 关闭遮罩层
    closeLoading() {
        loadingInstance.close();
    }
}
