package com.eazybytes.accounts.dto;

import jakarta.persistence.Column;
import lombok.Data;


@Data
public class AccountsDto {

    // acount dto
    private Long accountNumber;

    private String accountType;

    private String branchAddress;
}
