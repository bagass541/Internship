package com.bagas.adapters;

import java.lang.reflect.Type;
import java.time.LocalDate;


import com.bagas.entities.Discount;
import com.bagas.entities.enums.DiscountType;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class DiscountAdapter implements JsonSerializer<Discount> {

	@Override
	public JsonElement serialize(Discount src, Type typeOfSrc, JsonSerializationContext context) {
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("id", src.getId());
		jsonObject.addProperty("type", src.getType().toString());
		
		if(src.getType() == DiscountType.ONE) {
			jsonObject.add("date", context.serialize(src.getDate(), LocalDate.class));
		} else {
			jsonObject.add("dateFrom", context.serialize(src.getDateFrom(), LocalDate.class));
			jsonObject.add("dateTo", context.serialize(src.getDateTo(), LocalDate.class));
		}
		
		jsonObject.addProperty("discount", src.getDiscount());
		
		return jsonObject;
	}

}
