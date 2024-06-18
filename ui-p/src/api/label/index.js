import request from '@/api/request'

/**
 * 获取标签详情
 * @param data
 * @returns {AxiosPromise}
 */
export function queryLabelInfo(id) {
    return request({
        url: '/system/label/' + id,
        method: 'get',
    })
}

/**
 * 获取标签集合
 * @param data
 * @returns {AxiosPromise}
 */
export function queryLabelList(query) {
    return request({
        url: '/system/label/list',
        method: 'get',
        params: query
    })
}

/**
 * 添加标签
 * @param ids
 * @returns {AxiosPromise}
 */
export function addLabel(data) {
    return request({
        url: '/system/label',
        method: 'post',
        data: data
    })
}

/**
 * 修改标签
 * @param data
 * @returns {AxiosPromise}
 */
export function editLabel(data) {
    return request({
        url: '/system/label',
        method: 'put',
        data: data
    })
}

/**
 * 删除标签
 * @param ids
 * @returns {AxiosPromise}
 */
export function deleteLabel(id) {
    return request({
        url: '/system/label/' + id,
        method: 'delete',
    })
}

/**
 * 新增关联
 * @param ids
 * @returns {AxiosPromise}
 */
export function addCorr(data) {
    return request({
        url: '/system/label/corr',
        method: 'post',
        data: data
    })
}
