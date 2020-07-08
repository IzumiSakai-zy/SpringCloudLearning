package com.result;

import com.entities.Payment;
import lombok.Data;

@Data
public class CommonResult<T> {
    private Integer code;
    private String message;
    private T t;

    public CommonResult(Integer code, String message, T t) {
        this.code = code;
        this.message = message;
        this.t = t;
    }

    public CommonResult() {
    }

    public CommonResult(Integer code, String message) {
        this(code,message,null);
    }

    @Override
    public String toString() {
        return "CommonResult{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", t=" + t +
                '}';
    }
}
