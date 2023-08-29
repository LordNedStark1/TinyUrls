package com.urlshortener.customurlshortener.service;

import com.urlshortener.customurlshortener.dto.requests.BuildUrlRequest;
import com.urlshortener.customurlshortener.dto.response.ShortenedUrlResponse;

public interface UrlService {

    ShortenedUrlResponse shortenUrl(String actualUrlLink);

    ShortenedUrlResponse shortenUrl(BuildUrlRequest buildUrlRequest);
    String retrieveFullUrl(String shortenedUrl);

    ShortenedUrlResponse customizeUrl(String fullUrl, String customizedUrlChoice);

}
