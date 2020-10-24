package com.ypp.itproject.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.base.Charsets;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;
import com.ypp.itproject.entity.User;
import com.ypp.itproject.exception.RestException;
import com.ypp.itproject.mapper.UserMapper;
import com.ypp.itproject.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ypp.itproject.vo.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ypp ethan
 * @since 2020-09-22
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    private static final Logger logger = LoggerFactory.getLogger(IUserService.class);

    private static final String EDUCATION_INIT = "[]";
    private static final String WORK_INIT = "[]";
    private static final String SKILLSET_INIT = "{\"field\":[], \"tech\":[], \"other\":[]}";
    private static final String INTEREST_INIT = "[]";
    private static final String AWARD_INIT = "[]";

    private static final HashFunction hf = Hashing.sha256();

    private static final int TAGS_MAX_NUM = 20;

    @Override
    public User register(RegisterVo vo) {
        if (this.getOne(new QueryWrapper<User>().eq("username", vo.getUsername())) != null)
            throw new RestException(1, "user already exist");

        User user = new User();
        user.setUsername(vo.getUsername());
        user.setEmail(vo.getEmail());
        user.setPassword(hf.hashString(vo.getPassword(), Charsets.UTF_8).toString());
        user.setDisplayName(vo.getUsername());
        user.setEducation(EDUCATION_INIT);
        user.setWork(WORK_INIT);
        user.setSkillset(SKILLSET_INIT);
        user.setInterest(INTEREST_INIT);
        user.setAward(AWARD_INIT);

        if (this.save(user)) {
            return user;
        }
        return null;
    }

    @Override
    public User login(LoginVo vo) {
        User user = this.getOne(new QueryWrapper<User>().eq("username",vo.getUsername()));
        if (user == null)
            throw new RestException(1, "username not found");
        if (!user.getPassword().equals(hf.hashString(vo.getPassword(), Charsets.UTF_8).toString()))
            throw new RestException(2, "password incorrect");

        return user;
    }

    @Override
    public boolean updatePassword(int uid, PasswordVo vo) {
        String password = hf.hashString(vo.getPassword(), Charsets.UTF_8).toString();
        if(password.equals(this.getById(uid).getPassword()))
            throw new RestException(1, "new password cannot be the same as the old one");

        User user = new User();
        user.setUid(uid);
        user.setPassword(password);
        return this.updateById(user);
    }

    @Override
    public boolean updateAccount(int uid, AccountVo vo) {
        User user = vo.toUser();
        user.setUid(uid);
        return this.updateById(user);
    }

    @Override
    public boolean updateAboutMe(int uid, AboutMeVo vo) {
        User user = vo.toUser();
        user.setUid(uid);
        return this.updateById(user);
    }

    @Override
    public boolean addTags(int uid, Set<String> newTags) {
        User user = this.getById(uid);
        if (user == null) return false;
        Set<String> tags = Arrays.stream(user.getTag().split(",")).collect(Collectors.toSet());
        tags.addAll(newTags);
        tags = tags.stream().limit(TAGS_MAX_NUM).collect(Collectors.toSet());
        user.setTag(String.join(",", tags));
        return this.updateById(user);
    }

    @Override
    public Set<String> getTags(int uid) {
        User user = this.getById(uid);
        if (user == null) return null;
        return Arrays.stream(user.getTag().split(",")).collect(Collectors.toSet());
    }
}


