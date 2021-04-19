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
import java.util.logging.Logger;

@Configuration
public class TmsUserThriftServer {
    private final static Logger logger = Logger.getLogger(TmsUserThriftServer.class.getName());
    @Autowired
    private UserService.Iface userService;
    @Value("${service.port}")
    private int servicePort;

    @PostConstruct
    public void startThriftServer() {
        TProcessor processor = new UserService.Processor<>(userService);
        TNonblockingServerSocket socket = null;
        try {
            socket = new TNonblockingServerSocket(servicePort);
        } catch (TTransportException e) {
            e.printStackTrace();
        }
        if (null == socket) {
            System.exit(1);
        }
        TNonblockingServer.Args args = new TNonblockingServer.Args(socket);
        args.processor(processor);
        args.transportFactory(new TFramedTransport.Factory());
        args.protocolFactory(new TBinaryProtocol.Factory());
        TServer server = new TNonblockingServer(args);
        logger.info("启动Thrift服务器");
        server.serve();
    }
}
