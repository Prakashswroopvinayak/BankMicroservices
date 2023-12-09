package com.eazybytes.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;


@Data
@Schema(
        name = "Customer",
        description = "Schema to hold Customer and Account information"
)
public class CustomerDto {

    @Schema(
            description = "Name of the Customer",
            example = "Prakash Vinayak"
    )
    @NotEmpty(message = "Name can not be a null or empty")
    @Size(min=5,max=30, message = "The length of the customer should be between 5 and 30")
    private String name;

    @Schema(
            description = "email of the Customer",
            example = "tiwariprakash965@gmail.com"
    )
    @NotEmpty(message = "Email can not be a null or empty")
    @Email(message = "Email address should be a valid value")
    private String email;

    @Schema(
            description = "Mobile of the Customer",
            example = "9113434141"
    )
    @NotEmpty(message = "Mobile number can not be a null or empty")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digit")
    private String mobileNumber;

    @Schema(
            description = "Account details of customer"
    )
    private AccountsDto accountsDto;
}
