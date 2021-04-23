package com.cszjkj.aisc.fms_user.service.impl;

import com.cszjkj.aisc.fms_user.dto.VerifyCodeDTO;
import com.cszjkj.aisc.fms_user.service.IVerifyCodeService;
import org.springframework.stereotype.Service;

@Service
public class VerifyCodeService implements IVerifyCodeService {
    @Override
    public VerifyCodeDTO getVerifyCode(String mobilePhone, String emailAddr) {
        VerifyCodeDTO dto = new VerifyCodeDTO();
        dto.setMobilePhone("139001");
        dto.setEmailAddr("a@b.c");
        dto.setVerifyCode("a123456");
        return dto;
    }
}
