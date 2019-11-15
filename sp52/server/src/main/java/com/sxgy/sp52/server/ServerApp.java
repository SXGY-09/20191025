package com.sxgy.sp52.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author SXGY_09
 * @description 描述
 * @date 2019-11-15 13:47
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.sxgy.sp52"})
@MapperScan(basePackages = {"com.sxgy.sp52.*.mapper"})
public class ServerApp {
    public static void main(String[] args) {
        SpringApplication.run(ServerApp.class,args);
    }
}
