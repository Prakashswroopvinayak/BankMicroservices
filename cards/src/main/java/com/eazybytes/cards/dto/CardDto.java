package com.eazybytes.cards.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data

public class CardDto {
    private String mobileNumber;

    private String cardNumber;

    private String cardType;
    private Long totalLimit;
    private Long amountUsed;
    private Long availableAmount;
}
