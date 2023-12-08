package com.eazybytes.accounts.dto;

import jakarta.validation.constraints.*;
import lombok.Data;


@Data
public class CustomerDto {

    @NotEmpty(message = "Name can not be a null or empty")
    @Size(min=5,max=30, message = "The length of the customer should be between 5 and 30")
    private String name;

    @NotEmpty(message = "Email can not be a null or empty")
    @Email(message = "Email address should be a valid value")
    private String email;

    @NotEmpty(message = "Mobile number can not be a null or empty")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digit")
    private String mobileNumber;

    private AccountsDto accountsDto;
}
