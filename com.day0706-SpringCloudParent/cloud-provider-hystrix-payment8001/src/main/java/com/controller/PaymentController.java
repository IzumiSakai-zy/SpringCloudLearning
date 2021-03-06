package com.controller;


import com.result.CommonResult;
import com.entity.Payment;
import com.service.impl.PaymentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 * @author Izumi Sakai
 * @since 2020-07-08
 */
@RestController
@RequestMapping("/payment")
public class PaymentController {
    @Autowired
    PaymentServiceImpl paymentService;

    @Value("${server.port}")
    private String serverPort;

    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping("/payments/{id}")
    public CommonResult<Payment> query(@PathVariable Integer id){
        Payment payment = paymentService.getById(id);
        if (payment==null)
            return new CommonResult(404,"查询失败"+serverPort);
        else
            return new CommonResult(200,"查询成功"+serverPort,payment);
    }

    @RequestMapping("/payments/insert")
    public CommonResult insert(@RequestBody Payment payment){
        boolean save = paymentService.save(payment);
        if (save)
            return new CommonResult(200,"插入成功"+serverPort,payment);
        else
            return new CommonResult(404,"插入失败"+serverPort);
    }

    @RequestMapping("/hystrix/{id}")
    public CommonResult hystrix(@PathVariable("id") Integer id){
        String s = paymentService.timeOut(id);
        return new CommonResult(200,s);
    }

    @RequestMapping("/breakdown/{id}")
    public CommonResult breakdown(@PathVariable("id") Integer id){
        return paymentService.breakdown(id);
    }
}

