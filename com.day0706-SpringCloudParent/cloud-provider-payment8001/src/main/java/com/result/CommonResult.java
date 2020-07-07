package com.result;

import com.entity.Payment;
import lombok.Data;

@Data
public class CommonResult {
    private Integer code;
    private String message;
    private Payment payment;

    public CommonResult(Integer code, String message, Payment payment) {
        this.code = code;
        this.message = message;
        this.payment = payment;
    }

    public CommonResult(Integer code, String message) {
        this(code,message,null);
    }

    @Override
    public String toString() {
        return "CommonResult{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", payment=" + payment +
                '}';
    }
}
