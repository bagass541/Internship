package com.bagas.repositories;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import com.bagas.entities.Discount;
import com.bagas.entities.enums.DiscountType;
import com.bagas.gson.DBReader;
import com.bagas.gson.GsonConfigurator;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

public class DiscountRepositoryImpl implements DiscountRepository {

	private final String ELEMENT_NAME = "discounts";

	private DBReader dbReader;
	
	public DiscountRepositoryImpl(DBReader dbReader) {
		this.dbReader = dbReader;
	}

	@Override
	public Optional<Discount> getByDate(LocalDate date) throws IOException {
		JsonArray discountsJson = dbReader.getJsonArrayByName(ELEMENT_NAME);
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
