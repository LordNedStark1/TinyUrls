package com.urlshortener.customurlshortener.service;

import com.urlshortener.customurlshortener.dto.response.ShortenedUrlResponse;
import com.urlshortener.customurlshortener.factory.UrlFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TinyUrlsServiceImpl implements UrlServices{
    @Autowired
    UrlFactory urlFactory;
    @Override
    public ShortenedUrlResponse shortenUrl(String fullUrl) {

        String replacementUrl = urlFactory.shortenUrl();


        return ShortenedUrlResponse.builder()
                .replacedUrl(replacementUrl)
                .build();
    }
}
