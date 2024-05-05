package com.bagas.utils;

import com.bagas.entities.Event;
import com.bagas.entities.Settings;
import com.bagas.entities.Transaction;
import com.bagas.entities.enums.CurrencyType;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ConverterCurrency {

    public static void convertToBr(List<Transaction> transactions, List<Event> events,
                                   Settings settings) {

        BigDecimal startCostEUR = settings.getStartCostEUR();
        BigDecimal startCostUSD = settings.getStartCostUSD();

        transactions.stream()
                .filter(tr -> !(tr.getCurrency() == CurrencyType.Br))
                .forEach(tr -> applyEvents(tr, events, settings));

        settings.setStartCostEUR(startCostEUR);
        settings.setStartCostUSD(startCostUSD);
    }

    private static void applyEvents(Transaction transaction, List<Event> events, Settings settings) {
        int i = 0;

        events = events.stream()
                .filter(event -> event.getDate().isBefore(transaction.getDate()))
                .collect(Collectors.toList());

        while (i < events.size()) {
            if (events.get(i).getCurrency() == CurrencyType.EUR) {
                settings.setStartCostEUR(events.get(i).getCost());
            } else {
                settings.setStartCostUSD(events.get(i).getCost());
            }
            events.remove(i);
        }

        transaction.setMoney(transaction.getMoney().multiply(
                transaction.getCurrency() == CurrencyType.EUR ?
                        settings.getStartCostEUR() : settings.getStartCostUSD()));

        transaction.setCurrency(CurrencyType.Br);
    }
}
