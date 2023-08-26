package com.urlshortener.customurlshortener.dto.requests;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class SignUpRequest {
    private String userName;
    private String password;
    private String email;
}
