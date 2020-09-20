package com.ypp.itproject.controller;


import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;
import com.ypp.itproject.service.IUsersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ethan
 * @since 2020-09-20
 */
@Controller
@RequestMapping("/{uid}")
public class UsersController {
    private static final Logger logger = LoggerFactory.getLogger(StudentController.class);
    private static final String usernamePattern = "^\\w{3,20}$";
    private static final String passwordPattern = "^(?=.*[A-Za-z])(?=.*\\d)\\w{8,20}$";
    private static final HashFunction hf = Hashing.sha256();

    private final IUsersService service;

    public UsersController(IUsersService service) {
        this.service = service;
    }


}
