package com.jetbrains.testcontainersdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;

public class AbstractTest {


    // docker run -e MYSQL_USERNAME=... -e MYSQL_PASSWORD=... mysql:8.0.26

    @Container
    private static final MySQLContainer container = (MySQLContainer) new MySQLContainer("mysql:8.0.26")
            .withReuse(true);

    //withUsername(...)
    //withPassword


//    @Container
//    private static final RabbitMQContainer rabbitMQContainer = new RabbitMQContainer("rabbitmq:latest");
//
//    @Container
//    private static final GenericContainer genericContainer = new GenericContainer("myimage:mytag");
//


    @DynamicPropertySource
    public static void overrideProps(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", container::getJdbcUrl);
        registry.add("spring.datasource.username", container::getUsername);
        registry.add("spring.datasource.password", container::getPassword);

    }


}
