package com.user.exception;


import com.user.payload.ApiResponse;

import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(CustomeException.class)
    public ResponseEntity<ApiResponse> handlerResourceNotFoundException(CustomeException ex){
        ApiResponse response = ApiResponse.builder().message(ex.getMessage()).success(true)
                .status(HttpStatus.NOT_FOUND).build();

      return   ResponseEntity.ok().body(response);
    }
}
