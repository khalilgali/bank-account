package com.xalt.kata.exception;

import org.springframework.web.bind.annotation.ResponseBody;

@ResponseBody
public class InsufficientBalanceException extends RuntimeException{

    public InsufficientBalanceException(String message){
        super(message);
    }

    public InsufficientBalanceException(String message, Throwable throwable){
        super(message, throwable);
    }
}
