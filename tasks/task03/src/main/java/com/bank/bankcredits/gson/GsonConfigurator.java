package com.bank.bankcredits.gson;

import java.time.LocalDate;

import com.bank.bankcredits.adapters.DiscountAdapter;
import com.bank.bankcredits.adapters.LocalDateAdapter;
import com.bank.bankcredits.entities.Discount;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonConfigurator {

	private static Gson gson;
	
	public GsonConfigurator() {
		
	}

	public static Gson getGson() {
		if(gson != null) {
			return gson;
		} else {
			return gson = new GsonBuilder()
					.registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
					.registerTypeAdapter(Discount.class, new DiscountAdapter())
					.setPrettyPrinting()
					.create();
		}
	}
}
