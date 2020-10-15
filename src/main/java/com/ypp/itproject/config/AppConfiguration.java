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


    public AppConfiguration(AppProperties prop) {
        this.prop = prop;
    }

    @Bean
    LocaleResolver localeResolver() {
        // Force english for Spring Validation error messages
        return new FixedLocaleResolver(Locale.ENGLISH);
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
