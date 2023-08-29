package com.urlshortener.customurlshortener.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class LoginResponse {
    private String message;
    private boolean isLoggedIn;
    private String email;
}
