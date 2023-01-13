package com.sapient.creditcardsystem.exceptions;

import lombok.AllArgsConstructor;

public class InvalidCardException extends Exception{
    public InvalidCardException(String message) { super(message);}
}
