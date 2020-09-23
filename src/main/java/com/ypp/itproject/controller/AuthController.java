package com.ypp.itproject.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.base.Charsets;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;
import com.ypp.itproject.entity.User;
import com.ypp.itproject.exception.RestException;
import com.ypp.itproject.jwt.JwtUtil;
import com.ypp.itproject.service.IUserService;
import com.ypp.itproject.vo.auth.UserAuthVo;
import com.ypp.itproject.vo.util.SuccessWapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ypp
 * @since 2020-09-23
 */
@RestController
@RequestMapping("/user")
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(StudentController.class);

    private static final String usernamePattern = "^\\w{3,20}$";
    private static final String emailPattern = "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(.[a-zA-Z0-9_-]+)+$";
    private static final String passwordPattern = "^(?=.*[A-Za-z])(?=.*\\d)\\w{8,20}$";

    private static final HashFunction hf = Hashing.sha256();

    private final IUserService service;

    public AuthController(IUserService service) {
        this.service = service;
    }

    @PostMapping("/register")
    SuccessWapper register(@RequestBody User user) throws RestException {
        logger.debug("new register: " + user.toString());
        String username = user.getUsername();
        String password = user.getPassword();
        String email = user.getEmail();
        if (username == null || email == null || password == null)
            throw new RestException(0, "some fields are empty");
        if (!username.matches(usernamePattern))
            throw new RestException(1, "invalid username");
        if (service.getOne(new QueryWrapper<User>().eq("username", user.getUsername())) != null)
            throw new RestException(2, "user already exist");
        if (!email.matches(emailPattern))
            throw new RestException(3, "invalid email");
        if (!password.matches(passwordPattern))
            throw new RestException(4, "invalid password");
        user.setPassword(hf.hashString(password, Charsets.UTF_8).toString());
        user.setDisplayName(user.getUsername());
        return new SuccessWapper(service.save(user));
    }

    @PostMapping("/login")
    Map<String, String> login(@RequestBody User user) throws RestException {
        logger.debug("new login: " + user.toString());
        User actual = service.getOne(new QueryWrapper<User>().eq("username", user.getUsername()));
        if (actual == null)
            throw new RestException(0, "username not found");
        if (!actual.getPassword().equals(hf.hashString(user.getPassword(), Charsets.UTF_8).toString()))
            throw new RestException(1, "password incorrect");

        Map<String, String> map = new HashMap<>();
        String token = JwtUtil.issue(new UserAuthVo(actual.getUid(), actual.getUsername()));
        map.put("access-token", token);
        return map;
    }

}
