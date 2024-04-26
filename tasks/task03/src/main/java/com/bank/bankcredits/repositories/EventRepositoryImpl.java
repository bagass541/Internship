package com.bank.bankcredits.repositories;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.bank.bankcredits.entities.Event;
import com.bank.bankcredits.gson.DBReader;
import com.bank.bankcredits.gson.GsonConfigurator;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

public class EventRepositoryImpl implements EventRepository {

	private DBReader dbReader;
	
	public EventRepositoryImpl(DBReader dbReader) {
		this.dbReader = dbReader;
	}

	@Override
	public List<Event> getByPeriod(LocalDate dateFrom, LocalDate dateTo) throws IOException {
		JsonArray eventsJson = dbReader.getJsonArrayByName("events");
		List<Event> events = new ArrayList<Event>();
		
		for(JsonElement element : eventsJson) {
			Event event = GsonConfigurator.getGson().fromJson(element, Event.class);
			if(dateFrom.isBefore(event.getDate()) && dateTo.isAfter(event.getDate())) {
				events.add(event);
			}
		}
		
		return events;
	}

}
