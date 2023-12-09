package com.eazybytes.loans.loans.service.impl;

import com.eazybytes.loans.loans.dto.LoanDto;
import com.eazybytes.loans.loans.entity.Loan;
import com.eazybytes.loans.loans.mapper.LoanMapper;
import com.eazybytes.loans.loans.repository.LoanRepository;
import com.eazybytes.loans.loans.service.ILoanService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Component
@AllArgsConstructor
public class LoanServiceImpl implements ILoanService {

    LoanRepository loanRepository;
    @Override
    public String createLoan(String mobileNumber) {
        System.out.println(" in loan service");
        Loan loan = new Loan();
        loan.setLoanNumber("1st");
        loan.setLoanType("Personal");
        loan.setTotalLoan(1000000);
        loan.setMobileNumber(mobileNumber);
        loan.setCreatedBy("Admin");
        loan.setCreatedAt(LocalDateTime.now());
        loan.setAmountPaid(0);
        loan.setOutstandingAmount(100);

        loanRepository.save(loan);
        return "Loan created succesful";

    }

    @Override
    public LoanDto fetchLoan(String mobileNumber) {

        Loan loan = loanRepository.findByMobileNumber(mobileNumber).orElseThrow(
                ()-> new RuntimeException("Loan does not exit ")
        );
        return LoanMapper.mapToLoanDto(loan,new LoanDto());

    }

    @Override
    public Boolean updateLoanDetails(LoanDto loanDto) {
        Boolean isUpdated = false;
        String mobileNumber = loanDto.getMobileNumber();
        System.out.println(loanDto);
        System.out.println("Mobile Number "+mobileNumber);
        Loan loan = loanRepository.findByMobileNumber(mobileNumber).orElseThrow(
                ()-> new RuntimeException("Loan doesnot exist with given number")
        );

        LoanMapper.mapToLoan(loanDto,loan);
        loanRepository.save(loan);
        isUpdated= true;
        return  isUpdated;
    }

    @Override
    public String deleteLoanDetails(String mobileNumber) {

        Loan loan = loanRepository.findByMobileNumber(mobileNumber).orElseThrow(
                ()-> new RuntimeException("Loan is not available for this mobile number")
        );

        loanRepository.deleteByMobileNumber(mobileNumber);
        return "Account deleted successfully ";
    }
}
