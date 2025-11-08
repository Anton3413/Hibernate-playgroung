package com.anton3413.integration;

import com.anton31413.model.Company;
import com.anton31413.model.Role;
import com.anton31413.model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;

public class UserTest extends IntegrationTestBase {

    private Session session;
    private Transaction transaction;

    @BeforeEach
    void initSession(){
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();
    }

    @AfterEach
    void closeSession() {
        if (transaction != null && transaction.isActive()) {
            transaction.rollback();
        }
        session.close();
    }


    @Test
    void saveUserToDb(){
        Company company = Company.builder()
                .name("Facebook").build();

        User user = User.builder()
                .firstName("Anton")
                .lastName("Bondar")
                .username("anton3413")
                .age(25)
                .birthDate(LocalDate.of(2000, Month.JULY,16))
                .role(Role.USER)
                .company(company)
                .build();


        session.persist(user);
        session.flush();

        User found = session.find(User.class, user.getId());

        assertNotNull(found);
        assertNotNull(found.getCompany());

        assertEquals("Anton", found.getFirstName());

    }
}
