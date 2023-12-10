package com.eazybytes.loans.loans.controller;


import com.eazybytes.loans.loans.constants.LoansConstants;
import com.eazybytes.loans.loans.dto.LoanDto;
import com.eazybytes.loans.loans.dto.ResponseDto;
import com.eazybytes.loans.loans.service.ILoanService;
import com.eazybytes.loans.loans.service.impl.LoanServiceImpl;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor

public class LoansController {

   private ILoanService loanServiceImpl;
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createAccount(String mobileNumber){
       loanServiceImpl.createLoan(mobileNumber);
       return ResponseEntity
               .status(HttpStatus.CREATED)
               .body(new ResponseDto(LoansConstants.MESSAGE_201, LoansConstants.STATUS_201));
    }
    @GetMapping("/fetch")
    public ResponseEntity<LoanDto> fetchLoanDetailsByMobileNumber(String mobileNumber){

       LoanDto loanDto= loanServiceImpl.fetchLoan(mobileNumber);

       return ResponseEntity.
               status(HttpStatus.OK).
               body(loanDto);
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateLoanDetails(@RequestBody LoanDto loanDto){
        System.out.println(loanDto);
        Boolean isUpdated = loanServiceImpl.updateLoanDetails(loanDto);

        if(isUpdated){
            return ResponseEntity.
                    status(HttpStatus.OK)
                    .body( new ResponseDto(LoansConstants.MESSAGE_200, LoansConstants.STATUS_200));
        }else{
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(LoansConstants.MESSAGE_417_UPDATE, LoansConstants.STATUS_417));
        }
    }
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteLoanDetails(@RequestParam String mobileNumber){
        System.out.println(mobileNumber);
         Boolean isDeleted =  loanServiceImpl.deleteLoanDetails(mobileNumber);
         if(isDeleted){
             return ResponseEntity
                     .status(HttpStatus.OK)
                     .body(new ResponseDto(LoansConstants.MESSAGE_200, LoansConstants.STATUS_200));
         }else{
             return ResponseEntity
                     .status(HttpStatus.EXPECTATION_FAILED)
                     .body(new ResponseDto(LoansConstants.MESSAGE_417_DELETE,LoansConstants.STATUS_417));
         }
    }
}
