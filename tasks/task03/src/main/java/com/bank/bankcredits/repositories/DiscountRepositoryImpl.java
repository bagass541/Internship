package com.bank.bankcredits.repositories;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import com.bank.bankcredits.entities.Discount;
import com.bank.bankcredits.entities.enums.DiscountType;
import com.bank.bankcredits.gson.DBReader;
import com.bank.bankcredits.gson.GsonConfigurator;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

public class DiscountRepositoryImpl implements DiscountRepository {

	private DBReader dbReader;
	
	public DiscountRepositoryImpl(DBReader dbReader) {
		this.dbReader = dbReader;
	}

	@Override
	public Optional<Discount> getByDate(LocalDate date) throws IOException {
		JsonArray discountsJson = dbReader.getJsonArrayByName("discounts");
		List<Discount> discounts = new ArrayList<Discount>();
		
		for (JsonElement element : discountsJson) {
			Discount discount = GsonConfigurator.getGson().fromJson(element, Discount.class);

			if(discount.getType() == DiscountType.ONE && discount.getDate().equals(date)) {
				discounts.add(discount);
			} else if(discount.getType() == DiscountType.MANY 
					&& discount.getDateFrom().isBefore(date) 
					&& discount.getDateTo().isAfter(date)) {
				discounts.add(discount);
			}
		}
		return discounts.stream().max(Comparator.comparing(Discount::getDiscount));
	}
}
