package com.controller;

import com.entities.Payment;
import com.result.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.swing.text.html.HTMLDocument;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Izumi Sakai
 * @since 2020-07-08
 */
@RestController
@Slf4j
@RequestMapping("/consumer")
public class OrderController {
    private static final String PAYMENT_URL="http://cloud-payment-service";
    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/{id}")
    public CommonResult<Payment> find(@PathVariable("id") Integer id){
        return restTemplate.getForObject(PAYMENT_URL + "//payment/payments/"+id.toString(), CommonResult.class);
    }

    @RequestMapping("/insert")
    public CommonResult insert(HttpServletRequest request){
        String serial = request.getParameter("serial");
        Payment payment=new Payment();
        payment.setSerial(serial);
        System.out.println(payment);
        return restTemplate.postForObject(PAYMENT_URL+"//payment/payments/insert",payment,CommonResult.class);
    }
}

