package com.ypp.itproject.controller;

import com.ypp.itproject.entity.User;
import com.ypp.itproject.exception.RestException;
import com.ypp.itproject.service.IUserService;
import com.ypp.itproject.vo.AboutMeVo;
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
    private final IUserService UserService;
    private static final Logger logger = LoggerFactory.getLogger(StudentController.class);

    public AboutMeController(IUserService service) {this.UserService = service;}

    @GetMapping(value = "/{uid}")
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
    @PostMapping(value = "/update")
    SuccessWapper updateAboutMe(@RequestBody User user) throws RestException {
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
