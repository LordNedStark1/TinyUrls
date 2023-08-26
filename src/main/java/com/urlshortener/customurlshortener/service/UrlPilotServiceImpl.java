package com.urlshortener.customurlshortener.service;

import com.urlshortener.customurlshortener.dto.response.ShortenedUrlResponse;
import com.urlshortener.customurlshortener.exceptions.UrlNotFoundException;
import com.urlshortener.customurlshortener.factory.UrlFactory;
import com.urlshortener.customurlshortener.model.Url;
import com.urlshortener.customurlshortener.repositorie.UrlRepositories;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.urlshortener.customurlshortener.model.CustomMessage.URL_NOT_FOUND;

@Service
@AllArgsConstructor
public class UrlPilotServiceImpl implements UrlService {

    private final UrlFactory urlFactory;

    private final UrlRepositories urlRepositories;
    @Override
    public ShortenedUrlResponse shortenUrl(String actualUrlLink) {

        String replacementUrl = urlFactory.shortenUrl();

//        UrlBuildRequest urlBuildRequest = UrlBuildRequest.builder()
//                                            .actualUrlLink(actualUrlLink)
//                                            .urlReplacementLink(replacementUrl)
//                                            .build();

        Url url = urlFactory.buildUrl(actualUrlLink, replacementUrl);

        Url savedUrl = urlRepositories.save(url);

        return ShortenedUrlResponse.builder()
                .replacedUrl(savedUrl.getUrlReplacementLink())
                .build();
    }

    @Override
    public String retrieveFullUrl(String shortenedUrl) {
        Optional<Url> optionalUrl =  urlRepositories.findUrlByUrlReplacementLink(shortenedUrl);
        if (optionalUrl.isPresent()){
            Url url = optionalUrl.get();
            return url.getActualUrlLink();
        }
        throw new UrlNotFoundException(URL_NOT_FOUND);
    }

    @Override
    public ShortenedUrlResponse customizeUrl(String actualUrlLink, String customizedUrlChoice) {
        urlFactory.checkUrlIsUrlFree(customizedUrlChoice);

        Url url = urlFactory.buildUrl(actualUrlLink, customizedUrlChoice);

        Url savedUrl = urlRepositories.save(url);

        return ShortenedUrlResponse.builder()
                .replacedUrl(savedUrl.getUrlReplacementLink())
                .build();
    }

}
