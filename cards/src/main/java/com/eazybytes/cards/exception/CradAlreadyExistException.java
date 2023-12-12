package com.eazybytes.cards.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class CradAlreadyExistException extends RuntimeException{

    public CradAlreadyExistException(String message){
        super(message);
    }
}
