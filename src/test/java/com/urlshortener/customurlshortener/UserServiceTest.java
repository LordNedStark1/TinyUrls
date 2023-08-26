package com.urlshortener.customurlshortener;

import com.urlshortener.customurlshortener.dto.requests.SignUpRequest;
import com.urlshortener.customurlshortener.dto.response.SignUpResponse;
import com.urlshortener.customurlshortener.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserService userService;
    @Test
    public void testThatUserCanSignUp(){
        SignUpRequest signUpRequest = SignUpRequest.builder()
                .email("nedfirst@gmail.com")
                .password("P@ssw0rd")
                .userName("ned")
                .build();


        SignUpResponse signUpResponse = userService.signUp(signUpRequest);

        assertEquals(signUpResponse.getMessage(), "Successfully signed up");
        assertEquals(signUpResponse.getEmail(), "nedfirst@gmail.com");
        assertEquals(signUpResponse.getUserName(), "ned");
    }}
