package com.example.salarymanage;

import com.example.salarymanage.filter.CrossDomainFilter;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
//MapperScan:表示自动扫描指定的Mapper包下的接口 自动创建接口的对象 并注入到IOC容器中
@MapperScan("com.example.salarymanage.mapper")
public class SalaryManageApplication {

    public static void main(String[] args) {
        SpringApplication.run(SalaryManageApplication.class, args);
    }

    @Bean
    public FilterRegistrationBean<CrossDomainFilter> filterRegistrationBean(){
        FilterRegistrationBean<CrossDomainFilter> filter = new FilterRegistrationBean<>();
        filter.setFilter(new CrossDomainFilter());
        //设置过滤器的优先级 1表示优先级最高
        filter.setOrder(1);
        //设置请求的拦截方式  /* 表示拦截所有请求
        filter.addUrlPatterns("/*");
        return filter;
    }
}
