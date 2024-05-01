package com.bagas.repositories;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.bagas.entities.Event;
import com.bagas.gson.DBReader;
import com.bagas.gson.GsonConfigurator;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

public class EventRepositoryImpl implements EventRepository {

	private final String ELEMENT_NAME = "events";

	private DBReader dbReader;
	
	public EventRepositoryImpl(DBReader dbReader) {
		this.dbReader = dbReader;
	}

	@Override
	public List<Event> getByDateTo(LocalDate dateTo) throws IOException {
		JsonArray eventsJson = dbReader.getJsonArrayByName(ELEMENT_NAME);
		List<Event> events = new ArrayList<Event>();
		
		for(JsonElement element : eventsJson) {
			Event event = GsonConfigurator.getGson().fromJson(element, Event.class);
			if(dateTo.isAfter(event.getDate())) {
				events.add(event);
			}
		}
		events.sort(null);

		return events;
	}
}
