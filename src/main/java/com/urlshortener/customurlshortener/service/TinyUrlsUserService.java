package com.urlshortener.customurlshortener.service;

import com.urlshortener.customurlshortener.dto.requests.SignUpRequest;
import com.urlshortener.customurlshortener.dto.response.SignUpResponse;
import org.springframework.stereotype.Service;

@Service
public class TinyUrlsUserService implements UserService {
    @Override
    public SignUpResponse signUp(SignUpRequest signUpRequest) {
        return null;
    }
}
