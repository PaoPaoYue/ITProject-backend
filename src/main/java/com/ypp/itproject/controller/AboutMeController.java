package com.ypp.itproject.controller;

import com.ypp.itproject.entity.User;
import com.ypp.itproject.exception.RestException;
import com.ypp.itproject.jwt.JwtUtil;
import com.ypp.itproject.service.IUserService;
import com.ypp.itproject.vo.AboutMeVo;
import com.ypp.itproject.jwt.annotation.CheckLogin;
import com.ypp.itproject.vo.auth.UserAuthVo;
import com.ypp.itproject.vo.util.SuccessWapper;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;

/**
 * <p>
 *  user detail info controller
 * </p>
 *
 * @author: ypp
 * @author: ethan
 * @since 2020-09-23
 */
@Validated
@RestController
@RequestMapping("/user")
public class AboutMeController {

    private final IUserService service;

    public AboutMeController(IUserService service) {this.service = service;}

    /**
        Using this API to get user information for the aboutMe section, including
        education, work, skillset, interest, award

        @author: ypp
        @author: ethan
     */
    @GetMapping(value = "/about/{uid}")
    public AboutMeVo getAboutMe(@PathVariable("uid") @Min(1) int uid) {
        User user = service.getById(uid);
        if(user == null){
            // user does not exist
            throw new RestException(1, "user does not exist");
        }
        return new AboutMeVo(user);
    }


    /**
        Using this API to update user information for the aboutMe section, including
        education, work, skillset, interest, award

        @author: ypp
        @author: ethan
     */
    @CheckLogin
    @PostMapping(value = "/about/update")
    SuccessWapper updateAboutMe(@RequestBody @Valid AboutMeVo vo) {

        UserAuthVo userAuthVo = (UserAuthVo) JwtUtil.extract();
        int uid = userAuthVo.getUid();

        return new SuccessWapper(service.updateAboutMe(uid, vo));
    }

}
