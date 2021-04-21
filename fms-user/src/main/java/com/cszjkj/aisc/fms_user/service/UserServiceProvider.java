package com.cszjkj.aisc.fms_user.service;

import com.cszjkj.aisc.cm_user.UserService;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;
import org.apache.thrift.transport.layered.TFramedTransport;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 调用Thrift用户服务的工具类
 */
@Component
public class UserServiceProvider {
    @Value("${thrift.user.ip}")
    private String serverIp;
    @Value("${thrift.user.port}")
    private int serverPort;

    public List<Object> getUserServiceInfo() {
        List<Object> userServiceInfo = new ArrayList<>();
        TSocket socket = null;
        TTransport transport = null;
        UserService.Client client = null;
        int timeout = 3000;
        try {
            socket = new TSocket(serverIp, serverPort);
            transport = new TFramedTransport(socket);
            transport.open();
            TProtocol protocol = new TBinaryProtocol(transport);
            client = new UserService.Client(protocol);
        } catch (TTransportException e) {
            e.printStackTrace();
        }
        userServiceInfo.add(socket);
        userServiceInfo.add(transport);
        userServiceInfo.add(client);
        return userServiceInfo;
    }
}
