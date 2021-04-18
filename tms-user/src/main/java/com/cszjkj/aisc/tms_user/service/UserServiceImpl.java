package com.cszjkj.aisc.tms_user.service;

import com.cszjkj.aisc.cm_user.UserInfo;
import com.cszjkj.aisc.cm_user.UserService;
import com.cszjkj.aisc.tms_user.mapper.UserMapper;
import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService.Iface {
    @Autowired
    private UserMapper userMapper;

    @Override
    public UserInfo getUserById(long userId) throws TException {
        return userMapper.getUserById(userId);
    }

    @Override
    public UserInfo getUserByLoginName(String loginName) throws TException {
        return userMapper.getUserByLoginName(loginName);
    }

    @Override
    public void registerUser(UserInfo userInfo) throws TException {
        userMapper.registerUser(userInfo);
    }
}
