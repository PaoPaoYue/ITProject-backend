package com.ypp.itproject.controller;

import com.ypp.itproject.entity.User;
import com.ypp.itproject.exception.RestException;
import com.ypp.itproject.jwt.JwtUtil;
import com.ypp.itproject.jwt.annotation.CheckLogin;
import com.ypp.itproject.service.ICosService;
import com.ypp.itproject.service.IUserService;
import com.ypp.itproject.vo.AccountVo;
import com.ypp.itproject.vo.PasswordVo;
import com.ypp.itproject.vo.auth.UserAuthVo;
import com.ypp.itproject.vo.util.SuccessWapper;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.Set;

/**
 * <p>
 *  user控制器
 * </p>
 *
 * @author: ypp
 * @author: ethan
 * @since 2020-09-23
 */
@Validated
@RestController
@RequestMapping("/user")
public class UserController {

    private static final String FACEBOOK_PREFIX = "https://www.facebook.com/";
    private static final String LINKEDIN_PREFIX = "https://www.linkedin.com/";
    private static final String GITHUB_PREFIX = "https://github.com/";

    private final IUserService service;
    private final ICosService cosService;

    public UserController(IUserService service, ICosService cosService) {
        this.service = service;
        this.cosService = cosService;
    }

    /**
     Using this API to authenticate a user

     @author: ypp
     */
    @CheckLogin
    @GetMapping(value = "/auth")
    public SuccessWapper authenticate() {
        return new SuccessWapper(true);
    }

    /**
     Using this API to get user information for preview display

     @author: ypp
     @author: ethan
     */
    @GetMapping(value = "/account/{uid}")
    public AccountVo getAccount(@PathVariable("uid") @Min(1) int uid){
        User user = service.getById(uid);
        if(user == null){
            // user does not exist
            throw new RestException(1, "user does not exist");
        }
        return new AccountVo(user);
    }


    /**
     Using this API to update fields relates to account settings, which listed below.
     It can be used to update partial fields relates to user table, with only provide
     partial key:value pairs in the Payload. Please be aware that username cannot be
     changed。

     @author: ypp
     @author: ethan
     */
    @CheckLogin
    @PostMapping(value = "/account/update")
    SuccessWapper updateAccount(@RequestBody @Valid AccountVo vo) {
        // cannot modify username
        vo.setUsername(null);

        if (!vo.getAvatar().isEmpty() && !vo.getAvatar().startsWith(cosService.getImgPrefix()))
            throw new RestException(0, "invalid avatar source, must start with " + cosService.getImgPrefix());
        if (!vo.getContactFacebook().isEmpty() && !vo.getContactFacebook().startsWith(FACEBOOK_PREFIX))
            throw new RestException(0, "invalid Facebook link, must start with " + FACEBOOK_PREFIX);
        if (!vo.getContactGithub().isEmpty() && !vo.getContactGithub().startsWith(GITHUB_PREFIX))
            throw new RestException(0, "invalid Github link, must start with " + GITHUB_PREFIX);
        if (!vo.getContactLinkedin().isEmpty() && !vo.getContactLinkedin().startsWith(LINKEDIN_PREFIX))
            throw new RestException(0, "invalid Linkedin link, must start with " + LINKEDIN_PREFIX);

        UserAuthVo userAuthVo = (UserAuthVo) JwtUtil.extract();
        int uid = userAuthVo.getUid();

        return  new SuccessWapper(service.updateAccount(uid, vo));
    }


    /**
     Using this API to update a user's password. User must log in first.
     Password must differ from the exist one, and matches the patter as well.

     The payload of this API should only contain password field, as the other submitted
     fields will be ignored.

     @author: ypp
     @author: ethan

     */
    @CheckLogin
    @PostMapping(value = "/password/update")
    public SuccessWapper updatePassword(@RequestBody @Valid PasswordVo vo) {

        UserAuthVo userAuthVo = (UserAuthVo) JwtUtil.extract();
        int uid = userAuthVo.getUid();

        return new SuccessWapper(service.updatePassword(uid, vo));
    }

    @GetMapping(value = "/tag/{uid}")
    public Set<String> getTags(@PathVariable("uid") @Min(1) int uid) {
        return service.getTags(uid);
    }
}
