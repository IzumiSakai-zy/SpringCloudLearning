package com.controller;

import com.entity.Payment;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.result.CommonResult;
import com.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/consumerfeign")
//@DefaultProperties(defaultFallback = "timeOutHandler")
public class OrderFeignController {
    @Autowired
    private PaymentService service;

    @GetMapping("/get/{id}")
    public CommonResult<Payment> query(@PathVariable("id") Integer id){
        return service.query(id);
    }

    @RequestMapping("/insert")
    CommonResult<Payment> insert(Payment payment){
        return service.insert(payment);
    }

    @HystrixCommand(fallbackMethod = "timeOutHandler",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "3000")
    })
    @RequestMapping("/hystrix/{id}")
    CommonResult hystrix(@PathVariable("id") Integer id) throws InterruptedException {
        TimeUnit.SECONDS.sleep(5);
        return service.hystrix(id);
    }

    CommonResult timeOutHandler(Integer id){
        return new CommonResult(200,"客户端服务降级成功");
    }


}
