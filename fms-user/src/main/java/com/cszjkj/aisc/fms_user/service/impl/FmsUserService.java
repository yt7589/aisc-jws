package com.cszjkj.aisc.fms_user.service.impl;

import com.cszjkj.aisc.cm_user.UserDTO;
import com.cszjkj.aisc.cm_user.UserInfo;
import com.cszjkj.aisc.cm_user.UserService;
import com.cszjkj.aisc.fms_user.service.IFmsUserService;
import com.cszjkj.aisc.fms_user.service.ServiceProvider;
import org.apache.thrift.TException;
import org.apache.thrift.transport.TTransport;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

@Service
public class FmsUserService implements IFmsUserService {
    private final static Logger logger = Logger.getLogger(FmsUserService.class.getCanonicalName());
    public final static String DIGITS = "0123456789";
    public final static String ALPHABETS_SUPER_CASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public final static String ALPHABETS_LOWER_CASE = "abcdefghijklmnopqrstuvwxyz";
    public final static String ALPHABETS_NO_CASE = ALPHABETS_SUPER_CASE + ALPHABETS_LOWER_CASE;
    public final static String ALPHABETS_ALL = DIGITS + ALPHABETS_NO_CASE;

    @Autowired
    private ServiceProvider serviceProvider;
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public UserInfo login(String loginName, String loginPwd) {
        logger.info("loginName=" + loginName + "!");
        UserInfo userInfo = null;
        List<Object> userServiceInfo = serviceProvider.getTmsUserEnv();
        logger.info("step 1");
        TTransport transport = (TTransport) userServiceInfo.get(ServiceProvider.SERVICE_ENV_IDX_TRANSPORT);
        UserService.Client client = (UserService.Client) userServiceInfo.get(ServiceProvider.SERVICE_ENV_IDX_CLIENT);
        logger.info("step 2");
        // 1. 验证登录名和密码
        try {
            userInfo = client.getUserByLoginName(loginName);
            logger.info("step 3 userInfo=" + userInfo + "!");
            transport.close();
            logger.info("step 4");
        } catch (TException e) {
            logger.info("获取登录名对应用户异常: " + e.getMessage() + "!");
        }
        return userInfo;
    }

    public String generateToken() {
        return generateRandomCode(ALPHABETS_ALL, 32);
    }

    @Override
    public void cacheTokenUserDTO(String token, UserDTO userDTO) {
        // 3. 缓存用户
        int timeout = 60*24*14;
        redisTemplate.opsForValue().set(token, userDTO, timeout, TimeUnit.SECONDS);
    }

    @Override
    public UserDTO convertToUserDTO(UserInfo userInfo) {
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(userInfo, userDTO);
        return userDTO;
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
