package com.ypp.itproject.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.ypp.itproject.util.ContentEnumConverter;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.FixedLocaleResolver;

import java.util.Locale;

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
            // paginationInterceptor.setOverflow(false);
            // paginationInterceptor.setLimit(500);
            return new PaginationInterceptor();
        }
    }

    @Configuration
    public class WebConfig implements WebMvcConfigurer {
        @Override
        public void addFormatters(FormatterRegistry registry) {
            registry.addConverter(new ContentEnumConverter());
        }
    }

}
