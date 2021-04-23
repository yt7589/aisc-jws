package com.cszjkj.aisc.fms_user.service;

import com.cszjkj.aisc.fms_user.dto.VerifyCodeDTO;

public interface IVerifyCodeService {
    public VerifyCodeDTO getVerifyCode(String mobilePhone, String emailAddr);
}
