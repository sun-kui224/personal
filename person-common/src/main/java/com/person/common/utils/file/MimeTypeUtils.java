package com.person.common.utils.file;

import java.util.Arrays;

/**
 * 媒体类型
 * @author sk
 * @date 2024/5/24
 */
public class MimeTypeUtils {

    //图片
    public static final String[] IMAGE_EXT = {  "bmp", "gif", "jpg", "jpeg", "png" };

    //文档
    public static final String[] DOCUMENT_EXT = {"doc", "docx", "pdf","xls", "xlsx", "ppt", "pptx", "html", "dotx", "txt"};

    //视频
    public static final String[] VIDEO_EXT = {  "mp4", "avi" };

    //音频
    public static final String[] AUDIO_EXT = {  "mp3", "wav", "aiff", "flac", "alac" ,"wma"};

    /**
     * 允许下载的文件后缀
     */
    public static final String[] DEFAULT_ALLOWED_EXTENSION = {
            // 图片
            "bmp", "gif", "jpg", "jpeg", "png",
            // 音频
            "mp3", "wav", "aiff", "flac", "alac" ,"wma",
            //视频
            "mp4", "avi",
            // 文档
            "doc", "docx", "pdf","xls", "xlsx", "ppt", "pptx", "html", "dotx", "txt",
            // 其他格式
            "rar", "zip", "gz", "bz2"};
    public static int getFileType(String ext){
        if(Arrays.asList(IMAGE_EXT).indexOf(ext) != -1){
            return 2;
        }
        if(Arrays.asList(DOCUMENT_EXT).indexOf(ext) != -1){
            return 3;
        }
        if(Arrays.asList(VIDEO_EXT).indexOf(ext) != -1){
            return 4;
        }
        if(Arrays.asList(AUDIO_EXT).indexOf(ext) != -1){
            return 5;
        }
        return 6;
    }
}
