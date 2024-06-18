package com.person.system.commonService.file;

import com.person.system.commonService.FileService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 文件服务配置类
 * @author sk
 * @date 2024/5/24
 */
@Configuration
public class FileConfiguration {

    @Bean(name = "fileService")
    public FileService fileService() {
        return new LocalFileService();
    }
}
