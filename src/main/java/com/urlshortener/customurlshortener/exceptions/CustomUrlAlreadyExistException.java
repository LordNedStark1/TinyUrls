package com.urlshortener.customurlshortener.exceptions;

import com.urlshortener.customurlshortener.model.CustomMessage;

public class CustomUrlAlreadyExistException extends RuntimeException {
    public CustomUrlAlreadyExistException(CustomMessage message) {
        super(String.valueOf(message));
    }
}
