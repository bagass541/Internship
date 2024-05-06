package com.bagas.repositories;

import com.bagas.entities.Event;
import com.bagas.gson.DBReader;
import com.bagas.gson.GsonConfigurator;
import com.google.gson.JsonArray;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class EventRepositoryImpl implements EventRepository {

    private final String ELEMENT_NAME = "events";

    private DBReader dbReader;

    public EventRepositoryImpl(DBReader dbReader) {
        this.dbReader = dbReader;
    }

    @Override
    public List<Event> getByDateTo(LocalDate dateTo) throws IOException {
        JsonArray eventsJson = dbReader.getJsonArrayByName(ELEMENT_NAME);

        return eventsJson.asList().stream()
                .map(event -> GsonConfigurator.getGson().fromJson(event, Event.class))
                .filter(event -> dateTo.isAfter(event.getDate()))
                .sorted(Comparator.comparing(Event::getDate))
                .collect(Collectors.toList());
    }
}
