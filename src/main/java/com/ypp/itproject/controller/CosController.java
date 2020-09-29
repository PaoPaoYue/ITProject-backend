package com.ypp.itproject.controller;

import com.tencent.cloud.CosStsClient;
import com.ypp.itproject.jwt.annotation.CheckLogin;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.TreeMap;

/**
 * <p>
 *  cos授权服务
 * </p>
 *
 * @author: ypp
 * @since 2020-09-23
 */
@RestController
@RequestMapping("/cos")
public class CosController {

    private final TreeMap<String, Object> imgCosConfig;
    private final TreeMap<String, Object> fileCosConfig;

    public CosController(TreeMap<String, Object> imgCosConfig, TreeMap<String, Object> fileCosConfig) {
        this.imgCosConfig = imgCosConfig;
        this.fileCosConfig = fileCosConfig;
    }

    /**
        return image cos sts credentials, need to check login

        @author: ypp
     */
    @CheckLogin
    @GetMapping("/sts/img")
    public Map<String, Object> getCosImgCred () {
        try {
            //成功返回临时密钥信息
            return CosStsClient.getCredential(imgCosConfig).toMap();
        } catch (Exception e) {
            //失败抛出异常
            throw new IllegalArgumentException("no valid secret !");
        }
    }

    /**
        return file cos sts credentials, need to check login

        @author: ypp
     */
    @CheckLogin
    @GetMapping("/sts/file")
    public Map<String, Object> getCosFileCred () {
        try {
            //成功返回临时密钥信息
            return CosStsClient.getCredential(fileCosConfig).toMap();
        } catch (Exception e) {
            //失败抛出异常
            throw new IllegalArgumentException("no valid secret !");
        }
    }
}
