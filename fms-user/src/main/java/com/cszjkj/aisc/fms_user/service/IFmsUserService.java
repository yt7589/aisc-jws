package com.cszjkj.aisc.fms_user.service;

import com.cszjkj.aisc.cm_common.dto.UserDTO;
import com.cszjkj.aisc.cm_user.UserInfo;

public interface IFmsUserService {
    public UserInfo login(String loginName, String loginPwd);
    public String generateToken();
    public void cacheTokenUserDTO(String token, UserDTO userDTO);
    public UserDTO convertToUserDTO(UserInfo userInfo);
}
