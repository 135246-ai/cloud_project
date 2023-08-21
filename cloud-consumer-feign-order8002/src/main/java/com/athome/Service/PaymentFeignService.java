package com.athome.Service;

import com.athome.entities.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Service
@FeignClient("CLOUD-PAYMENT-SERVICE")
public interface PaymentFeignService {

    @GetMapping("/payment/selectById/{id}")
    CommonResult selectById(@PathVariable("id") long id);

    @GetMapping("/payment/getFeignTimeOut")
    CommonResult<String> getFeignTimeOut();
}
