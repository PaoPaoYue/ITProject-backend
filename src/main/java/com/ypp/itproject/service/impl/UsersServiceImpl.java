package com.ypp.itproject.service.impl;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ypp.itproject.entity.User;
import com.ypp.itproject.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ethan
 * @since 2020-09-20
 */
@Service
public class UsersServiceImpl extends ServiceImpl<UserMapper, User> implements IService<User> {

}
