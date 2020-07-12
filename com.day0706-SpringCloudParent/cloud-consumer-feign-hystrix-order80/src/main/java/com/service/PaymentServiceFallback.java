package com.service;

import com.entity.Payment;
import com.result.CommonResult;
import org.springframework.stereotype.Component;

@Component
public class PaymentServiceFallback implements PaymentService{
    @Override
    public CommonResult<Payment> query(Integer id) {
        return new CommonResult<>(200,"query服务降级成功");
    }

    @Override
    public CommonResult<Payment> insert(Payment payment) {
        return new CommonResult<>(200,"insert服务降级成功");
    }

    @Override
    public CommonResult hystrix(Integer id) {
        return new CommonResult<>(200,"hystrix服务降级成功");
    }
}
