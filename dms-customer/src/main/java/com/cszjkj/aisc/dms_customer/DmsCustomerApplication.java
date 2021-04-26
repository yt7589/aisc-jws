package com.cszjkj.aisc.dms_customer;

import java.util.concurrent.CountDownLatch;
import com.cszjkj.aisc.cm_customer.ICustomerService;
import com.cszjkj.aisc.dms_customer.service.DmsCustomerService;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.ServiceConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement(proxyTargetClass = true)
@ComponentScan(basePackages = {"com.cszjkj.aisc.dms_customer.*"})
@MapperScan({"com.cszjkj.aisc.dms_customer.mapper"}) // 可逗号分隔的多个
public class DmsCustomerApplication {
    private static String zookeeperHost = System.getProperty("zookeeper.host", "127.0.0.1");;
    private static String zookeeperPort = System.getProperty("zookeeper.port", "2181");

    public static void main(String[] args) {
        ServiceConfig<DmsCustomerService> service = new ServiceConfig<>();
        service.setApplication(new ApplicationConfig("first-dubbo-provider"));
        service.setRegistry(new RegistryConfig("zookeeper://" + zookeeperHost + ":" + zookeeperPort));
        service.setInterface(ICustomerService.class);
        service.setRef(new DmsCustomerService());
        service.export();
        System.out.println("dubbo service started");
        try {
            new CountDownLatch(1).await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
