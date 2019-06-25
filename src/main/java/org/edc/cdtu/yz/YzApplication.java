package org.edc.cdtu.yz;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@MapperScan(basePackages = "org.edc.cdtu.yz.mapper")
public class YzApplication {
    public static void main(String[] args) {
        SpringApplication.run(YzApplication.class, args);
    }
}

