package com.urlshortener.customurlshortener.repositorie;

import com.urlshortener.customurlshortener.model.Url;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UrlRepositories extends MongoRepository<Url, String> {
     Optional<Url> findUrlByUrlReplacementLink(String replacementUrl);
}
