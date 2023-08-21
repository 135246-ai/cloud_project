package com.athome.exception;


import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.athome.entities.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
public class FallbackException {

    public static CommonResult fallBack(Long id, Throwable e){
        return new CommonResult<>(500,"服务调用失败,原因是"+e.getMessage(),id);
    }
    public static CommonResult blockFallBack(Long id, BlockException exception){
        log.error("调用接口 payment接口失败，id={}", id);
        return new CommonResult<>(500,"服务调用失败",id);
    }
}
