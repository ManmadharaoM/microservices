package com.hotel.service.exceptions;

public class ResourceNotException extends RuntimeException {
    public ResourceNotException(String s) {
    }

    public ResourceNotException(){
        super("Resource not found !!");
    }
}
