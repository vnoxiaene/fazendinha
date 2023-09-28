package com.vnoxiaene.fazendinha.controller;

import com.vnoxiaene.fazendinha.exception.InvalidDataNascimentoException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GalinhaControllerAdviceHandler {

    @ExceptionHandler(value = {InvalidDataNascimentoException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String invalidDataNascimentoException(InvalidDataNascimentoException invalidDataNascimentoException, WebRequest request){
        return invalidDataNascimentoException.getMessage();
    }
}
