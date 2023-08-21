package com.athome.service.impl;

import com.athome.service.PaymentHystrixService;
import org.springframework.stereotype.Service;


@Service
public class PaymentHystrixServiceImpl implements PaymentHystrixService {
    @Override
    public String paymentInfo_OK(Integer id) {
        return "xnsanxajs";
    }

    @Override
    public String paymentInfo_TimeOut(Integer id) {
        return "哭啦啦啦**********************"+id;
    }
}
