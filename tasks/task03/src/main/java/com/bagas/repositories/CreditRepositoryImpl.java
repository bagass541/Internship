package com.bagas.repositories;

import com.bagas.entities.Credit;
import com.bagas.gson.DBReader;
import com.bagas.gson.GsonConfigurator;
import com.google.gson.JsonArray;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static com.bagas.utils.DateUtility.isInRange;

public class CreditRepositoryImpl implements CreditRepository {

    private final String ELEMENT_NAME = "credits";

    private DBReader dbReader;

    public CreditRepositoryImpl(DBReader dbReader) {
        this.dbReader = dbReader;
    }

    @Override
    public List<Credit> getByUserIdPeriod(Long userId, LocalDate dateFrom, LocalDate dateTo) throws IOException {
        JsonArray creditsJson = dbReader.getJsonArrayByName(ELEMENT_NAME);

        return creditsJson.asList().stream()
                .map(credit -> GsonConfigurator.getGson().fromJson(credit, Credit.class))
                .filter(credit -> credit.getUserId() == userId && isInRange(dateFrom, dateTo, credit.getDate()))
                .collect(Collectors.toList());
    }
}
