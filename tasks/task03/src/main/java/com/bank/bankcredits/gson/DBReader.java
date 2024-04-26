package com.bank.bankcredits.gson;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import com.bank.bankcredits.Utils.JsonFileFilter;
import com.bank.bankcredits.entities.Database;
import com.bank.bankcredits.exceptions.IncorrectUserName;
import com.bank.bankcredits.exceptions.IncorrectUserSecondName;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class DBReader {
	
	private final String PATH_TO_DATA = "target\\data";
	
	private JsonObject getJsonRootDB() throws IOException {
		BufferedReader reader = Files.newBufferedReader(Paths.get(PATH_TO_DATA + "\\db.json"));
		JsonObject jsonObject = GsonConfigurator.getGson().fromJson(reader, JsonObject.class);
		
		return jsonObject;
	}
	
	public JsonArray getJsonArrayByName(String name) throws IOException {
		JsonObject data = getJsonRootDB().getAsJsonObject("data");
		return data.getAsJsonArray(name);
	}
	
	void initDB(List<String> departments) throws IOException, IncorrectUserName, IncorrectUserSecondName {
		JsonObject rootDB = getJsonRootDB();
		
		if(rootDB == null) {
			rootDB = new JsonObject();
			rootDB.add("data", GsonConfigurator.getGson().toJsonTree(new Database()));
		}
		
		File directory = new File(PATH_TO_DATA);
		String[] filesStr = directory.list(new JsonFileFilter(departments));
		
		if(filesStr == null) {
			System.out.println("Файлы не найдены");
		} else {
			for (int i = 0; i < filesStr.length; i++) {
				moveRecords(filesStr[i], rootDB);
			}
		}
	}
	
	private void moveRecords(String fileName, JsonObject targetRoot) throws IOException, IncorrectUserName, IncorrectUserSecondName {		
		BufferedReader reader = Files.newBufferedReader(Paths.get(PATH_TO_DATA + "\\" + fileName));
		
		JsonObject targetDB = targetRoot.getAsJsonObject("data");

		JsonObject srcObject = GsonConfigurator.getGson().fromJson(reader, JsonObject.class);
		JsonObject srcDBJson = srcObject.getAsJsonObject("data");
		reader.close();
		
		srcDBJson.entrySet().forEach(el -> moveElements(el.getValue().getAsJsonArray(), targetDB.get(el.getKey()).getAsJsonArray()));
			
		BufferedWriter writerTarget = Files.newBufferedWriter(Paths.get(PATH_TO_DATA + "\\db.json"));
		GsonConfigurator.getGson().toJson(targetRoot, writerTarget);
		writerTarget.close();
		
		BufferedWriter writerSource = Files.newBufferedWriter(Paths.get(PATH_TO_DATA + "\\" +  fileName));
		GsonConfigurator.getGson().toJson(srcObject, writerSource); 
		writerSource.close();
		

	}
	
	private void moveElements(JsonArray src, JsonArray target) {
		for (int i = 0; i < src.size(); i++) {
			target.add(src.get(i));
			src.remove(i);
			i--;
		}
	}
}
