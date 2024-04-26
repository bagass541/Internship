package com.bank.bankcredits.repositories;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import com.bank.bankcredits.entities.Credit;

public interface CreditRepository {
	
	List<Credit> getByUserIdPeriod(long userId, LocalDate dateFrom, LocalDate dateTo) throws IOException;
}
