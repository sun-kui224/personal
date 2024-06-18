package com.person.system.commonService;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.OutputStream;
import java.util.Date;

/**
 * 文件服务接口
 * @author sk
 * @date 2024/5/24
 */
public interface FileService {

    /**
     * 文件下载
     * @param fileName 文件名称
     * @param out      输出流
     * @return
     */
    public void downLoad(String fileName, OutputStream out) throws Exception;

    /**
     * 文件上传
     * @param file 文件
     * @return 文件名称
     */
    public String upload(MultipartFile file) throws Exception;

    /**
     * 删除文件
     * @param fileName
     * @return
     */
    public boolean delete(String fileName) throws Exception;

    /**
     * 编码文件名
     */
    default String extractFilename(MultipartFile file) {
        String fName = file.getOriginalFilename();
        Date now = new Date();
        String param1 = DateFormatUtils.format(now, "yyyy/MM/dd");

        String profile = fName.substring(0,fName.lastIndexOf("."));

        // 使用当前时间戳的后16位作为序列号
        long timestamp = now.getTime();
        String serialNum = String.format("%016d", timestamp & 0xFFFF_FFFFL); // 取最后16位

        String ext = fName.substring(fName.lastIndexOf(".")+1);

        String format = String.format("%s/%s_%s.%s", param1, profile, serialNum, ext);
        // 替换占位符，返回最终的文件名
        return format;
    }
}
