package com.hlw.edu.exception;

/**
 * @description: Course操作异常类
 */
public class CourseOperationException extends RuntimeException{
    private static final long serialVersionUID = -7989645915942446156L;

    public CourseOperationException(String msg){super(msg);}
}
