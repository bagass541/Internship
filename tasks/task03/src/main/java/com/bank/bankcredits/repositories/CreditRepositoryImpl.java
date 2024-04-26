package com.bank.bankcredits.repositories;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.bank.bankcredits.entities.Credit;
import com.bank.bankcredits.gson.DBReader;
import com.bank.bankcredits.gson.GsonConfigurator;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

public class CreditRepositoryImpl implements CreditRepository {
	
	private DBReader dbReader;
	
	public CreditRepositoryImpl(DBReader dbReader) {
		this.dbReader = dbReader;
	}

	@Override
	public List<Credit> getByUserIdPeriod(long userId, LocalDate dateFrom, LocalDate dateTo) throws IOException {
		JsonArray creditsJson = dbReader.getJsonArrayByName("credits");
		List<Credit> credits = new ArrayList<Credit>();
		
		for (JsonElement element : creditsJson) {
			Credit credit = GsonConfigurator.getGson().fromJson(element, Credit.class);
			if(credit.getUserId() == userId && dateFrom.isBefore(credit.getDate()) && dateTo.isAfter(credit.getDate())) {
				credits.add(credit);
			}
		}
		return credits;
	}


}
