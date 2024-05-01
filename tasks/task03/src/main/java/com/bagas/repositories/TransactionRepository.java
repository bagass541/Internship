package com.bagas.repositories;

import com.bagas.entities.Transaction;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public interface TransactionRepository {

	List<Transaction> getByCreditIdDateTo(long creditId, LocalDate dateFrom, LocalDate dateTo) throws IOException;
}
