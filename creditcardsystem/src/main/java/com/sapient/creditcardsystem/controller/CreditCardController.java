package com.sapient.creditcardsystem.controller;

import com.sapient.creditcardsystem.exceptions.InvalidCardException;
import com.sapient.creditcardsystem.model.CreditCard;
import com.sapient.creditcardsystem.service.CreditCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/creditcard")
@CrossOrigin
public class CreditCardController {

    @Autowired
    private CreditCardService creditCardService;

    @PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> add(@Valid @RequestBody CreditCard creditCard) throws InvalidCardException {
        creditCardService.saveCreditCard(creditCard);
        return ResponseEntity.ok("Credit Card Added Successfully");
    }

    @GetMapping("/getAll")
    public List<CreditCard> getAllCreditCards() {
        return creditCardService.getAllCreditCards();
    }
}
