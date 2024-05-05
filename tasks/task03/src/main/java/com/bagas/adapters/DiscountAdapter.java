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

	private final String ID_PROPERTY = "id";

	private final String TYPE_PROPERTY = "type";

	private final String DATE_PROPERTY = "date";

	private final String DATE_FROM_PROPERTY = "dateFrom";

	private final String DATE_TO_PROPERTY = "dateTo";

	private final String DISCOUNT_PROPERTY = "discount";

	@Override
	public JsonElement serialize(Discount src, Type typeOfSrc, JsonSerializationContext context) {
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty(ID_PROPERTY, src.getId());
		jsonObject.addProperty(TYPE_PROPERTY, src.getType().toString());
		
		if(src.getType() == DiscountType.ONE) {
			jsonObject.add(DATE_PROPERTY, context.serialize(src.getDate(), LocalDate.class));
		} else {
			jsonObject.add(DATE_FROM_PROPERTY, context.serialize(src.getDateFrom(), LocalDate.class));
			jsonObject.add(DATE_TO_PROPERTY, context.serialize(src.getDateTo(), LocalDate.class));
		}
		
		jsonObject.addProperty(DISCOUNT_PROPERTY, src.getDiscount());
		
		return jsonObject;
	}
}
