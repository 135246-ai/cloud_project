package com.athome.controller;

import com.athome.entities.CommonResult;
import com.athome.entities.Payment;
import com.athome.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;
    @Value("${server.port}")
    private String port;

    @PostMapping("/payment/insert")
    public CommonResult insert(@RequestBody Payment payment){

        int result = paymentService.insert(payment);
        if(result > 0){
            log.info("****************"+result);
            return new CommonResult(200,"插入成功",result);
        }else {
            return new CommonResult<>(444,"插入失败",null);
        }
    }

    @GetMapping("/payment/selectById/{id}")
    public CommonResult selectById(@PathVariable("id") long id){
        Payment payment = paymentService.selectById(id);
        if(payment != null){
            log.info("*****************"+ payment);
            return new CommonResult(200,"查询成功"+port,payment);
        }else {
            return new CommonResult<>(444,"查询失败",null);
        }
    }
    @GetMapping("/test")
    public String test(){
        return "hello!!!!";
    }

    @GetMapping("/payment/getFeignTimeOut")
    public CommonResult<String> getFeignTimeOut(){
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return new CommonResult<>(200,"查询成功"+port,port);
    }
}
