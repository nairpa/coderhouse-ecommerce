package edu.coderhouse.ecommerce.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class ErrorMessage {
    private int statusCode;
    private LocalDate timeStamp;
    private String message;
    private String description;
}
