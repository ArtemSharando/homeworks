package com.epam.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@DiscriminatorValue("account")
public class BankAccount extends BillingDetails{
    private String account;
    private String bandName;
}
