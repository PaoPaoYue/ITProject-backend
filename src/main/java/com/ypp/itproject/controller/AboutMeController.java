package com.ypp.itproject.controller;

import com.ypp.itproject.exception.RestException;
import com.ypp.itproject.vo.AboutMe;
import com.ypp.itproject.jwt.annotation.CheckLogin;
import com.ypp.itproject.vo.util.SuccessWapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

/*
    About Me Page controller
    @author: ethan
 */

@RestController
@RequestMapping("/user/about")
public class AboutMeController {
    private final IUsersService UserService;
    private static final Logger logger = LoggerFactory.getLogger(StudentController.class);

    public AboutMeController(IUsersService service) {this.UserService = service;}

    @GetMapping(value = "/{uid}")
    public AboutMe getAboutMe(@PathVariable("uid") int uid){
        /*
            Return JSON type response for about me page
            @author: ethan
         */

        //TODO: Check user available, return 422 if yes
        Users user = UserService.getById(uid);
        AboutMe am= new AboutMe(user);
        return am;
    }

    @CheckLogin
    @PostMapping(value = "/update")
    SuccessWapper updateAboutMe(@RequestBody Users user) throws RestException {
        logger.debug(user.toString());
//        UsersAuthVo userAuthVo = (UsersAuthVo) JwtUtil.extract(); //TODO: Adapt the Users Class
//        if(user.getUid() != userAuthVo.getUid()){
//            throw new RestException(0, "fake mfk");
//        }
//        Integer uid = userAuthVo.getUid(); //TODO: change to setUid(uid) after the jwtWorks
        user.setUid(1);
        return new SuccessWapper(UserService.updateById(user));
    }

}
