package com.bagas.repositories;

import com.bagas.entities.Discount;
import com.bagas.entities.enums.DiscountType;
import com.bagas.gson.DBReader;
import com.bagas.gson.GsonConfigurator;
import com.google.gson.JsonArray;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.Optional;

import static com.bagas.utils.DateUtility.isInRange;

public class DiscountRepositoryImpl implements DiscountRepository {

    private final String ELEMENT_NAME = "discounts";

    private DBReader dbReader;

    public DiscountRepositoryImpl(DBReader dbReader) {
        this.dbReader = dbReader;
    }

    @Override
    public Optional<Discount> getByDate(LocalDate date) throws IOException {
        JsonArray discountsJson = dbReader.getJsonArrayByName(ELEMENT_NAME);

        return discountsJson.asList().stream()
                .map(discount -> GsonConfigurator.getGson().fromJson(discount, Discount.class))
                .filter(discount -> (discount.getType() == DiscountType.ONE && discount.getDate().equals(date))
                        || (discount.getType() == DiscountType.MANY
                        && isInRange(discount.getDateFrom(), discount.getDateTo(), date)))
                .max(Comparator.comparing(Discount::getDiscount));
    }
}
