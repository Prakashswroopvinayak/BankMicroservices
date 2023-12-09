package com.eazybytes.loans.loans.mapper;

import com.eazybytes.loans.loans.dto.LoanDto;
import com.eazybytes.loans.loans.entity.Loan;

public class LoanMapper {

    public static Loan mapToLoan(LoanDto loanDto, Loan loan){

        loan.setTotalLoan(loanDto.getTotalLoan());
        loan.setLoanType(loanDto.getLoanType());
        loan.setLoanNumber(loanDto.getLoanNumber());
        loan.setOutstandingAmount(loanDto.getOutstandingAmount());
        loan.setMobileNumber(loanDto.getMobileNumber());
        loan.setAmountPaid(loanDto.getAmountPaid());

        return loan;
    }

    public static LoanDto mapToLoanDto(Loan loan, LoanDto loanDto){
        System.out.println("Loan "+ loan);
        loanDto.setTotalLoan(loan.getTotalLoan());
        loanDto.setLoanType(loan.getLoanType());
        loanDto.setLoanNumber(loan.getLoanNumber());
        loanDto.setOutstandingAmount(loan.getOutstandingAmount());
        loanDto.setMobileNumber(loan.getMobileNumber());
        loanDto.setAmountPaid(loan.getAmountPaid());

        return loanDto;
    }
}
