package com.bagas.gson;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.stream.Stream;

import com.bagas.entities.Settings;
import com.bagas.entities.ShowFor;
import com.google.gson.JsonObject;

public class SettingsReader {

//	private final String PATH_TO_SETTINGS = "src\\main\\data\\settings.json";

	private final String PATH_TO_SETTINGS = "settings.json";

	public Settings getSettings() throws IOException {
		JsonObject jsonObject;
//        try (BufferedReader reader = Files.newBufferedReader(Paths.get(PATH_TO_SETTINGS))) {
//			jsonObject = GsonConfigurator.getGson().fromJson(reader, JsonObject.class);
//        }

		InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("settings.json");

		try (BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {

			jsonObject = GsonConfigurator.getGson().fromJson(reader, JsonObject.class);
		}

		JsonObject settingsJson = jsonObject.getAsJsonObject("settings");
		JsonObject showForJson = settingsJson.getAsJsonObject("showFor");

		Settings settings = GsonConfigurator.getGson().fromJson(settingsJson, Settings.class);
		ShowFor showFor = GsonConfigurator.getGson().fromJson(showForJson, ShowFor.class);

		settings.setShowFor(showFor);
		//System.out.println("settings: " + settings);
		return settings;
	}
}
