package com.urlshortener.customurlshortener.dto.requests;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UrlBuildRequest {

    private String actualUrlLink;
    private String urlReplacementLink;
    private String userId;
    private String description;
}
