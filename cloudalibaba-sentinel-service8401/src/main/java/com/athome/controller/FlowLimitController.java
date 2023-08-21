package com.athome.controller;


import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class FlowLimitController {

    @GetMapping("/testA")
    @SentinelResource(value = "testA")
    public String testA() throws InterruptedException {
//        TimeUnit.MILLISECONDS.sleep(800);
        return "------testA";
    }
    @GetMapping("/testB")
    public String testB(){
        log.info(Thread.currentThread().getName()+"------testB");
        return "------testB";
    }
    @GetMapping("/testD")
    public String testD(){
//        try {
//            TimeUnit.MILLISECONDS.sleep(1000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
        log.info("----test RT");
        return "------testD";
    }

    @GetMapping("/testE/{a}")
    public String testE(@PathVariable("a") Integer a){
        if (a == 0){
            int b= 10/0;
        }
        log.info("----test RT");
        return "------testE";
    }

    @GetMapping("/testHostKey")
    @SentinelResource(value = "testHostKey", blockHandler = "deal_testHotKey")
    public String testHotKey(@RequestParam(value = "a", required = false) String a,
                             @RequestParam(value = "b", required = false) String b){
        return "----testHostKey";
    }

    public String deal_testHotKey(String a, String b, BlockException blockException){
        return "dela_testHotKey_err,crying";
    }
}
