package com.ypp.itproject.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.FixedLocaleResolver;

import java.util.Locale;
import java.util.TreeMap;

@Configuration
@EnableConfigurationProperties(AppProperties.class)
public class AppConfiguration {

    private final AppProperties prop;

    private final String allowPrefix = "*";

    // 密钥的权限列表。简单上传、表单上传和分片上传需要以下的权限
    // 其他权限列表请看 https://cloud.tencent.com/document/product/436/31923
    private final String[] allowActions = new String[] {
        // 简单上传
        "name/cos:PutObject",
        // 表单上传、小程序上传
        "name/cos:PostObject",
        // 分片上传
        "name/cos:InitiateMultipartUpload",
        "name/cos:ListMultipartUploads",
        "name/cos:ListParts",
        "name/cos:UploadPart",
        "name/cos:CompleteMultipartUpload",
        // 删除对象
        "name/cos:DeleteObject"
    };

    public AppConfiguration(AppProperties prop) {
        this.prop = prop;
    }

    @Bean
    LocaleResolver localeResolver() {
        // Force english for Spring Security error messages
        return new FixedLocaleResolver(Locale.ENGLISH);
    }

    @Bean
    public TreeMap<String, Object> imgCosConfig() {
        TreeMap<String, Object> imgCosConfig = new TreeMap<>();
        // 替换为您的 SecretId
        imgCosConfig.put("SecretId", prop.getQcloudSecretId());
        // 替换为您的 SecretKey
        imgCosConfig.put("SecretKey", prop.getQcloudSecretKey());

        // 临时密钥有效时长，单位是秒，默认1800秒，目前主账号最长2小时（即7200秒），子账号最长36小时（即129600秒）
        imgCosConfig.put("durationSeconds", prop.getCosMaxAge());

        // 换成您的 bucket
        imgCosConfig.put("bucket", prop.getCosImgBucket());
        // 换成 bucket 所在地区
        imgCosConfig.put("region", prop.getCosRegion());

        imgCosConfig.put("allowPrefix", allowPrefix);

        imgCosConfig.put("allowActions", allowActions);

        return imgCosConfig;
    }

    @Bean
    public TreeMap<String, Object> fileCosConfig() {
        TreeMap<String, Object> fileCosConfig = new TreeMap<>();
        // 替换为您的 SecretId
        fileCosConfig.put("SecretId", prop.getQcloudSecretId());
        // 替换为您的 SecretKey
        fileCosConfig.put("SecretKey", prop.getQcloudSecretKey());

        // 临时密钥有效时长，单位是秒，默认1800秒，目前主账号最长2小时（即7200秒），子账号最长36小时（即129600秒）
        fileCosConfig.put("durationSeconds", prop.getCosMaxAge());

        // 换成您的 bucket
        fileCosConfig.put("bucket", prop.getCosFileBucket());
        // 换成 bucket 所在地区
        fileCosConfig.put("region", prop.getCosRegion());

        fileCosConfig.put("allowPrefix", allowPrefix);

        fileCosConfig.put("allowActions", allowActions);

        return fileCosConfig;
    }

    @Configuration
    @MapperScan("com.ypp.itproject.mapper")
    public static class MybatisPlusConfigurer {

        @Bean
        public PaginationInterceptor paginationInterceptor() {
            // 设置请求的页面大于最大页后操作， true调回到首页，false 继续请求  默认false
            // paginationInterceptor.setOverflow(false);
            // 设置最大单页限制数量，默认 500 条，-1 不受限制
            // paginationInterceptor.setLimit(500);
            return new PaginationInterceptor();
        }
    }


}
