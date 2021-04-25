package com.cszjkj.aisc.fms_user.service;

import com.cszjkj.aisc.cm_message.MessageService;
import com.cszjkj.aisc.cm_user.UserService;
import org.apache.thrift.TServiceClient;
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
public class ServiceProvider {
    public final static int SERVICE_ENV_IDX_SOCKET = 0;
    public final static int SERVICE_ENV_IDX_TRANSPORT = 1;
    public final static int SERVICE_ENV_IDX_CLIENT = 2;
    @Value("${tms.user.ip}")
    private String tmsUserIp;
    @Value("${tms.user.port}")
    private int tmsUserPort;
    @Value("${tms.message.ip}")
    private String tmsMessageIp;
    @Value("${tms.message.port}")
    private int tmsMessagePort;

    public enum ServiceType {
        USER,
        MESSAGE
    }

    public List<Object> getTmsUserEnv() {
        List<Object> userServiceInfo = new ArrayList<>();
        return getServiceEnv(tmsUserIp, tmsUserPort, ServiceType.USER, userServiceInfo);
    }

    public List<Object> getTmsMessageEnv() {
        List<Object> messageServiceEnv = new ArrayList<>();
        return getServiceEnv(tmsMessageIp, tmsMessagePort, ServiceType.MESSAGE, messageServiceEnv);
    }

    private List<Object> getServiceEnv(String ip, int port, ServiceType serviceType, List<Object> serviceEnv) {
        TSocket socket = null;
        TTransport transport = null;
        TServiceClient client = null;
        int timeout = 3000;
        try {
            socket = new TSocket(ip, port);
            transport = new TFramedTransport(socket);
            transport.open();
            TProtocol protocol = new TBinaryProtocol(transport);
            if (serviceType == ServiceType.USER) {
                client = new UserService.Client(protocol);
            } else if (serviceType == ServiceType.MESSAGE) {
                client = new MessageService.Client(protocol);
            } else {
                client = null;
            }
        } catch (TTransportException e) {
            e.printStackTrace();
        }
        serviceEnv.add(socket);
        serviceEnv.add(transport);
        serviceEnv.add(client);
        return serviceEnv;
    }
}
