package com.urlshortener.customurlshortener.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ShortenedUrlResponse {
    private String replacedUrl;
}
