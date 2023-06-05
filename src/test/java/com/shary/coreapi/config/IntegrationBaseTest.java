package com.shary.coreapi.config;

import com.shary.coreapi.config.init.PostgreSQLInitializer;
import org.junit.jupiter.api.BeforeAll;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

//@ActiveProfiles("test")
//@SpringBootTest
//@ContextConfiguration(initializers = {
//        PostgreSQLInitializer.Initializer.class
//})
//@Transactional
public abstract class IntegrationBaseTest {

    @BeforeAll
    static void init() {
        PostgreSQLInitializer.container.start();
    }
}
