package com.sapient.creditcardsystem.service;

import com.sapient.creditcardsystem.model.CreditCard;

import java.util.List;

public interface CreditCardService {
    public CreditCard saveCreditCard(CreditCard creditCard);
    public List<CreditCard> getAllCreditCards();

    public Boolean validateCard(CreditCard creditCard);
}
