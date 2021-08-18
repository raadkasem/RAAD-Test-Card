package com.sky.coding.repo;

import com.sky.coding.model.*;
import org.springframework.stereotype.*;
import reactor.core.publisher.*;

import javax.annotation.*;
import java.time.*;
import java.time.format.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;

/**
 * Simulate Data from external source.
 * DO NOT CHANGE
 */
@Repository
public class TransactionRepository {

    private final DateTimeFormatter formatter = DateTimeFormatter.ISO_INSTANT.withZone(ZoneId.of("Z"));
    private List<Transaction> data;

    public Mono<List<Transaction>> getData() {
        return Mono.just(data);
    }

    @PostConstruct
    private void init() {
        data = new ArrayList<>();
        addRecord(TransactionType.LOSS, "2031-12-01T10:21:30Z", 2);
        addRecord(TransactionType.LOSS, "2031-12-01T10:21:30Z", 4);
        addRecord(TransactionType.LOSS, "2031-12-01T10:22:30Z", 6);

        addRecord(TransactionType.RETURN, "2031-12-01T10:00:30Z", 1);
        addRecord(TransactionType.RETURN, "2031-12-01T10:01:30Z", 3);
        addRecord(TransactionType.RETURN, "2031-12-01T10:44:30Z", 1);
        addRecord(TransactionType.RETURN, "2031-12-01T11:00:00Z", 8);

        addRecord(TransactionType.SALES, "2031-12-01T10:00:00Z", 1);
        addRecord(TransactionType.SALES, "2031-12-01T10:01:30Z", 3);
        addRecord(TransactionType.SALES, "2031-12-01T10:16:30Z", 5);
        addRecord(TransactionType.SALES, "2031-12-01T10:21:30Z", 2);
        addRecord(TransactionType.SALES, "2031-12-01T10:24:30Z", 4);
        addRecord(TransactionType.SALES, "2031-12-01T10:29:59Z", 6);
        addRecord(TransactionType.SALES, "2031-12-01T10:30:59Z", 10);

        duplicate();
    }

    private void addRecord(TransactionType type, String time, long val) {
        data.add(Transaction.builder()
                .type(type)
                .id(UUID.randomUUID())
                .value(val)
                .timestamp(ZonedDateTime.parse(time, formatter))
                .build());
    }

    private void duplicate() {
        IntStream.range(0, 20).forEach(it -> {
            int i = ThreadLocalRandom.current().nextInt(0, data.size());
            data.add(data.get(i).copy());
        });

    }
}
