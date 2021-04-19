package com.cszjkj.aisc.fms_user.http;

import java.io.Serializable;

public class Response implements Serializable {
    private String code;
    private String message;

    public Response(String code, String message) {
        this.code = code;
        this.message = message;
    }

    // 定义常用错误响应
    public final static Response USER_LOGIN_NAME_PWD_INVALID =
            new Response("1001", "登录名和密码错误");
}
