package com.urlshortener.customurlshortener.service;

import com.urlshortener.customurlshortener.dto.response.ShortenedUrlResponse;

public interface UrlServices {

    ShortenedUrlResponse shortenUrl(String s);

    String retrieveFullUrl(String shortenedUrl);
}
