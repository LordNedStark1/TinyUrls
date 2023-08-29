package com.urlshortener.customurlshortener.model;

public enum CustomMessage {
    URL_ALREADY_EXIST("url already exists"),
    URL_NOT_FOUND("url not found"),
    USER_ALREADY_EXIST("user already exists"),
    LOGIN_SUCCESSFUL("Login SuccessFUL");

    private final String message;


    CustomMessage(String message) {
        this.message = message;
    }

    public String getMessage(){
        return this.message;
    }
}
