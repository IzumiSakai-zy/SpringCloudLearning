package com.service;

import com.entity.Payment;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.result.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Component
@FeignClient(value = "cloud-payment-hystrix-service",fallback = PaymentServiceFallback.class)
public interface PaymentService {
    @GetMapping("/payment/payments/{id}")
    CommonResult<Payment> query(@PathVariable("id") Integer id);

    @RequestMapping("/payment/payments/insert")
    CommonResult<Payment> insert(Payment payment);

    @RequestMapping("/payment/hystrix/{id}")
    CommonResult hystrix(@PathVariable("id") Integer id);
}
