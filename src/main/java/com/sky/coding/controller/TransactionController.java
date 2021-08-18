package com.sky.coding.controller;

import com.sky.coding.model.*;
import com.sky.coding.service.*;
import lombok.extern.slf4j.*;
import org.springframework.http.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.*;

import java.util.*;

@Slf4j
@Controller
public class TransactionController {

    TransactionService service;

    public TransactionController(TransactionService service) {
        this.service = service;
    }

    @GetMapping(value = "/api/v1/transactions", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Mono<List<Transaction>> getAllTransactions() {
        return service.getTransactionData();
    }
}
