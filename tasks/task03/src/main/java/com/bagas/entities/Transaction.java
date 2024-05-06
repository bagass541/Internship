package com.bagas.entities;

import com.bagas.entities.enums.CurrencyType;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class Transaction {

    private long id;

    private LocalDate date;

    private long userId;

    private long creditId;

    private CurrencyType currency;

    private BigDecimal money;
}
