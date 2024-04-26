package com.bank.bankcredits;

import java.io.IOException;

import com.bank.bankcredits.entities.Settings;
import com.bank.bankcredits.exceptions.IncorrectUserName;
import com.bank.bankcredits.exceptions.IncorrectUserSecondName;
import com.bank.bankcredits.exceptions.UserNotFoundException;
import com.bank.bankcredits.gson.DBReader;
import com.bank.bankcredits.gson.GsonScanner;
import com.bank.bankcredits.gson.SettingsReader;
import com.bank.bankcredits.services.AppService;

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
		} catch (IOException | IncorrectUserName | IncorrectUserSecondName | UserNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
