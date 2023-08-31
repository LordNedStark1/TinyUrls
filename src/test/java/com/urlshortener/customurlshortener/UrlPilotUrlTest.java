package com.urlshortener.customurlshortener;

import com.urlshortener.customurlshortener.dto.response.ModifiedUrlResponse;
import com.urlshortener.customurlshortener.exceptions.CustomUrlAlreadyExistException;
import com.urlshortener.customurlshortener.exceptions.ImproperUrlException;
import com.urlshortener.customurlshortener.exceptions.UrlBaseException;
import com.urlshortener.customurlshortener.service.UrlService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.mongodb.internal.authentication.AwsCredentialHelper.LOGGER;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j

class UrlPilotUrlTest {
    @Autowired
    private UrlService urlServices;
    private String fullUrl = "http://jebfbeiwbiuifjjbdfusfbeuifbiuirDS97H43e4e-6tghg6v";



    @Test
    void testThatUrlIsShortened() {

        ModifiedUrlResponse shortenedUrlResponse =
                urlServices.shortenUrl(fullUrl);

        int shortenedUrlLength = shortenedUrlResponse.getReplacedUrl().length();

        boolean isShortened = shortenedUrlLength > 22 && shortenedUrlLength < 25;
        boolean isShorterThanActual = fullUrl.length() > shortenedUrlLength;
        assertTrue(isShorterThanActual);
        assertTrue(isShortened);

    }
    @Test
    void checkThatFullUrlIsRetrievedWithShortenedUrl(){

        ModifiedUrlResponse shortenedUrlResponse =
                urlServices.shortenUrl(fullUrl);

        String shortenedUrl = shortenedUrlResponse.getReplacedUrl();

        String fullUrlFound = urlServices.retrieveFullUrl(shortenedUrl);
        assertEquals(fullUrl, fullUrlFound);
    }
    @Test
    void TestThatCustomNameCanUsedAsShortUrl(){
        String customizedUrlChoice = "nedCo";
        ModifiedUrlResponse modifiedUrlResponse = new ModifiedUrlResponse();
        modifiedUrlResponse.setCompleteUrl(customizedUrlChoice);
        try {
            modifiedUrlResponse =
                    urlServices.customizeUrl(fullUrl, customizedUrlChoice);
        } catch ( UrlBaseException urlBaseException) {
            LOGGER.info(urlBaseException.getMessage());
            modifiedUrlResponse.setReplacedUrl(customizedUrlChoice);
        }

        String modifiedUrl = modifiedUrlResponse.getReplacedUrl();



        String fullUrlFound = urlServices.retrieveFullUrl(modifiedUrl);
        assertEquals(fullUrl, fullUrlFound);
    }
    @Test
    void testThatCustomUrlCanNotBeUsedTwice(){
        String customizedUrlChoice = "nedCo";

        ModifiedUrlResponse modifiedUrlResponse = new ModifiedUrlResponse();
        modifiedUrlResponse.setCompleteUrl(customizedUrlChoice);

         try {
            modifiedUrlResponse =
                    urlServices.customizeUrl(fullUrl, customizedUrlChoice);
        } catch (Exception ignored) {}

         assertThrows( CustomUrlAlreadyExistException.class, ()-> urlServices.customizeUrl(fullUrl, customizedUrlChoice));

    }
    @Test
    void checkThatImproperUrlIsNotUsed(){
        String improperUrl = "mendosa";
        String customizedUrlChoice = "customurlchoice";

        assertThrows(ImproperUrlException.class,
                        ()-> urlServices.customizeUrl(improperUrl, customizedUrlChoice));

        assertThrows(ImproperUrlException.class, ()-> urlServices.shortenUrl(improperUrl));
    }

}
