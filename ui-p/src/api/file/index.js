import request from '@/api/request'

/**
 * 上传文件
 * @param ids
 * @returns {AxiosPromise}
 */
export function addFile(data) {
    return request({
        url: '/system/file',
        method: 'post',
        data: data
    })
}

/**
 * 删除文件
 * @param ids
 * @returns {AxiosPromise}
 */
export function deleteFile(ids) {
    return request({
        url: '/system/file/' + ids,
        method: 'delete',
    })
}

/**
 * 修改文件名
 * @param data
 * @returns {AxiosPromise}
 */
export function editName(data) {
    return request({
        url: '/system/file/editName',
        method: 'post',
        data: data
    })
}

/**
 * 获取文件详情
 * @param data
 * @returns {AxiosPromise}
 */
export function queryFileInfo(id) {
    return request({
        url: '/system/file/' + id,
        method: 'get',
    })
}
