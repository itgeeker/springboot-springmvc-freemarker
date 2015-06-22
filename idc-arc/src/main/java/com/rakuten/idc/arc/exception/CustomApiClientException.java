package com.rakuten.idc.arc.exception;

import com.rakuten.gid.services.rest.client.ApiClientException;

public class CustomApiClientException extends ApiClientException{

    private static final long serialVersionUID = 1L;

    public CustomApiClientException(){
        super("Custom Api Client Exception!");
    }
    
    public CustomApiClientException(String message) {
        super(message);
    }

    public CustomApiClientException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
