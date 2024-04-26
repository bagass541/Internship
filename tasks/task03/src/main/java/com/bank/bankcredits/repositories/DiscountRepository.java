package com.bank.bankcredits.repositories;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;

import com.bank.bankcredits.entities.Discount;

public interface DiscountRepository {
	
	Optional<Discount> getByDate(LocalDate date) throws IOException;
}
