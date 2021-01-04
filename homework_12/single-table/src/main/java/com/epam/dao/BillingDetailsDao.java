package com.epam.dao;

import com.epam.domain.BillingDetails;

import java.util.List;

public interface BillingDetailsDao {

    List<BillingDetails> get(Long userId);

}
