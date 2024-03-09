package com.tom.exception;

public enum ServiceCode {

	OK(200,"OK");

    private int value;
    private String message;

    ServiceCode(int value , String message){
        this.value = value;
        this.message = message;
    }

    public int getValue(){
        return value;
    }

    public String getMessage() {
        return message;
    }
}
