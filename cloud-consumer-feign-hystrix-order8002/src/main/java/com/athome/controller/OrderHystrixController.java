package com.athome.controller;


import com.athome.service.PaymentHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/consumer")
@Slf4j
@DefaultProperties(defaultFallback = "paymentInfo_Global_Handle")
public class OrderHystrixController {

    @Resource
    private PaymentHystrixService hystrixService;

    @Value("${server.port}")
    private String serverPort;

    @HystrixCommand
    @GetMapping("/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id){
        String result = hystrixService.paymentInfo_OK(id);
        log.info("**************"+result);
        return result;
    }


    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandle",commandProperties = {
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds", value = "10000")
    })
    @GetMapping("/hystrix/timeOut/{id}")
    public String paymentInfo_TimeOut(@PathVariable("id") Integer id){
        String result = hystrixService.paymentInfo_TimeOut(id);
        log.info("**************"+result);
        return result;
    }

    public String paymentInfo_TimeOutHandle(@PathVariable("id") Integer id){
        return "我是消费者8002，对方支付系统繁忙请5秒后再试自己运行出错请检查自己，哭哭哭！！！"+id;
    }

    public String paymentInfo_Global_Handle(){
        return "我是消费者8002，系统异常啦！！！";
    }
}
