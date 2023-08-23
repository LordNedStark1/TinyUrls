package com.urlshortener.customurlshortener.service;

import com.urlshortener.customurlshortener.dto.requests.UrlBuildRequest;
import com.urlshortener.customurlshortener.dto.response.ShortenedUrlResponse;
import com.urlshortener.customurlshortener.exceptions.UrlNotFoundException;
import com.urlshortener.customurlshortener.factory.UrlFactory;
import com.urlshortener.customurlshortener.model.Url;
import com.urlshortener.customurlshortener.repositorie.UrlRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TinyUrlsServiceImpl implements UrlServices{
    @Autowired
    UrlFactory urlFactory;
    @Autowired
    private UrlRepositories urlRepositories;
    @Override
    public ShortenedUrlResponse shortenUrl(String actualUrlLink) {

        String replacementUrl = urlFactory.shortenUrl();

        UrlBuildRequest urlBuildRequest = UrlBuildRequest.builder()
                                            .actualUrlLink(actualUrlLink)
                                            .urlReplacementLink(replacementUrl)
                                            .build();

        Url url = buildUrl(urlBuildRequest);

        Url savedUrl = urlRepositories.save(url);

        return ShortenedUrlResponse.builder()
                .replacedUrl(savedUrl.getUrlReplacementLink())
                .build();
    }

    private Url buildUrl(UrlBuildRequest urlBuildRequest) {
       return Url.builder()
                    .actualUrlLink(urlBuildRequest.getActualUrlLink())
                    .urlReplacementLink(urlBuildRequest.getUrlReplacementLink())
                    .build();
    }

    @Override
    public String retrieveFullUrl(String shortenedUrl) {
        Optional<Url> optionalUrl =  urlRepositories.findUrlByUrlReplacementLink(shortenedUrl);
        if (optionalUrl.isPresent()){
            Url url = optionalUrl.get();
            return url.getActualUrlLink();
        }
        throw new UrlNotFoundException("Actual url is not found");
    }
}
