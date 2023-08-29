package com.urlshortener.customurlshortener.dto.requests;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@@Setter
@Getter
@ToString

public class BuildUrlRequest {
    private String actualUrlLink;
    private String urlReplacementLink;

    private String description;
}
