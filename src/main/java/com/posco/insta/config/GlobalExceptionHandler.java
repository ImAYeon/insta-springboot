package com.posco.insta.config;

import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = IllegalAccessException.class)
    public ResponseEntity<Map<String, Object>> illegalAccessException
            (IllegalAccessException e){
        Map<String, Object> map = new HashMap<>();
        map.put("errMsg", e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map); // BAD_REQUEST : 400
    }
}
