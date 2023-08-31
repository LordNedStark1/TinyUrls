package com.urlshortener.customurlshortener.service;

import com.urlshortener.customurlshortener.dto.requests.BuildUrlRequest;
import com.urlshortener.customurlshortener.dto.response.ModifiedUrlResponse;

public interface UrlService {

    ModifiedUrlResponse shortenUrl(String actualUrlLink);

    ModifiedUrlResponse shortenUrl(BuildUrlRequest buildUrlRequest, String userId);
    String retrieveFullUrl(String shortenedUrl);

    ModifiedUrlResponse customizeUrl(String fullUrl, String customizedUrlChoice);

}
