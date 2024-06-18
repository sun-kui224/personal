import request from '@/api/request'

/**
 * 删除日志
 * @param query
 * @returns {AxiosPromise}
 */
export function delLog(ids) {
    return request({
        url: '/system/log/' + ids,
        method: 'delete'
    })
}

/**
 * 清空日志
 * @param ids
 * @returns {*}
 */
export function cleanLog() {
    return request({
        url: '/system/log/clean',
        method: 'delete'
    })
}
