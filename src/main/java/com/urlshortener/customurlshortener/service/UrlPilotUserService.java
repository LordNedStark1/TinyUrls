package com.urlshortener.customurlshortener.service;

import com.urlshortener.customurlshortener.dto.requests.SignUpRequest;
import com.urlshortener.customurlshortener.dto.response.LoginResponse;
import com.urlshortener.customurlshortener.dto.response.SignUpResponse;
import com.urlshortener.customurlshortener.exceptions.InvalidCredentialsException;
import com.urlshortener.customurlshortener.exceptions.UserAlreadyExistsException;
import com.urlshortener.customurlshortener.model.User;
import com.urlshortener.customurlshortener.repositorie.UserRepository;
import lombok.AllArgsConstructor;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import static com.urlshortener.customurlshortener.model.CustomMessage.LOGIN_SUCCESSFUL;
import static com.urlshortener.customurlshortener.model.CustomMessage.USER_ALREADY_EXIST;

@Service
@AllArgsConstructor
public class UrlPilotUserService implements UserService {
    private final UserRepository userRepository;
    @Override
    public SignUpResponse signUp(SignUpRequest signUpRequest) {
        User user = new User();
        validateEmail(signUpRequest.getEmail());
        BeanUtils.copyProperties(  signUpRequest, user);

        User savedUser = userRepository.save(user);

        SignUpResponse signUpResponse = new SignUpResponse();
         BeanUtils.copyProperties( savedUser,signUpResponse);

         signUpResponse.setMessage("Successfully signed up");

        return signUpResponse;
    }

    @Override
    public LoginResponse login(String email, String password) {
        User user = findUserByEmail(email);
        boolean passwordMatches = isPasswordMatching(user.getPassword() , password);
        if (passwordMatches) {
            return LoginResponse.builder()
                                .isLoggedIn(true)
                                .email(user.getEmail())
                                .message(LOGIN_SUCCESSFUL.getMessage())
                                .build();
        }
        throw new InvalidCredentialsException("Invalid credentials");
    }

    private boolean isPasswordMatching(String userPassword, String password) {
        return userPassword.equals(password);
    }

    private User findUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(
                        ()-> new InvalidCredentialsException("User Not Found Exception"));
    }

    private void validateEmail(String email) {
        userRepository.findByEmail(email).ifPresent((value)->{
            throw new UserAlreadyExistsException(USER_ALREADY_EXIST.getMessage());
        });
    }
}
