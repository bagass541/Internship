package com.bank.bankcredits.services;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import com.bank.bankcredits.entities.Credit;
import com.bank.bankcredits.entities.Discount;
import com.bank.bankcredits.repositories.CreditRepository;
import com.bank.bankcredits.repositories.DiscountRepository;

public class CreditService {

	private CreditRepository creditRepository;
	
	private DiscountRepository discountRepository;

	public CreditService(CreditRepository creditRepository, DiscountRepository discountRepository) {
		this.creditRepository = creditRepository;
		this.discountRepository = discountRepository;
	}

	public List<Credit> getByUserIdPeriod(long userId, LocalDate dateFrom, LocalDate dateTo) throws IOException {
		return creditRepository.getByUserIdPeriod(userId, dateFrom, dateTo);
	}
	
	public Discount getDiscountByDate(LocalDate date) throws IOException {
		return discountRepository.getByDate(date).orElse(null);
	}
}
