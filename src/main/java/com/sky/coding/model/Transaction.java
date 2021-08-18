package com.sky.coding.model;

import lombok.*;

import java.time.*;
import java.util.*;

@Getter
@Builder
public class Transaction {
    UUID id;
    TransactionType type;
    ZonedDateTime timestamp;
    long value;

    public Transaction copy() {
        return builder()
                .id(id)
                .type(type)
                .value(value)
                .timestamp(timestamp)
                .build();
    }
}
