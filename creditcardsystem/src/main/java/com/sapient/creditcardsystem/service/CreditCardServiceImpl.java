package com.sapient.creditcardsystem.service;

import com.sapient.creditcardsystem.exceptions.InvalidCardException;
import com.sapient.creditcardsystem.model.CreditCard;
import com.sapient.creditcardsystem.repository.CreditCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.sapient.creditcardsystem.utils.Validator.validateCardDetails;

@Service
public class CreditCardServiceImpl implements CreditCardService{

    @Autowired
    private CreditCardRepository creditCardRepository;

    @Override
    public CreditCard saveCreditCard(CreditCard creditCard) throws InvalidCardException{
        Boolean isValidCard = validateCardDetails(creditCard);
        if(isValidCard) {
            creditCard.setBalance(0.0);
            return creditCardRepository.save(creditCard);
        } else {
            throw new InvalidCardException("Invalid Card Number provided.");
        }
    }

    @Override
    public List<CreditCard> getAllCreditCards() {
        return creditCardRepository.findAll();
    }

}
