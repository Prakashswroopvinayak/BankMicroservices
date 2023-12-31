package com.eazybytes.cards.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ErrorResponseDto {

    public String apiPath;
    public HttpStatus errorCode;
    public String errorMsg;
    public LocalDateTime errorTime;
}
