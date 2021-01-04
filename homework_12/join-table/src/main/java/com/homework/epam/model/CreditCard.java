package com.homework.epam.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "credit_card")
@Getter
@Setter
public class CreditCard extends BillingDetails {
    @Column(name = "card_number")
    private int cardNumber;

    @Column(name = "exp_month")
    private String expMonth;

    @Column(name = "exp_year")
    private String expYear;

}
