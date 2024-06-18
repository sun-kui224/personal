import request from '@/api/request'

/**
 * 查询字典详情
 */
export function getInfo(id) {
    return request({
        url: '/dict/type/' + id,
        method: 'get',
    })
}

/**
 * 删除字典
 * @param query
 * @returns {AxiosPromise}
 */
export function delDict(id) {
    return request({
        url: '/dict/type/' + id,
        method: 'delete'
    })
}

/**
 * 停用字典
 * @param query
 * @returns {AxiosPromise}
 */
export function stopDict(id) {
    return request({
        url: '/dict/type/stopDict/' + id,
        method: 'delete'
    })
}

/**
 * 启用字典
 * @param query
 * @returns {AxiosPromise}
 */
export function startDict(id) {
    return request({
        url: '/dict/type/startDict/' + id,
        method: 'delete'
    })
}

/**
 * 添加字典
 * @param data
 * @returns {AxiosPromise}
 */
export function addDict(data) {
    return request({
        url: '/dict/type',
        method: 'post',
        data: data
    })
}

/**
 * 修改字典
 * @param data
 * @returns {AxiosPromise}
 */
export function updateDict(data) {
    return request({
        url: '/dict/type',
        method: 'put',
        data: data
    })
}
