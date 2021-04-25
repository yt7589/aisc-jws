package com.cszjkj.aisc.fms_user.service.impl;

import com.cszjkj.aisc.cm_message.MessageService;
import com.cszjkj.aisc.fms_user.dto.VerifyCodeDTO;
import com.cszjkj.aisc.fms_user.service.IVerifyCodeService;
import com.cszjkj.aisc.fms_user.service.ServiceProvider;
import org.apache.commons.lang.StringUtils;
import org.apache.thrift.TException;
import org.apache.thrift.transport.TTransport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class VerifyCodeService implements IVerifyCodeService {
    private final static Logger logger = Logger.getLogger(VerifyCodeService.class.getCanonicalName());

    @Autowired
    private ServiceProvider serviceProvider;

    @Override
    public VerifyCodeDTO getVerifyCode(String mobilePhone, String emailAddr) {
        List<Object> tmsMessageEnv = serviceProvider.getTmsMessageEnv();
        TTransport transport = (TTransport) tmsMessageEnv.get(ServiceProvider.SERVICE_ENV_IDX_TRANSPORT);
        MessageService.Client client = (MessageService.Client)tmsMessageEnv.get(ServiceProvider.SERVICE_ENV_IDX_CLIENT);
        String verifyCode = "123456";
        VerifyCodeDTO dto = new VerifyCodeDTO();
        dto.setMobilePhone(mobilePhone);
        dto.setEmailAddr(emailAddr);
        try {
            if (!StringUtils.isBlank(mobilePhone)) {
                client.send_sms(mobilePhone, verifyCode);
                dto.setVerifyCode(verifyCode);
            } else if (StringUtils.isBlank(emailAddr)) {
                client.send_email(emailAddr, verifyCode);
                dto.setVerifyCode(verifyCode);
            } else {
                dto.setVerifyCode("");
            }
        } catch (TException ex) {
            dto.setVerifyCode("");
        }
        transport.close();
        return dto;
    }
}
