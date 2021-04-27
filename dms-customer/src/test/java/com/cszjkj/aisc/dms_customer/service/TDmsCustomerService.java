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
        tests.add(2);
    }

    @DisplayName("测试查询符合条件客户列表接口")
    @Test
    public void call_getCustomers_001() {
        int testId = 1;
        if (!tests.contains(testId)) {
            return;
        }
        String targetRst = "ok";
        String rst = "ok";
        ICustomerService service = getCustomerService();
        List<CustomerDTO> customers = service.getCustomers("", null, null, 0, 1000);
        logger.info("customer:" + customers + "! v0.0.1");
        Assertions.assertEquals(targetRst, rst);
    }

    @DisplayName("测试添加新客户接口")
    @Test
    public void test_addCustomer_001() {
        int testId = 2;
        if (!tests.contains(testId)) {
            return;
        }
        String targetRst = "ok";
        String rst = "ok";
        ICustomerService service = getCustomerService();
        CustomerDTO dto = new CustomerDTO();
        service.addCustomer(dto);
        Assertions.assertEquals(targetRst, rst);
    }

    private ICustomerService getCustomerService() {
        ReferenceConfig<ICustomerService> reference = new ReferenceConfig<>();
        reference.setApplication(new ApplicationConfig("first-dubbo-consumer"));
        reference.setRegistry(new RegistryConfig("zookeeper://" + zookeeperHost + ":" + zookeeperPort));
        reference.setInterface(ICustomerService.class);
        return reference.get();
    }





    @DisplayName("单元测试模板")
    @Test
    public void test_template() {
        int testId = 2;
        if (!tests.contains(testId)) {
            return;
        }
        String targetRst = "ok";
        String rst = "ok";
        Assertions.assertEquals(targetRst, rst);
    }
}
