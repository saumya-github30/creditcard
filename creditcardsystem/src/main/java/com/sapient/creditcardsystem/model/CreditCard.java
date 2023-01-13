package com.sapient.creditcardsystem.model;

import javax.persistence.*;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name="credit_card")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreditCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cardId;

    @NotBlank(message = "Name is mandatory")
    private String customerName;

    @Size(min=13, max=19,message = "Card number is not valid")
    private String cardNumber;
    private double balance;
    private double maxLimit;

}
