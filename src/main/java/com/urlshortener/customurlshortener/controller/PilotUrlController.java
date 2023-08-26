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
        UrlService urlService;

        @PostMapping("/shortenUrl")
    public ShortenedUrlResponse shortenUrl(@PathVariable String completeUrl) {
            System.out.println(completeUrl);
        return urlService.shortenUrl(completeUrl);
    }
    @GetMapping("")
    public String retrieveUrl(@PathVariable String requestedUrl) {
          return urlService.retrieveFullUrl(requestedUrl);
    }
}
