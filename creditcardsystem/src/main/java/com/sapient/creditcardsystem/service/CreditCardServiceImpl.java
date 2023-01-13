package com.sapient.creditcardsystem.service;

import com.sapient.creditcardsystem.model.CreditCard;
import com.sapient.creditcardsystem.repository.CreditCardRepository;
import com.sapient.creditcardsystem.utils.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreditCardServiceImpl implements CreditCardService{

    @Autowired
    private CreditCardRepository creditCardRepository;

    @Override
    public CreditCard saveCreditCard(CreditCard creditCard) {
        return creditCardRepository.save(creditCard);
    }

    @Override
    public List<CreditCard> getAllCreditCards() {
        return creditCardRepository.findAll();
    }

    @Override
    public Boolean validateCard(CreditCard creditCard) {
        String cardNumber = creditCard.getCardNumber();
        if(cardNumber=="" || cardNumber==null) {
            return false;
        }
        cardNumber = cardNumber.replaceAll("\\s", "");;
        int sum = 0;
        boolean alternate=false;
        for(int i=cardNumber.length()-1; i>=0; i--) {
            int n = Integer.parseInt(cardNumber.substring(i, i+1));
            if(alternate) {
                n *= 2;
                if(n>9) {
                    n=(n%10) + 1;
                }
            }
            sum += n;
            alternate = !alternate;
        }
        return (sum%10 == 0);
    }
}
