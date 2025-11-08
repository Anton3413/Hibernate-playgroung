package com.anton31413.util;


import com.anton31413.model.Company;
import com.anton31413.model.User;
import lombok.Getter;
import lombok.experimental.UtilityClass;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy;
import org.hibernate.boot.model.naming.PhysicalNamingStrategySnakeCaseImpl;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

@UtilityClass
public class HibernateConfig {

    @Getter
    private static final SessionFactory sessionFactory = buildSessionFactory();

    private SessionFactory buildSessionFactory(){
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .applySetting("hibernate.connection.driver_class", "org.postgresql.Driver")
                .applySetting("hibernate.connection.url", "jdbc:postgresql://localhost:5432/postgres")
                .applySetting("hibernate.connection.username", "postgres")
                .applySetting("hibernate.connection.password", "1111")
                .applySetting(
                        "hibernate.physical_naming_strategy",
                        "org.hibernate.boot.model.naming.PhysicalNamingStrategySnakeCaseImpl"
                )
                //.applySetting("hibernate.hbm2ddl.auto", "create-drop")
                .applySetting("hibernate.show_sql", "true")
                .applySetting("hibernate.format_sql", "true")
                .build();

        MetadataSources sources = new MetadataSources(registry)
                .addAnnotatedClass(User.class)
                .addAnnotatedClass(Company.class);

        Metadata metadata = sources.getMetadataBuilder().build();

        return metadata.getSessionFactoryBuilder().build();
    }
}
