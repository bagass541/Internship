package com.bank.bankcredits.services;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.bank.bankcredits.Utils.TableGenerator;
import com.bank.bankcredits.entities.Credit;
import com.bank.bankcredits.entities.Discount;
import com.bank.bankcredits.entities.Result;
import com.bank.bankcredits.entities.Settings;
import com.bank.bankcredits.entities.Transaction;
import com.bank.bankcredits.entities.User;
import com.bank.bankcredits.entities.enums.CurrencyType;
import com.bank.bankcredits.entities.enums.StatusType;
import com.bank.bankcredits.exceptions.UserNotFoundException;
import com.bank.bankcredits.gson.DBReader;
import com.bank.bankcredits.repositories.CreditRepositoryImpl;
import com.bank.bankcredits.repositories.DiscountRepositoryImpl;
import com.bank.bankcredits.repositories.EventRepositoryImpl;
import com.bank.bankcredits.repositories.TransactionRepositoryImpl;
import com.bank.bankcredits.repositories.UserRepositoryImpl;

public class AppService {

	private UserService userService;
	
	private CreditService creditService;
	
	private TransactionService transactionService;

	public AppService() {
		DBReader dbReader = new DBReader();
		this.userService = new UserService(new UserRepositoryImpl(dbReader));
		this.creditService = new CreditService(new CreditRepositoryImpl(dbReader), new DiscountRepositoryImpl(dbReader));
		this.transactionService = new TransactionService(new TransactionRepositoryImpl(dbReader), new EventRepositoryImpl(dbReader));
	}
	
	public void printResults(Settings settings) throws IOException, UserNotFoundException {
		TableGenerator tableGenerator = new TableGenerator();
		StringBuilder table = tableGenerator.createTable();
		
		for (Integer id : settings.getShowFor().getUsers()) {
			List<Result> results = getResultsByUser(id, settings);
			table = tableGenerator.addTransactions(results, table);
		}
		
		System.out.println(table.toString());
	}
	
	private List<Result> getResultsByUser(long userId, Settings settings) throws IOException, UserNotFoundException {
		List<Result> results = new ArrayList<Result>();
		
		User user = userService.getById(userId);
		List<Credit> credits = creditService.getByUserIdPeriod(userId, settings.getDateFrom(), settings.getDateTo());
		
		for(Credit credit : credits) {;
			Discount discount = creditService.getDiscountByDate(credit.getDate());
			credit.applyDiscount(discount);
			
			List<Transaction> transactions = transactionService.getByCreditIdDateTo(credit.getId(), settings.getDateFrom(), settings.getDateTo());
			credit.applyTransactions(getTotalBr(transactions, settings));
			
			String fullName = user.getName() + " " + user.getSecondName();
			results.add(new Result(credit.getId(), userId, fullName, transactions.size(),
					credit.getMoney(), credit.getPeriod(), StatusType.IN_PROGRESS, null));
		}
		
		return results;
	}
	
	private BigDecimal getTotalBr(List<Transaction> transactions, Settings settings) {
		BigDecimal sum = BigDecimal.ZERO;
		for(Transaction transaction : transactions) {
			if(transaction.getCurrency() == CurrencyType.EUR) {
				sum = sum.add(transaction.getMoney().multiply(settings.getStartCostEUR()));
			} else if(transaction.getCurrency() == CurrencyType.USD) {
				sum = sum.add(transaction.getMoney().multiply(settings.getStartCostUSD()));
			} else {
				sum = sum.add(transaction.getMoney());
			}
		}
		
		return sum;
	}
}
