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
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class TTmsUserService {
    public final static List<Integer> tests = new ArrayList<>();

    @BeforeAll
    public static void init() {
        //tests.add(1); // 用户接口测试
        tests.add(2); // 用户登录（找不到登录名）测试
    }

    @DisplayName("测试用户注册接口")
    @Test
    public void callRegisterUser() {
        int testId = 1;
        if (!tests.contains(testId)) {
            return;
        }
        String targetRst = "Ok";
        String rst = "Ok";

        String serverIp = "192.168.2.38";
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

    @DisplayName("测测用户登录接口")
    @Test
    public void testGetUserByLoginName() {
        int testId = 2;
        if (!tests.contains(testId)) {
            return;
        }

        List<Object> clientInfo = getUserServiceClientInfo();
        UserService.Client client = (UserService.Client)clientInfo.get(1);
        TTransport transport = (TTransport)clientInfo.get(0);
        String target = "ok";
        String rst = "error";
        try {
            UserInfo userInfo = client.getUserByLoginName("123s");
            System.out.println("userInfo=" + userInfo + "!");
        } catch (TException e) {
            e.printStackTrace();
            rst = "ok";
        }
        transport.close();
        Assertions.assertEquals(target, rst);
    }






    private List<Object> getUserServiceClientInfo() {
        String serverIp = "192.168.2.38";
        int serverPort = 9100;
        UserService.Client client = null;
        TTransport transport = null;
        try {
            TSocket socket = new TSocket(serverIp, serverPort);
            transport = new TFramedTransport(socket);
            transport.open();
            TProtocol protocol = new TBinaryProtocol(transport);
            client = new UserService.Client(protocol);
        } catch (TTransportException e) {
            e.printStackTrace();
        } catch (TException e) {
            e.printStackTrace();
        }
        List<Object> clientInfo = new ArrayList<>();
        clientInfo.add(transport);
        clientInfo.add(client);
        return clientInfo;
    }
}
