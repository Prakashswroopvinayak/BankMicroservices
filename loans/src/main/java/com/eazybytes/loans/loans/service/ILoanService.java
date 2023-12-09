package com.eazybytes.loans.loans.service;

import com.eazybytes.loans.loans.dto.LoanDto;

public interface ILoanService {

    String createLoan(String mobileNumber);
    LoanDto fetchLoan(String mobileNumber);

    Boolean updateLoanDetails(LoanDto loanDto);

    String deleteLoanDetails(String mobileNumber);
}
