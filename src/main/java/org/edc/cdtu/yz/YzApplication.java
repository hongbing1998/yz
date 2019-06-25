package org.edc.cdtu.yz;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

@Configuration
//@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class}) //exclude:做连接数据库使用 无则报：Failed to determine a suitable driver class
@SpringBootApplication
@MapperScan("org.edc.cdtu.yz.mapper")
public class YzApplication {
    public static void main(String[] args) {
        SpringApplication.run(YzApplication.class, args);
    }
}

