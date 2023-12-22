package com.example.cityswift.server.exceptions;

public class SimpleException extends RuntimeException{
    private final int errorCode;

    public SimpleException(int errorCode, String message){
        super(message);
        this.errorCode = errorCode;
    }

    public int getErrorCode(){
        return errorCode;
    }
}
