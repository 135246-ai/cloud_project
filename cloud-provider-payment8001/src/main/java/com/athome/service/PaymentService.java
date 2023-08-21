package com.athome.service;

import com.athome.entities.Payment;

public interface PaymentService {

    int insert(Payment payment);

    Payment selectById(long id);
}
