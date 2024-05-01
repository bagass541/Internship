package com.bagas.entities;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import com.bagas.entities.enums.PeriodType;
import com.google.gson.annotations.Expose;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Credit {

    private long id;

    private long userId;

    private LocalDate date;

    private PeriodType period;

    private BigDecimal money;

    private double rate;

    @Expose(serialize = false)
    private LocalDate repayment;

    public void applyDiscount(Discount discount) {
        if (discount != null) {
            rate -= discount.getDiscount();
        }

        rate = Math.max(rate, 0);
    }

    public void applyAddPercents(LocalDate dateFrom, LocalDate dateTo) {
        if(money.compareTo(BigDecimal.ZERO) < 0) {
            return;
        }

        int times = 0;

        switch (period) {
            case DAY -> times = (int) ChronoUnit.DAYS.between(dateFrom, dateTo);
            case WEEK -> times = (int) ChronoUnit.WEEKS.between(dateFrom, dateTo);
            case MONTH -> times = (int) ChronoUnit.MONTHS.between(dateFrom, dateTo);
            case YEAR -> times = (int) ChronoUnit.YEARS.between(dateFrom, dateTo);
        }

        for (int i = 0; i < times; i++) {
            money = money.add(money.abs().multiply(BigDecimal.valueOf(rate / 100)));
        }
    }

    public void processTransactions(List<Transaction> transactions, BigDecimal costUSD, BigDecimal costEUR, LocalDate dateTo) {
        LocalDate dateCredit = date;

        for (Transaction transaction : transactions) {
            applyAddPercents(dateCredit, transaction.getDate().minusDays(1));
            dateCredit = transaction.getDate();

            money = money.subtract(transaction.getMoney());

            if (money.compareTo(BigDecimal.ZERO) < 0) {
                repayment = transaction.getDate();
                break;
            }
        }

        if (transactions.isEmpty() || !dateCredit.equals(dateTo)) {
            applyAddPercents(dateCredit, dateTo);
        }
    }
}
