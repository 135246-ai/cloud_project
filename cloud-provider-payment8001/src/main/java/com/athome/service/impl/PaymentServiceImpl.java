package com.athome.service.impl;

import com.athome.entities.Payment;
import com.athome.mapper.PaymentMapper;
import com.athome.service.PaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Resource
    private PaymentMapper paymentMapper;
    @Override
    public int insert(Payment payment) {
        return paymentMapper.insert(payment);
    }

    @Override
    public Payment selectById(long id) {
        return paymentMapper.selectById(id);
    }
}
