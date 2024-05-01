package com.bagas.utils;

import com.bagas.entities.Result;

import java.math.RoundingMode;
import java.util.List;

public class TableGenerator {

    private final String TABLE_FORMAT = " %10s | %15s | %26s | %21s | %16.16s | %14s | %12s | %s \n";

    private final String TABLE_SEPARATOR = "------------|-----------------|----------------------------" +
            "|-----------------------|------------------" +
            "|----------------|--------------|---------------\n";

    public StringBuilder createTable() {
        StringBuilder table = new StringBuilder();
        table.append(" Id кредита | Id пользователя | Имя и фамилия пользователя "
                        + "| Количество транзакций |   Размер долга   | Период кредита |    Статус    | Дата погашения \n")
                .append(TABLE_SEPARATOR);

        return table;
    }

    public StringBuilder addTransactions(List<Result> results, StringBuilder table) {
        for (Result res : results) {
            String debt = res.getDebt().setScale(3, RoundingMode.CEILING).toString();

            if (debt.length() > 17) {
                debt = ">999999999999999";
            }

            table.append(String.format(TABLE_FORMAT,
                            res.getCreditId(), res.getUserId(), res.getFullName(),
                            res.getCountTransactions(), debt, res.getPeriodType(),
                            res.getStatusType(), res.getDateRepayment()))
                    .append(TABLE_SEPARATOR);
        }

        return table;
    }
}
