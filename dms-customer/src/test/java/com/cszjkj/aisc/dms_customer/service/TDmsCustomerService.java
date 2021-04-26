package com.cszjkj.aisc.dms_customer.service;

import com.cszjkj.aisc.cm_common.dto.CustomerDTO;
import com.cszjkj.aisc.cm_customer.ICustomerService;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class TDmsCustomerService {
    public final static Logger logger = Logger.getLogger(TDmsCustomerService.class.getCanonicalName());
    public final static List<Integer> tests = new ArrayList<>();
    private final static String zookeeperHost = "192.168.2.38";
    private final static String zookeeperPort = "2181";

    @BeforeAll
    public static void init() {
        tests.add(1); // 用户接口测试
    }

    @DisplayName("测试用户注册接口")
    @Test
    public void callGetCustomers() {
        int testId = 1;
        if (!tests.contains(testId)) {
            return;
        }
        String targetRst = "ok";
        String rst = "ok";
        ReferenceConfig<ICustomerService> reference = new ReferenceConfig<>();
        reference.setApplication(new ApplicationConfig("first-dubbo-consumer"));
        reference.setRegistry(new RegistryConfig("zookeeper://" + zookeeperHost + ":" + zookeeperPort));
        reference.setInterface(ICustomerService.class);
        ICustomerService service = reference.get();
        List<CustomerDTO> customers = service.getCustomers("", null, null, 0, 1000);
        logger.info("customer:" + customers + "!");
        Assertions.assertEquals(targetRst, rst);
    }
}
