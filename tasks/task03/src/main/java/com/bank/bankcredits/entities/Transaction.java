package com.bank.bankcredits.entities;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.bank.bankcredits.entities.enums.CurrencyType;

import lombok.Data;

@Data
public class Transaction {
	private long id;
	
	private LocalDate date;
	
	private long userId;
	
	private long creditId;
	
	private CurrencyType currency;
	
	private BigDecimal money;
}
