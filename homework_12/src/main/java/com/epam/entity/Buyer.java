package com.epam.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "buyer")
public class Buyer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @OneToMany(orphanRemoval = true, mappedBy = "buyer")
    private List<BillingDetails> billingDetails;
}
