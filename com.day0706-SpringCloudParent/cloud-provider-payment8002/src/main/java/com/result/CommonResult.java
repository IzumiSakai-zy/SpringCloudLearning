package com.result;


public class CommonResult<T> {
    private Integer code;
    private String message;
    private T t;

    public CommonResult(Integer code, String message, T t) {
        this.code = code;
        this.message = message;
        this.t = t;
    }

    public CommonResult(Integer code, String message) {
        this(code,message,null);
    }

    public CommonResult() {
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
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
