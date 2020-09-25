package com.ypp.itproject.controller;


import com.google.common.base.Charsets;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;
import com.ypp.itproject.entity.User;
import com.ypp.itproject.exception.RestException;
import com.ypp.itproject.jwt.JwtUtil;
import com.ypp.itproject.jwt.annotation.CheckLogin;
import com.ypp.itproject.service.IUserService;
import com.ypp.itproject.vo.AccountVo;
import com.ypp.itproject.vo.auth.UserAuthVo;
import com.ypp.itproject.vo.util.SuccessWapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;



@RestController
@RequestMapping("/user")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(StudentController.class);
    private static final String passwordPattern = "^(?=.*[A-Za-z])(?=.*\\d)\\w{8,20}$";
    private static final String emailPattern = "^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$";
    private static final HashFunction hf = Hashing.sha256();

    private final IUserService service;

    public UserController(IUserService service) {
        this.service = service;
    }


    @CheckLogin
    @GetMapping(value = "/account/{uid}")
    public AccountVo getCurrentAccount(@PathVariable("uid") int uid) throws RestException{
        /*
            Return current account setting except password
            @author: ethan
         */
        UserAuthVo userAuthVo = (UserAuthVo) JwtUtil.extract();
        Integer myUID = userAuthVo.getUid();//uid extract from jwt
        User user = service.getById(uid);
        if(uid != myUID){
            //uid from the api does not match logged in user
            throw new RestException(0, "no permission");
        }
        if(uid<=0 || user == null){
            // uid less and equal to zero
            // user does not exist
            throw new RestException(1, "user does not exist");
        }
        AccountVo ac = new AccountVo(user);
        return ac;
    }

    @CheckLogin
    @PostMapping(value = "/account/update")
    SuccessWapper updateAccount(@RequestBody User user) throws RestException, IllegalAccessException {
        /*
            Using this API to update fields relates to account settings, which listed below.
            It can be used to update partial fields reltes to user table, with only provide
            partial key:value pairs in the Payload. Please be aware that username cannot be
            changed, email address will be checked for matching the pattern, password will be
            checked as well by calling update account_password API.

            @author: ethan
         */

        UserAuthVo userAuthVo = (UserAuthVo) JwtUtil.extract();
        Integer uid = userAuthVo.getUid();

        if(user.getPassword() != null){
            SuccessWapper resUpdatePwd = updatePassword(user);
            if(!resUpdatePwd.isSuccess()){
                throw new RestException(0, "failed setting new password");
            }else{
                user.setPassword(null);
                if(user.isEmpty()){
                    return new SuccessWapper(true);
                }
            }
        }
        if(user.getEmail()!=null && !user.getEmail().matches(emailPattern)){
            throw new RestException(1, "email address does not match the pattern");
        }
        if(user.getUsername()!=null){
            throw new RestException(2, "username cannot be changed");
        }
        if((user.getDescription() != null && service.isExcelled(user.getDescription(), 400)) ||
                (user.getDisplayName() != null && service.isExcelled(user.getDisplayName(), 50)) ||
                (user.getSimpleDescription() != null && service.isExcelled(user.getSimpleDescription(), 100))){
            //length of some fields does not exceed the limitation
            throw new RestException(3, "field length exceed the limitation");
        }

        user.setUid(uid);
        return new SuccessWapper(service.updateById(user));
    }


    @CheckLogin
    @PostMapping(value = "/account/password/update")
    public SuccessWapper updatePassword(@RequestBody User user) throws RestException{
        /*
            Using this API to update a user's password. User must log in first.
            Password must differ from the exist one, and matches the patter as well.
            This API can be used by itself, and it is embedded with update account API
            as well. Put password field into the payload of update account API, and it
            will using this API for updating password at the backend.

            The payload of this API should only contain password field, as the other submitted
            fields will be ignored.

            @author: ethan

         */
        UserAuthVo userAuthVo = (UserAuthVo) JwtUtil.extract();

        Integer uid = userAuthVo.getUid();
        String currentPwd = service.getById(uid).getPassword();

        String newPassword = user.getPassword();
        if(newPassword==null || uid == null){
            throw new RestException(4, "no password provided");
        }
        if(!newPassword.matches(passwordPattern)){
            throw new RestException(5, "non-valid password");
        }

        String encryptNewPwd = hf.hashString(newPassword, Charsets.UTF_8).toString();
        if(encryptNewPwd.equals(currentPwd)){
            throw new RestException(6, "new password cannot be the same as old one");
        }
        // Password valid, polished, not duplicated, ready to set
        User userWidPwd = new User();
        userWidPwd.setUid(uid);
        userWidPwd.setPassword(encryptNewPwd);
        return new SuccessWapper(service.updateById(userWidPwd));
    }
}
