package com.wk.lab04;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

//@Testcontainers
@SpringBootTest
class Lab04CrmMicroApplicationTests {
    
	//@Container
    //static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16-alpine");

    //@DynamicPropertySource
//    static void configureProperties(DynamicPropertyRegistry registry) {
//        registry.add("spring.datasource.url", postgres::getJdbcUrl);
//        registry.add("spring.datasource.username", postgres::getUsername);
//        registry.add("spring.datasource.password", postgres::getPassword);
//        registry.add("spring.datasource.hikari.max-lifetime",()->"20000");
//        registry.add("spring.datasource.hikari.idle-timeout", ()->"10000");
//    }
    
	 //@Test
	void contextLoads() {
	}

}
