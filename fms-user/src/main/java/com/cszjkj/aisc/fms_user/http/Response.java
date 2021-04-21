package com.cszjkj.aisc.fms_user.http;

import com.cszjkj.aisc.fms_user.dto.RegisterUserDTO;

import java.io.Serializable;

public class Response<T> implements Serializable {
    private String code;
    private String message;
    private T data;

    public Response() {
        this("0", "Ok", null);
    }

    public Response(T data) {
        this("0", "Ok", data);
    }

    public Response(String code, String message) {
        this(code, message, null);
    }

    public Response(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    // 定义常用错误响应
    public final static Response USER_LOGIN_NAME_PWD_INVALID =
            new Response<>("1001", "登录名和密码错误");
    public final static Response USER_LOGIN_NAME_CAN_NOT_BE_NULL =
            new Response<>("1002", "登录名不能为空");
    public final static Response MOBILE_EMAIL_BOTH_NULL =
            new Response<>("1003", "手机号和邮箱地址不能同时为空");
    public final static Response<RegisterUserDTO> VERIFY_CODE_NULL =
            new Response<>("1004", "验证码不能为空");
    public final static Response<RegisterUserDTO> VERIFY_CODE_INVALID =
            new Response<>("1005", "验证码不正确");
}
