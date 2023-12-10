package com.eazybytes.loans.loans.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatusCode;

import java.time.LocalDateTime;

@AllArgsConstructor
@Data
public class ErrorResponseDto {

    String apiPath;
    HttpStatusCode errorCode;
    String errorMessage;
    LocalDateTime errorTime;
}
