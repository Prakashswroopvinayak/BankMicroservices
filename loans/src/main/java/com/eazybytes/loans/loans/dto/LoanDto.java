package com.eazybytes.loans.loans.dto;


import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

@Data
public class LoanDto {


    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digit")
    @NotEmpty(message = "Mobile number can not be empty")
    private String mobileNumber;

    @Pattern(regexp = "(^$|[0-9]{10})", message = "Loan number must be 10 digit")
    @NotEmpty(message = "Loan number can not be empty")
    private String loanNumber;


    @NotEmpty(message = "Loan Type can not be empty")
    private String loanType;


    @Positive(message = "Total Loan amount should be greater than zero")
    @NotNull(message = "Total Loan amount can not be null")
    private int totalLoan;


    @PositiveOrZero(message = "Total loan amount paid is greater than equal or equal to zero")
    private int amountPaid;


    @PositiveOrZero(message = "Outstanding amount loan amount paid is greater than equal or equal to zero")
    private int outstandingAmount;
}
