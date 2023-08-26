package com.urlshortener.customurlshortener.service;

import com.urlshortener.customurlshortener.dto.requests.SignUpRequest;
import com.urlshortener.customurlshortener.dto.response.SignUpResponse;
import com.urlshortener.customurlshortener.model.User;
import com.urlshortener.customurlshortener.repositorie.UserRepository;
import lombok.AllArgsConstructor;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UrlPilotUserService implements UserService {
    private final UserRepository userRepository;
    @Override
    public SignUpResponse signUp(SignUpRequest signUpRequest) {
        User user = new User();
        BeanUtils.copyProperties(  signUpRequest, user);

        User savedUser = userRepository.save(user);

        SignUpResponse signUpResponse = new SignUpResponse();
         BeanUtils.copyProperties( savedUser,signUpResponse);
        System.out.println(signUpResponse);
         signUpResponse.setMessage("Successfully signed up");

        return signUpResponse;
    }
}
