package com.urlshortener.customurlshortener.dto.response;

import lombok.Data;

@Data

public class ModifiedUrlResponse {
    private String replacedUrl;
    private String completeUrl;
}
