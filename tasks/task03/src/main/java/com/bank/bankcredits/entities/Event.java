package com.bank.bankcredits.entities;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.bank.bankcredits.entities.enums.CurrencyType;

import lombok.Data;

@Data
public class Event implements Comparable<Event> {

	private long id;
	
	private CurrencyType currency;
	
	private BigDecimal cost;
	
	private LocalDate date;

	@Override
	public int compareTo(Event o) {
		return date.isAfter(o.getDate()) ? 1 : -1;
	}
	
}
