import request from '@/api/request'

/**
 * 获取工具详情
 * @param ids
 * @returns {AxiosPromise}
 */
export function getInfo(id) {
    return request({
        url: '/study/util/' + id,
        method: 'get',
    })
}

/**
 * 获取工具列表
 * @param data
 * @returns {AxiosPromise}
 */
export function getUtilList(query) {
    return request({
        url: '/study/util/list',
        method: 'get',
        params: query
    })
}

/**
 * 新增工具
 * @param ids
 * @returns {AxiosPromise}
 */
export function addUtil(data) {
    return request({
        url: '/study/util',
        method: 'post',
        data: data
    })
}

/**
 * 修改工具
 * @param data
 * @returns {AxiosPromise}
 */
export function editUtil(data) {
    return request({
        url: '/study/util',
        method: 'put',
        data: data
    })
}

/**
 * 删除工具
 * @param ids
 * @returns {AxiosPromise}
 */
export function deleteUtil(id) {
    return request({
        url: '/study/util/' + id,
        method: 'delete',
    })
}
