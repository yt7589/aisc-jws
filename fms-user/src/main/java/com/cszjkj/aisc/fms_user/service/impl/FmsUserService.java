package com.cszjkj.aisc.fms_user.service.impl;

import com.cszjkj.aisc.cm_user.UserInfo;
import com.cszjkj.aisc.fms_user.service.IFmsUserService;
import com.cszjkj.aisc.fms_user.service.UserServiceProvider;
import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
public class FmsUserService implements IFmsUserService {
    public final static String DIGITS = "0123456789";
    public final static String ALPHABETS_SUPER_CASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public final static String ALPHABETS_LOWER_CASE = "abcdefghijklmnopqrstuvwxyz";
    public final static String ALPHABETS_NO_CASE = ALPHABETS_SUPER_CASE + ALPHABETS_LOWER_CASE;
    public final static String ALPHABETS_ALL = DIGITS + ALPHABETS_NO_CASE;

    @Autowired
    private UserServiceProvider userServiceProvider;
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public UserInfo login(String loginName, String loginPwd) {
        UserInfo userInfo = null;
        // 1. 验证登录名和密码
        try {
            userInfo = userServiceProvider.getUserService().getUserByLoginName(loginName);
        } catch (TException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String generateToken() {
        return generateRandomCode(ALPHABETS_ALL, 32);
    }

    @Override
    public void cacheTokenUserInfo(String token, UserInfo userInfo) {
        // 3. 缓存用户
        int timeout = 60*24*14;
        redisTemplate.opsForValue().set("yt0421", userInfo, timeout, TimeUnit.SECONDS);
    }

    public static String generateRandomCode(String alphabets, int size) {
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
