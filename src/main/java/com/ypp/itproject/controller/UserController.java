package com.ypp.itproject.controller;


import com.google.common.base.Charsets;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;
import com.ypp.itproject.entity.User;
import com.ypp.itproject.exception.RestException;
import com.ypp.itproject.jwt.annotation.CheckLogin;
import com.ypp.itproject.service.IUserService;
import com.ypp.itproject.vo.Account;
import com.ypp.itproject.vo.util.SuccessWapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ethan
 * @since 2020-09-22
 */

@RestController
@RequestMapping("/ypp.itproject/user")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(StudentController.class);
    private static final String usernamePattern = "^\\w{3,20}$";
    private static final String passwordPattern = "^(?=.*[A-Za-z])(?=.*\\d)\\w{8,20}$";
    private static final HashFunction hf = Hashing.sha256();

    private final IUserService service;

    public UserController(IUserService service) {
        this.service = service;
    }

    @CheckLogin
    @GetMapping(value = "/user/account/{uid}")
    public Account getCurrentAccount(@PathVariable("uid") int uid){
        /*
            Return current account setting except password
            @author: ethan
         */
        User user = service.getById(uid);
        Account ac = new Account(user);
        return ac;
    }

    @CheckLogin
    @PostMapping(value = "/user/account/{{uid}}")
    SuccessWapper updateAccount(@RequestBody User user) throws RestException {
        /*
            Update username, password, email, location, status
            @author: ethan
         */
        logger.debug(user.toString());
//        UsersAuthVo userAuthVo = (UsersAuthVo) JwtUtil.extract(); //TODO: Adapt the Users Class
//        if(user.getUid() != userAuthVo.getUid()){
//            throw new RestException(0, "fake mfk");
//        }
//        Integer uid = userAuthVo.getUid(); //TODO: change to setUid(uid) after the jwtWorks
        String username = user.getUsername();
        String password = user.getPassword();
        if (username == null || password == null)
            throw new RestException(0, "no username or password provided");
        if (!username.matches(usernamePattern))
            throw new RestException(1, "username non-valid");
        if (!password.matches(passwordPattern))
            throw new RestException(3, "password non-valid");
        user.setPassword(hf.hashString(password, Charsets.UTF_8).toString());
        user.setUid(1);
        return new SuccessWapper(service.updateById(user));
    }

}
