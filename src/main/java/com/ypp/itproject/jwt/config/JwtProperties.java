package com.ypp.itproject.jwt.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

@ConfigurationProperties(prefix = "jwt-ypp")
public class JwtProperties {

    /**
     * 加密accessToken所使用的密钥
     */
    private String secret = "1234";

    /**
     * accessToken中存放subject数据的map名称
     */
    private String tokenDataName = "data";

    /**
     * accessToken的理论过期时间，单位分钟，token如果超过该时间则接口响应的header中附带新的token信息
     */
    private int maxAliveMinute = 30;

    /**
     * accessToken的最大生存周期，单位分钟，在此时间内的token无需重新登录即可刷新
     */
    private int maxIdleMinute = 60;

    /**
     * 是否启用token的自动刷新机制
     */
    private boolean enableAutoRefreshToken = false;

    @NestedConfigurationProperty
    private JwtCacheProperties cache = new JwtCacheProperties();

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getTokenDataName() {
        return tokenDataName;
    }

    public void setTokenDataName(String tokenDataName) {
        this.tokenDataName = tokenDataName;
    }

    public int getMaxAliveMinute() {
        return maxAliveMinute;
    }

    public void setMaxAliveMinute(int maxAliveMinute) {
        this.maxAliveMinute = maxAliveMinute;
    }

    public int getMaxIdleMinute() {
        return maxIdleMinute;
    }

    public void setMaxIdleMinute(int maxIdleMinute) {
        this.maxIdleMinute = maxIdleMinute;
    }

    public boolean isEnableAutoRefreshToken() {
        return enableAutoRefreshToken;
    }

    public void setEnableAutoRefreshToken(boolean enableAutoRefreshToken) {
        this.enableAutoRefreshToken = enableAutoRefreshToken;
    }

    public JwtCacheProperties getCache() {
        return cache;
    }

    public void setCache(JwtCacheProperties cache) {
        this.cache = cache;
    }

    public int getRefreshWindow() { return maxIdleMinute - maxAliveMinute;}

    @Override
    public String toString() {
        return "JwtProperties{" +
                "tokenDataName='" + tokenDataName + '\'' +
                ", maxAliveMinute=" + maxAliveMinute +
                ", maxIdleMinute=" + maxIdleMinute +
                ", enableAutoRefreshToken=" + enableAutoRefreshToken +
                ", cache=" + cache +
                '}';
    }
}
