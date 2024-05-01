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

	private final String PATH_TO_SETTINGS = File.separator + "data" + File.separator +"settings.json";

	public Settings getSettings() throws IOException {
		JsonObject jsonObject;
//        try (BufferedReader reader = Files.newBufferedReader(Paths.get(PATH_TO_SETTINGS))) {
//			jsonObject = GsonConfigurator.getGson().fromJson(reader, JsonObject.class);
//        }
		InputStream in = getClass().getResourceAsStream(File.separator + "data" + File.separator +"settings.json");
		InputStream in2 = getClass().getResourceAsStream("data" + File.separator + "settings.json");
		InputStream in3 = getClass().getResourceAsStream("settings.json");
		InputStream in5 = getClass().getResourceAsStream("..\\..\\..\\data\\settings.json");
		InputStream in6 = getClass().getResourceAsStream("..\\..\\data\\settings.json");
		InputStream in4 = getClass().getResourceAsStream("C:\\Users\\user\\Internship\\tasks\\task03\\" +
				"target\\task03-1.0-SNAPSHOT-jar-with-dependencies.zip");
		System.out.println(in);
		System.out.println(in2);
		System.out.println(in3);
		System.out.println(in4);
		System.out.println(in5);
		System.out.println(in6);
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {

			jsonObject = GsonConfigurator.getGson().fromJson(reader, JsonObject.class);
		}

		JsonObject settingsJson = jsonObject.getAsJsonObject("settings");
		JsonObject showForJson = settingsJson.getAsJsonObject("showFor");

		Settings settings = GsonConfigurator.getGson().fromJson(settingsJson, Settings.class);
		ShowFor showFor = GsonConfigurator.getGson().fromJson(showForJson, ShowFor.class);

		settings.setShowFor(showFor);

		return settings;
	}
}
