package com.ypp.itproject.jwt;

import com.ypp.itproject.jwt.config.JwtProperties;
import com.ypp.itproject.jwt.core.AutoRefreshInterceptor;
import com.ypp.itproject.jwt.core.CheckInterceptor;
import com.ypp.itproject.jwt.core.JwtManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.PostConstruct;

@Configuration
@EnableConfigurationProperties(JwtProperties.class)
@ConditionalOnBean(IJwtExceptionService.class)
public class JwtAutoConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(JwtAutoConfiguration.class);

    private final JwtProperties prop;

    public JwtAutoConfiguration(JwtProperties prop) {
        this.prop = prop;
    }

    @PostConstruct
    public void afterConstruct() {
        logger.info("JWT configuration starts with properties:" + prop);
        if (prop.getMaxIdleMinute() < prop.getMaxAliveMinute()) {
            throw new IllegalArgumentException("maxIdleMinute must be larger than maxAliveMinute");
        }
    }

    @Autowired
    public void setJetManager(JwtManager manager) {
        JwtUtil.setManager(manager);
    }

    @Configuration
    public static class JWTWebMvcConfigurer implements WebMvcConfigurer {

        private final JwtProperties prop;

        private final JwtManager manager;

        public JWTWebMvcConfigurer(JwtProperties prop, JwtManager manager) {
            this.prop = prop;
            this.manager = manager;
        }

        @Override
        public void addInterceptors(InterceptorRegistry registry) {
            registry.addInterceptor(new CheckInterceptor(manager)).addPathPatterns("/**");
            if (prop.isEnableAutoRefreshToken())
                registry.addInterceptor(new AutoRefreshInterceptor(manager)).addPathPatterns("/**");
        }

    }
}
