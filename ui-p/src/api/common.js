import request from '@/api/request'

/**
 * 上传文件（单个）
 */
export function upload(file) {
    const formData = new FormData();
    formData.append('file', file);
    return request({
        url: '/common/file/upload',
        method: 'post',
        data: formData,
        headers: { 'Content-Type': 'multipart/form-data' }, // 添加此行
    })
}


/**
 * 上传文件（多个）
 */
export function uploadFiles(files) {
    return request({
        url: '/common/file/uploadFiles',
        method: 'post',
        data: files,
        headers: { 'Content-Type': 'multipart/form-data' }, // 添加此行
    })
}


/**
 * 下载文件 fileUrl和id同时存在时，优先使用fileUrl
 * @param fileUrl     文件路径
 * @param id          文件id
 * @param delete      是否删除源文件，默认false
 * @returns {AxiosPromise}
 */
export function downloadFile(query) {
    return new Promise((resolve, reject) => {
        request({
            url: '/common/file/download?fileUrl='+encodeURIComponent(query.fileUrl),
            method: 'get',
            responseType: 'blob', // 重要！设置响应类型为blob，以便处理二进制数据
            headers: {
                'Content-Type': 'application/octet-stream', // 根据后端要求，可能需要设置Content-Type为application/octet-stream
            },
        }).then((response) => {
            console.log(response)
            const blob = new Blob([response.data]);
            const fileName = response.headers['download-filename']
            saveFileFromBlob(blob, fileName);
            resolve();
        }).catch(reject);
    });
}

function saveFileFromBlob(blob, fileName) {
    const url = URL.createObjectURL(blob);
    const link = document.createElement('a');
    link.href = url;
    link.download = fileName;
    link.style.display = 'none';
    document.body.appendChild(link);
    link.click();
    document.body.removeChild(link);
    URL.revokeObjectURL(url); // 释放URL引用，节省内存
}
