package com.urlshortener.customurlshortener.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document
public class Url {
    @Id
    private String Id;
    private String ActualUrlLink;
    private String urlReplacementLink;
    private String userId;
    private String description;
    private String numberOfClicks;
    private LocalDate date = LocalDate.now();
}
