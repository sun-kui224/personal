import request from '@/api/request'

/**
 * 获取记录
 * @param ids
 * @returns {AxiosPromise}
 */
export function getInfo(id) {
    return request({
        url: '/study/records/' + id,
        method: 'get',
    })
}

/**
 * 新增记录
 * @param ids
 * @returns {AxiosPromise}
 */
export function addBugRecord(data) {
    return request({
        url: '/study/records',
        method: 'post',
        data: data
    })
}

/**
 * 修改记录
 * @param data
 * @returns {AxiosPromise}
 */
export function editBugRecord(data) {
    return request({
        url: '/study/records',
        method: 'put',
        data: data
    })
}

/**
 * 删除记录
 * @param ids
 * @returns {AxiosPromise}
 */
export function deleteBugRecord(id) {
    return request({
        url: '/study/records/' + id,
        method: 'delete',
    })
}
