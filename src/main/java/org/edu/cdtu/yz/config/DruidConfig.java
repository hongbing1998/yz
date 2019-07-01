package org.edu.cdtu.yz.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.Arrays;
import java.util.HashMap;

@Configuration
public class DruidConfig {

    /**
     * 定制配置druid数据源
     */
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DruidDataSource druidDataSource() {
        return new DruidDataSource();
    }

    /**
     * 注入druid管理后台的数据监控对象
     */
    @Bean
    public ServletRegistrationBean servletRegistrationBean() {
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean();
        HashMap<String, String> initParams = new HashMap<>();
        initParams.put("loginUsername", "admin");
        initParams.put("loginPassword", "888888");
        servletRegistrationBean.setUrlMappings(Arrays.asList("/druid/*"));
        servletRegistrationBean.setServlet(new StatViewServlet());
        servletRegistrationBean.setInitParameters(initParams);
        return servletRegistrationBean;
    }

    /**
     * 注入数据源过滤器
     */
    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        HashMap<String, String> initParams = new HashMap<>();
        initParams.put("exclusions", "*.js,*.css,*.druid/*");
        filterRegistrationBean.setFilter(new WebStatFilter());
        filterRegistrationBean.setInitParameters(initParams);
        filterRegistrationBean.setUrlPatterns(Arrays.asList("/*"));
        return filterRegistrationBean;
    }
}