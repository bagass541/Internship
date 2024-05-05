package com.bagas.repositories;

import com.bagas.entities.Credit;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;


public interface CreditRepository {
	
	List<Credit> getByUserIdPeriod(Long userId, LocalDate dateFrom, LocalDate dateTo) throws IOException;
}
