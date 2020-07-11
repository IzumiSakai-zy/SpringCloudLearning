package com.controller;

import com.entity.Payment;
import com.result.CommonResult;
import com.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/consumerfeign")
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
}
