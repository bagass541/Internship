package com.bank.bankcredits.entities;

import java.time.LocalDate;

import com.bank.bankcredits.entities.enums.DiscountType;

import lombok.Data;

@Data
public class Discount {

	private long id;
	
	private DiscountType type;
	
	private LocalDate date;
	
	private LocalDate dateFrom;
	
	private LocalDate dateTo;
	
	private double discount;
}
