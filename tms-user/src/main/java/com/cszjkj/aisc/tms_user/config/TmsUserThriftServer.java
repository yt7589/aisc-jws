package com.cszjkj.aisc.tms_user.config;

import com.cszjkj.aisc.cm_user.UserService;
import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TNonblockingServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TTransportException;
import org.apache.thrift.transport.layered.TFramedTransport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class TmsUserThriftServer {
    @Autowired
    private UserService.Iface userService;
    @Value("${service.port}")
    private int servicePort;

    @PostConstruct
    public void startThriftServer() {
        System.out.println("step 1");
        TProcessor processor = new UserService.Processor<>(userService);
        System.out.println("step 2");
        TNonblockingServerSocket socket = null;
        try {
            socket = new TNonblockingServerSocket(servicePort);
        } catch (TTransportException e) {
            e.printStackTrace();
        }
        System.out.println("step 3");
        if (null == socket) {
            System.exit(1);
        }
        System.out.println("step 4");
        TNonblockingServer.Args args = new TNonblockingServer.Args(socket);
        System.out.println("step 5");
        args.processor(processor);
        System.out.println("step 6");
        args.transportFactory(new TFramedTransport.Factory());
        System.out.println("step 7");
        args.protocolFactory(new TBinaryProtocol.Factory());
        System.out.println("step 8");
        TServer server = new TNonblockingServer(args);
        System.out.println("step 9");
        server.serve();
        System.out.println("step 10");
    }
}
