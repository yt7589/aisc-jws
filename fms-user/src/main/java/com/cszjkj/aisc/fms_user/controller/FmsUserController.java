package com.cszjkj.aisc.fms_user.controller;

import com.cszjkj.aisc.cm_common.http.Response;
import com.cszjkj.aisc.cm_common.dto.UserDTO;
import com.cszjkj.aisc.cm_user.UserInfo;
import com.cszjkj.aisc.fms_user.dto.LoginDTO;
import com.cszjkj.aisc.fms_user.dto.RegisterUserDTO;
import com.cszjkj.aisc.fms_user.dto.VerifyCodeDTO;
import com.cszjkj.aisc.fms_user.http.FmsUserResponse;
import com.cszjkj.aisc.fms_user.rto.RegisterUserRTO;
import com.cszjkj.aisc.fms_user.service.impl.FmsUserService;
import com.cszjkj.aisc.fms_user.service.impl.VerifyCodeService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class FmsUserController {
    @Autowired
    private FmsUserService fmsUserService;
    @Autowired
    private VerifyCodeService verifyCodeService;

    @RequestMapping(value = "/loginUser", method = RequestMethod.POST)
    public Response<LoginDTO> login(@RequestParam("loginName") String loginName, @RequestParam("loginPwd") String loginPwd) {
        UserInfo userInfo = fmsUserService.login(loginName, loginPwd);
        if (null == userInfo) {
            return FmsUserResponse.USER_LOGIN_NAME_PWD_INVALID;
        }
        if (!userInfo.getLoginPwd().equals(loginPwd)) {
            return FmsUserResponse.USER_LOGIN_NAME_PWD_INVALID;
        }
        // 2. 生成token
        String token = fmsUserService.generateToken();
        UserDTO userDTO = fmsUserService.convertToUserDTO(userInfo);
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setToken(token);
        loginDTO.setUserDTO(userDTO);
        fmsUserService.cacheTokenUserDTO(token, userDTO);
        return new FmsUserResponse<>(loginDTO);
    }

    @RequestMapping(value = "/getVerifyCode", method = RequestMethod.GET)
    public Response<VerifyCodeDTO> getVerifyCode(
            @RequestParam(value = "mobilePhone", required = false) String mobilePhone,
            @RequestParam(value = "emailAddr", required = false) String emailAddr) {
        VerifyCodeDTO dto = verifyCodeService.getVerifyCode(mobilePhone, emailAddr);
        Response<VerifyCodeDTO> resp = new Response<>(dto);
        return resp;
    }

    @RequestMapping(value = "/registerUser", method = RequestMethod.POST)
    public Response<RegisterUserDTO> registerUser(
            @RequestBody RegisterUserRTO rto
            ) {
        if (StringUtils.isBlank(rto.getLoginName())) {
            return FmsUserResponse.USER_LOGIN_NAME_CAN_NOT_BE_NULL;
        }
        if (StringUtils.isBlank(rto.getMobilePhone()) && StringUtils.isBlank(rto.getEmailAddr())) {
            return FmsUserResponse.MOBILE_EMAIL_BOTH_NULL;
        }
        if (StringUtils.isBlank(rto.getVerifyCode())) {
            return FmsUserResponse.VERIFY_CODE_NULL;
        }
        // 发送验证码
        RegisterUserDTO dto = new RegisterUserDTO();
        dto.setUserId(1008);
        dto.setRoleId(1);
        dto.setRoleName("系统管理员admin");
        return new Response(dto);
    }





}
