package com.bagas.entities;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.bagas.entities.enums.PeriodType;
import com.bagas.entities.enums.StatusType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class Result {

	private long creditId;
	
	private long userId;
	
	private String fullName;
	
	private int countTransactions;
	
	private BigDecimal debt;
	
	private PeriodType periodType;
	
	private StatusType statusType;
	
	private LocalDate dateRepayment;
}
