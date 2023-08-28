package com.urlshortener.customurlshortener;

import com.urlshortener.customurlshortener.dto.response.ShortenedUrlResponse;
import com.urlshortener.customurlshortener.exceptions.CustomUrlAlreadyExistException;
import com.urlshortener.customurlshortener.service.UrlService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest

class UrlPilotUrlTest {
    @Autowired
    private UrlService urlServices;
        String fullUrl = "http://jebfbeiwbiuifjjbdfusfbeuifbiuirDS97H43e4e-6tghg6v";

    @Test
    void testThatUrlIsShortened() {

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

        ShortenedUrlResponse shortenedUrlResponse =
                urlServices.shortenUrl(fullUrl);

        String shortenedUrl = shortenedUrlResponse.getReplacedUrl();

        String fullUrlFound = urlServices.retrieveFullUrl(shortenedUrl);
        assertEquals(fullUrl, fullUrlFound);
    }
    @Test
    void TestThatCustomNameCanUsedAsShortUrl(){
        String customizedUrlChoice = "nedCo";
        ShortenedUrlResponse shortenedUrlResponse = new ShortenedUrlResponse();
        shortenedUrlResponse.setCompleteUrl(customizedUrlChoice);
        try {
            shortenedUrlResponse =
                    urlServices.customizeUrl(fullUrl, customizedUrlChoice);
        } catch (Exception ignored) {}

        String shortenedUrl = shortenedUrlResponse.getReplacedUrl();

        String fullUrlFound = urlServices.retrieveFullUrl(shortenedUrl);
        assertEquals(fullUrl, fullUrlFound);
    }
    @Test
    void testThatCustomUrlCanNotBeUsedTwice(){
        String customizedUrlChoice = "nedCo";

        ShortenedUrlResponse shortenedUrlResponse = new ShortenedUrlResponse();
        shortenedUrlResponse.setCompleteUrl(customizedUrlChoice);

         try {
            shortenedUrlResponse =
                    urlServices.customizeUrl(fullUrl, customizedUrlChoice);
        } catch (Exception ignored) {}

         assertThrows( CustomUrlAlreadyExistException.class, ()-> urlServices.customizeUrl(fullUrl, customizedUrlChoice));

    }
    @Test
    void checkThatImproperUrlIsNotUsed(){

    }

}
