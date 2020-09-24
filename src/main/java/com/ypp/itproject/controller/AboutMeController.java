package com.ypp.itproject.controller;

import com.ypp.itproject.entity.User;
import com.ypp.itproject.exception.RestException;
import com.ypp.itproject.jwt.JwtUtil;
import com.ypp.itproject.service.IUserService;
import com.ypp.itproject.vo.AboutMeVo;
import com.ypp.itproject.jwt.annotation.CheckLogin;
import com.ypp.itproject.vo.auth.UserAuthVo;
import com.ypp.itproject.vo.util.SuccessWapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

/*
    About Me Page controller
    @author: ethan
 */

@RestController
@RequestMapping("/user")
public class AboutMeController {
    private final IUserService UserService;
    private static final Logger logger = LoggerFactory.getLogger(StudentController.class);

    public AboutMeController(IUserService service) {this.UserService = service;}


    @GetMapping(value = "/about/{uid}")
    public AboutMeVo getAboutMe(@PathVariable("uid") int uid) throws RestException{
        /*
            Return JSON type response for about me page
            @author: ethan
         */

        User user = UserService.getById(uid);
        if(uid<=0 || user == null){
            // uid less and equal to zero
            // user does not exist
            throw new RestException(422, "user does not exist");
        }
        AboutMeVo am= new AboutMeVo(user);
        return am;
    }

    @CheckLogin
    @PostMapping(value = "/about/update")
    SuccessWapper updateAboutMe(@RequestBody User user) throws RestException {
        UserAuthVo userAuthVo = (UserAuthVo) JwtUtil.extract();
        Integer uid = userAuthVo.getUid();
        User currentUser = UserService.getById(uid);
        if(currentUser == null){
            throw new RestException(422, "not login");
        }
        if(user.getUsername()!=null){
            throw new RestException(422, "username cannot be changed");
        }
        if(user.getPassword()!=null){
            throw new RestException(422, "password cannot be updated through this api");
        }
        user.setUid(uid);
        return new SuccessWapper(UserService.updateById(user));
    }

}
