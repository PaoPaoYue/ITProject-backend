package com.ypp.itproject.service;

import com.ypp.itproject.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ypp.itproject.exception.RestException;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ethan
 * @since 2020-09-22
 */
public interface IUserService extends IService<User> {
    public boolean checkLength(User user);
}

