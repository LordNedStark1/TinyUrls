package com.urlshortener.customurlshortener.service;

import com.urlshortener.customurlshortener.dto.requests.BuildUrlRequest;
import com.urlshortener.customurlshortener.dto.response.ShortenedUrlResponse;
import com.urlshortener.customurlshortener.exceptions.UrlNotFoundException;
import com.urlshortener.customurlshortener.factory.UrlFactory;
import com.urlshortener.customurlshortener.model.Url;
import com.urlshortener.customurlshortener.model.User;
import com.urlshortener.customurlshortener.repositorie.UrlRepositories;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.urlshortener.customurlshortener.model.CustomMessage.URL_NOT_FOUND;

@Service
@AllArgsConstructor
public class UrlPilotServiceImpl implements UrlService {

    private final UrlFactory urlFactory;
    private final UserService userService;

    private final UrlRepositories urlRepositories;
    @Override
    public ShortenedUrlResponse shortenUrl(String actualUrlLink) {

        String replacementUrl = urlFactory.shortenUrl();

        Url url = urlFactory.buildUrl(actualUrlLink, replacementUrl);

        Url savedUrl = urlRepositories.save(url);

     return buildShortenedUrlResponse(savedUrl);
    }
    @Override
    public ShortenedUrlResponse shortenUrl(BuildUrlRequest buildUrlRequest) {

        String replacementUrl = urlFactory.shortenUrl();
        buildUrlRequest.setUrlReplacementLink(replacementUrl);
        User user = userService.findUserByEmail(buildUrlRequest.getEmail());

        Url url = urlFactory.buildUrl(buildUrlRequest);
        url.setDate();
        url.setUserId(user.getId());

        Url savedUrl = urlRepositories.save(url);

        return buildShortenedUrlResponse(savedUrl) ;
    }

    private static ShortenedUrlResponse buildShortenedUrlResponse(Url savedUrl) {
        ShortenedUrlResponse shortenedUrlResponse = new ShortenedUrlResponse();
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
    public ShortenedUrlResponse customizeUrl(String actualUrlLink, String customizedUrlChoice) {
        urlFactory.checkUrlIsUrlFree(customizedUrlChoice);

        Url url = urlFactory.buildUrl(actualUrlLink, customizedUrlChoice);

        Url savedUrl = urlRepositories.save(url);

        return null;
    }


}
