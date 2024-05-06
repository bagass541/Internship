package com.bagas;

import com.bagas.entities.Settings;
import com.bagas.exceptions.IncorrectField;
import com.bagas.exceptions.UserNotFoundException;
import com.bagas.gson.DBReader;
import com.bagas.gson.GsonScanner;
import com.bagas.gson.SettingsReader;
import com.bagas.services.AppService;

import java.io.IOException;


public class Executor {

    private GsonScanner gsonScanner;

    private AppService appService;

    public Executor() {
        this.gsonScanner = new GsonScanner(new DBReader(), new SettingsReader());
        this.appService = new AppService();
    }

    public void start() {
        try {
            Settings settings = gsonScanner.startScan();
            appService.printResults(settings);
        } catch (IOException | IncorrectField | UserNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
