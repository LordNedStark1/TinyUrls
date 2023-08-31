package com.urlshortener.customurlshortener.service;

import com.urlshortener.customurlshortener.dto.requests.BuildUrlRequest;
import com.urlshortener.customurlshortener.dto.requests.SignUpRequest;
import com.urlshortener.customurlshortener.dto.response.LoginResponse;
import com.urlshortener.customurlshortener.dto.response.ModifiedUrlResponse;
import com.urlshortener.customurlshortener.dto.response.SignUpResponse;
import com.urlshortener.customurlshortener.model.User;

public interface UserService {
    SignUpResponse signUp(SignUpRequest signUpRequest);

    LoginResponse login(String email, String password);

    User findUserByEmail(String email);

    ModifiedUrlResponse shortenUrl(BuildUrlRequest buildUrlRequest);
}
