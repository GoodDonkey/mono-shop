package com.looselycoupled.monoshop.users.web;

import com.looselycoupled.monoshop.common.web.dtos.ErrorResponseBody;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@RestControllerAdvice
public class MemberControllerAdvice {
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseBody> on(MethodArgumentNotValidException exception, HttpServletRequest request) {
        HashMap<String, String> errors = new HashMap<>();
        exception.getBindingResult()
                 .getAllErrors()
                 .forEach(error -> errors.put(((FieldError) error).getField(), error.getDefaultMessage()));
        return ResponseEntity.badRequest().body(ErrorResponseBody.badRequestOf(exception, errors, request.getRequestURI()));
    }
}
