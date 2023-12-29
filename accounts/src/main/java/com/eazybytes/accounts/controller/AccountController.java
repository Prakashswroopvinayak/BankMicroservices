package com.eazybytes.accounts.controller;


import com.eazybytes.accounts.constants.AccountsConstants;
import com.eazybytes.accounts.dto.AccountsDto;
import com.eazybytes.accounts.dto.CustomerDto;
import com.eazybytes.accounts.dto.ErrorResponseDto;
import com.eazybytes.accounts.dto.ResponseDto;
import com.eazybytes.accounts.service.IAccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


// we have created account servics for post request

@RequestMapping(path="/api", produces={MediaType.APPLICATION_JSON_VALUE})
@Validated
@Tag(
        name = "CRUD REST API for Accounts in EazyBank",
        description = "CRUD REST API in EazyBank to CREATE, UPDATE, FETCH and DELETE account details"

)

@RestController
public class AccountController {


    private IAccountService iAccountService;

    @Value("${build.version}")
    private String  buildVersion;

    @Autowired
    private Environment environment;
    public AccountController(IAccountService iAccountService){

        this.iAccountService = iAccountService;
    }




    @Operation(
            summary = "Create Account REST API",
            description = "REST API to create new Customer and Account inside Prakash Bank"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "HTTP Status CREATED"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )

    })

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody CustomerDto customerDto){
        iAccountService.createAccount(customerDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(AccountsConstants.STATUS_201,AccountsConstants.MESSAGE_201));
    }


    @Operation(
            summary = "Fetch Account REST API",
            description = "REST API to Fetch Account details from Prakash Bank based on mobile number"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status Ok"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )

    })

    @GetMapping("/fetch")
    public ResponseEntity<CustomerDto> fetchAccountDetails(@RequestParam
                                                               @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digit")
                                                               String mobileNumber){
        CustomerDto customerDto = iAccountService.fetchAccount(mobileNumber);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(customerDto);
    }

    @Operation(
            summary = "Update Account Details REST API",
            description = "REST API to Update Account and customer details from Prakash Bank based on mobile number"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Request processed successfully"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "Expectation Failed"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "An error occurred. Please try again or contact Dev team",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateAccountDetails(@Valid @RequestBody CustomerDto customerDto){
        boolean isUpdated = iAccountService.updateAccount(customerDto);
        if(isUpdated){
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
        }else{
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(AccountsConstants.STATUS_417,AccountsConstants.MESSAGE_417_UPDATE));
        }
    }

    @Operation(
            summary = "Delete Account Details REST API",
            description = "REST API to Delete Account and customer  from Prakash Bank based on mobile number"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status Ok"
            ),
            @ApiResponse(
              responseCode = "417",
              description = "Expectation Failed"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "An error occurred. Please try again or contact Dev team",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteAccountDetails(@RequestParam
                                                                @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digit")
                                                                String mobileNumber){
        boolean isDeleted= iAccountService.deleteAccount(mobileNumber);
        if(isDeleted){
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
        }else{
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(AccountsConstants.STATUS_417,AccountsConstants.MESSAGE_417_DELETE));
        }
    }

    @Operation(
            summary = "Get Build information",
            description = "Get Build information that is deployed in accounts microservices"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status Ok"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )

    })
    @GetMapping("/build-info")
    public ResponseEntity<String> getBuildInfo(){

        return ResponseEntity.
                    status(HttpStatus.OK).
                    body(buildVersion);
    }

    @Operation(
            summary = "Get Java Vesrion",
            description = "GGet Java Vesrion that is Installed in accounts microservices"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status Ok"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )

    })
    @GetMapping("/java-version")
    public ResponseEntity<String> getJavaVersion(){

        return ResponseEntity.
                status(HttpStatus.OK).
                body(environment.getProperty("JAVA_HOME"));
    }

}
