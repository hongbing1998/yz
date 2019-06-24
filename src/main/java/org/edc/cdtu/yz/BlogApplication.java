package org.edc.cdtu.yz;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

@Configuration
//@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class}) //exclude:做连接数据库使用 无则报：Failed to determine a suitable driver class
@SpringBootApplication
@MapperScan("com.zhengqing.blog.mapper")
public class BlogApplication {
    public static void main(String[] args) {
        SpringApplication.run(BlogApplication.class, args);
    }
}

