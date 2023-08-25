package com.urlshortener.customurlshortener;

import com.urlshortener.customurlshortener.dto.requests.SignUpRequest;
import com.urlshortener.customurlshortener.dto.response.ShortenedUrlResponse;
import com.urlshortener.customurlshortener.dto.response.SignUpResponse;
import com.urlshortener.customurlshortener.service.UrlServices;
import com.urlshortener.customurlshortener.service.UserService;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@AllArgsConstructor
class CustomUrlShortenerApplicationTests {

    private final UrlServices urlServices;
    private final UserService userService;
    @Test
    void testThatUrlIsShortened() {
        String fullUrl = "http://jebfbeiwbiuifjjbdfusfbeuifbiuirDS97H43e4e-6tghg6v";

        ShortenedUrlResponse shortenedUrlResponse =
                urlServices.shortenUrl(fullUrl);

        int shortenedUrlLength = shortenedUrlResponse.getReplacedUrl().length();

        boolean isShortened = shortenedUrlLength > 22 && shortenedUrlLength < 25;
        boolean isShorterThanActual = fullUrl.length() > shortenedUrlLength;
        assertTrue(isShorterThanActual);
        assertTrue(isShortened);

    }
    @Test
    void checkThatFullUrlIsRetrievedWithShortenedUrl(){
        String fullUrl = "http://jebfbeiwbiuifjjbdfusfbeuifbiuirDS97H43e4e-6tghg6v";

        ShortenedUrlResponse shortenedUrlResponse =
                urlServices.shortenUrl(fullUrl);

        String shortenedUrl = shortenedUrlResponse.getReplacedUrl();

        String fullUrlFound = urlServices.retrieveFullUrl(shortenedUrl);
        assertEquals(fullUrl, fullUrlFound);
    }
//    @Test
    public void testThatUserCanSignUp(){
        SignUpRequest signUpRequest = SignUpRequest.builder()
                                        .email("nedfirst@gmail.com")
                                        .password("P@ssw0rd")
                                        .username("ned")
                                        .build();


        SignUpResponse signUpResponse = userService.signUp(signUpRequest);

        assertEquals(signUpResponse.getMessage(), "Successfully signed up");
        assertEquals(signUpResponse.getEmail(), "nedfirst@gmail.com");
        assertEquals(signUpResponse.getUserName(), "ned");
    }

}
