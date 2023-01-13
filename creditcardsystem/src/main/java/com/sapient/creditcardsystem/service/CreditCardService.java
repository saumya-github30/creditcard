package com.sapient.creditcardsystem.service;

import com.sapient.creditcardsystem.exceptions.InvalidCardException;
import com.sapient.creditcardsystem.model.CreditCard;

import java.util.List;

public interface CreditCardService {
    public CreditCard saveCreditCard(CreditCard creditCard) throws InvalidCardException;
    public List<CreditCard> getAllCreditCards();
}
