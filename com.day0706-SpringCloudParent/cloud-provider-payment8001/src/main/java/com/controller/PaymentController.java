package com.controller;


import com.result.CommonResult;
import com.entity.Payment;
import com.service.impl.PaymentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Izumi Sakai
 * @since 2020-07-08
 */
@RestController
@RequestMapping("/payment")
public class PaymentController {
    @Autowired
    PaymentServiceImpl paymentService;

    @GetMapping("/payments/{id}")
    public CommonResult query(@PathVariable Integer id){
        Payment payment = paymentService.getById(id);
        if (payment==null)
            return new CommonResult(404,"查询失败");
        else
            return new CommonResult(200,"查询成功",payment);
    }
}

