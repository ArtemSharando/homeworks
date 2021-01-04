package test;

import com.homework.epam.dao.BillingDetailsDao;
import com.homework.epam.model.BankAccount;
import com.homework.epam.model.BillingDetails;
import com.homework.epam.model.CreditCard;
import com.homework.epam.model.Buyer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import test.config.HibernateConfiguration;

import javax.persistence.EntityTransaction;
import java.util.HashSet;
import java.util.Set;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {HibernateConfiguration.class})
@ComponentScan("com.homework.epam")
public class InheritanceHibernateIT {
    @Autowired
    BillingDetailsDao billingDetailsDao;

    @Autowired
    SessionFactory sessionFactory;

    private Session session;

    private EntityTransaction transaction;

    private Buyer buyer;

    Set<BillingDetails> details = new HashSet<>();

    @Before
    public void init(){
        session = sessionFactory.openSession();
        transaction = session.getTransaction();
        transaction.begin();

        buyer = new Buyer();
        buyer.setFirstName("First name");
        buyer.setLastName("Last name");

        CreditCard creditCard = new CreditCard();
        creditCard.setCardNumber(4149);
        creditCard.setExpMonth("12");
        creditCard.setExpYear("2030");
        creditCard.setBuyer(buyer);

        BankAccount bankAccount = new BankAccount();
        bankAccount.setAccount("Account");
        bankAccount.setBankName("Bank name");
        bankAccount.setBuyer(buyer);

        details.add(creditCard);
        details.add(bankAccount);
        buyer.setBillingDetails(details);
    }

    @After
    public void after(){
        session.close();
    }

    @Test
    public void addAndGetOwnerAndDetailsWithDao(){
        int userId = (Integer) session.save(buyer);
        transaction.commit();
        Assert.assertEquals(buyer.getBillingDetails().size(), billingDetailsDao.get(userId).size());
    }
}
