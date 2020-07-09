package com.service;

import com.entity.Payment;
import com.result.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Component
@FeignClient("cloud-payment-service")
public interface PaymentService {
    @GetMapping("/payment/payments/{id}")
    CommonResult<Payment> query(@PathVariable("id") Integer id);

    @RequestMapping("/payment/payments/insert")
    CommonResult<Payment> insert(Payment payment);
}
