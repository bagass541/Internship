package com.bagas.gson;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import com.bagas.entities.Database;
import com.bagas.exceptions.IncorrectField;
import com.bagas.utils.JsonFileFilter;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class DBReader {

    private final String PATH_TO_DB = "db.json";

    private final String PATH_TO_DATA = "target" + File.separator + "data" + File.separator;

    private JsonObject getJsonRootDB() throws IOException {
//        InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream(PATH_TO_DB);
//        try (BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
//            JsonObject b = GsonConfigurator.getGson().fromJson(reader, JsonObject.class);
//            System.out.println("getJsonRootDB: " + b);
//            return b;
//        }
        File folder = new File(PATH_TO_DATA);
        folder.mkdir();

        try (BufferedReader reader = Files.newBufferedReader(
                Paths.get(PATH_TO_DATA + "db.json"))) {
            JsonObject b = GsonConfigurator.getGson().fromJson(reader, JsonObject.class);
      //      System.out.println("getJsonRootDB: " + b);
            return b;
        } catch (NoSuchFileException ex) {
            return null;
        }
    }

    public JsonArray getJsonArrayByName(String name) throws IOException {
        JsonObject data = getJsonRootDB().getAsJsonObject("data");
        return data.getAsJsonArray(name);
    }

    void initDB(List<String> departments) throws IOException, IncorrectField {
        JsonObject rootDB = getJsonRootDB();

        if (rootDB == null) {
            rootDB = new JsonObject();
            rootDB.add("data", GsonConfigurator.getGson().toJsonTree(new Database()));

            for (String department : departments) {
                moveRecords("db_" + department + ".json", rootDB);
            }
        }
    }

    private void moveRecords(String fileName, JsonObject targetRoot) throws IOException, IncorrectField {
        JsonObject targetDB = targetRoot.getAsJsonObject("data");
        JsonObject srcObject;

        InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
            srcObject = GsonConfigurator.getGson().fromJson(reader, JsonObject.class);

        }

        JsonObject srcDBJson = srcObject.getAsJsonObject("data");

        srcDBJson.entrySet().forEach(el -> moveElements(el.getValue().getAsJsonArray(),
                targetDB.get(el.getKey()).getAsJsonArray()));

        try (BufferedWriter writerTarget = Files.newBufferedWriter(Paths.get(PATH_TO_DATA + PATH_TO_DB))) {
            GsonConfigurator.getGson().toJson(targetRoot, writerTarget);
        }

        try (BufferedWriter writerSource = Files.newBufferedWriter(Paths.get(PATH_TO_DATA + fileName))) {
            GsonConfigurator.getGson().toJson(srcObject, writerSource);
        }
    }

    private void moveElements(JsonArray src, JsonArray target) {
        for (int i = 0; i < src.size(); i++) {
            target.add(src.get(i));
            src.remove(i);
            i--;
        }
    }
}
