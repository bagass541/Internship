package com.bank.bankcredits.entities;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import lombok.Data;

@Data
public class Settings {

	private LocalDate dateFrom;
	
	private LocalDate dateTo;
	
	private ShowFor showFor;
	
	private String sortBy;
	
	private List<String> useDepartments;
	
	private BigDecimal startCostEUR;
	
	private BigDecimal startCostUSD;
}
