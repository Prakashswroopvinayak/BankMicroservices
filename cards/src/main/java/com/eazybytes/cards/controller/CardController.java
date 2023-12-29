package com.eazybytes.cards.controller;


import com.eazybytes.cards.constant.CardConstant;
import com.eazybytes.cards.dto.CardDto;
import com.eazybytes.cards.dto.CardsContactInfoDto;
import com.eazybytes.cards.dto.ResponseDto;
import com.eazybytes.cards.service.ICardService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.awt.*;

@RestController
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
public class CardController {



    private ICardService cardServiceImpl;
    @Value("${build.version}")
    private String buildVersion;

    @Autowired
    private Environment environment;

    @Autowired
    private CardsContactInfoDto cardsContactInfoDto;
    public CardController(ICardService cardServiceImpl){
        this.cardServiceImpl = cardServiceImpl;
    }
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createCard(@Valid @RequestParam
                                                      @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digit")
                                                      String mobileNumber){
        cardServiceImpl.createCard(mobileNumber);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(CardConstant.STATUS_201,CardConstant.MESSAGE_201));
    }

    @GetMapping("/fetch")
    public ResponseEntity<CardDto> fethchCard(@Valid @RequestParam
                                                  @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digit")
                                                  String mobileNumber){

        CardDto cardDto = cardServiceImpl.fetchCard(mobileNumber);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(cardDto);

    }
    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateCard(@Valid @RequestBody CardDto cardDto){
        Boolean isUpdated = cardServiceImpl.updateCard(cardDto);
        if(isUpdated){
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(CardConstant.STATUS_200,CardConstant.MESSAGE_200));
        }else{
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(CardConstant.STATUS_417,CardConstant.MESSAGE_417_UPDATE));
        }

    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteCard(@Valid @RequestParam
                                                      @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digit")
                                                      String mobileNumber){
        Boolean isDeleted = cardServiceImpl.deleteCard(mobileNumber);

        if(isDeleted){
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(CardConstant.STATUS_200, CardConstant.MESSAGE_200));
        }else{

            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(CardConstant.STATUS_417,CardConstant.MESSAGE_417_DELETE));
        }


    }

    @GetMapping("/build-info")
    public ResponseEntity<String> getBuildInfo(){

        return  ResponseEntity
                    .status(HttpStatus.OK)
                    .body(buildVersion);
    }

    @GetMapping("/java-version")
    public ResponseEntity<String> getJavaVersion(){

        return  ResponseEntity
                .status(HttpStatus.OK)
                .body(environment.getProperty("JAVA_HOME"));
    }

    @GetMapping("/contact-info")
    public ResponseEntity<CardsContactInfoDto> getContactInfo(){

        return  ResponseEntity
                .status(HttpStatus.OK)
                .body(cardsContactInfoDto);
    }
}
