package com.liao.exception;

/**
 * 文件上传于瞎子啊异常类
 */
public class FileException extends Exception{
    public FileException(String message) {
        super(message);
    }

    public FileException(String message, Throwable cause) {
        super(message, cause);
    }

}
