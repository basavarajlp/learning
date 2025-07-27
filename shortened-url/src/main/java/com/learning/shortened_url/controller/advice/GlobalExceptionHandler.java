package com.learning.shortened_url.controller.advice;

import com.learning.shortened_url.dto.ApiResponse;
import com.learning.shortened_url.exception.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ApiResponse<?>> badRequestException(Exception ex){
        ApiResponse<?> apiResponse = new ApiResponse<>(false, ex.getMessage(), null);
        return ResponseEntity.badRequest().body(apiResponse);
    }
}
