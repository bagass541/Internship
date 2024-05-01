package com.bagas.gson;

import com.bagas.entities.Settings;
import com.bagas.exceptions.IncorrectField;

import java.io.IOException;

public class GsonScanner {
	
	private DBReader dbReader;
	
	private SettingsReader settingsReader;
	
	public GsonScanner(DBReader dbReader, SettingsReader settingsReader) {
		this.dbReader = dbReader;
		this.settingsReader = settingsReader;
	}
	
	public Settings startScan() throws IOException, IncorrectField {
		Settings settings = settingsReader.getSettings();
		dbReader.initDB(settings.getUseDepartments());
		
		return settings;
	}
	
	
	
}
