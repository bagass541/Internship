package com.bank.bankcredits.repositories;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import com.bank.bankcredits.entities.Transaction;

public interface TransactionRepository {

	List<Transaction> getByCreditIdDateTo(long creditId, LocalDate dateFrom, LocalDate dateTo) throws IOException;
}
