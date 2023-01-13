package com.sapient.creditcardsystem.utils;

import com.sapient.creditcardsystem.model.CreditCard;

public class Validator {

    public static boolean validateCardDetails(CreditCard creditCard) {
        String cardNumber = creditCard.getCardNumber();

        cardNumber = cardNumber.replaceAll("\\s", "");
        String regex = "\\d+";
        if(!cardNumber.matches(regex)) {
            return false;
        }

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
