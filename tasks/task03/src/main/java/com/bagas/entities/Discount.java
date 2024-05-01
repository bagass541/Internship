package com.bagas.entities;

import java.time.LocalDate;

import com.bagas.entities.enums.DiscountType;
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
