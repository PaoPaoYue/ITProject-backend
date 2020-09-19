package com.ypp.itproject.service.impl;

import com.ypp.itproject.entity.Users;
import com.ypp.itproject.mapper.UsersMapper;
import com.ypp.itproject.service.IUsersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements IUsersService {

}
