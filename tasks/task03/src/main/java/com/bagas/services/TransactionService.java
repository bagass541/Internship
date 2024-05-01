package com.bagas.services;

import com.bagas.entities.Event;
import com.bagas.entities.Transaction;
import com.bagas.repositories.EventRepository;
import com.bagas.repositories.TransactionRepository;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

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
	
	public List<Event> getEventsByDateTo(LocalDate dateTo) throws IOException {
		return eventRepository.getByDateTo(dateTo);
	}
}
