package com.bank.bankcredits.entities;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.bank.bankcredits.entities.enums.CurrencyType;

import lombok.Data;

@Data
public class Transaction implements Comparable<Transaction> {
	private long id;
	
	private LocalDate date;
	
	private long userId;
	
	private long creditId;
	
	private CurrencyType currency;
	
	private BigDecimal money;

	@Override
	public int compareTo(Transaction o) {
		return this.date.isAfter(o.getDate()) ? 1 : 0;
	}
}
