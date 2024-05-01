package com.bagas.repositories;

import com.bagas.entities.Event;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public interface EventRepository {

	List<Event> getByDateTo(LocalDate dateTo) throws IOException;
}
