package com.bagas.services;

import com.bagas.entities.*;
import com.bagas.entities.enums.ShowForType;
import com.bagas.exceptions.UserNotFoundException;
import com.bagas.gson.DBReader;
import com.bagas.repositories.*;
import com.bagas.utils.TableGenerator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static com.bagas.utils.ConverterCurrency.convertToBr;
import static com.bagas.utils.ResultMapper.createResult;

public class AppService {

    private final UserService userService;

    private CreditService creditService;

    private TransactionService transactionService;

    public AppService() {
        DBReader dbReader = new DBReader();
        this.userService = new UserService(new UserRepositoryImpl(dbReader));
        this.creditService = new CreditService(new CreditRepositoryImpl(dbReader), new DiscountRepositoryImpl(dbReader));
        this.transactionService = new TransactionService(new TransactionRepositoryImpl(dbReader),
                new EventRepositoryImpl(dbReader));
    }

    public void printResults(Settings settings) throws IOException, UserNotFoundException {
        TableGenerator tableGenerator = new TableGenerator();
        StringBuilder table = tableGenerator.createTable();

        List<Result> results = getResultsByUsers(settings);
        sortResultsBySettings(settings.getSortBy(), results);
        table = tableGenerator.addTransactions(results, table);

        System.out.println(table.toString());
    }

    // Через стрим тут не сделаешь 
    private void sortResultsBySettings(String sortBy, List<Result> results) {
        Comparator<Result> comparator;
        if (sortBy.equals("NAME")) {
            comparator = Comparator.comparing(Result::getFullName);
        } else if (sortBy.equals("DEBT")) {
            comparator = Comparator.comparing(Result::getDebt);
        } else {
            comparator = Comparator.comparing(Result::getDateRepayment,
                    Comparator.nullsFirst(Comparator.naturalOrder()));
        }

        results.sort(comparator);
    }

    private List<Result> getResultsByUsers(Settings settings) throws IOException {
        List<Result> results = new ArrayList<>();
        List<Event> events = transactionService.getEventsByDateTo(settings.getDateTo());
        List<User> users = userService.getUsers(settings);

        for (User user : users) {
            List<Credit> credits = creditService
                    .getByUserIdPeriod(user.getId(), settings.getDateFrom(), settings.getDateTo());

            for (Credit credit : credits) {
                Discount discount = creditService.getDiscountByDate(credit.getDate());
                credit.applyDiscount(discount);

                List<Transaction> transactions = transactionService.getByCreditIdDateTo(credit.getId(),
                        settings.getDateFrom(), settings.getDateTo());

                convertToBr(transactions, new ArrayList<>(events), settings);
                credit.processTransactions(transactions, settings.getDateTo());
                results.add(createResult(credit, user, transactions.size()));
            }
        }

        return results;
    }
}
