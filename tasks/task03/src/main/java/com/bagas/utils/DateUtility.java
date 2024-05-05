package com.bagas.utils;

import com.bagas.entities.enums.PeriodType;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class DateUtility {

    public static int countPeriods(LocalDate dateFrom, LocalDate dateTo, PeriodType type) {
        int times = 0;

        switch (type) {
            case DAY -> times = (int) ChronoUnit.DAYS.between(dateFrom, dateTo);
            case WEEK -> times = (int) ChronoUnit.WEEKS.between(dateFrom, dateTo);
            case MONTH -> times = (int) ChronoUnit.MONTHS.between(dateFrom, dateTo);
            case YEAR -> times = (int) ChronoUnit.YEARS.between(dateFrom, dateTo);
        }

        return times;
    }

    public static boolean isInRange(LocalDate dateFrom, LocalDate dateTo, LocalDate date) {
        return dateFrom.isBefore(date) && dateTo.isAfter(date);
    }
}
