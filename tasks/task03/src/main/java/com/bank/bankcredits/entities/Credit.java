package com.bank.bankcredits.entities;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.bank.bankcredits.entities.enums.PeriodType;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Credit {

	private long id;
	
	private long userId;
	
	private LocalDate date;
	
	private PeriodType period;
	
	private BigDecimal money;
	
	private double rate;
	
	public void applyDiscount(Discount discount) {
		if (discount != null) {
			rate -= discount.getDiscount();
		}
	}
	
	public void applyTransactions(BigDecimal transactionMoney) {
		money = money.subtract(transactionMoney);
	}
	
	public void applyAddPercents() {
		money = money.add(money.multiply(BigDecimal.valueOf(rate / 100)));
	}
}
