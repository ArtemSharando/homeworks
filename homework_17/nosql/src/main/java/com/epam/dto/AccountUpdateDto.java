package com.epam.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountUpdateDto {

    private String cardNumber;

    private String nameOnAccount;

    private String expirationDate;
}
