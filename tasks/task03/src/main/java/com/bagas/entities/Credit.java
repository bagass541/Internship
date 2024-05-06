package com.bagas.entities;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.bagas.entities.enums.PeriodType;
import com.bagas.utils.DateUtility;
import com.google.gson.annotations.Expose;
import lombok.AllArgsConstructor;
import lombok.Data;

import static com.bagas.utils.DateUtility.countPeriods;

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
        if(isZeroBalance(money)) {
            return;
        }

        int times = countPeriods(dateFrom, dateTo, period);

        for (int i = 0; i < times; i++) {
            money = money.add(money.abs().multiply(BigDecimal.valueOf(rate / 100)));
        }
    }

    public void processTransactions(List<Transaction> transactions, LocalDate dateTo) {
        LocalDate dateCredit = date;

        for (Transaction transaction : transactions) {
            applyAddPercents(dateCredit, transaction.getDate().minusDays(1));
            dateCredit = transaction.getDate();

            money = money.subtract(transaction.getMoney());

            if (isZeroBalance(money)) {
                repayment = transaction.getDate();
                break;
            }
        }

        if (transactions.isEmpty() || !dateCredit.equals(dateTo)) {
            applyAddPercents(dateCredit, dateTo);
        }
    }

    private boolean isZeroBalance(BigDecimal money) {
        return money.compareTo(BigDecimal.ZERO) <= 0;
    }
}
