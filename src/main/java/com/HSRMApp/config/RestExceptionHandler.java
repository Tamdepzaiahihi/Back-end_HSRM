package com.HSRMApp.config;

import com.HSRMApp.dto.ErrorDto;
import com.HSRMApp.exception.RestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(RestException.class)
    @ResponseBody
    public ResponseEntity<ErrorDto> handleRestException(RestException e) {
        return ResponseEntity.status(e.getStatus()).body(ErrorDto.builder().message(e.getMessage())
                .build());
    }
}
