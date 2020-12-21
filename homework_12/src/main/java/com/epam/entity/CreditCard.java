package com.epam.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@DiscriminatorValue("CC")
public class CreditCard extends BillingDetails{

    @Column(name = "card_number")
    private String cardNumber;

    @Column(name = "exp_year")
    private int expYear;

    @Column(name = "exp_month")
    private int expMonth;

}
