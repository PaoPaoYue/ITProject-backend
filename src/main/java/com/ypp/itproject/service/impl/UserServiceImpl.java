package com.ypp.itproject.service.impl;

import com.ypp.itproject.entity.User;
import com.ypp.itproject.mapper.UserMapper;
import com.ypp.itproject.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ypp
 * @since 2020-09-23
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
