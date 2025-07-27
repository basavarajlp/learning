package com.learning.shortened_url.controller;

import com.learning.shortened_url.dto.ApiResponse;
import com.learning.shortened_url.dto.RequestDto;
import com.learning.shortened_url.service.UrlShortenerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/url")
public class ShortenedUrlController {
    @Autowired
    UrlShortenerService service;

    @PostMapping
    public ResponseEntity<ApiResponse<String>> shortenUrl(@RequestBody RequestDto requestDto){
        Optional<String> encodedUrl = service.shortenUrl(requestDto);
        ApiResponse<String> response;
        if(encodedUrl.isEmpty()){
            response = new ApiResponse<>(false," Something went wrong!! ",null);
            return ResponseEntity.badRequest().body(response);
        }
        response = new ApiResponse<>(true," URL Shortened Successfully!! ",encodedUrl.get());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{encodedUrl}")
    public ResponseEntity<ApiResponse<String>> redirectUrl(@PathVariable String encodedUrl){
        Optional<String> originalUrl = service.getOriginalUrl(encodedUrl);
        if(originalUrl.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(URI.create(originalUrl.get()));
        return new ResponseEntity<>(httpHeaders, HttpStatus.MOVED_PERMANENTLY);
    }
}
