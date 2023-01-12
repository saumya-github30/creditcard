package com.sapient.creditcardsystem.model;

import javax.persistence.*;

import lombok.*;

@Entity
@Table(name="credit_card")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreditCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cardId;

    private String customerName;
    private String cardNumber;
    private double balance;
    private double maxLimit;

}
