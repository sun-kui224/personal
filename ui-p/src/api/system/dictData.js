import request from '@/api/request'

/**
 * 查询字典数据详情
 */
export function getInfo(id) {
    return request({
        url: '/dict/data/' + id,
        method: 'get',
    })
}

/**
 * 查询字典数据集合
 * @param query
 * @returns {AxiosPromise}
 */
export function getList(query) {
    return request({
        url: '/dict/data/list',
        method: 'get',
        params: query
    })
}

/**
 * 删除字典数据
 * @param query
 * @returns {AxiosPromise}
 */
export function delDictData(id) {
    return request({
        url: '/dict/data/' + id,
        method: 'delete'
    })
}

/**
 * 停用字典
 * @param query
 * @returns {AxiosPromise}
 */
export function stopDictData(id) {
    return request({
        url: '/dict/data/stopDict/' + id,
        method: 'delete'
    })
}

/**
 * 启用字典
 * @param query
 * @returns {AxiosPromise}
 */
export function startDictData(id) {
    return request({
        url: '/dict/data/startDict/' + id,
        method: 'delete'
    })
}

/**
 * 添加字典
 * @param data
 * @returns {AxiosPromise}
 */
export function addDictData(data) {
    return request({
        url: '/dict/data',
        method: 'post',
        data: data
    })
}

/**
 * 修改字典
 * @param data
 * @returns {AxiosPromise}
 */
export function updateDictData(data) {
    return request({
        url: '/dict/data',
        method: 'put',
        data: data
    })
}
