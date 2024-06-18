package com.person.system.commonService.file;

import com.person.common.config.ApplicationConfig;
import com.person.common.utils.file.FileUtils;
import com.person.system.commonService.FileService;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

/**
 * 本地文件服务类
 * @author sk
 * @date 2024/5/24
 */
public class LocalFileService implements FileService {

    @Override
    public void downLoad(String fileName, OutputStream out) throws Exception {
        if (!FileUtils.checkAllowDownload(fileName)) {
            throw new Exception(String.format("文件%s非法，不允许下载。", fileName));
        }
        String filePath = ApplicationConfig.getPath() + File.separator + fileName;
        File file = new File(filePath);
        if (!file.exists()) {
            throw new FileNotFoundException(String.format("文件%s不存在", filePath));
        }
        // 创建文件输入流，用于读取文件内容
        try (FileInputStream fis = new FileInputStream(filePath)) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }
            out.flush();
        } catch (IOException e) {
            throw new IOException("文件写入输出流时发生错误", e);
        }
    }

    @Override
    public String upload(MultipartFile file) throws Exception {
        try {
            String originalFilename = file.getOriginalFilename();
            if (!FileUtils.checkAllowUpload(originalFilename)) {
                throw new Exception(String.format("文件名称(%s)非法，不允许上传。", originalFilename));
            }
            // 生成唯一文件名
            String uniqueFileName = extractFilename(file);

            // 获取保存文件的根路径
            String filePath = ApplicationConfig.getPath();

            // 构建完整的文件保存路径
            String fullFilePath = filePath + File.separator + uniqueFileName;
            File artfile = new File(fullFilePath);
            if(!artfile.exists()){
                artfile.mkdirs();
            }
            // 保存文件
            file.transferTo(artfile);
            return uniqueFileName;
        } catch (Exception e) {
            throw new Exception("文件上传出错", e);
        }
    }

    @Override
    public boolean delete(String fileName) throws Exception {
        boolean flag = false;
        // 构建完整的文件保存路径
        String fullFilePath =  ApplicationConfig.getPath() + File.separator + fileName;
        File file = new File(fullFilePath);
        if (file.isFile() && file.exists())
        {
            flag = file.delete();
        }
        return flag;
    }
}
