package com.athome.mapper;

import com.athome.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PaymentMapper {

    int insert(Payment payment);

    Payment selectById(@Param("id") long id);
}
