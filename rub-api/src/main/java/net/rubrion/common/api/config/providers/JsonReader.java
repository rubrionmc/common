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
import com.google.gson.reflect.TypeToken;
import net.rubrion.common.api.config.ConfigReader;

import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.Map;

/**
 * Configuration file reader implementation for JSON files.
 * Uses Gson library for parsing JSON format.
 *
 * @see ConfigReader
 */
public class JsonReader implements ConfigReader {

    private static final Gson GSON = new Gson();
    private static final Type MAP_TYPE = new TypeToken<Map<String, Object>>(){}.getType();

    /**
     * Loads and parses a JSON configuration file.
     *
     * @param file the JSON file to load (must not be null)
     * @return a map containing all configuration key-value pairs from the file
     * @throws RuntimeException if the file cannot be read or contains invalid JSON
     */
    @Override
    public Map<String, Object> load(File file) {
        try (FileReader reader = new FileReader(file)) {
            return GSON.fromJson(reader, MAP_TYPE);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load JSON file: " + file.getName(), e);
        }
    }
}


