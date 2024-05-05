package com.bagas.services;

import com.bagas.entities.Credit;
import com.bagas.entities.Discount;
import com.bagas.repositories.CreditRepository;
import com.bagas.repositories.DiscountRepository;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class CreditService {

    private CreditRepository creditRepository;

    private DiscountRepository discountRepository;

    public CreditService(CreditRepository creditRepository, DiscountRepository discountRepository) {
        this.creditRepository = creditRepository;
        this.discountRepository = discountRepository;
    }

    public List<Credit> getByUserIdPeriod(long userId, LocalDate dateFrom, LocalDate dateTo) throws IOException {
        return creditRepository.getByUserIdPeriod(userId, dateFrom, dateTo);
    }

    public Discount getDiscountByDate(LocalDate date) throws IOException {
        return discountRepository.getByDate(date)
                .orElse(null);
    }
}
