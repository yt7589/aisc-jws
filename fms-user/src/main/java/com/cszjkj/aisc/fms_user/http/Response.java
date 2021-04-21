package com.cszjkj.aisc.fms_user.http;

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
}
