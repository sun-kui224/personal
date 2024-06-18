import request from '@/api/request'

/**
 * 查询菜单列表
 * @param query
 * @returns {AxiosPromise}
 */
export function queryMenuList(query) {
    return request({
        url: '/system/menu/list',
        method: 'get',
        params: query
    })
}

/**
 * 查询菜单详情
 */
export function getInfo(id) {
    return request({
        url: '/system/menu/' + id,
        method: 'get',
    })
}

/**
 * 删除菜单
 * @param query
 * @returns {AxiosPromise}
 */
export function delMenu(id) {
    return request({
        url: '/system/menu/' + id,
        method: 'delete'
    })
}

/**
 * 停用菜单
 * @param query
 * @returns {AxiosPromise}
 */
export function stopMenu(id) {
    return request({
        url: '/system/menu/stopMenu/' + id,
        method: 'put'
    })
}

/**
 * 启用菜单
 * @param query
 * @returns {AxiosPromise}
 */
export function startMenu(id) {
    return request({
        url: '/system/menu/startMenu/' + id,
        method: 'put'
    })
}

/**
 * 添加菜单
 * @param data
 * @returns {AxiosPromise}
 */
export function addMenu(data) {
    return request({
        url: '/system/menu',
        method: 'post',
        data: data
    })
}

/**
 * 修改菜单
 * @param data
 * @returns {AxiosPromise}
 */
export function updateMenu(data) {
    return request({
        url: '/system/menu',
        method: 'put',
        data: data
    })
}
