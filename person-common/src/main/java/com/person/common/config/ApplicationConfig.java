package com.person.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 项目配置
 *
 * @author sk
 */
@Component
@ConfigurationProperties(prefix = "personal")
public class ApplicationConfig
{
    /** 项目名称 */
    private static String name;

    /** 版本 */
    private static String version;

    /** 版权年份 */
    private static String timeYear;

    /** 上传路径 */
    private static String path;

    /** 验证码类型 */
    private static String captchaType;

    /** 文件访问前缀 */
    private static String prefix;

    public static String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public static String getTimeYear() {
        return timeYear;
    }

    public void setTimeYear(String timeYear) {
        this.timeYear = timeYear;
    }

    public static String getPath() {
        return path;
    }

    public void setPath(String path) {
        ApplicationConfig.path = path;
    }

    public static String getCaptchaType() {
        return captchaType;
    }

    public void setCaptchaType(String captchaType) {
        ApplicationConfig.captchaType = captchaType;
    }

    public static String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        ApplicationConfig.prefix = prefix;
    }
}
