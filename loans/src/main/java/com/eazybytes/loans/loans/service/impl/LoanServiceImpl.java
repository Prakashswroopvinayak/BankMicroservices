package com.eazybytes.loans.loans.service.impl;

import com.eazybytes.loans.loans.constants.LoansConstants;
import com.eazybytes.loans.loans.dto.LoanDto;
import com.eazybytes.loans.loans.entity.Loan;
import com.eazybytes.loans.loans.exception.LoanAlreadyExistsException;
import com.eazybytes.loans.loans.exception.ResourceNotFoundException;
import com.eazybytes.loans.loans.mapper.LoanMapper;
import com.eazybytes.loans.loans.repository.LoanRepository;
import com.eazybytes.loans.loans.service.ILoanService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
@Component
@AllArgsConstructor
public class LoanServiceImpl implements ILoanService {

    LoanRepository loanRepository;
    @Override
    public void createLoan(String mobileNumber) {
        Optional<Loan> loan = loanRepository.findByMobileNumber(mobileNumber);
        if(loan.isPresent()){
            throw  new LoanAlreadyExistsException("Loan", "Mobile Number", mobileNumber);
        }
        loanRepository.save(ceateLoanByMobileNumber(mobileNumber));

    }

    private Loan ceateLoanByMobileNumber(String mobileNumber){
        Loan loan = new Loan();
        long randomLoanNumber = 100000000000L + new Random().nextInt(900000000);
        loan.setLoanNumber(Long.toString(randomLoanNumber));
        loan.setLoanType(LoansConstants.HOME_LOAN);
        loan.setTotalLoan(LoansConstants.NEW_LOAN_LIMIT);
        loan.setMobileNumber(mobileNumber);
        loan.setCreatedBy("Admin");
        loan.setCreatedAt(LocalDateTime.now());
        loan.setAmountPaid(0);
        loan.setOutstandingAmount(LoansConstants.NEW_LOAN_LIMIT);
        return loan;
    }

    @Override
    public LoanDto fetchLoan(String mobileNumber) {
        Loan loan = loanRepository.findByMobileNumber(mobileNumber).orElseThrow(
                ()-> new ResourceNotFoundException("Loan","Mobile Number",mobileNumber)
        );

        return LoanMapper.mapToLoanDto(loan,new LoanDto());

    }

    @Override
    public Boolean updateLoanDetails(LoanDto loanDto) {
        Boolean isUpdated = false;
        String mobileNumber = loanDto.getMobileNumber();
        Loan loan = loanRepository.findByMobileNumber(mobileNumber).orElseThrow(
                ()-> new ResourceNotFoundException("Loan", "Mobile Number", mobileNumber)
        );

        LoanMapper.mapToLoan(loanDto,loan);
        loanRepository.save(loan);
        isUpdated= true;
        return  isUpdated;
    }

    @Override
    public Boolean deleteLoanDetails(String mobileNumber) {
        Boolean isDeleted = false;
        Loan loan = loanRepository.findByMobileNumber(mobileNumber).orElseThrow(
                ()-> new ResourceNotFoundException("Loan","Mobile Number",mobileNumber)
        );

        loanRepository.deleteByMobileNumber(mobileNumber);
        isDeleted = true;
        return isDeleted;
    }
}
