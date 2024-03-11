package com.tom.exception;


public class ServiceException extends RuntimeException {

	private ServiceCode serverCode;

    public ServiceException(ServiceCode serverCode, String message) {
        super(message);
        this.serverCode = serverCode;
    }

    public ServiceCode getServiceCode(){
        return serverCode;
    }
}
