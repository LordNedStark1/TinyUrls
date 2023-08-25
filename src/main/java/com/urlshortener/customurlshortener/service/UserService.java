package com.urlshortener.customurlshortener.service;

import com.urlshortener.customurlshortener.dto.requests.SignUpRequest;
import com.urlshortener.customurlshortener.dto.response.SignUpResponse;

public interface UserService {
    SignUpResponse signUp(SignUpRequest signUpRequest);
}
