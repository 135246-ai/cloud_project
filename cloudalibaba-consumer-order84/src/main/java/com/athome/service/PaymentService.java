package com.athome.service;


import com.athome.entities.CommonResult;
import com.athome.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Service
@FeignClient(value = "${service-url.nacos-user-service-name}",fallback = PaymentServiceImpl.class)
public interface PaymentService {

    @GetMapping("/payment/{id}")
    CommonResult<Payment> payment(@PathVariable(value = "id",required = false) Long id);
}
