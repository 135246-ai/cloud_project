package com.athome.cloudOrder.controller;

import com.athome.cloudOrder.HandJava.MyLb;
import com.athome.entities.CommonResult;
import com.athome.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Slf4j
public class OrderController {

    @Resource
    private RestTemplate restTemplate;
    public static final String PAYMENT_URL = "http://cloud-payment-service";

    @Resource
    private MyLb myLb;

    @Resource
    private DiscoveryClient discoveryClient;

    @GetMapping ("/consumer/payment/insert")
    public CommonResult<Integer> insert(Payment payment){
        return restTemplate.postForObject(PAYMENT_URL+"/payment/insert",payment,CommonResult.class);
    }

    @GetMapping("/consumer/payment/selectById/{id}")
    public CommonResult<Payment> selectById(@PathVariable("id") long id){
        return restTemplate.getForObject(PAYMENT_URL+"/payment/selectById/"+id,CommonResult.class);
    }

    @GetMapping("/consumer/payment/testLoadBalance/{id}")
    public CommonResult<Payment> testLoadBalance(@PathVariable("id") long id){

        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        if(instances == null || instances.isEmpty()){
            return null;
        }
        ServiceInstance serviceInstance = myLb.instances(instances);
        return restTemplate.getForObject(serviceInstance.getUri()+"/payment/selectById/"+id,CommonResult.class);
    }
}
