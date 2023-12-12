package com.eazybytes.cards.controller;


import com.eazybytes.cards.dto.CardDto;
import com.eazybytes.cards.dto.ResponseDto;
import com.eazybytes.cards.service.ICardService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.*;

@RestController
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
public class CardController {


    private ICardService cardServiceImpl;
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createCard(@RequestParam String mobileNumber){
        cardServiceImpl.createCard(mobileNumber);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto("200","Card Created succesfull"));
    }

    @GetMapping("/fetch")
    public ResponseEntity<CardDto> fethchCard(@RequestParam String mobileNumber){

        CardDto cardDto = cardServiceImpl.fetchCard(mobileNumber);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(cardDto);

    }
    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateCard(@RequestBody CardDto cardDto){
        Boolean isUpdated = cardServiceImpl.updateCard(cardDto);
        if(isUpdated){
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto("201","Updated SuccesFull"));
        }else{
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto("417","Updation request failed"));
        }

    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteCard(@RequestParam String mobileNumber){
        Boolean isDeleted = cardServiceImpl.deleteCard(mobileNumber);

        if(isDeleted){
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto("200", "Deleted succesfully"));
        }else{

            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto("417","Expectation failed"));
        }


    }
}
