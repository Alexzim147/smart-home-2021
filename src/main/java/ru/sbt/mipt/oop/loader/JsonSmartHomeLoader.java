package ru.sbt.mipt.oop.loader;

import com.google.gson.Gson;
import ru.sbt.mipt.oop.objects.SmartHome;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonSmartHomeLoader implements SmartHomeLoader {
    private final String filename;

    public JsonSmartHomeLoader(String filename) {
        this.filename = filename;
    }

    @Override
    public SmartHome loadSmartHome() {
        Gson gson = new Gson();
        String json = null;
        try {
            json = new String(Files.readAllBytes(Paths.get(filename)));
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return gson.fromJson(json, SmartHome.class);
    }
}
