package com.urlshortener.customurlshortener.service;

import com.urlshortener.customurlshortener.dto.response.ShortenedUrlResponse;

public interface UrlService {

    ShortenedUrlResponse shortenUrl(String s);

    String retrieveFullUrl(String shortenedUrl);

    ShortenedUrlResponse customizeUrl(String fullUrl, String customizedUrlChoice);
}
