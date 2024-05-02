package com.bagas.gson;

import java.io.*;
import java.nio.file.Files;
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

//    private final String PATH_TO_DB = "src\\main\\data\\db.json";
//
//    private final String PATH_TO_DATA = "src\\main\\data" + File.separator;

    private final String PATH_TO_DB = "db.json";

//    private final String PATH_TO_DATA = "data" + File.separator;

    private JsonObject getJsonRootDB() throws IOException {
//        try (BufferedReader reader = Files.newBufferedReader(Paths.get(PATH_TO_DB))) {
//
//            return GsonConfigurator.getGson().fromJson(reader, JsonObject.class);
//        }
        InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream(PATH_TO_DB);
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
            JsonObject b = GsonConfigurator.getGson().fromJson(reader, JsonObject.class);
            System.out.println("getJsonRootDB: " + b);
            return b;
        }
    }

    public JsonArray getJsonArrayByName(String name) throws IOException {
        System.out.println(getJsonRootDB());
       // JsonObject data = getJsonRootDB().getAsJsonObject("src/main/data");
        JsonObject data = getJsonRootDB().getAsJsonObject("data");
        JsonArray asJsonArray = data.getAsJsonArray(name);
        System.out.println(asJsonArray);
        return asJsonArray;
    }

    void initDB(List<String> departments) throws IOException, IncorrectField {
        JsonObject rootDB = getJsonRootDB();

        if (rootDB == null) {
            rootDB = new JsonObject();
            rootDB.add("data", GsonConfigurator.getGson().toJsonTree(new Database()));
        }
        System.out.println("initial db: " + rootDB);
        File directory = new File(File.separator);
        String[] filesStr = directory.list(new JsonFileFilter(departments));
      //  String[] filesStr = File.  new JsonFileFilter(departments));
//        System.out.println("fileStr: ");
//        Arrays.stream(filesStr).forEach(System.out::println);

//        if (filesStr == null) {
//            System.out.println("Файлы не найдены");
//        } else {
//            for (String s : filesStr) {
//                moveRecords(s, rootDB);
//            }
//        }
        moveRecords("db_gomel.json", rootDB);
    }

    private void moveRecords(String fileName, JsonObject targetRoot) throws IOException, IncorrectField {
        JsonObject targetDB = targetRoot.getAsJsonObject("data");
        JsonObject srcObject;

        InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
            srcObject = GsonConfigurator.getGson().fromJson(reader, JsonObject.class);

        }

        JsonObject srcDBJson = srcObject.getAsJsonObject("data");
        System.out.println("srcDBJSON: " + srcDBJson);
        srcDBJson.entrySet().forEach(el -> moveElements(el.getValue().getAsJsonArray(),
                targetDB.get(el.getKey()).getAsJsonArray()));

        try (BufferedWriter writerTarget = Files.newBufferedWriter(Paths.get(PATH_TO_DB))) {
            GsonConfigurator.getGson().toJson(targetRoot, writerTarget);
        }
        System.out.println("targetDB: " + targetDB);

        try (BufferedWriter writerSource = Files.newBufferedWriter(Paths.get(fileName))) {
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
