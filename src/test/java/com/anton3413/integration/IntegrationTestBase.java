package com.anton3413.integration;


import com.anton31413.model.Company;
import com.anton31413.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.slf4j.LoggerFactory;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.containers.output.Slf4jLogConsumer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
public abstract class IntegrationTestBase {

    @Container
    protected static PostgreSQLContainer<?> POSTGRES =
            new PostgreSQLContainer<>("postgres:18.0")
                    .withInitScript("init.sql");

    protected static SessionFactory sessionFactory;

    @BeforeAll
    static void initSessionFactory() {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .applySetting("hibernate.connection.driver_class", "org.postgresql.Driver")
                .applySetting("hibernate.connection.url", POSTGRES.getJdbcUrl())
                .applySetting("hibernate.connection.username", POSTGRES.getUsername())
                .applySetting("hibernate.connection.password", POSTGRES.getPassword())
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

        sessionFactory = metadata.getSessionFactoryBuilder().build();
    }

    @AfterAll
    static void closeSessionFactory() {
        if (sessionFactory != null) sessionFactory.close();
    }

}
