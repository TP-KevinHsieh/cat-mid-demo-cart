package com.cat.mid.demo.cart.cartms.interfaces.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@Slf4j
@ActiveProfiles("unittest")
@SpringBootTest
@Testcontainers
@ContextConfiguration(initializers = {CartCommandControllerTest.Initializer.class})
@AutoConfigureMockMvc
class CartCommandControllerTest {
    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper mapper = new ObjectMapper();

    @Container
    private static final PostgreSQLContainer database =
            (PostgreSQLContainer) new PostgreSQLContainer("postgres:9.6")
                    .withDatabaseName("cartdb")
                    .withUsername("postgres")
                    .withPassword("1qaz@WSX")
                    .withInitScript("init_postgres.sql");

    static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        @Override
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            TestPropertyValues.of(
                    "spring.datasource.url=" + database.getJdbcUrl(),
                    "spring.datasource.username=" + database.getUsername(),
                    "spring.datasource.password=" + database.getPassword())
                    .applyTo(configurableApplicationContext.getEnvironment());
        }
    }

    @BeforeAll
    static void beforeAll() {
        database.start();
    }

    @Test
    void addCart() throws Exception {
        MvcResult result = mockMvc.perform(
                post("/cat-mid-demo-cart/cart/add")
                        .content("{\"customerId\":\"e92e3295-b305-4c02-b995-67effaf7cff6\"}")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn();

        log.info("Add cart run test success, result:{}", result.getResponse().getContentAsString());
    }
}