package com.liao.exception;

/**
 * 自定义异常类
 */
public class RegistException extends RuntimeException {
    //定义无参构造方法
    public RegistException() {
        super();
    }
    //定义有参构造方法
    public RegistException(String message) {
        super(message);
    }
}
