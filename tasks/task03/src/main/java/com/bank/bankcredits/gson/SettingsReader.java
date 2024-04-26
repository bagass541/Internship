package com.bank.bankcredits.gson;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

import com.bank.bankcredits.entities.Settings;
import com.bank.bankcredits.entities.ShowFor;
import com.google.gson.JsonObject;

public class SettingsReader {

	private final String PATH_TO_SETTINGS = "target\\data\\settings.json";

	public Settings getSettings() throws IOException {
		String settingsStr;
		try(Stream<String> stream = Files.lines(Path.of(PATH_TO_SETTINGS))) {
			settingsStr = stream.reduce((s1, s2) -> s1 + s2).get();
		} 
	
		JsonObject jsonObject = GsonConfigurator.getGson().fromJson(settingsStr, JsonObject.class);
		JsonObject settingsJson = jsonObject.getAsJsonObject("settings");
		JsonObject showForJson = settingsJson.getAsJsonObject("showFor");

		Settings settings = GsonConfigurator.getGson().fromJson(settingsJson, Settings.class);
		ShowFor showFor = GsonConfigurator.getGson().fromJson(showForJson, ShowFor.class);

		settings.setShowFor(showFor);

		return settings;
	}
}
