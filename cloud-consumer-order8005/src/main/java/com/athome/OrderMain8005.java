package com.athome;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient  //该注解用于向使用consul或zookeeper作为注册中心时注册服务
public class OrderMain8005 {
    public static void main(String[] args) {
        SpringApplication.run(OrderMain8005.class);
        System.out.println("Hello world!");
    }
}