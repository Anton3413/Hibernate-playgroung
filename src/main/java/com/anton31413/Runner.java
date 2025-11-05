package com.anton31413;

import com.anton31413.model.Company;
import com.anton31413.model.Role;
import com.anton31413.model.User;
import com.anton31413.util.HibernateConfig;
import com.anton31413.util.LoggerConfig;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;


import java.time.LocalDate;
import java.time.Month;
@Slf4j
public class Runner {
    public static void main(String[] args) {
        LoggerConfig.configure();
        try(SessionFactory sessionFactory = HibernateConfig.buildSessionFactory()){
            Session session = sessionFactory.openSession();
            session.beginTransaction();

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

            log.debug("Object has been created {} ", user);
            log.warn("Commiting...");
            session.persist(user);
            session.getTransaction().commit();
        }
    }
}
