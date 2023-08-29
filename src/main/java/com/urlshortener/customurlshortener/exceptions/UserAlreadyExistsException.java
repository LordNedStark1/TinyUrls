package com.urlshortener.customurlshortener.exceptions;

public class UserAlreadyExistsException extends UrlBaseException{
    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
