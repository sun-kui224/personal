import request from '@/api/request'

/**
 * 获取网址列表
 * @param data
 * @returns {AxiosPromise}
 */
export function getWebList(query) {
    return request({
        url: '/study/website/list',
        method: 'get',
        params: query
    })
}

/**
 * 新增网址
 * @param ids
 * @returns {AxiosPromise}
 */
export function addWeb(data) {
    return request({
        url: '/study/website',
        method: 'post',
        data: data
    })
}

/**
 * 修改网址
 * @param data
 * @returns {AxiosPromise}
 */
export function editWeb(data) {
    return request({
        url: '/study/website',
        method: 'put',
        data: data
    })
}

/**
 * 删除网址
 * @param ids
 * @returns {AxiosPromise}
 */
export function deleteWeb(id) {
    return request({
        url: '/study/website/' + id,
        method: 'delete',
    })
}
