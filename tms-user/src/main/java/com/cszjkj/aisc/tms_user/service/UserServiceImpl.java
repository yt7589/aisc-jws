package com.cszjkj.aisc.tms_user.service;

import com.cszjkj.aisc.cm_user.UserDTO;
import com.cszjkj.aisc.cm_user.UserInfo;
import com.cszjkj.aisc.cm_user.UserService;
import com.cszjkj.aisc.tms_user.mapper.UserMapper;
import org.apache.thrift.TException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class UserServiceImpl implements UserService.Iface {
    public final static Logger logger = Logger.getLogger(UserServiceImpl.class.getCanonicalName());

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserInfo getUserById(long userId) throws TException {
        return userMapper.getUserById(userId);
    }

    @Override
    public UserInfo getUserByLoginName(String loginName) throws TException {
        logger.log(Level.INFO, "获取指定登录名用户信息 v0.0.1");
        /*UserDTO userDTO = userMapper.getUserByLoginName(loginName);
        UserInfo userInfo = new UserInfo();
        BeanUtils.copyProperties(userDTO, userInfo);*/
        return userMapper.getUserByLoginName(loginName);
    }

    @Override
    public void registerUser(UserInfo userInfo) throws TException {
        userMapper.registerUser(userInfo);
    }
}
