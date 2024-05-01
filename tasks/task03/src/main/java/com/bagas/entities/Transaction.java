package com.bagas.entities;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.bagas.entities.enums.CurrencyType;
import lombok.Data;

@Data
public class Transaction implements Comparable<Transaction> {
    private long id;

    private LocalDate date;

    private long userId;

    private long creditId;

    private CurrencyType currency;

    private BigDecimal money;

    public void convertToBr(List<Event> events, Settings settings) {
        if (currency == CurrencyType.Br) {
            return;
        }

        int i = 0;
        while (i < events.size()) {
            if (events.get(i).getDate().isBefore(date)) {
                if (events.get(i).getCurrency() == CurrencyType.EUR) {
                    settings.setStartCostEUR(events.get(i).getCost());
                } else {
                    settings.setStartCostUSD(events.get(i).getCost());
                }
                events.remove(i);
            } else {
                break;
            }
        }

        if (currency == CurrencyType.EUR) {
            money = money.multiply(settings.getStartCostEUR());
        } else {
            money = money.multiply(settings.getStartCostUSD());
        }

        currency = CurrencyType.Br;
    }

    @Override
    public int compareTo(Transaction o) {
        return this.date.isAfter(o.getDate()) ? 1 : 0;
    }
}
