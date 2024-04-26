package com.bank.bankcredits.entities;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import com.bank.bankcredits.entities.enums.CurrencyType;
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
	
	public void applyAddPercents(LocalDate dateFrom, LocalDate dateTo) {
		int times = 0;
		
		switch(period) {
		case DAY -> times = (int) ChronoUnit.DAYS.between(dateFrom, dateTo);
		case WEEK -> times = (int) ChronoUnit.WEEKS.between(dateFrom, dateTo);
		case MONTH -> times = (int) ChronoUnit.MONTHS.between(dateFrom, dateTo);
		case YEAR -> times = (int) ChronoUnit.YEARS.between(dateFrom, dateTo);
		}
		
		for (int i = 0; i < times; i++) {
			money = money.add(money.multiply(BigDecimal.valueOf(rate / 100)));
			
		}
	}
	
	public BigDecimal processTransactions(List<Transaction> transactions, BigDecimal costUSD, BigDecimal costEUR, LocalDate dateTo) {
		BigDecimal sum = BigDecimal.ZERO;
		LocalDate dateCredit = date;
		
		for(Transaction transaction : transactions) {
			applyAddPercents(dateCredit, transaction.getDate().minusDays(1));
			dateCredit = transaction.getDate();
			
			if(transaction.getCurrency() == CurrencyType.EUR) {
				money = money.subtract(transaction.getMoney().multiply(costEUR));
			} else if(transaction.getCurrency() == CurrencyType.USD) {
				money = money.subtract(transaction.getMoney().multiply(costUSD));
			} else {
				money = money.subtract(transaction.getMoney());
			}
			
		}
		
		if(transactions.size() == 0 || !dateCredit.equals(dateTo)) applyAddPercents(dateCredit, dateTo);
		
		return sum;
	}
}
