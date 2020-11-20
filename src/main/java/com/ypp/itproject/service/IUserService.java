package com.ypp.itproject.service;

import com.ypp.itproject.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ypp.itproject.vo.*;

import java.util.Set;

public interface IUserService extends IService<User> {
    User register(RegisterVo vo);
    User login(LoginVo vo);
    boolean updatePassword(int uid, PasswordVo vo);
    boolean updateAccount(int uid, AccountVo vo);
    boolean updateAboutMe(int uid, AboutMeVo vo);
    boolean addTags(int uid, Set<String> newTags);
    Set<String> getTags(int uid);
}

