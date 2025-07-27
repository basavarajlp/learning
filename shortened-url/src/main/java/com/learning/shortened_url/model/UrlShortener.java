package com.learning.shortened_url.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.sql.Timestamp;
import java.time.LocalDate;

@Entity
@EnableJpaAuditing
@Data
public class UrlShortener {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column
    String longUrl;

    @Column
    @CreationTimestamp
    Timestamp createdAt;
}
