package com.eazybytes.loans.loans.controller;


import com.eazybytes.loans.loans.dto.LoanDto;
import com.eazybytes.loans.loans.service.ILoanService;
import com.eazybytes.loans.loans.service.impl.LoanServiceImpl;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor

public class LoansController {

   private ILoanService loanServiceImpl;
    @PostMapping("/create")
    public String createAccount(String mobileNumber){
        return loanServiceImpl.createLoan(mobileNumber);


    }
    @GetMapping("/fetch")
    public LoanDto fetchLoanDetailsByMobileNumber(String mobileNumber){
        System.out.println("hhvhvs1");
        System.out.println(loanServiceImpl.fetchLoan(mobileNumber));
        System.out.println("hhvhvs");
        return loanServiceImpl.fetchLoan(mobileNumber);
    }

    @PutMapping("/update")
    public String updateLoanDetails(@RequestBody LoanDto loanDto){
        System.out.println(loanDto);
        Boolean isUpdated = loanServiceImpl.updateLoanDetails(loanDto);

        if(isUpdated){
            return "Updated sucess full";
        }else{
            return "unable to update";
        }
    }
    @DeleteMapping("/delete")
    public String deleteLoanDetails(@RequestParam String mobileNumber){
        System.out.println(mobileNumber);
         return loanServiceImpl.deleteLoanDetails(mobileNumber);
    }
}
