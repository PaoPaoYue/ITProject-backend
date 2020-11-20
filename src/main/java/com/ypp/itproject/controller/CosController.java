package com.ypp.itproject.controller;

import com.tencent.cloud.CosStsClient;
import com.ypp.itproject.jwt.JwtUtil;
import com.ypp.itproject.jwt.annotation.CheckLogin;
import com.ypp.itproject.service.ICosService;
import com.ypp.itproject.vo.auth.UserAuthVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * <p>
 *  cos authorization service controller
 * </p>
 *
 * @author: ypp
 * @since 2020-09-23
 */
@RestController
@RequestMapping("/cos")
public class CosController {

    private final ICosService service;

    public CosController(ICosService service) {
        this.service = service;
    }

    /**
        return image cos sts credentials, need to check login

        @author: ypp
     */
    @CheckLogin
    @GetMapping("/sts/img")
    public Map<String, Object> getCosImgCred () {
        try {
            UserAuthVo vo = (UserAuthVo) JwtUtil.extract();
            int uid = vo.getUid();
            return CosStsClient.getCredential(service.getImgSecret(uid)).toMap();
        } catch (Exception e) {
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
            UserAuthVo vo = (UserAuthVo) JwtUtil.extract();
            int uid = vo.getUid();
            return CosStsClient.getCredential(service.getFileSecret(uid)).toMap();
        } catch (Exception e) {
            throw new IllegalArgumentException("no valid secret !");
        }
    }
}
