package com.urlshortener.customurlshortener.model;

public enum CustomMessage {
    URL_ALREADY_EXIST("Url Already Exists"),
    URL_NOT_FOUND("Url not Found"),
    IMPROPER_URL_EXCEPTION("Improper Url Exception"),
    USER_ALREADY_EXIST("User Already Exists"),
    LOGIN_SUCCESSFUL("Login SuccessFUL");

    private final String message;


    CustomMessage(String message) {
        this.message = message;
    }

    public String getMessage(){
        return this.message;
    }
}
