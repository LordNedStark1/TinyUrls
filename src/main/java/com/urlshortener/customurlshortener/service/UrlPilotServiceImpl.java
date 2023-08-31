package com.urlshortener.customurlshortener.service;

import com.urlshortener.customurlshortener.dto.requests.BuildUrlRequest;
import com.urlshortener.customurlshortener.dto.response.ModifiedUrlResponse;
import com.urlshortener.customurlshortener.exceptions.ImproperUrlException;
import com.urlshortener.customurlshortener.exceptions.UrlNotFoundException;
import com.urlshortener.customurlshortener.factory.UrlFactory;
import com.urlshortener.customurlshortener.model.Url;
import com.urlshortener.customurlshortener.repositorie.UrlRepositories;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.urlshortener.customurlshortener.model.CustomMessage.IMPROPER_URL_EXCEPTION;
import static com.urlshortener.customurlshortener.model.CustomMessage.URL_NOT_FOUND;

@Service
@AllArgsConstructor
public class UrlPilotServiceImpl implements UrlService {

    private final UrlFactory urlFactory;
    private final UrlRepositories urlRepositories;
    @Override
    public ModifiedUrlResponse shortenUrl(String actualUrlLink) {
        checkUrlIsValid(actualUrlLink);
        String replacementUrl = urlFactory.shortenUrl();

        Url url = urlFactory.buildUrl(actualUrlLink, replacementUrl);
        Url savedUrl = urlRepositories.save(url);

     return buildModifiedUrlResponse(savedUrl);
    }
    @Override
    public ModifiedUrlResponse shortenUrl(BuildUrlRequest buildUrlRequest, String userId) {

        String replacementUrl = urlFactory.shortenUrl();
        buildUrlRequest.setUrlReplacementLink(replacementUrl);

        Url url = urlFactory.buildUrl(buildUrlRequest);
        url.setDate();
        url.setUserId(userId);

        Url savedUrl = urlRepositories.save(url);

        return buildModifiedUrlResponse(savedUrl) ;
    }

    private static ModifiedUrlResponse buildModifiedUrlResponse(Url savedUrl) {
        ModifiedUrlResponse shortenedUrlResponse = new ModifiedUrlResponse();
        shortenedUrlResponse.setCompleteUrl(savedUrl.getActualUrlLink());
        shortenedUrlResponse.setReplacedUrl(savedUrl.getUrlReplacementLink());
        return shortenedUrlResponse;
    }


    @Override
    public String retrieveFullUrl(String shortenedUrl){
        Optional<Url> optionalUrl =  urlRepositories.findUrlByUrlReplacementLink(shortenedUrl);
        if (optionalUrl.isPresent()){
            Url url = optionalUrl.get();
            return url.getActualUrlLink();
        }
        throw new UrlNotFoundException(URL_NOT_FOUND.getMessage());
    }

    @Override
    public ModifiedUrlResponse customizeUrl(String actualUrlLink, String customizedUrlChoice) {
        checkUrlIsValid(actualUrlLink);
        urlFactory.checkUrlIsFreeForUse(customizedUrlChoice);

        Url url = urlFactory.buildUrl(actualUrlLink, customizedUrlChoice);

        Url savedUrl = urlRepositories.save(url);

        return buildModifiedUrlResponse(savedUrl);
    }

    private void checkUrlIsValid(String actualUrlLink) {
        boolean isValid = actualUrlLink.startsWith("http://") || actualUrlLink.startsWith("https://");
        if (!isValid)
            throw new ImproperUrlException(IMPROPER_URL_EXCEPTION.getMessage());
    }


}
