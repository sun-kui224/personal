package com.person.system.controller;

import com.person.common.base.domain.AjaxResult;
import com.person.common.config.ApplicationConfig;
import com.person.common.domain.entity.FileEntity;
import com.person.common.utils.file.FileUtils;
import com.person.common.utils.file.MimeTypeUtils;
import com.person.system.commonService.FileService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 公共操作-文件上传和下载
 * @author sk
 * @date 2024/5/24
 */
@Api(tags = "上传/下载")
@RestController
@RequestMapping("/common/file")
public class CommonController {

    private static final Logger log = LoggerFactory.getLogger(CommonController.class);

    @Autowired
    private FileService fileService;

    /**
     * 下载
     * @param fileUrl  文件名
     * @param delete    是否删除文件，默认false
     * @param response
     * @param request
     */
    @GetMapping("/download")
    public void fileDownload(
            @RequestParam("fileUrl")String fileUrl,
            @RequestParam(value = "delete", defaultValue = "false")Boolean delete,
            HttpServletResponse response,
            HttpServletRequest request) {
        try {
            fileUrl = fileUrl.substring(ApplicationConfig.getPrefix().length());
            String realFileName = System.currentTimeMillis() + fileUrl.substring(fileUrl.indexOf("_") + 1);
            setResponseHeader(response, realFileName);
            fileService.downLoad(fileUrl,response.getOutputStream());
            if (delete) {
                fileService.delete(fileUrl);
            }
        }
        catch (Exception e)
        {
            log.error("下载文件失败", e);
        }
    }

    /**
     * 上传（单个）
     */
    @PostMapping("/upload")
    public AjaxResult uploadFile(MultipartFile file) throws Exception {
        try {
            String upload = fileService.upload(file);
            String fileExtension = FileUtils.getFileExtension(file.getOriginalFilename());
            FileEntity entry = new FileEntity();
            entry.setFileName(FileUtils.getFilePrefix(file.getOriginalFilename())); //名称
            entry.setFileUrl(ApplicationConfig.getPrefix()+upload);                 //路径
            entry.setSize(file.getSize());                                          //大小
            entry.setType(MimeTypeUtils.getFileType(fileExtension));                //类型
            entry.setFileExt(fileExtension);                                        //后缀
            return AjaxResult.success(entry);
        }
        catch (Exception e) {
            return AjaxResult.error(e.getMessage());
        }
    }

    /**
     * 上传（多个）
     */
    @PostMapping("/uploadFiles")
    public AjaxResult uploadFile(MultipartFile[] file) throws Exception {
        try {
            List<FileEntity> list = new ArrayList<>();
            for(MultipartFile f: file){
                String upload = fileService.upload(f);
                FileEntity entry = new FileEntity();
                String fileExtension = FileUtils.getFileExtension(f.getOriginalFilename());
                entry.setFileName(FileUtils.getFilePrefix(f.getOriginalFilename()));
                entry.setFileUrl(ApplicationConfig.getPrefix()+upload);
                entry.setSize(f.getSize());
                entry.setType(MimeTypeUtils.getFileType(fileExtension));
                entry.setFileExt(fileExtension);
                list.add(entry);
            }
            AjaxResult ajax = AjaxResult.success(list);
            return ajax;
        }
        catch (Exception e) {
            return AjaxResult.error(e.getMessage());
        }
    }

    private void setResponseHeader(HttpServletResponse response, String fileName) throws IOException
    {
        response.reset();
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Expose-Headers", "Content-Disposition,download-filename");
        response.setHeader("Content-Disposition", "attachment; filename="+fileName);
        response.setContentType("application/octet-stream; charset=UTF-8");
        response.setHeader("download-filename", fileName);
    }

}
