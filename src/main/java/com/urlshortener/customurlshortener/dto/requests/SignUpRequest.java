package com.urlshortener.customurlshortener.dto.requests;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class SignUpRequest {
    private String username;
    private String password;
    private String email;
}
