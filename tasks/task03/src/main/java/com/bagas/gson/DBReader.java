package com.bagas.gson;

import com.bagas.entities.Database;
import com.bagas.exceptions.IncorrectField;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

public class DBReader {

    private final String PATH_TO_DB = "db.json";

    private final String PATH_TO_DATA = "target" + File.separator + "data" + File.separator;

    private final String BEGINNING_DEPARTMENT_NAME = "db_";

    private final String JSON_EXTENSION = ".json";

    private final String DATA_PROPERTY = "data";

    private JsonObject getJsonRootDB() throws IOException {
        File folder = new File(PATH_TO_DATA);
        folder.mkdir();

        try (BufferedReader reader = Files.newBufferedReader(
                Paths.get(PATH_TO_DATA + PATH_TO_DB))) {

            return GsonConfigurator.getGson().fromJson(reader, JsonObject.class);
        } catch (NoSuchFileException ex) {
            System.out.println("Не удалось получить базу данных");
            return null;
        }
    }

    public JsonArray getJsonArrayByName(String name) throws IOException {
        JsonObject data = Objects.requireNonNull(getJsonRootDB()).getAsJsonObject(DATA_PROPERTY);
        return data.getAsJsonArray(name);
    }

    void initDB(List<String> departments) throws IOException, IncorrectField {
        JsonObject rootDB = getJsonRootDB();

        if (rootDB == null) {
            rootDB = new JsonObject();
            rootDB.add(DATA_PROPERTY, GsonConfigurator.getGson().toJsonTree(new Database()));

            for (String department : departments) {
                moveRecords(BEGINNING_DEPARTMENT_NAME + department + JSON_EXTENSION, rootDB);
            }
        }
    }

    private void moveRecords(String fileName, JsonObject targetRoot) throws IOException, IncorrectField {
        JsonObject targetDB = targetRoot.getAsJsonObject(DATA_PROPERTY);
        JsonObject srcObject;

        InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {

            srcObject = GsonConfigurator.getGson().fromJson(reader, JsonObject.class);
        }

        JsonObject srcDBJson = srcObject.getAsJsonObject(DATA_PROPERTY);

        srcDBJson.entrySet()
                .forEach(el -> moveElements(el.getValue().getAsJsonArray(),
                        targetDB.get(el.getKey()).getAsJsonArray()));

        try (BufferedWriter writerTarget = Files.newBufferedWriter(
                Paths.get(PATH_TO_DATA + PATH_TO_DB))) {

            GsonConfigurator.getGson().toJson(targetRoot, writerTarget);
        }

        try (BufferedWriter writerSource = Files.newBufferedWriter(
                Paths.get(PATH_TO_DATA + fileName))) {

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
