package com.urlshortener.customurlshortener.dto.response;

import lombok.Builder;
import lombok.Data;

@Data

public class ShortenedUrlResponse {
    private String replacedUrl;
    private String completeUrl;
}
