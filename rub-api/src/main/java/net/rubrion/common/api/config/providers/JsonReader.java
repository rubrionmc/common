/**
 * RPL-LICENSE NOTICE
 * <br><br>
 * This Sourcecode is under the RPL-LICENSE. <br>
 * License at: <a href="https://github.com/rubrionmc/.github/blob/main/licensens/RUBRION_PUBLIC">GITHUB</a>
 * <br><br>
 * Copyright (c) LeyCM <leycm@proton.me> <br>
 * Copyright (c) maintainers <br>
 * Copyright (c) contributors
 */
package net.rubrion.common.api.config.providers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import net.rubrion.common.api.config.ConfigReader;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * ConfigReader implementation for JSON files using Gson.
 */
public class JsonReader implements ConfigReader {

    private static final Gson GSON = new GsonBuilder()
            .setPrettyPrinting()
            .create();

    private static final Type MAP_TYPE = new TypeToken<Map<String, Object>>(){}.getType();

    @Override
    public @NotNull Map<String, Object> load(@NotNull File file) {
        try (Reader reader = new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8)) {
            Map<String, Object> data = GSON.fromJson(reader, MAP_TYPE);
            return data != null ? data : new HashMap<>();
        } catch (IOException e) {
            throw new RuntimeException("Failed to load JSON file: " + file.getPath(), e);
        }
    }

    @Override
    public void save(@NotNull File file, @NotNull Map<String, Object> data) {
        try (Writer writer = new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8)) {
            GSON.toJson(data, writer);
        } catch (IOException e) {
            throw new RuntimeException("Failed to save JSON file: " + file.getPath(), e);
        }
    }
}