package com.atguigu.ggkt.vod;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author wzj
 * @Date 2022/8/13 15:13
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.atguigu")
public class ServiceVodApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceVodApplication.class, args);
    }
}
