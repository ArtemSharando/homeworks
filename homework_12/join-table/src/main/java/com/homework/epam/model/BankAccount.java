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
@Table(name = "bank_account")
@Getter
@Setter
public class BankAccount extends BillingDetails {
    private String account;

    @Column(name = "bank_name")
    private String bankName;

}
