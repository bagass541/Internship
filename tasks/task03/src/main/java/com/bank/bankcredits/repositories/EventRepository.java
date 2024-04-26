package com.bank.bankcredits.repositories;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import com.bank.bankcredits.entities.Event;

public interface EventRepository {

	List<Event> getByPeriod(LocalDate dateFrom, LocalDate dateTo) throws IOException;
}
