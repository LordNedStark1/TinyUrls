package com.urlshortener.customurlshortener;

import com.urlshortener.customurlshortener.dto.response.ShortenedUrlResponse;
import com.urlshortener.customurlshortener.service.UrlServices;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class CustomUrlShortenerApplicationTests {
    @Autowired
    UrlServices urlServices;
    @Test
    void testThatUrlIsShortened() {
        String fullUrl = "http://jebfbeiwbiuifjjbdfusfbeuifbiuirDS97H43e4e-6tghg6v";

        ShortenedUrlResponse shortenedUrlResponse =
                urlServices.shortenUrl(fullUrl);

        int urlLength = shortenedUrlResponse.getReplacedUrl().length();

        boolean isShortened = urlLength > 22 && urlLength < 25;
        assertTrue(isShortened);

    }
    @Test
    void checkThatFullUrlIsRetrivedWithShortenedUrl(){
        String fullUrl = "http://jebfbeiwbiuifjjbdfusfbeuifbiuirDS97H43e4e-6tghg6v";

        ShortenedUrlResponse shortenedUrlResponse =
                urlServices.shortenUrl(fullUrl);

        String shortenedUrl = shortenedUrlResponse.getReplacedUrl();

        String fullUrlFound = urlServices.retrieveFullUrl(shortenedUrl);
        assertEquals(fullUrl, fullUrlFound);
    }


}
