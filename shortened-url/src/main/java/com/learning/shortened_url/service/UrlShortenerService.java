package com.learning.shortened_url.service;

import com.learning.shortened_url.dto.RequestDto;
import com.learning.shortened_url.exception.BadRequestException;
import com.learning.shortened_url.model.UrlShortener;
import com.learning.shortened_url.repository.UrlShortenerRepo;
import com.learning.shortened_url.utils.EncodeUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class UrlShortenerService {

    @Autowired
    UrlShortenerRepo repo;

    public Optional<String> shortenUrl(RequestDto dto){
        if(Objects.isNull(dto) || Objects.isNull(dto.getUrl())){
            throw new BadRequestException(" Invalid Input!! ");
        }
        UrlShortener urlShortener = repo.findByLongUrl(dto.getUrl()).orElse(null);
        if(Objects.isNull(urlShortener)){
            urlShortener = new UrlShortener();
            urlShortener.setLongUrl(dto.getUrl());
            // The below try-catch block is used to validate the field again in case of multiple instances concurrent request for the same URL
            try{
                repo.save(urlShortener);
            }catch(DataIntegrityViolationException exception){
                urlShortener = repo.findByLongUrl(dto.getUrl()).orElseThrow();
            }
        }
        return EncodeUtility.encode(urlShortener.getId());
    }

    public Optional<String> getOriginalUrl(String encodedUrl){
        if(Objects.isNull(encodedUrl)){
            throw new BadRequestException(" Invalid Input!! ");
        }
        Optional<Long> originalId = EncodeUtility.decode(encodedUrl);
        if(originalId.isEmpty()){
            return Optional.empty();
        }
        UrlShortener urlShortener = repo.findById(originalId.get()).orElse(null);
        if(Objects.isNull(urlShortener)){
            return Optional.empty();
        }
        return Optional.of(urlShortener.getLongUrl());
    }
}
