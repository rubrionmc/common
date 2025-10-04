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

import net.rubrion.common.api.config.ConfigReader;
import org.jetbrains.annotations.NotNull;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * ConfigReader implementation for YAML files using SnakeYAML.
 */
public class YamlReader implements ConfigReader {

    private static final Yaml YAML = new Yaml();

    @Override
    public @NotNull Map<String, Object> load(@NotNull File file) {
        try (Reader reader = new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8)) {
            Object data = YAML.load(reader);
            if (data instanceof Map) {
                @SuppressWarnings("unchecked")
                Map<String, Object> map = (Map<String, Object>) data;
                return map;
            }
            return new HashMap<>();
        } catch (IOException e) {
            throw new RuntimeException("Failed to load YAML file: " + file.getPath(), e);
        }
    }

    @Override
    public void save(@NotNull File file, @NotNull Map<String, Object> data) {
        try (Writer writer = new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8)) {
            YAML.dump(data, writer);
        } catch (IOException e) {
            throw new RuntimeException("Failed to save YAML file: " + file.getPath(), e);
        }
    }
}