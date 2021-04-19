package com.cszjkj.aisc.fms_user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement(proxyTargetClass = true)
@ComponentScan(basePackages = {"com.cszjkj.aisc.fms_user.*"})
public class FmsUserApplication {
    public static void main(String[] args) {
        SpringApplication.run(FmsUserApplication.class, args);
    }
}
