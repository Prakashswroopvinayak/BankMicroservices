package com.eazybytes.cards.dto;

import jakarta.persistence.EntityListeners;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class CardDto {

    @NotEmpty(message = "Mobile Number can not empty")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digit")
    private String mobileNumber;
    @NotEmpty(message = "Card Number can not empty")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Card number must be 10 digit")
    private String cardNumber;

    @NotEmpty(message = "Card Type can not be empty")
    private String cardType;

    @NotNull(message = "Total Limit can not be Null")
    private Long totalLimit;
    @NotNull(message = "Amount used can not be Null")
    private Long amountUsed;
    @NotNull(message = "Available  amount can not be Null")
    private Long availableAmount;
}
