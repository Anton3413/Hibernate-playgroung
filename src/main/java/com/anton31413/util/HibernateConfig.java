package com.anton31413.util;


import com.anton31413.model.Company;
import com.anton31413.model.User;
import lombok.experimental.UtilityClass;
import org.hibernate.SessionFactory;
import org.hibernate.boot.model.naming.PhysicalNamingStrategySnakeCaseImpl;
import org.hibernate.cfg.Configuration;

@UtilityClass
public class HibernateConfig {

    public SessionFactory buildSessionFactory(){
        Configuration config = new Configuration();
        config.setPhysicalNamingStrategy(new PhysicalNamingStrategySnakeCaseImpl());
        config.addAnnotatedClass(User.class);
        config.addAnnotatedClass(Company.class);
        config.configure();
        return config.buildSessionFactory();
    }
}
