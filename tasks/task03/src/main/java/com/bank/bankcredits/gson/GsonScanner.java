package com.bank.bankcredits.gson;

import java.io.IOException;

import com.bank.bankcredits.entities.Settings;
import com.bank.bankcredits.exceptions.IncorrectUserName;
import com.bank.bankcredits.exceptions.IncorrectUserSecondName;

public class GsonScanner {
	
	private DBReader dbReader;
	
	private SettingsReader settingsReader;
	
	public GsonScanner(DBReader dbReader, SettingsReader settingsReader) {
		this.dbReader = dbReader;
		this.settingsReader = settingsReader;
	}
	
	public Settings startScan() throws IOException, IncorrectUserName, IncorrectUserSecondName {
		Settings settings = settingsReader.getSettings();
		dbReader.initDB(settings.getUseDepartments());
		
		return settings;
	}
	
	
	
}
