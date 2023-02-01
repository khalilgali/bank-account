package com.xalt.kata.exception;

import org.springframework.web.bind.annotation.ResponseBody;

@ResponseBody
public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(String message){
        super(message);
    }

    public ResourceNotFoundException(String message, Throwable throwable){
        super(message, throwable);
    }
}
