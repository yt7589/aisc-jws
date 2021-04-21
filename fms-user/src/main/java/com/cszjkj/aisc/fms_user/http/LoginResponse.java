package com.cszjkj.aisc.fms_user.http;

import com.cszjkj.aisc.cm_user.UserDTO;

public class LoginResponse extends Response {
    private String token;
    private UserDTO userDTO;

    public LoginResponse(String token, UserDTO userDTO) {
        this.token = token;
        this.userDTO = userDTO;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }
}
