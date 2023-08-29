package com.urlshortener.customurlshortener;

import com.urlshortener.customurlshortener.dto.requests.BuildUrlRequest;
import com.urlshortener.customurlshortener.dto.requests.SignUpRequest;
import com.urlshortener.customurlshortener.dto.response.LoginResponse;
import com.urlshortener.customurlshortener.dto.response.ShortenedUrlResponse;
import com.urlshortener.customurlshortener.dto.response.SignUpResponse;
import com.urlshortener.customurlshortener.exceptions.InvalidCredentialsException;
import com.urlshortener.customurlshortener.exceptions.UrlBaseException;
import com.urlshortener.customurlshortener.exceptions.UserAlreadyExistsException;
import com.urlshortener.customurlshortener.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.urlshortener.customurlshortener.model.CustomMessage.LOGIN_SUCCESSFUL;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserService userService;


    private String fullUrl = "http://jebfbeiwbiuifjjbdfusfbeuifbiuirDS97H43e4e-6tghg6v";
    @Test
    public void testThatUserCanSignUp(){
        SignUpRequest signUpRequest = buildSignUpRequest("email@gmail.com");


        SignUpResponse signUpResponse ;
        try{
            signUpResponse = userService.signUp(signUpRequest);

            assertEquals(signUpResponse.getMessage(), "Successfully signed up");
            assertEquals(signUpResponse.getEmail(), "email@gmail.com");
            assertEquals(signUpResponse.getUserName(), "ned");
        }catch (UrlBaseException ignored){}

    }
    @Test
    public void testThatUserCannotSignUpWithTheSameEmail(){
        SignUpRequest signUpRequest = buildSignUpRequest("useralreadyexistsexceptonemail@gmail.com");

        try{
         userService.signUp(signUpRequest);
        }catch (UrlBaseException urlBaseException){}

        assertThrows(UserAlreadyExistsException.class,()-> userService.signUp(signUpRequest));
    }
    @Test
    public void testThatUserCanLogIn(){
        SignUpRequest signUpRequest =buildSignUpRequest("emailben@gmail.com");

        SignUpResponse signUpResponse ;

        try{
            userService.signUp(signUpRequest);
        }catch (UrlBaseException urlBaseException){}
        LoginResponse loginResponse =
                userService.login(signUpRequest.getEmail(), signUpRequest.getPassword());

        assertTrue(loginResponse.isLoggedIn());
        assertEquals(loginResponse.getMessage(), LOGIN_SUCCESSFUL.getMessage());
    }
    @Test
    public void testLoginThrowsException(){
     assertThrows(InvalidCredentialsException.class,
             ()->  userService.login("InvalidEmail", "WrongPassWord"));
    }
    @Test
    public void userCanShortenUrl(){
        SignUpRequest signUpRequest =buildSignUpRequest("loggedinuserurlshortened@gmail.com");

        LoginResponse loginResponse = null ;

        try{
            userService.signUp(signUpRequest);
        loginResponse = userService.login(signUpRequest.getEmail(), signUpRequest.getPassword());
        }catch (UrlBaseException urlBaseException){}

        BuildUrlRequest buildUrlRequest = BuildUrlRequest.builder()
                .actualUrlLink(fullUrl)
                .email(signUpRequest.getEmail())
                .description("link to no were")
                .build();

        ShortenedUrlResponse shortenedUrlResponse =
                            userService.shortenUrl( buildUrlRequest);

        int shortenedUrlLength = shortenedUrlResponse.getReplacedUrl().length();
        boolean isShortened = shortenedUrlLength > 22 && shortenedUrlLength < 25;

        boolean isShorterThanActual = fullUrl.length() > shortenedUrlLength;

        assertTrue(isShorterThanActual);
        assertTrue(isShortened);
        assertEquals(shortenedUrlResponse.getCompleteUrl(), fullUrl);

    }
    private SignUpRequest buildSignUpRequest(String email){

        return  SignUpRequest.builder()
                .email(email)
                .password("P@ssw0rd")
                .userName("ned")
                .build();
    }
}
