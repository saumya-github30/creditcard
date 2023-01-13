package com.sapient.creditcardsystem.controller;

import com.sapient.creditcardsystem.model.CreditCard;
import com.sapient.creditcardsystem.service.CreditCardService;
import com.sapient.creditcardsystem.utils.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/creditcard")
@CrossOrigin
public class CreditCardController {

    @Autowired
    private CreditCardService creditCardService;

    @PostMapping("/add")
    public String add(@RequestBody CreditCard creditCard) {
        creditCard.setBalance(0);
        Boolean isValidCard = creditCardService.validateCard(creditCard);
        if(isValidCard) {
            creditCardService.saveCreditCard(creditCard);
            return "Credit Card Added Successfully.";
        } else {
            return "Please enter valid card number";
        }
    }

    @GetMapping("/getAll")
    public List<CreditCard> getAllCreditCards() {
        return creditCardService.getAllCreditCards();
    }
}
