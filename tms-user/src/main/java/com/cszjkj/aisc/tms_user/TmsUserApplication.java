package com.cszjkj.aisc.tms_user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement(proxyTargetClass = true)
@ComponentScan(basePackages = {"com.cszjkj.aisc.tms_user.*"})
@MapperScan({"com.cszjkj.aisc.tms_user.mapper"}) // 可逗号分隔的多个
public class TmsUserApplication {
    public static void main(String[] args) {
        System.out.println("app 1");
        SpringApplication.run(TmsUserApplication.class, args);
        System.out.println("app 2");
    }
}
