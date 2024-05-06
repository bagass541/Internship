package com.bagas.repositories;

import com.bagas.entities.Transaction;
import com.bagas.gson.DBReader;
import com.bagas.gson.GsonConfigurator;
import com.google.gson.JsonArray;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static com.bagas.utils.DateUtility.isInRange;

public class TransactionRepositoryImpl implements TransactionRepository {

    private final String ELEMENT_NAME = "transactions";

    private DBReader dbReader;

    public TransactionRepositoryImpl(DBReader dbReader) {
        this.dbReader = dbReader;
    }

    @Override
    public List<Transaction> getByCreditIdDateTo(Long creditId, LocalDate dateFrom, LocalDate dateTo) throws IOException {
        JsonArray transactionsJson = dbReader.getJsonArrayByName(ELEMENT_NAME);

        return transactionsJson.asList().stream()
                .map(tr -> GsonConfigurator.getGson().fromJson(tr, Transaction.class))
                .filter(tr -> tr.getId() == creditId && isInRange(dateFrom, dateTo, tr.getDate()))
                .sorted(Comparator.comparing(Transaction::getDate))
                .collect(Collectors.toList());
    }
}
