package com.cszjkj.aisc.fms_user.controller;

import com.cszjkj.aisc.cm_user.UserInfo;
import com.cszjkj.aisc.fms_user.http.Response;
import com.cszjkj.aisc.fms_user.service.UserServiceProvider;
import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@RequestMapping("/user")
public class UserController {
    public final static String DIGITS = "0123456789";
    public final static String ALPHABETS_SUPER_CASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public final static String ALPHABETS_LOWER_CASE = "abcdefghijklmnopqrstuvwxyz";
    public final static String ALPHABETS_NO_CASE = ALPHABETS_SUPER_CASE + ALPHABETS_LOWER_CASE;
    public final static String ALPHABETS_ALL = DIGITS + ALPHABETS_NO_CASE;
    @Autowired
    private UserServiceProvider userServiceProvider;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Response login(@RequestParam("loginName") String loginName, @RequestParam("loginPwd") String loginPwd) {
        UserInfo userInfo = null;
        // 1. 验证登录名和密码
        try {
            userInfo = userServiceProvider.getUserService().getUserByLoginName(loginName);
        } catch (TException e) {
            e.printStackTrace();
            return Response.USER_LOGIN_NAME_PWD_INVALID;
        }
        if (null == userInfo) {
            return Response.USER_LOGIN_NAME_PWD_INVALID;
        }
        if (!userInfo.getLoginPwd().equals(loginPwd)) {
            return Response.USER_LOGIN_NAME_PWD_INVALID;
        }
        // 2. 生成token
        String token = generateToken();
        // 3. 缓存用户
        return null;
    }



    public String generateToken() {
        return generateRandomCode(ALPHABETS_ALL, 32);
    }

    public String generateRandomCode(String alphabets, int size) {
        StringBuilder code = new StringBuilder(size);
        Random random = new Random();
        int loc = 0;
        for (int i=0; i<size; i++) {
            loc = random.nextInt(alphabets.length());
            code.append(alphabets.charAt(loc));
        }
        return code.toString();
    }
}
