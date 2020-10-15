package com.ypp.itproject.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;

@Validated
@ConfigurationProperties(prefix = "app-ypp")
public class AppProperties {

    @NotBlank
    private String qcloudAppId;
    @NotBlank
    private String qcloudSecretId;
    @NotBlank
    private String qcloudSecretKey;

    @NotBlank
    private String cosRegion;
    private int cosMaxAge = 7200;
    @NotBlank
    private String cosImgBucket;
    @NotBlank
    private String cosFileBucket;

    public String getQcloudAppId() {
        return qcloudAppId;
    }

    public void setQcloudAppId(String qcloudAppId) {
        this.qcloudAppId = qcloudAppId;
    }

    public String getQcloudSecretId() {
        return qcloudSecretId;
    }

    public void setQcloudSecretId(String qcloudSecretId) {
        this.qcloudSecretId = qcloudSecretId;
    }

    public String getQcloudSecretKey() {
        return qcloudSecretKey;
    }

    public void setQcloudSecretKey(String qcloudSecretKey) {
        this.qcloudSecretKey = qcloudSecretKey;
    }

    public String getCosRegion() {
        return cosRegion;
    }

    public void setCosRegion(String cosRegion) {
        this.cosRegion = cosRegion;
    }

    public int getCosMaxAge() {
        return cosMaxAge;
    }

    public void setCosMaxAge(int cosMaxAge) {
        this.cosMaxAge = cosMaxAge;
    }

    public String getCosImgBucket() {
        return cosImgBucket;
    }

    public void setCosImgBucket(String cosImgBucket) {
        this.cosImgBucket = cosImgBucket;
    }

    public String getCosFileBucket() {
        return cosFileBucket;
    }

    public void setCosFileBucket(String cosFileBucket) {
        this.cosFileBucket = cosFileBucket;
    }

    @Override
    public String toString() {
        return "AppProperties{" +
                "qcloudAppId='" + qcloudAppId + '\'' +
                ", cosRegion='" + cosRegion + '\'' +
                ", cosMaxAge=" + cosMaxAge +
                ", cosImgBucket='" + cosImgBucket + '\'' +
                ", cosFileBucket='" + cosFileBucket + '\'' +
                '}';
    }
}
