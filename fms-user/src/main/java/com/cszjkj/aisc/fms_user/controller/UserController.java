package com.cszjkj.aisc.fms_user.controller;

import com.cszjkj.aisc.cm_user.UserDTO;
import com.cszjkj.aisc.cm_user.UserInfo;
import com.cszjkj.aisc.fms_user.dto.LoginDTO;
import com.cszjkj.aisc.fms_user.http.LoginResponse;
import com.cszjkj.aisc.fms_user.http.Response;
import com.cszjkj.aisc.fms_user.service.impl.FmsUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private FmsUserService fmsUserService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Response<LoginDTO> login(@RequestParam("loginName") String loginName, @RequestParam("loginPwd") String loginPwd) {
        UserInfo userInfo = fmsUserService.login(loginName, loginPwd);
        if (null == userInfo) {
            return Response.USER_LOGIN_NAME_PWD_INVALID;
        }
        if (!userInfo.getLoginPwd().equals(loginPwd)) {
            return Response.USER_LOGIN_NAME_PWD_INVALID;
        }
        // 2. 生成token
        String token = fmsUserService.generateToken();
        UserDTO userDTO = fmsUserService.convertToUserDTO(userInfo);
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setToken(token);
        loginDTO.setUserDTO(userDTO);
        fmsUserService.cacheTokenUserDTO(token, userDTO);
        return new Response<>(loginDTO);
    }





}
