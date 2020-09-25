package com.ypp.itproject.service.impl;

import com.ypp.itproject.entity.User;
import com.ypp.itproject.exception.RestException;
import com.ypp.itproject.mapper.UserMapper;
import com.ypp.itproject.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ypp.itproject.util.StringUtil;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ethan
 * @since 2020-09-22
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    private static final int MAX_LENGTH_DESCRIPTION = 400;
    private static final int MAX_LENGTH_SIMPLE_DESCRIPTION = 100;
    private static final int MAX_LENGTH_DISPLAY_NAME = 50;

    @Override
    public boolean checkLength(User user){
        /*
            Return  false if some fields length exceed the limit
                    true otherwise
         */
        boolean flag = true;
        if((user.getDescription() != null
                && StringUtil.isExcelled(user.getDescription(), MAX_LENGTH_DESCRIPTION)) ||
                (user.getDisplayName() != null
                        &&  StringUtil.isExcelled(user.getDisplayName(), MAX_LENGTH_DISPLAY_NAME)) ||
                (user.getSimpleDescription() != null
                        &&  StringUtil.isExcelled(user.getSimpleDescription(), MAX_LENGTH_SIMPLE_DESCRIPTION
                ))){
            flag = false;
        }
        return flag;
    }
}
