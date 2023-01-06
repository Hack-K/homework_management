package com.hlw.edu.exception;

/**
 * @description: Homework操作异常类
 */
public class HomeworkOperationException extends RuntimeException{
    private static final long serialVersionUID = 7621921089468686702L;

    public HomeworkOperationException(String msg){super(msg);}
}
