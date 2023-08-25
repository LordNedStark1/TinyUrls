package com.urlshortener.customurlshortener.dto.response;

import lombok.Data;

@Data
public class SignUpResponse {
    private String email;
    private String message;
    private String userName;
}
