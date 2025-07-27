package com.learning.shortened_url.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiResponse<T> {
    boolean success;
    String message;
    T data;
}
