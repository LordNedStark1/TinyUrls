package com.urlshortener.customurlshortener.dto.requests;

import lombok.Data;

@Data
public class UrlTransformationRequest {
    private String urlToBeShortened;
    private String actualUrl;
    private String customizedUr;
}
