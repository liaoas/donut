package com.liao.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ApplicationContextEvent;
import org.springframework.context.event.EventListener;

import java.io.IOException;


/**
 *
 * TODO: 应用启动 自动打开Chrome浏览器
 * @author LiAo
 * @date 2020/5/20 16:44
 */
@Configuration
public class IndexConfig {

    private final Logger logger = LoggerFactory.getLogger(IndexConfig.class);


    @EventListener({ApplicationContextEvent.class})
    void applicationContextEvent(){
        String url = "http://localhost:8080/donut/login.html";

        logger.info("应用准备就绪.....启动浏览器");

        logger.info("Start address                : {}",url);

        Runtime runtime = Runtime.getRuntime();
        try {
            runtime.exec("rundll32 url.dll,FileProtocolHandler " + url);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
