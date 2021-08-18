package com.sky.coding.service;

import com.sky.coding.model.*;
import com.sky.coding.repo.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;
import org.mockito.*;
import org.mockito.junit.jupiter.*;
import reactor.core.publisher.*;

import java.time.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class TransactionServiceTest {

    @Mock
    TransactionRepository repo;

    @InjectMocks
    TransactionService target;

    @Nested
    class GetTransactionData {

        @Test void getDataNotEmpty() {
            List<Transaction> data = new ArrayList<>();
            data.add(Transaction.builder()
                    .type(TransactionType.SALES)
                    .id(UUID.randomUUID())
                    .value(55)
                    .timestamp(ZonedDateTime.now())
                    .build());
            Mockito.when(repo.getData()).thenReturn(Mono.just(data));

            List<Transaction> result = target.getTransactionData().block();
            assertEquals(1, result.size());
            assertEquals(55, result.get(0).getValue());
            assertEquals(TransactionType.SALES, result.get(0).getType());
        }

        @Test void getDataEmpty() {
            Mockito.when(repo.getData()).thenReturn(Mono.just(new ArrayList<>()));
            List<Transaction> result = target.getTransactionData().block();
            assertEquals(0, result.size());
        }

    }

}