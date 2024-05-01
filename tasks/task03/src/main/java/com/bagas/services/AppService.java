package com.bagas.services;

import com.bagas.entities.*;
import com.bagas.entities.enums.StatusType;
import com.bagas.exceptions.UserNotFoundException;
import com.bagas.gson.DBReader;
import com.bagas.repositories.*;
import com.bagas.utils.TableGenerator;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class AppService {

    private UserService userService;

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

        List<Result> results = getResultsByUsers(settings.getShowFor().getUsers(), settings);
        sortResultsBySettings(settings.getSortBy(), results);
        table = tableGenerator.addTransactions(results, table);

        System.out.println(table.toString());
    }

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

    private List<Result> getResultsByUsers(List<Integer> usersId, Settings settings) throws IOException, UserNotFoundException {
        List<Result> results = new ArrayList<Result>();
        List<Event> events = transactionService.getEventsByDateTo(settings.getDateTo());

        for (Integer userId : usersId) {
            User user = userService.getById(userId);
            List<Credit> credits = creditService.getByUserIdPeriod(userId, settings.getDateFrom(), settings.getDateTo());

            for (Credit credit : credits) {
                Discount discount = creditService.getDiscountByDate(credit.getDate());
                credit.applyDiscount(discount);

                List<Transaction> transactions = transactionService.getByCreditIdDateTo(credit.getId(),
                        settings.getDateFrom(), settings.getDateTo());

                BigDecimal startCostEUR = settings.getStartCostEUR();
                BigDecimal startCostUSD = settings.getStartCostUSD();

                transactions.forEach(tr -> tr.convertToBr(new ArrayList<>(events), settings));

                settings.setStartCostEUR(startCostEUR);
                settings.setStartCostUSD(startCostUSD);

                credit.processTransactions(transactions, settings.getStartCostUSD(), settings.getStartCostEUR(),
                        settings.getDateTo());

                String fullName = user.getName() + " " + user.getSecondName();
                results.add(new Result(credit.getId(), userId, fullName, transactions.size(), credit.getMoney(),
                        credit.getPeriod(), StatusType.getStatusType(credit.getMoney()), credit.getRepayment()));
            }
        }

        return results;
    }
}
