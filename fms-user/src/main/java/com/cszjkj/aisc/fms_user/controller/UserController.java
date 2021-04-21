package com.cszjkj.aisc.fms_user.controller;

import com.cszjkj.aisc.cm_user.UserInfo;
import com.cszjkj.aisc.fms_user.http.Response;
import com.cszjkj.aisc.fms_user.service.UserServiceProvider;
import com.cszjkj.aisc.fms_user.service.impl.FmsUserService;
import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private FmsUserService fmsUserService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Response login(@RequestParam("loginName") String loginName, @RequestParam("loginPwd") String loginPwd) {
        UserInfo userInfo = fmsUserService.login(loginName, loginPwd);
        if (null == userInfo) {
            return Response.USER_LOGIN_NAME_PWD_INVALID;
        }
        if (!userInfo.getLoginPwd().equals(loginPwd)) {
            return Response.USER_LOGIN_NAME_PWD_INVALID;
        }
        // 2. 生成token
        String token = fmsUserService.generateToken();
        fmsUserService.cacheTokenUserInfo(token, userInfo);
        return null;
    }





}
