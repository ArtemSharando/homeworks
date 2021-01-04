package com.homework.epam.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "buyer")
@Getter
@Setter
public class Buyer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @OneToMany(mappedBy = "buyer", fetch = FetchType.LAZY,cascade=CascadeType.ALL)
    Set<BillingDetails> billingDetails;

}
