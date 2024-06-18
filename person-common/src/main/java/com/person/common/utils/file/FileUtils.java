package com.person.common.utils.file;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.*;

/**
 * 文件工具类
 * @author sk
 * @date 2024/5/24
 */
public class FileUtils {

    /**
     * 检查是否是运行下载的文件类型
     * @param resource
     * @return
     */
    public static boolean checkAllowDownload(String resource) {
        // 禁止目录上跳级别
        if (StringUtils.contains(resource, ".."))
        {
            return false;
        }

        if (ArrayUtils.contains(MimeTypeUtils.DEFAULT_ALLOWED_EXTENSION, getFileExtension(resource)))
        {
            return true;
        }
        return false;
    }

    /**
     * 校验文件是否可以上传
     * @param resource
     * @return
     */
    public static boolean checkAllowUpload(String resource) {
        if(resource == null ||resource.trim().length() == 0){
            return false;
        }
        int i = resource.lastIndexOf(".");
        if(i<=0){
            return false;
        }
        String ext = resource.substring(resource.lastIndexOf(".")+1);
        if (ArrayUtils.contains(MimeTypeUtils.DEFAULT_ALLOWED_EXTENSION, ext))
        {
            return true;
        }
        return false;
    }

    /**
     * 获取文件后缀
     * @param resource
     * @return
     */
    public static String getFileExtension(String resource){
        int dotIndex = resource.lastIndexOf('.');
        if (dotIndex > 0) {
            return StringUtils.lowerCase(resource.substring(dotIndex + 1));
        }
        return null;
    }

    /**
     * 获取文件名称
     * @param resource
     * @return
     */
    public static String getFilePrefix(String resource){
        int dotIndex = resource.lastIndexOf('.');
        if (dotIndex > 0) {
            return StringUtils.lowerCase(resource.substring(0,dotIndex));
        }
        return null;
    }
}
