package com.service.impl;

import com.entity.Payment;
import com.mapper.PaymentMapper;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.ribbon.proxy.annotation.Hystrix;
import com.result.CommonResult;
import com.service.IPaymentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.concurrent.TimeUnit;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Izumi Sakai
 * @since 2020-07-08
 */
@Service
public class PaymentServiceImpl extends ServiceImpl<PaymentMapper, Payment> implements IPaymentService {
    @HystrixCommand(fallbackMethod = "timeOutHandler",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "3000")
    })
    public String timeOut(Integer id){
        try {
            TimeUnit.SECONDS.sleep(5);
            return "success";
        } catch (InterruptedException e) {
            e.printStackTrace();
            return "fail";
        }
    }

    public String timeOutHandler(Integer id){
        return "服务降级成功";
    }

    //服务熔断
    @HystrixCommand(fallbackMethod = "breakdownError",commandProperties = {
            //是否开启断路器
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),
            //请求次数
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),
            //时间窗口期
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMillisecond",value = "10000"),
            //达到百分之多少开启跳闸
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60"),
    })
    public CommonResult breakdown(Integer id){
        if (id<0)
            throw new RuntimeException("不能为负数");
        return new CommonResult(200,"是正数成功");
    }

    public CommonResult breakdownError(){
        return new CommonResult(200,"服务熔断成功");
    }
}
