package com.cszjkj.aisc.fms_user.http;

import com.cszjkj.aisc.cm_common.http.Response;
import com.cszjkj.aisc.fms_user.dto.RegisterUserDTO;

public class FmsUserResponse<T> extends Response<T> {
    public FmsUserResponse() {
        super();
    }

    public FmsUserResponse(T data) {
        super(data);
    }

    public FmsUserResponse(String code, String message) {
        super(code, message);
    }

    public FmsUserResponse(String code, String message, T data) {
        super(code, message, data);
    }

    // 定义常用错误响应
    public final static Response USER_LOGIN_NAME_PWD_INVALID =
            new FmsUserResponse<>("1001", "登录名和密码错误");
    public final static Response USER_LOGIN_NAME_CAN_NOT_BE_NULL =
            new FmsUserResponse<>("1002", "登录名不能为空");
    public final static Response MOBILE_EMAIL_BOTH_NULL =
            new FmsUserResponse<>("1003", "手机号和邮箱地址不能同时为空");
    public final static Response<RegisterUserDTO> VERIFY_CODE_NULL =
            new FmsUserResponse<>("1004", "验证码不能为空");
    public final static Response<RegisterUserDTO> VERIFY_CODE_INVALID =
            new FmsUserResponse<>("1005", "验证码不正确");
}
