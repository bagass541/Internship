package com.bank.bankcredits.services;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import com.bank.bankcredits.entities.Event;
import com.bank.bankcredits.entities.Transaction;
import com.bank.bankcredits.repositories.EventRepository;
import com.bank.bankcredits.repositories.TransactionRepository;

public class TransactionService {

	private TransactionRepository transactionRepository;
	
	private EventRepository eventRepository;

	public TransactionService(TransactionRepository transactionRepository, EventRepository eventRepository) {
		this.transactionRepository = transactionRepository;
		this.eventRepository = eventRepository;
	}

	public List<Transaction> getByCreditIdDateTo(long creditId, LocalDate dateFrom, LocalDate dateTo) throws IOException {
		return transactionRepository.getByCreditIdDateTo(creditId, dateFrom, dateTo);
	}
	
	public List<Event> getByPeriod(LocalDate dateFrom, LocalDate dateTo) throws IOException {
		return eventRepository.getByPeriod(dateFrom, dateTo);
	}
}
