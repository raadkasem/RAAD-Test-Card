package com.sky.coding.controller;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.web.servlet.*;
import org.springframework.boot.test.context.*;
import org.springframework.test.web.reactive.server.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class TransactionControllerIT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void httpClientExample() {
        this.webTestClient
                .get()
                .uri("/api/v1/transactions")
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody().jsonPath("$.length()").isEqualTo(34);
    }
}