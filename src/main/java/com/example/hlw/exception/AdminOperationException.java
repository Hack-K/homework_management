package com.hlw.edu.exception;

/**
 * @description: Admin操作异常类
 */
public class AdminOperationException extends RuntimeException{
    private static final long serialVersionUID = -4473514521615519498L;

    public AdminOperationException(String msg){super(msg);}
}
