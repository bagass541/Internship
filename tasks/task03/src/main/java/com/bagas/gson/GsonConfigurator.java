package com.bagas.gson;

import java.time.LocalDate;

import com.bagas.adapters.DiscountAdapter;
import com.bagas.adapters.LocalDateAdapter;
import com.bagas.entities.Discount;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonConfigurator {

    private static Gson gson;

    public GsonConfigurator() {

    }

    public static Gson getGson() {
        if (gson != null) {
            return gson;
        } else {
            return gson = new GsonBuilder()
                    .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                    .registerTypeAdapter(Discount.class, new DiscountAdapter())
                    .setPrettyPrinting()
                    .create();
        }
    }
}
