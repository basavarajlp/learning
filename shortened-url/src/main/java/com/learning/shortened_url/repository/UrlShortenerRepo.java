package com.learning.shortened_url.repository;

import com.learning.shortened_url.model.UrlShortener;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UrlShortenerRepo extends CrudRepository<UrlShortener,Long> {

    Optional<UrlShortener> findByLongUrl(String longUrl);
}
