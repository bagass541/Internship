package com.bagas.entities;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.bagas.entities.enums.CurrencyType;
import lombok.Data;

@Data
public class Event implements Comparable<Event> {

	private long id;
	
	private CurrencyType currency;
	
	private BigDecimal cost;
	
	private LocalDate date;

	@Override
	public int compareTo(Event o) {
		return date.compareTo(o.getDate());
	}
	
}
