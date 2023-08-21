package com.athome.controller;


import com.athome.entities.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@Slf4j
public class OrderController {

    @Resource
    private RestTemplate restTemplate;

    public static final String PAYMENT_URL = "http://cloud-payment-service";


    @GetMapping("/consumer/payment/consul/{id}")
    public CommonResult<String> selectById(@PathVariable("id") long id){
        log.info("\t"+id);
       return restTemplate.getForObject(PAYMENT_URL+"/payment/consul",CommonResult.class);
    }
}
