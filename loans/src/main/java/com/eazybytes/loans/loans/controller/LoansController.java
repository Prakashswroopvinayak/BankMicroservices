package com.eazybytes.loans.loans.controller;


import com.eazybytes.loans.loans.constants.LoansConstants;
import com.eazybytes.loans.loans.dto.LoanDto;
import com.eazybytes.loans.loans.dto.LoansContactInfoDto;
import com.eazybytes.loans.loans.dto.ResponseDto;
import com.eazybytes.loans.loans.service.ILoanService;
import com.eazybytes.loans.loans.service.impl.LoanServiceImpl;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
public class LoansController {

   private ILoanService loanServiceImpl;
    @Value("${build.version}")
    private String buildVersion;

    @Autowired
    private Environment environment;

    @Autowired
    private LoansContactInfoDto loansContactInfoDto;
   public LoansController(ILoanService loanServiceImpl){
       this.loanServiceImpl = loanServiceImpl;
   }

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createAccount(@Valid @RequestParam
                                                         @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digit")
                                                         String mobileNumber){
       loanServiceImpl.createLoan(mobileNumber);
       return ResponseEntity
               .status(HttpStatus.CREATED)
               .body(new ResponseDto(LoansConstants.MESSAGE_201, LoansConstants.STATUS_201));
    }
    @GetMapping("/fetch")
    public ResponseEntity<LoanDto> fetchLoanDetailsByMobileNumber(@Valid @RequestParam
                                                                      @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digit")
                                                                      String mobileNumber){

       LoanDto loanDto= loanServiceImpl.fetchLoan(mobileNumber);

       return ResponseEntity.
               status(HttpStatus.OK).
               body(loanDto);
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateLoanDetails(@Valid @RequestBody LoanDto loanDto){
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
    public ResponseEntity<ResponseDto> deleteLoanDetails(@Valid
                                                             @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digit")
                                                             @RequestParam String mobileNumber){
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

    @GetMapping("/build-info")
    public ResponseEntity<String> getBuildInfo(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(buildVersion);
    }

    @GetMapping("/java-version")
    public ResponseEntity<String> getJavaVersion(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(environment.getProperty("JAVA_HOME"));
    }

    @GetMapping("/contact-info")
    public ResponseEntity<LoansContactInfoDto> getContactInfo(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(loansContactInfoDto);
    }
}
