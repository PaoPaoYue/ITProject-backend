package com.ypp.itproject.controller;

import com.ypp.itproject.entity.User;
import com.ypp.itproject.jwt.JwtUtil;
import com.ypp.itproject.service.IUserService;
import com.ypp.itproject.vo.LoginVo;
import com.ypp.itproject.vo.RegisterVo;
import com.ypp.itproject.vo.auth.UserAuthVo;
import com.ypp.itproject.vo.util.SuccessWapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  登录授权
 * </p>
 *
 * @author ypp
 * @since 2020-09-23
 */
@RestController
@RequestMapping("/user")
public class AuthController {

    private final IUserService service;

    public AuthController(IUserService service) {
        this.service = service;
    }

    /**
        Using this API to register a new user with username, email and password

        @author: ypp
     */
    @PostMapping("/register")
    public SuccessWapper register(@RequestBody @Valid RegisterVo vo)  {
        return new SuccessWapper(service.register(vo) != null);
    }

    /**
        login with this API, providing username and password

        @author: ypp
     */
    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody @Valid LoginVo vo) {
        User user = service.login(vo);

        Map<String, Object> map = new HashMap<>();
        String token = JwtUtil.issue(new UserAuthVo(user.getUid(), user.getUsername()));
        map.put("access-token", token);
        map.put("uid", user.getUid());
        return map;
    }

}
