package com.bank.bankcredits.entities;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.bank.bankcredits.entities.enums.PeriodType;
import com.bank.bankcredits.entities.enums.StatusType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Result {

	private long creditId;
	
	private long userId;
	
	private String fullName;
	
	private int countTransactions;
	
	private BigDecimal debt;
	
	private PeriodType periodType;
	
	private StatusType statusType;
	
	private LocalDate dateRepayment;

	@Override
	public String toString() {
		return "Result [creditId=" + creditId + ", userId=" + userId + ", fullName=" + fullName + ", countTransactions="
				+ countTransactions + ", debt=" + debt + ", periodType=" + periodType + ", statusType=" + statusType
				+ ", dateRepayment=" + dateRepayment + "]";
	}
	
	
}
