package com.ypp.itproject.service.impl;

import com.ypp.itproject.config.AppProperties;
import com.ypp.itproject.service.ICosService;
import org.springframework.stereotype.Service;

import java.util.TreeMap;

@Service
public class CosService implements ICosService {

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

    private final AppProperties prop;

    public CosService(AppProperties prop) {
        this.prop = prop;
    }

    @Override
    public TreeMap<String, Object> getImgSecret(int uid) {
        TreeMap<String, Object> imgSecret = new TreeMap<>();
        // 替换为您的 SecretId
        imgSecret.put("SecretId", prop.getQcloudSecretId());
        // 替换为您的 SecretKey
        imgSecret.put("SecretKey", prop.getQcloudSecretKey());

        // 临时密钥有效时长，单位是秒，默认1800秒，目前主账号最长2小时（即7200秒），子账号最长36小时（即129600秒）
        imgSecret.put("durationSeconds", prop.getCosMaxAge());

        // 换成您的 bucket
        imgSecret.put("bucket", prop.getCosImgBucket());
        // 换成 bucket 所在地区
        imgSecret.put("region", prop.getCosRegion());

        imgSecret.put("allowPrefix", uid + "/*");

        imgSecret.put("allowActions", allowActions);

        return imgSecret;
    }

    @Override
    public TreeMap<String, Object> getFileSecret(int uid) {
        TreeMap<String, Object> fileSecret = new TreeMap<>();
        // 替换为您的 SecretId
        fileSecret.put("SecretId", prop.getQcloudSecretId());
        // 替换为您的 SecretKey
        fileSecret.put("SecretKey", prop.getQcloudSecretKey());

        // 临时密钥有效时长，单位是秒，默认1800秒，目前主账号最长2小时（即7200秒），子账号最长36小时（即129600秒）
        fileSecret.put("durationSeconds", prop.getCosMaxAge());

        // 换成您的 bucket
        fileSecret.put("bucket", prop.getCosFileBucket());
        // 换成 bucket 所在地区
        fileSecret.put("region", prop.getCosRegion());

        fileSecret.put("allowPrefix", uid + "/*");

        fileSecret.put("allowActions", allowActions);

        return fileSecret;
    }

    @Override
    public String getImgPrefix() {
        return "https://"+prop.getCosImgBucket()+".cos."+prop.getCosRegion()+".myqcloud.com/";
    }

    @Override
    public String getFilePrefix() {
        return "https://"+prop.getCosFileBucket()+".cos."+prop.getCosRegion()+".myqcloud.com/";
    }
}
