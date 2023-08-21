package com.athome.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.athome.entities.CommonResult;
import com.athome.entities.Payment;
import com.athome.exception.FallbackException;
import com.athome.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@Slf4j
public class PaymentController {

    @Value("${service-url.nacos-user-service-name}")
    private String serverName;
    @Resource
     private PaymentService paymentService;
    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/fallback/{id}")
    @SentinelResource(value = "fallback",
            blockHandlerClass = FallbackException.class,
            blockHandler = "blockFallBack",
            fallbackClass = FallbackException.class,
            fallback = "fallBack")
    public CommonResult<Payment> fallback(@PathVariable("id") Long id){
        CommonResult result = restTemplate.getForObject("http://" + serverName + "/payment/" + id, CommonResult.class);
        if (id == 4) {
            throw new IllegalArgumentException("IllegalArgumentException,非法参数异常....");
        } else if (result.getData() == null) {
            throw new NullPointerException("NullPointerException,该ID没有对应记录,空指针异常");
        }
        return result;
    }
    @GetMapping("/payment/{id}")
    @SentinelResource(value = "fallback",
            blockHandlerClass = FallbackException.class,
            blockHandler = "blockFallBack",
            fallbackClass = FallbackException.class,
            fallback = "fallBack")
    public CommonResult<Payment> payment(@PathVariable(value = "id",required = false) Long id){
        return paymentService.payment(id);
    }




}
