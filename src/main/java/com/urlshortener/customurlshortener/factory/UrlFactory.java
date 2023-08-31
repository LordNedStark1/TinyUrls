package com.urlshortener.customurlshortener.factory;

import com.urlshortener.customurlshortener.Utils.AppUtils;
import com.urlshortener.customurlshortener.dto.requests.BuildUrlRequest;
import com.urlshortener.customurlshortener.dto.requests.UrlBuildRequest;
import com.urlshortener.customurlshortener.exceptions.CustomUrlAlreadyExistException;
import com.urlshortener.customurlshortener.model.Url;
import com.urlshortener.customurlshortener.repositorie.UrlRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Random;

import static com.urlshortener.customurlshortener.model.CustomMessage.URL_ALREADY_EXIST;

@Component
public class UrlFactory {
    @Autowired
    private UrlRepositories urlRepositories;
    private static final String companyLink = "http://urlpilot.at/";
    private static Character [] capitalLetters = AppUtils.getCapitalLetters();
    private static Character [] smallLetters = AppUtils.getSmallLetters();
    private static final Random random = new Random();


    public String shortenUrl() {
        String generatedUrl = null;
        boolean isNotGenerated = true;
        do {
            generatedUrl = generateShortUrl();
            Optional<Url> foundUrl = urlRepositories.findUrlByUrlReplacementLink(generatedUrl);
            if(foundUrl.isEmpty()) isNotGenerated = false;
        }while(isNotGenerated);

        return  generatedUrl;
    }

    private String generateShortUrl() {
        String shortUrl = companyLink + generateRandomString();

        return shortUrl;
    }
    private String generateRandomString() {
        StringBuilder randomString = new StringBuilder();

        randomString
                    .append(smallLetters[randomIndexGenerator()])
                    .append(smallLetters[randomIndexGenerator()])
                    .append(capitalLetters[randomIndexGenerator()])
                    .append(capitalLetters[randomIndexGenerator()]);
        return randomString.toString();
    }

    private int randomIndexGenerator() {

        return random.nextInt(26);
    }
    private int randomLengthGenerator(){
        int length = 3 + random.nextInt(4);
        return length;
    }
    public Url buildUrl(UrlBuildRequest urlBuildRequest) {
        return Url.builder()
                .actualUrlLink(urlBuildRequest.getActualUrlLink())
                .urlReplacementLink(urlBuildRequest.getUrlReplacementLink())
                .build();
    }
    public Url buildUrl(BuildUrlRequest buildUrlRequest) {
        return Url.builder()
                .actualUrlLink(buildUrlRequest.getActualUrlLink())
                .date(LocalDate.now())
                .urlReplacementLink(buildUrlRequest.getUrlReplacementLink())
                .description(buildUrlRequest.getDescription())
                .build();
    }
    public Url buildUrl(String actualUrlLink, String replacementUrl) {
        return Url.builder()
                .actualUrlLink(actualUrlLink)
                .urlReplacementLink(replacementUrl)
                .build();
    }
    public void checkUrlIsFreeForUse(String customUrl) {

        urlRepositories
                .findUrlByUrlReplacementLink(customUrl)
                .ifPresent((value)->{
                    throw new CustomUrlAlreadyExistException(URL_ALREADY_EXIST.getMessage());
                });

//        Optional<Url> optionalUrl = urlRepositories
//                                        .findUrlByUrlReplacementLink(customUrl);
//                if(optionalUrl.isPresent())
//                    throw new CustomUrlAlreadyExistException(URL_ALREADY_EXIST);



    }


}



