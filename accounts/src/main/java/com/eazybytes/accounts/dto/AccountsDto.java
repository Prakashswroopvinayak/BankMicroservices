package com.eazybytes.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;


@Data
@Schema(
        name ="Account",
    description = "Schema to hold Account information of Customer"
)
public class AccountsDto {


    @Schema(
            description = "Account number of Customer",
            example = "1020304050"
    )
    @NotEmpty(message = "Account number can not be a null or empty")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Account number must be 10 digit")
    private Long accountNumber;

    @Schema(
            description = "Type of account",
            example = "Saving"
    )
    @NotEmpty(message = "Account Type can not be null or empty")
    private String accountType;

    @Schema(
            description = "Adress of Branch office",
            example = "Areraj"
    )
    @NotEmpty(message = "Branch Address can not be null or empty")
    private String branchAddress;
}
