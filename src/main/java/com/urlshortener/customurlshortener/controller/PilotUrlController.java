package com.urlshortener.customurlshortener.controller;

import com.urlshortener.customurlshortener.dto.response.ShortenedUrlResponse;
import com.urlshortener.customurlshortener.service.UrlService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor

@RequestMapping("/urlpilot")

@CrossOrigin("*")
public class PilotUrlController {
        private final UrlService urlService;

        @PostMapping("/shortenUrl/")
    public ShortenedUrlResponse shortenUrl( String completeUrl) {
            ShortenedUrlResponse response = urlService.shortenUrl(completeUrl);
//            System.out.println(response);
        return response;
    }
    @GetMapping("")
    public String retrieveUrl(@PathVariable String requestedUrl) {
          return urlService.retrieveFullUrl(requestedUrl);
    }
}
