package com.bagas.gson;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

import com.bagas.entities.Database;
import com.bagas.exceptions.IncorrectField;
import com.bagas.utils.JsonFileFilter;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class DBReader {

//    private final String PATH_TO_DB = "src\\main\\data\\db.json";
//
//    private final String PATH_TO_DATA = "src\\main\\data" + File.separator;

    private final String PATH_TO_DB = "data" + File.separator + "db.json";

    private final String PATH_TO_DATA = "data" + File.separator;

    private JsonObject getJsonRootDB() throws IOException {
//        try (BufferedReader reader = Files.newBufferedReader(Paths.get(PATH_TO_DB))) {
//
//            return GsonConfigurator.getGson().fromJson(reader, JsonObject.class);
//        }
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(Objects.requireNonNull(getClass().getResourceAsStream(PATH_TO_DB))))) {

            return GsonConfigurator.getGson().fromJson(reader, JsonObject.class);
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
        }

        File directory = new File(PATH_TO_DATA);
        String[] filesStr = directory.list(new JsonFileFilter(departments));

        if (filesStr == null) {
            System.out.println("Файлы не найдены");
        } else {
            for (String s : filesStr) {
                moveRecords(s, rootDB);
            }
        }
    }

    private void moveRecords(String fileName, JsonObject targetRoot) throws IOException, IncorrectField {
        JsonObject targetDB = targetRoot.getAsJsonObject("data");
        JsonObject srcObject;
//        try (BufferedReader reader = Files.newBufferedReader(Paths.get(PATH_TO_DATA + File.separator + fileName))) {
//            srcObject = GsonConfigurator.getGson().fromJson(reader, JsonObject.class);
//
//        }
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                        Objects.requireNonNull(
                                getClass().getResourceAsStream(PATH_TO_DATA + File.separator + fileName))))) {
            srcObject = GsonConfigurator.getGson().fromJson(reader, JsonObject.class);

        }
       // JsonObject srcDBJson = srcObject.getAsJsonObject("src/main/data");
        JsonObject srcDBJson = srcObject.getAsJsonObject("data");

        srcDBJson.entrySet().forEach(el -> moveElements(el.getValue().getAsJsonArray(),
                targetDB.get(el.getKey()).getAsJsonArray()));

        try (BufferedWriter writerTarget = Files.newBufferedWriter(Paths.get(PATH_TO_DB))) {
            GsonConfigurator.getGson().toJson(targetRoot, writerTarget);
        }


        try (BufferedWriter writerSource = Files.newBufferedWriter(Paths.get(PATH_TO_DATA + File.separator + fileName))) {
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
