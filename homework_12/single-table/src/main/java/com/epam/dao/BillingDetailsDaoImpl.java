package com.epam.dao;

import com.epam.domain.BillingDetails;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public class BillingDetailsDaoImpl implements BillingDetailsDao{

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public List<BillingDetails> get(Long userId) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select bd from BillingDetails bd where bd.buyer.id= :buyer_id");
        query.setParameter("buyer_id", userId);
        return query.list();
    }
}
