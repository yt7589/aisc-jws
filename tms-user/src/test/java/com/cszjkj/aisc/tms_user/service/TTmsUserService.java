package com.cszjkj.aisc.tms_user.service;

import com.cszjkj.aisc.cm_user.UserInfo;
import com.cszjkj.aisc.cm_user.UserService;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;
import org.apache.thrift.transport.layered.TFramedTransport;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TTmsUserService {
    @DisplayName("测试用户注册接口")
    @Test
    public void callRegisterUser() {
        String targetRst = "Ok";
        String rst = "Ok";

        String serverIp = "192.168.94.18";
        int serverPort = 9100;
        try {
            TSocket socket = new TSocket(serverIp, serverPort);
            TTransport transport = new TFramedTransport(socket);
            transport.open();
            TProtocol protocol = new TBinaryProtocol(transport);
            UserService.Client client = new UserService.Client(protocol);
            UserInfo info = new UserInfo();
            info.setRealName("李三");
            info.setLoginName("ls");
            info.setLoginPwd("123456");
            info.setMobilePhone("139");
            info.setEmailAddr("ls@qq.com");
            client.registerUser(info);
        } catch (TTransportException e) {
            e.printStackTrace();
        } catch (TException e) {
            e.printStackTrace();
        }

        Assertions.assertEquals(targetRst, rst);
    }
}
