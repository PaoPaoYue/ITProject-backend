package com.ypp.itproject.service.impl;

import com.ypp.itproject.jwt.IJwtExceptionService;
import com.ypp.itproject.vo.exception.CheckLoginExceptionVo;
import com.ypp.itproject.vo.exception.CheckPermissionExceptionVo;
import org.springframework.stereotype.Service;

@Service
public class JwtExceptionServiceImpl implements IJwtExceptionService {

    @Override
    public Object getLoginExceptionResponse() {
        return new CheckLoginExceptionVo("not login");
    }

    @Override
    public Object getPermissionExceptionResponse() {
        return new CheckPermissionExceptionVo("no permission");
    }
}
