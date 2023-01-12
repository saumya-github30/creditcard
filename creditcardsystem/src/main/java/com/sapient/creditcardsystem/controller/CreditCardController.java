package com.sapient.creditcardsystem.controller;

import com.sapient.creditcardsystem.model.CreditCard;
import com.sapient.creditcardsystem.service.CreditCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/creditcard")
public class CreditCardController {

    @Autowired
    private CreditCardService creditCardService;

    @PostMapping("/add")
    public String add(@RequestBody CreditCard creditCard) {
        creditCardService.saveCreditCard(creditCard);
        return "Credit Card Added Successfully.";
    }
}
