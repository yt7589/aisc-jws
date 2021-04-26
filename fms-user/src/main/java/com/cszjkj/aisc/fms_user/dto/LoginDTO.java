package com.cszjkj.aisc.fms_user.dto;

import com.cszjkj.aisc.cm_common.dto.UserDTO;

import java.io.Serializable;

public class LoginDTO implements Serializable {
    private String token;
    private UserDTO userDTO;

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
