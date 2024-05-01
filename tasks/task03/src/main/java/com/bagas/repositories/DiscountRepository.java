package com.bagas.repositories;

import com.bagas.entities.Discount;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;


public interface DiscountRepository {
	
	Optional<Discount> getByDate(LocalDate date) throws IOException;
}
