package com.bagas.repositories;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.bagas.entities.Transaction;
import com.bagas.gson.DBReader;
import com.bagas.gson.GsonConfigurator;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

public class TransactionRepositoryImpl implements TransactionRepository {

	private final String ELEMENT_NAME = "transactions";

	private DBReader dbReader;
	
	public TransactionRepositoryImpl(DBReader dbReader) {
		this.dbReader = dbReader;
	}

	@Override
	public List<Transaction> getByCreditIdDateTo(long creditId, LocalDate dateFrom, LocalDate dateTo) throws IOException {
		JsonArray transactionsJson = dbReader.getJsonArrayByName(ELEMENT_NAME);
		List<Transaction> transactions = new ArrayList<Transaction>();
		
		for(JsonElement element : transactionsJson) {
			Transaction transaction = GsonConfigurator.getGson().fromJson(element, Transaction.class);
			if(transaction.getCreditId() == creditId && dateFrom.isBefore(transaction.getDate()) && dateTo.isAfter(transaction.getDate())) {
				transactions.add(transaction);
			}
		}

		transactions.sort(null);

		return transactions;
	}
}
