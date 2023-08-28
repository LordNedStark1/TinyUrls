package com.urlshortener.customurlshortener.exceptions;


public class CustomUrlAlreadyExistException extends RuntimeException {
    public CustomUrlAlreadyExistException(String message) {
        super(message);
    }
}
