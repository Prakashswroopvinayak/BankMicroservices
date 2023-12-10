package com.eazybytes.loans.loans.service;

import com.eazybytes.loans.loans.dto.LoanDto;

public interface ILoanService {

     public void createLoan(String mobileNumber);
    public LoanDto fetchLoan(String mobileNumber);

    public Boolean updateLoanDetails(LoanDto loanDto);

    public Boolean deleteLoanDetails(String mobileNumber);
}
