package com.athome.service;

import com.athome.entities.CommonResult;
import com.athome.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class PaymentServiceImpl implements PaymentService{


    @Override
    public CommonResult<Payment> payment(Long id)  {

        log.error("调用接口 payment接口失败，id={}", id);
        return new CommonResult<>(44444, "服务降级返回,---PaymentFallbackService", new Payment(id, "errorSerial"));
    }
}
