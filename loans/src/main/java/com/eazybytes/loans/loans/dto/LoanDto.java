package com.eazybytes.loans.loans.dto;


import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

@Data
public class LoanDto {


    private String mobileNumber;


    private String loanNumber;


    private String loanType;


    private int totalLoan;


    private int amountPaid;


    private int outstandingAmount;
}
