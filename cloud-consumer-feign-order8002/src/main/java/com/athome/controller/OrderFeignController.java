package com.athome.controller;

import com.athome.Service.PaymentFeignService;
import com.athome.entities.CommonResult;
import com.athome.entities.Payment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class OrderFeignController {

    @Resource
    PaymentFeignService paymentFeignService;


    @GetMapping("/consumer/payment/selectById/{id}")
    public CommonResult<Payment> selectById(@PathVariable("id") long id){
        return paymentFeignService.selectById(id);
    }

    @GetMapping("/consumer/payment/getFeignTimeOut")
    public CommonResult<String> getFeignTimeOut(){
        return paymentFeignService.getFeignTimeOut();
    }

}
