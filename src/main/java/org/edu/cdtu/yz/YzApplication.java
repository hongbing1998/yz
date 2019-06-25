package org.edu.cdtu.yz;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "org.edu.cdtu.yz.mapper")
public class YzApplication {
    public static void main(String[] args) {
        SpringApplication.run(YzApplication.class, args);
    }
}

