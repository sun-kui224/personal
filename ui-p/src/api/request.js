import axios from 'axios'
import store from '@/store'
import {Notification , Message , MessageBox} from 'element-ui'
import { getToken } from '@/utils/auth'

export let isRelogin = { show: false };

// 创建axios实例
const service = axios.create({
    // axios中请求配置有baseURL选项，表示请求URL公共部分
    baseURL: process.env.VUE_APP_BASE_API,
    // 超时
    timeout: 10000,
    headers:{
        'Content-Type':'application/json;charset=utf-8'
    }
})

// request拦截器
service.interceptors.request.use(config => {
    // 是否需要设置 token
    const isToken = (config.headers || {}).isToken === false
    if (getToken() && !isToken) {
        config.headers['Authorization'] = 'Bearer ' + getToken() // 让每个请求携带自定义token 请根据实际情况自行修改
    }
    // get请求映射params参数
    if (config.method === 'get' && config.params) {
        let url = config.url + '?' + tansParams(config.params);
        url = url.slice(0, -1);
        config.params = {};
        config.url = url;
    }
    return config;
}, error => {
    // 对请求错误做些什么
    console.error(error);
});

// response拦截器
service.interceptors.response.use(
    response => {
        /**
         * 这里可以根据自己的需求对返回的数据进行处理
         * 例如，统一处理错误码，或者统一处理返回的data
         */
        const res = response.data;
        if (response.request.responseType ===  'blob' && response.status === 200) {
            return response
        }
        if(res.code === 401) {
            if (!isRelogin.show) {
                isRelogin.show = true;
                MessageBox.confirm('登录状态已过期，您可以继续留在该页面，或者重新登录', '系统提示', { confirmButtonText: '重新登录', cancelButtonText: '取消', type: 'warning' }).then(() => {
                    isRelogin.show = false;
                    store.dispatch('LogOut').then(() => {
                        location.href = '/index';
                    })
                }).catch(() => {
                    isRelogin.show = false;
                });
            }
            return Promise.reject('无效的会话，或者会话已过期，请重新登录。')
        }else if(res.code === 601) {
            Message({ message: res.msg, type: 'warning' })
            return Promise.reject('error')
        } else if (res.code === 500) {
            Message({ message: res.msg, type: 'error' })
            return Promise.reject(new Error(res.msg))
        }else if (res.code !== 200) {   //其他错误码
            Notification.error({ title: res.msg })
            return Promise.reject('error')
        }else {
            return res
        }
    },
    error => {
        // 对响应错误做点什么，例如显示错误信息
        console.error('Error:', error);
        return Promise.reject(error);
    }
);

function tansParams(params) {
    let result = ''
    for (const propName of Object.keys(params)) {
        const value = params[propName];
        var part = encodeURIComponent(propName) + "=";
        if (value !== null && value !== "" && typeof (value) !== "undefined") {
            if (typeof value === 'object') {
                for (const key of Object.keys(value)) {
                    if (value[key] !== null && value[key] !== "" && typeof (value[key]) !== 'undefined') {
                        let params = propName + '[' + key + ']';
                        var subPart = encodeURIComponent(params) + "=";
                        result += subPart + encodeURIComponent(value[key]) + "&";
                    }
                }
            } else {
                result += part + encodeURIComponent(value) + "&";
            }
        }
    }
    return result
}
export default service
