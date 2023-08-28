package com.urlshortener.customurlshortener.exceptions;


public class CustomUrlAlreadyExistException extends UrlBaseException {
    public CustomUrlAlreadyExistException(String message) {
        super(message);
    }
}
