package com.ypp.itproject.controller;

import com.ypp.itproject.vo.AboutMe;
import com.ypp.itproject.entity.Users;
import com.ypp.itproject.jwt.annotation.CheckLogin;
import com.ypp.itproject.service.IUsersService;
import com.ypp.itproject.vo.util.SuccessWapper;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/{uid}")
public class AboutMeController {
    private final IUsersService UserService;

    public AboutMeController(IUsersService service) {this.UserService = service;}

    @GetMapping(value = "/about")
    public AboutMe getAboutMe(@PathVariable("uid") int uid){
        /*
            Return JSON type response for about me page
            @author: ethan
         */
        Users user = UserService.getById(uid);
        AboutMe am= new AboutMe(user);
        return am;
    }

    @CheckLogin
    @PostMapping(value = "/about")
    SuccessWapper updateAboutMe(@RequestBody Users user){
        return new SuccessWapper(UserService.updateById(user));
    }

}
