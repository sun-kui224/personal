import request from '@/api/request'

/**
 * 获取书籍列表
 * @param data
 * @returns {AxiosPromise}
 */
export function getBookList(query) {
    return request({
        url: '/study/book/list',
        method: 'get',
        params: query
    })
}

/**
 * 新增书籍
 * @param ids
 * @returns {AxiosPromise}
 */
export function addBook(data) {
    return request({
        url: '/study/book',
        method: 'post',
        data: data
    })
}

/**
 * 修改书籍
 * @param data
 * @returns {AxiosPromise}
 */
export function editBook(data) {
    return request({
        url: '/study/book',
        method: 'put',
        data: data
    })
}

/**
 * 删除书籍
 * @param ids
 * @returns {AxiosPromise}
 */
export function deleteBook(id) {
    return request({
        url: '/study/book/' + id,
        method: 'delete',
    })
}
