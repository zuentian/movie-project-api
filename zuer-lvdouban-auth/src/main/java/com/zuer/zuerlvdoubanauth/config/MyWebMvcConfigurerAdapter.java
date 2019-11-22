package com.zuer.zuerlvdoubanauth.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
@PropertySource("classpath:avatarUpload.properties")
public class MyWebMvcConfigurerAdapter implements WebMvcConfigurer {

    private static final Logger logger= LoggerFactory.getLogger(MyWebMvcConfigurerAdapter.class);


    @Value("${static.image.path}")
    private String imagePath;
    /**
     * 配置静态访问资源
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        logger.info("配置静态访问资源图片路径imagePath=["+imagePath+"]");
        /*
        图片路径是放置在其他地方，页面读取的时候需要访问这个路径，此处是配置静态资源的路径
         */
        registry.addResourceHandler("/image/**").addResourceLocations("file:"+imagePath);
    }

}
