package com.athome.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class NacosController {

    @Value("${server.port}")
    public String serverPort;

    @GetMapping("payment/nacos/{id}")
    public String getPayment(@PathVariable("id") String id){
        return "nacos registry, serverPort:"+"\t"+ serverPort+ "\t id" + id;
    }
}
