package com.sky.coding.service;

import com.sky.coding.model.*;
import com.sky.coding.repo.*;
import org.springframework.stereotype.*;
import reactor.core.publisher.*;

import java.util.*;

@Service
public class TransactionService {
    TransactionRepository repository;

    public TransactionService(TransactionRepository repository) {
        this.repository = repository;
    }

    public Mono<List<Transaction>> getTransactionData() {
        return repository.getData();
    }
}
