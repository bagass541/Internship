package com.bank.bankcredits.Utils;

import java.util.List;

import com.bank.bankcredits.entities.Result;

public class TableGenerator {

	public StringBuilder createTable() {
		StringBuilder table = new StringBuilder();
		table.append(" Id кредита | Id пользователя | Имя и фамилия пользователя "
				+ "| Количество транзакций | Размер долга | Период кредита |    Статус    | Дата погашения \n");
		
		return table;
	}
	
	public StringBuilder addTransactions(List<Result> results, StringBuilder table) {
		for(Result res : results) {
			table.append(String.format(" %10s | %15s | %26s | %21s | %12s | %14s | %12s | %s \n", res.getCreditId(), res.getUserId(), res.getFullName(),
					res.getCountTransactions(), res.getDebt(), res.getPeriodType(), res.getStatusType(), res.getDateRepayment()));
		}
		
		return table;
	}
}
