import request from '@/api/request'

// 查询菜单列表
export function queryPaginationList(pathUrl,queryParams) {
    return request({
        url: pathUrl,
        method: 'get',
        params: queryParams
    })
}
