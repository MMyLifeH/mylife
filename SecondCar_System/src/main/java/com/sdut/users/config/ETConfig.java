package com.sdut.users.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration //该注解表示该类是一个配置类，Spring 启动时扫描该注解
public class ETConfig  implements WebMvcConfigurer {
    //配置图片的存放路径和虚拟路径的关系
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/pics/**")
                .addResourceLocations("file:c:/upload/");
    }
}
